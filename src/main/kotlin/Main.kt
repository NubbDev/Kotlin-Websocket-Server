package ca.helios5009

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.absolutePathString

val quitingStatements = listOf("exit", "quit", "q", "e")

external fun test(): String

fun main() {
	val port = 443

	val commandsParse = CommandsParse()
	val fileToExecute = Path("blueLeft.csv")
	val pathSegment = File(fileToExecute.absolutePathString())
	val commandString = commandsParse.read(pathSegment.readText())
	val commands = jacksonObjectMapper().readValue(commandString, listOf<LinkedHashMap<String, Any>>()::class.java)


	for (command in commands) {
		println(command["Start"])
	}



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

