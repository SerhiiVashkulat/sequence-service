package ua.vashkulat.sequenceservice.model

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "motif_index")
data class MotifIndex(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val index: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motif_sequence_id")
    val motifSequence: MotifSequence
)
