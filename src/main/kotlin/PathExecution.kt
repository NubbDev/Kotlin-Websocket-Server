package ca.helios5009

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.absolutePathString

class PathExecution {
	private val commandsParse = CommandsParse()
	val path = mutableListOf<JsonNode>()

	fun setPath(file: String) {
		val fileToExecute = Path(file)
		val pathSegment = File(fileToExecute.absolutePathString())
		val parsed = commandsParse.read(pathSegment.readText())
		val commandArray: JsonNode = jacksonObjectMapper().readTree(parsed)
		path.addAll(commandArray)
	}

	fun run() {
		for (commands in path) {
			commands.fields().forEach {command ->
				when (command.key.lowercase()) {
					"start" -> handleStart(command.value)
					"end" -> handleEnd(command.value)
					"wait" -> handleWait(command.value)
					"line" -> handleLine(command.value)
					"spline" -> handleSpline(command.value)
				}
			}
		}

	}


	private fun handleStart(start: JsonNode) {
		println("Announce ${start.get("event").get("message")}")
		println("${start.get("x")}, ${start.get("y")}, ${start.get("rot")}")
	}

	private fun handleEnd(end: JsonNode) {
		println("Announce ${end.get("message")}")
	}

	private fun handleLine(line: JsonNode) {

	}

	private fun handleWait(wait: JsonNode) {

	}

	private fun handleSpline(spline: JsonNode) {

	}
}