package ca.helios5009.core.misc.events

class EventListener {

	var value = ""
	private val triggerFunctions = mutableListOf<Event>()

	fun Subscribe(function: Event) {
		triggerFunctions.add(function)
	}

	fun call(value: String) {
		this.value = value
		triggerFunctions.forEach {
			if (it.event == value) {
				it.run()
			}
		}
	}

}

