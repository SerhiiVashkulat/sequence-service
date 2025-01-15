package ua.vashkulat.sequenceservice.dto.request

import jakarta.validation.constraints.Pattern

data class SequenceRequest(
    @field:Pattern(regexp = "^[ATCG]+$", message = "Sequence must consist only of A, T, C, G")
    val sequence: String
)
