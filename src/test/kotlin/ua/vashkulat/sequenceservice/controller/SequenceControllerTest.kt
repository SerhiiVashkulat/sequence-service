package ua.vashkulat.sequenceservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ua.vashkulat.sequenceservice.dto.request.MotifIndexesRequest
import ua.vashkulat.sequenceservice.service.SequenceService

@WebMvcTest(SequenceController::class)
class SequenceControllerTest(
    @Autowired private val mvc: MockMvc,
    @Autowired private val objectMapper: ObjectMapper,
) {
    @MockitoBean
    private lateinit var sequenceService: SequenceService

    @Test
    fun `retrieve motif indexes should return error`(){
        val invalidSequence = MotifIndexesRequest(
            sequence = "AAA4",
            motif = "AA"
        )

        mvc.perform(post(API_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(invalidSequence))
        ).andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.description").value("Sequence must consist only of A, T, C, G"))

    }

    private companion object {
        private const val API_PATH = "/api/v1/sequences/motif-indexes"
    }
}