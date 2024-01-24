package ca.helios5009

import java.io.File
import java.nio.file.Paths

class CommandsParse {
	init {
//		path.replace("")
		System.loadLibrary("hyperionlib")
	}

	external fun read(commands: String): String

}