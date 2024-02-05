package ca.helios5009.core

import java.io.File
import java.nio.file.Paths

class AutonPaths {
	val folder = Paths.get("paths")
	fun storePath(content: String, fileName: String) {
		File("${folder.toAbsolutePath()}/${fileName}").writeText(content)
	}
	fun readPath(fileName: String): String {
		return File("${folder.toAbsolutePath()}/${fileName}").readText()
	}
	fun getAllPaths(): List<String> {
		return folder.toFile().listFiles()!!.map { it.name }
	}
}