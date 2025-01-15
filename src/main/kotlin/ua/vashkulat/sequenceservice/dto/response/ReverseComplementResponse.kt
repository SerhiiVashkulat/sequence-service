package ua.vashkulat.sequenceservice.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ReverseComplementResponse(
    @field:JsonProperty("reverse_complement")
    val reverseComplement: String
)
