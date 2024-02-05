package ca.helios5009

class EventListener {
	private val listeners = mutableListOf<Event>()

	fun subscribe(event: Event) {
		listeners.add(event)
	}

	fun set(value: String) {
		for (listener in listeners) {
			listener.onTrigger(value)
		}
	}
}

object Event {
	fun onTrigger(t: String) {}
}