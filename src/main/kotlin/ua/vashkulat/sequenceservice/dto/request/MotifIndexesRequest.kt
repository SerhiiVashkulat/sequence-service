package ua.vashkulat.sequenceservice.dto.request

import jakarta.validation.constraints.Pattern

data class MotifIndexesRequest(
    @field:Pattern(regexp = "^[ATCG]+$", message = "Sequence must consist only of A, T, C, G")
    val sequence: String,
    @field:Pattern(regexp = "^[ATCG]+$", message = "Sequence must consist only of A, T, C, G")
    val motif: String
)