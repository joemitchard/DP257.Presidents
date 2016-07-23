package Presidents

import java.util.*
import kotlin.comparisons.compareBy

/**
 * Created by joemitchard on 23/07/2016.
 *
 */

class Presidents {

    fun run() : Unit {

        val presidents = readPresidents()

        val dates = presidents.map { it.born }.toList().distinct()

        val dateCounters = getDateCounters(dates)

        countYearsBorn(dateCounters, presidents)

        printResults(dateCounters)
    }


    private fun printResults(countedYears: ArrayList<DateCounter>):Unit {

        val sorted = countedYears.sortedWith(compareBy({ it.counter })).sortedByDescending { it.counter }

        println("Year with the highest president births: ${sorted[0].year}, total: ${sorted[0].counter}")

    }

    private fun  countYearsBorn(dateCounters: ArrayList<DateCounter>, presidents: ArrayList<President>) : Unit {

        for ((name, born) in presidents) {
            dateCounters.find { it.year.equals(born) }!!.counter = dateCounters.find { it.year.equals(born) }!!.counter + 1
        }
    }

    private fun readPresidents(): ArrayList<President> {
        val presidents = ArrayList<President>()

        val dataList = Data().get()


        for (data in dataList) {

            val splitData = data.split(',')

            val born = splitData[1].trim().split(' ').last().toInt()
            val diedStr = splitData[3].trim().split(' ').last()
            var died = 0

            if (diedStr != "") {
                died = diedStr.toInt()
            }

            presidents.add(President(name = splitData[0], born = born, died = died))

        }


        return presidents
    }

    private fun getDateCounters(dates: List<Int>): ArrayList<DateCounter> {

        val dateCounters = ArrayList<DateCounter>()

        dates.forEach { dateCounters.add(DateCounter(it, 0)) }

        return dateCounters

    }
}