import java.time.LocalDateTime

class Conference(val name: String, val location: String){

    private val schedule = mutableListOf<Talk>()

    val talks
        get() = schedule.toList()

    fun addTalk(talk: Talk) {
        schedule.add(talk)
    }
}

data class Talk(
    val topic: String,
    val speaker: String,
    val time: LocalDateTime,
    val type: TalkType = TalkType.CONFERENCE
)

enum class TalkType {
    CONFERENCE, KEYNOTE
}

inline fun conference(config: ConferenceDsl.() -> Unit) : Conference {
    val dsl = ConferenceDsl().apply(config)
    return Conference(dsl.name, dsl.location).apply {
        dsl.talkList.forEach(this::addTalk)
    }
}
