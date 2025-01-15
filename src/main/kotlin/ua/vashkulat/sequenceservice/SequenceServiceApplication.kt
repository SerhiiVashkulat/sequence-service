package ua.vashkulat.sequenceservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SequenceServiceApplication

fun main(args: Array<String>) {
    runApplication<SequenceServiceApplication>(*args)
}
