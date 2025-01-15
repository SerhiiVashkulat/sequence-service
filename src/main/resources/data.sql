CREATE TABLE IF NOT EXISTS  sequence
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    sequence_name    VARCHAR(255) NOT NULL UNIQUE ,
    gc_content  DOUBLE NOT NULL,
    reverse_complement VARCHAR(255) NOT NULL,
    rna_sequence VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS motif_sequence
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    sequence_id BIGINT       NOT NULL,
    motif      VARCHAR(255) NOT NULL UNIQUE ,
    CONSTRAINT fk_sequence FOREIGN KEY (sequence_id) REFERENCES sequence (id)
);

CREATE TABLE IF NOT EXISTS motif_index
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    motif_sequence_id BIGINT NOT NULL,
    index           INT    NOT NULL,
    CONSTRAINT fk_motif_sequence FOREIGN KEY (motif_sequence_id) REFERENCES motif_sequence (id)
);

CREATE INDEX idx_sequence_name ON sequence(sequence_name);
CREATE INDEX idx_motif ON motif_sequence(motif);
