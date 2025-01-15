package ua.vashkulat.sequenceservice.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class GcContentResponse(
    @field:JsonProperty("gc_content")
    val gcContent: Double
)
