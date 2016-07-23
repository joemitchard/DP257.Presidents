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
            dateCounters.find { it.year.equals(born.year) }!!.counter = dateCounters.find {it.year.equals(born.year)}!!.counter + 1
        }
    }

    private fun readPresidents(): ArrayList<President> {
        val presidents = ArrayList<President>()

        presidents.add(President("Test", Date(2016, 1, 1), Date(2016, 12, 31)))
        presidents.add(President("Test2", Date(2014, 1, 1), Date(2018, 12, 31)))
        presidents.add(President("Test3", Date(2014, 1, 1), Date(2018, 12, 31)))
        presidents.add(President("Test4", Date(2016, 2, 1), Date(2017, 12, 31)))
        presidents.add(President("Test5", Date(2017, 2, 1), Date(2017, 12, 31)))
        presidents.add(President("Test6", Date(2015, 2, 1), Date(2017, 12, 31)))
        presidents.add(President("Test7", Date(2004, 2, 1), Date(2019, 12, 31)))
        presidents.add(President("Test8", Date(2015, 2, 1), Date(2019, 12, 31)))
        presidents.add(President("Test8", Date(2016, 2, 1), Date(2019, 12, 31)))

        return presidents
    }

    private fun getDateCounters(dates: List<Date>): ArrayList<DateCounter> {

        val dateCounters = ArrayList<DateCounter>()

        dates.forEach { dateCounters.add(DateCounter(it.year, 0)) }

        return dateCounters

    }
}