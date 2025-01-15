package ua.vashkulat.sequenceservice.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "sequence")
data class Sequence(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?= null,
    @Column(name = "sequence_name")
    val sequenceName: String,
    @Column(name = "gc_content")
    val gcContent: Double,
    @Column(name = "rna_sequence")
    val rnaSequence: String,
    @Column(name = "reverse_complement")
    val reverseComplement: String,
    @OneToMany(mappedBy = "sequence", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val motifSequences: List<MotifSequence> = emptyList()
)
