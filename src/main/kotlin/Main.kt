fun main() {
    val user1 = User("Sergey", "0168", 86399)
    println(user1.run { agoToText(timeAgo) })
}

data class User(val name: String, val id: String, var timeAgo: Int) {

    fun agoToText(timeAgo: Int): String {
        val sec = "секунду"
        val secTwoFour = "секунды"
        val secBeforeTwenty = "секунд"
        val min = "минуту"
        val minTwoFour = "минуты"
        val minBeforeTwenty = "минут"
        val hour = "час"
        val hourTwoFour = "часа"
        val hourBeforeTwenty = "часов"
        val day = "вчера"
        val dayTwoFour = "позовчера"

        var time = when {
            timeAgo in 0 until 60 -> timeAgo
            timeAgo in 60 until (60 * 60) -> timeAgo / 60
            timeAgo in (60 * 60) until (60 * 60) * 24 -> (timeAgo / 60) / 60
            timeAgo in (60 * 60) * 24 until ((60 * 60) * 24) * 3 -> ((timeAgo / 60) / 60) / 24
            else -> return "$name был в сети давно"
        }

        var case = when {
            timeAgo in 0 until 60 -> when {
                time % 10 == 1 && time % 100 != 11 -> sec
                time % 10 in 2..4 -> secTwoFour
                else -> secBeforeTwenty
            }

            timeAgo in 60 until (60 * 60) -> when {
                time % 10 == 1 && time % 100 != 11 -> min
                time % 10 in 2..4 -> minTwoFour
                else -> minBeforeTwenty
            }

            timeAgo in (60 * 60) until (60 * 60) * 24 -> when {
                time % 10 == 1 && time % 100 != 11 -> hour
                time % 10 in 2..4 -> hourTwoFour
                else -> hourBeforeTwenty
            }

            timeAgo in (60 * 60) * 24 until ((60 * 60) * 24) * 3 -> when (timeAgo) {
                in (60 * 60) * 24 until ((60 * 60) * 24) * 2 -> return "$name был в сети $day"
                in ((60 * 60) * 24) * 2 until ((60 * 60) * 24) * 3 -> return "$name был в сети $dayTwoFour"
                else -> return "$name был в сети давно"
            }

            else -> return "$name был в сети давно"

        }


        return when {
            (timeAgo <= 60) -> "$name был в сети $time $case назад"
            else -> "$name был в сети $time $case назад"
        }

    }
}