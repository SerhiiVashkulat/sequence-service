package ua.vashkulat.sequenceservice.service

import ua.vashkulat.sequenceservice.dto.response.GcContentResponse
import ua.vashkulat.sequenceservice.dto.response.MotifIndexesResponse
import ua.vashkulat.sequenceservice.dto.response.ReverseComplementResponse
import ua.vashkulat.sequenceservice.dto.response.RnaSequenceResponse
import ua.vashkulat.sequenceservice.dto.response.ValidSequenceResponse

interface SequenceService {
    fun validateSequence(sequence: String): ValidSequenceResponse
    fun calculateGcContentBySequence(sequence: String): GcContentResponse
    fun replaceRnaBySequence(sequence: String): RnaSequenceResponse
    fun retrieveMotifIndexesBySequence(sequence: String, motif: String): MotifIndexesResponse
    fun reverseComplementBySequence(sequence: String): ReverseComplementResponse
}
