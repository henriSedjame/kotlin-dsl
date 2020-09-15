import java.time.LocalDateTime

class ConferenceDsl() {

    private val _talkList = mutableListOf<Talk>()

    val talkList
        get() = _talkList.toList()

    lateinit var name: String
    lateinit var location: String
    val talks = TalkConfigDsl()

    inner class TalkConfigDsl {
        private val _talkList = this@ConferenceDsl._talkList


        val conferenceTalk
            get() = EmptyTalk(TalkType.CONFERENCE)

        val keynoteTalk
            get() = EmptyTalk(TalkType.KEYNOTE)

        operator fun invoke(config: TalkConfigDsl.() -> Unit) {
            this.apply(config)
        }

        fun conferenceTalk(topic: String, speaker: String, time : LocalDateTime){
            _talkList.add(Talk(topic, speaker, time, TalkType.CONFERENCE))
        }

        fun keynoteTalk(topic: String, speaker: String, time : LocalDateTime){
            _talkList.add(Talk(topic, speaker, time, TalkType.KEYNOTE))
        }

        inner class EmptyTalk(val type: TalkType) {
            infix fun at(time: String) = TimedTalk(this, LocalDateTime.parse(time))
        }

        inner class TimedTalk(val emptyTalk: EmptyTalk, val time: LocalDateTime){
            infix fun by(speaker: String) = TimeAndAuthoredTalk(this, speaker)
        }

        inner class TimeAndAuthoredTalk(val timedTalk: TimedTalk, val speaker: String) {
            infix fun titled(topic: String) = _talkList.add(
                Talk(topic, speaker, timedTalk.time, timedTalk.emptyTalk.type)
            )
        }

        operator fun Talk.unaryPlus() = _talkList.add(this)

    }
}


