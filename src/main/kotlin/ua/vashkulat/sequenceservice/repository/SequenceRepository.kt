package ua.vashkulat.sequenceservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ua.vashkulat.sequenceservice.model.Sequence

@Repository
interface SequenceRepository: JpaRepository<Sequence, Long> {
    fun findBySequenceName(sequence: String): Sequence?
}
