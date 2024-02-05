package ca.helios5009

import ca.helios5009.core.CommandExecute
import ca.helios5009.core.misc.events.Event
import ca.helios5009.core.misc.events.EventListener
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext


val quitingStatements = listOf("exit", "quit", "q", "e")
val listener = EventListener()

fun main() {
	val port = 443
	val executor = CommandExecute("blueLeft.csv", listener)
	listener.Subscribe(OutTakePixel())
	executor.execute()





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

class OutTakePixel(): Event("purple_outtake") {
	@OptIn(DelicateCoroutinesApi::class)
	override fun run() {
		CoroutineScope(Dispatchers.Default).launch {
			println("Purple Outtake, taking 5 seconds")
			delay(5000)
			listener.call("Purple_Outtake_Finish")
		}
	}
}

