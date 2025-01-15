package ua.vashkulat.sequenceservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ua.vashkulat.sequenceservice.model.MotifIndex


interface MotifIndexRepository: JpaRepository<MotifIndex, Long> {
    @Query(
        """
        SELECT motifIndex.index
        FROM MotifSequence motifSequence
        JOIN motifSequence.motifIndexes motifIndex
        JOIN motifSequence.sequence sequence
        WHERE sequence.sequenceName = :sequenceName
        AND motifSequence.motif = :motif
    """
    )
    fun findIndexesByMotifAndSequence(
        @Param("sequenceName") sequenceName: String,
        @Param("motif") motif: String
    ): List<Int>
}
