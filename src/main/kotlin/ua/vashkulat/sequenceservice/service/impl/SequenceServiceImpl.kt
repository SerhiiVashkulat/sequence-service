package ua.vashkulat.sequenceservice.service.impl

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ua.vashkulat.sequenceservice.dto.response.GcContentResponse
import ua.vashkulat.sequenceservice.dto.response.MotifIndexesResponse
import ua.vashkulat.sequenceservice.dto.response.ReverseComplementResponse
import ua.vashkulat.sequenceservice.dto.response.RnaSequenceResponse
import ua.vashkulat.sequenceservice.dto.response.ValidSequenceResponse
import ua.vashkulat.sequenceservice.exception.MotifNotValidException
import ua.vashkulat.sequenceservice.model.MotifIndex
import ua.vashkulat.sequenceservice.model.MotifSequence
import ua.vashkulat.sequenceservice.model.Sequence
import ua.vashkulat.sequenceservice.repository.MotifIndexRepository
import ua.vashkulat.sequenceservice.repository.MotifSequenceRepository
import ua.vashkulat.sequenceservice.repository.SequenceRepository
import ua.vashkulat.sequenceservice.service.SequenceService

@Service
class SequenceServiceImpl(
    private val sequenceRepository: SequenceRepository,
    private val motifSequenceRepository: MotifSequenceRepository,
    private val motifIndexRepository: MotifIndexRepository
): SequenceService {

    override fun validateSequence(sequence: String): ValidSequenceResponse {
        this.processSaveSequence(sequence)
        return ValidSequenceResponse(valid = true)
    }

    override fun calculateGcContentBySequence(sequence: String): GcContentResponse {
        return sequenceRepository.findBySequenceName(sequence)?.let {
            GcContentResponse(it.gcContent)
        } ?: GcContentResponse(this.processSaveSequence(sequence).gcContent)
    }

    override fun replaceRnaBySequence(sequence: String): RnaSequenceResponse {
        return sequenceRepository.findBySequenceName(sequence)?.let {
            RnaSequenceResponse(it.rnaSequence)
        } ?: RnaSequenceResponse(this.processSaveSequence(sequence).rnaSequence)
    }

    @Transactional
    override fun retrieveMotifIndexesBySequence(sequence: String, motif: String): MotifIndexesResponse {
        return takeIf { motif.length < sequence.length }
            ?.let {
                sequenceRepository.findBySequenceName(sequence)?.let { sequenceEntity ->
                    MotifIndexesResponse(
                        motifIndexes = motifIndexRepository.findIndexesByMotifAndSequence(sequence, motif)
                            .takeIf { it.isNotEmpty() }
                            ?: processSavedMotifWithIndexes(sequenceEntity, motif)
                    )
                } ?: MotifIndexesResponse(
                    motifIndexes = processSavedMotifWithIndexes(
                        this.processSaveSequence(sequence),
                        motif
                    )
                )
            } ?: throw MotifNotValidException("Motif length must be smaller than sequence name length")
    }

    override fun reverseComplementBySequence(sequence: String): ReverseComplementResponse {
        return ReverseComplementResponse(
            reverseComplement = sequenceRepository.findBySequenceName(sequence)?.reverseComplement
                ?: this.processSaveSequence(sequence).reverseComplement
        )
    }

    private fun processSavedMotifWithIndexes(sequence: Sequence, motif: String): List<Int> {
        val motifSequence = motifSequenceRepository.save(MotifSequence(sequence = sequence, motif = motif))
        val motifIndexes = findMotifIndexes(sequence.sequenceName, motifSequence.motif)
        val indexes = motifIndexes.map { MotifIndex(index = it, motifSequence = motifSequence) }
        motifIndexRepository.saveAll(indexes)

        return motifIndexes
    }

    private fun processSaveSequence(sequence: String): Sequence {
        return sequenceRepository.save(
            Sequence(
                sequenceName = sequence,
                gcContent = calculateGcContent(sequence),
                rnaSequence = replaceTtoU(sequence),
                reverseComplement = generateReverseComplement(sequence)
            )
        )
    }

    private fun calculateGcContent(sequence: String): Double {
        return sequence.count { it == 'G' || it == 'C' }
            .toDouble()
            .div(sequence.length)
            .times(100)
            .let {
                String.format("%.1f", it).toDouble()
            }
    }

    private fun replaceTtoU(sequence: String): String {
        return sequence.replace('T', 'U')
    }

    private fun findMotifIndexes(sequence: String, motif: String): List<Int> {
        return Regex(motif)
            .findAll(sequence)
            .map { it.range.first }
            .toList()
    }

    private fun generateReverseComplement(sequence: String): String {
        val complementMap = mapOf(
            'A' to 'T',
            'T' to 'A',
            'C' to 'G',
            'G' to 'C'
        )
        return sequence.map { complementMap[it] }.reversed().joinToString("")
    }
}
