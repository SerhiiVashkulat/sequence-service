package ua.vashkulat.sequenceservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import ua.vashkulat.sequenceservice.model.MotifSequence

interface MotifSequenceRepository: JpaRepository<MotifSequence, Long> {
}
