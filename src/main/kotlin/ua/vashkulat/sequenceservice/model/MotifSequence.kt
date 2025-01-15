package ua.vashkulat.sequenceservice.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "motif_sequence")
data class MotifSequence(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sequence_id")
    val sequence: Sequence,

    val motif: String,

    @OneToMany(mappedBy = "motifSequence", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val motifIndexes: List<MotifIndex> = emptyList()
)
