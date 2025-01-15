package ua.vashkulat.sequenceservice.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class RnaSequenceResponse(
    @field:JsonProperty("rna_sequence")
    val rnaSequence: String
)
