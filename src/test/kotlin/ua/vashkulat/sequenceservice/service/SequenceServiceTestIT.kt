package ua.vashkulat.sequenceservice.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ua.vashkulat.sequenceservice.dto.response.GcContentResponse
import ua.vashkulat.sequenceservice.dto.response.MotifIndexesResponse
import ua.vashkulat.sequenceservice.dto.response.ReverseComplementResponse
import ua.vashkulat.sequenceservice.dto.response.RnaSequenceResponse
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
class SequenceServiceTestIT {

    @Autowired private lateinit var sequenceService: SequenceService

    @Test
    fun calculateGcContent() {
        val sequenceName = "ATCGTGCT"

        val actualResult = sequenceService.calculateGcContentBySequence(sequenceName)
        val expectedResult = GcContentResponse(gcContent = 50.0)
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun replaceTtoU() {
        val sequenceName = "ATCGTGCT"

        val actualResult = sequenceService.replaceRnaBySequence(sequenceName)
        val expectedResult = RnaSequenceResponse(rnaSequence = "AUCGUGCU")
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun generateReverseComplement() {
        val sequenceName = "ATCG"

        val actualResult = sequenceService.reverseComplementBySequence(sequenceName)
        val expectedResult = ReverseComplementResponse(reverseComplement = "CGAT")
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun findMotifIndex() {
        val sequenceName = "ATCGATCGAT"
        val motif = "AT"

        val actualResult = sequenceService.retrieveMotifIndexesBySequence(sequenceName, motif)
        val expectedResult = MotifIndexesResponse(motifIndexes = listOf(0, 4, 8))
        assertEquals(expectedResult, actualResult)
    }

}