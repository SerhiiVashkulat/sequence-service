package ua.vashkulat.sequenceservice.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ua.vashkulat.sequenceservice.dto.response.ValidSequenceResponse
import ua.vashkulat.sequenceservice.dto.response.GcContentResponse
import ua.vashkulat.sequenceservice.dto.request.MotifIndexesRequest
import ua.vashkulat.sequenceservice.dto.response.MotifIndexesResponse
import ua.vashkulat.sequenceservice.dto.response.ReverseComplementResponse
import ua.vashkulat.sequenceservice.dto.response.RnaSequenceResponse
import ua.vashkulat.sequenceservice.dto.request.SequenceRequest
import ua.vashkulat.sequenceservice.service.SequenceService

@RestController
@RequestMapping("/api/v1/sequences")
@Validated
class SequenceController(
    private val sequenceService: SequenceService
) {

    @PostMapping("/valid")
    fun validateSequence(
        @Valid @RequestBody sequence: SequenceRequest
    ): ResponseEntity<ValidSequenceResponse> {
        return ResponseEntity.ok(sequenceService.validateSequence(sequence.sequence))
    }

    @PostMapping("/gc-content")
    fun calculateGcContent(
        @Valid @RequestBody sequence: SequenceRequest
    ): ResponseEntity<GcContentResponse> {
        return ResponseEntity.ok(sequenceService.calculateGcContentBySequence(sequence.sequence))
    }

    @PostMapping("/rna-sequence")
    fun replaceRnaSequence(
        @Valid @RequestBody sequence: SequenceRequest
    ): ResponseEntity<RnaSequenceResponse> {
        return ResponseEntity.ok(sequenceService.replaceRnaBySequence(sequence.sequence))
    }

    @PostMapping("/motif-indexes")
    fun retrieveMotifIndexes(
        @Valid @RequestBody payload: MotifIndexesRequest
    ): ResponseEntity<MotifIndexesResponse> {
        return ResponseEntity.ok(sequenceService.retrieveMotifIndexesBySequence(payload.sequence, payload.motif))
    }

    @PostMapping("/reverse-complement")
    fun reverseSequenceWithComplement(
        @Valid @RequestBody sequence: SequenceRequest
    ): ResponseEntity<ReverseComplementResponse> {
        return ResponseEntity.ok(sequenceService.reverseComplementBySequence(sequence.sequence))
    }
}