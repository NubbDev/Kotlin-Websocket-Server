package ca.helios5009

import java.net.Socket
import java.util.*

class ClientHandler(
	private val client: Socket,
	val onDisconnect: (ClientHandler) -> Unit):
	Runnable {

	val reader = Scanner(client.getInputStream())
	val writer = client.getOutputStream()
	var running = false
	override fun run() {
		running = true

		while (running) {
			try {
				val data = reader.nextLine()

                                                                           				println(data)
			} catch (e: Exception) {
				println("Client disconnected: ${client.inetAddress.hostAddress}: ${client.port}")
				running = false
			}
		}
	}
}