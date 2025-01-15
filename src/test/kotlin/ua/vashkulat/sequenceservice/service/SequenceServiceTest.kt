package ua.vashkulat.sequenceservice.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import ua.vashkulat.sequenceservice.dto.response.GcContentResponse
import ua.vashkulat.sequenceservice.dto.response.RnaSequenceResponse
import ua.vashkulat.sequenceservice.exception.MotifNotValidException
import ua.vashkulat.sequenceservice.model.Sequence
import ua.vashkulat.sequenceservice.repository.MotifIndexRepository
import ua.vashkulat.sequenceservice.repository.MotifSequenceRepository
import ua.vashkulat.sequenceservice.repository.SequenceRepository
import ua.vashkulat.sequenceservice.service.impl.SequenceServiceImpl
import kotlin.test.assertEquals


@ExtendWith(MockitoExtension::class)
class SequenceServiceTest(
    @Mock private val sequenceRepository: SequenceRepository,
    @Mock private val motifSequenceRepository: MotifSequenceRepository,
    @Mock private val motifIndexRepository: MotifIndexRepository
) {
    private val sequenceService: SequenceService =
        SequenceServiceImpl(sequenceRepository, motifSequenceRepository, motifIndexRepository)

    @Test
    fun `calculateGcContentBySequence should calculate and save new sequence`() {
        val sequenceName = "ATCGTGCT"
        val sequence = Sequence(
            id = 1,
            sequenceName = sequenceName,
            gcContent = 50.0,
            rnaSequence = "AUCGUGCU",
            reverseComplement = "ACGTCAT"
        )

        `when`(sequenceRepository.findBySequenceName(sequenceName)).thenReturn(null)

        `when`(sequenceRepository.save(any())).thenReturn(sequence)

        val actualResult = sequenceService.calculateGcContentBySequence(sequenceName)
        val expectedResult = GcContentResponse(gcContent = 50.0)
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `replaceRnaBySequence should return rnaSequence from entity`(){
        val sequenceName = "ATCGTGCT"
        val existEntity = Sequence(
            id = 1,
            sequenceName = sequenceName,
            gcContent = 50.0,
            rnaSequence = "AUCGUGCU",
            reverseComplement = "ACGTCAT"
        )

        `when`(sequenceRepository.findBySequenceName(sequenceName)).thenReturn(existEntity)

        val actualResult = sequenceService.replaceRnaBySequence(sequenceName)
        val expectedResult = RnaSequenceResponse(rnaSequence = "AUCGUGCU")
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `retrieveMotifIndexesBySequence should throw error`(){
        val sequence = "AA"
        val motif = "AAA"

        assertThrows<MotifNotValidException> {
            sequenceService.retrieveMotifIndexesBySequence(sequence, motif)
        }
    }
}