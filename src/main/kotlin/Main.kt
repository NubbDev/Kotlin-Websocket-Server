package ca.helios5009

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.absolutePathString

val quitingStatements = listOf("exit", "quit", "q", "e")

external fun test(): String

fun main() {
	val port = 443
	val pathExecution = PathExecution()
	pathExecution.setPath("blueLeft.csv")

	pathExecution.run()


//	val server = HyperionServer(port = port)
//	server.start()
//	while (true) {
//		val input = readlnOrNull()
//		if (input?.lowercase() in quitingStatements) {
//			println("Exiting...")
//			server.stop()
//			break
//		}
//	}
}

