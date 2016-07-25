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

        printBornResults(dateCounters)

        calculateMostPresidentsAlive(presidents)
    }

    private fun calculateMostPresidentsAlive(presidents: ArrayList<President>) {


        val firstBorn = getFirstBornDate(presidents)

        val dateRange = firstBorn.rangeTo(2016).toList()

        val counters = getDateCounters(dateRange)


        for (date in dateRange) {

            val alivePresidents = presidents.filter { isAlive(date, it.born, it.died) }.toList()

            counters.add(DateCounter(date, alivePresidents.count()))

        }

        val sortedCounters = sortDateCounters(counters)

        val highestTotal = sortedCounters.first().counter

        val yearsWithMostAlive = sortedCounters.filter { it.counter.equals(highestTotal) }.toList() as ArrayList<DateCounter>

        printAliveResults(yearsWithMostAlive)

    }

    private fun isAlive(year: Int, born: Int, died: Int?): Boolean {
        if (died != null) {

            return (year >= born && year <= died)

        } else {

            return (year >= born)

        }
    }

    private fun getFirstBornDate(presidents: ArrayList<President>): Int {

        var births = presidents.map { it.born }.toList().distinct()

        births = births.sortedWith(compareBy { it }).sorted()

        println(births)

        return births.first()

    }

    private fun sortDateCounters(countedYears: ArrayList<DateCounter>): List<DateCounter> {

        return countedYears.sortedWith(compareBy({ it.counter })).sortedByDescending { it.counter }.toList()

    }

    private fun printBornResults(countedYears: ArrayList<DateCounter>): Unit {

        val sorted = sortDateCounters(countedYears)

        println("Year with the highest president births: ${sorted[0].year}, total: ${sorted[0].counter}")

    }

    private fun printAliveResults(filteredYears: ArrayList<DateCounter>): Unit {

        val years = filteredYears.map { it.year }.toList()

        println("On the years below there were ${filteredYears.first().counter} presidents alive.")
        println(years)

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
            val died = if (diedStr.equals("")) null else diedStr.toInt()

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