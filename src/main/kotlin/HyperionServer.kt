package ca.helios5009

import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.lang.Exception
import java.net.InetSocketAddress

class HyperionServer(private val address: String = "127.0.0.1", private val port: Int = 443): WebSocketServer(InetSocketAddress(address, port)) {

	override fun onOpen(socket: WebSocket,handshake: ClientHandshake) {
		val values = LinkedHashMap<String, Any>()
		values["event"] = "pong"
		val json = jacksonMapperBuilder().build().writeValueAsString(values)
		socket.send(json)
		println("Socket opened from ${socket.remoteSocketAddress}")
	}

	override fun onClose(socket: WebSocket?, p1: Int, p2: String?, p3: Boolean) {
		broadcast("closed connection: ${socket?.remoteSocketAddress}")
		println("Socket closed from ${socket?.remoteSocketAddress}")
	}

	override fun onMessage(socket: WebSocket, message: String) {
		val data = jacksonObjectMapper().readValue(message, LinkedHashMap<String, Any>()::class.java)
		println("Message from ${socket.remoteSocketAddress}: $data")
		when (data["event"]) {
			"ping" -> {
				val values = LinkedHashMap<String, Any>()
				values["event"] = "pong"
				val json = jacksonMapperBuilder().build().writeValueAsString(values)
				socket.send(json)
			}
		}
	}

	override fun onError(socket: WebSocket, error: Exception) {
		println("Error from ${socket.remoteSocketAddress}: ${error.message}")

	}

	override fun onStart() {
		println("Server started on port: ws://${address}:${port}")
		connectionLostTimeout = 0
		connectionLostTimeout = 100
	}

}