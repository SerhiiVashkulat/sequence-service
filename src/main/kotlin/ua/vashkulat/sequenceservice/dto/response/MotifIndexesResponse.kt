package ua.vashkulat.sequenceservice.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class MotifIndexesResponse(
    @field:JsonProperty("motif_indices")
    val motifIndexes: List<Int>
)
