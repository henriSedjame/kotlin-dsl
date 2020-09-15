
fun main() {

    val conference = conference {
        name = "Devoxx UK 2019"
        location = "London"

        talks {
            conferenceTalk at "2019-02-02T00:00:00" by "Simon Wirtz" titled "Kotlin avanced features"
            keynoteTalk at "2019-02-02T10:00:00" by "Sebastien Deleuze" titled "Spring Fu"
        }
    }

    conference.talks.forEach {
        println(it)
    }
}

