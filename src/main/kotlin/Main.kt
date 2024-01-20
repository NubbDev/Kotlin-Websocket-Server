package ca.helios5009

import java.net.ServerSocket

val SOCKETLIST = mutableListOf<ClientHandler>()
fun main() {
	val host: String = "localhost"
	val port: Int = 443
	val server = ServerSocket(port)

	println("Server started on port $port")

	while (true) {
		val socket = server.accept()
		val client = ClientHandler(socket, onDisconnect)
		SOCKETLIST.add(client)
		println("Client connected: ${socket.inetAddress.hostAddress}: ${socket.port}")

	}
}

val onDisconnect: (ClientHandler) -> Unit = { client ->
	client.running = false
	SOCKETLIST.remove(client)
}