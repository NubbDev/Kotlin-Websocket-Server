package ca.helios5009.core

import java.io.File
import java.nio.file.Paths

class CommandsParse {
	init {
		val path = Paths.get("src/main/rust/target/debug/hyperionlib.dll")
		System.load(path.toAbsolutePath().toString())
	}

	external fun read(commands: String): String

}