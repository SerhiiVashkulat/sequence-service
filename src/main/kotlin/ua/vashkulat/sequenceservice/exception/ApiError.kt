package ua.vashkulat.sequenceservice.exception

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime


data class ApiError(
    val description: String,
    val path: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    val timestamp: LocalDateTime = LocalDateTime.now()
)
