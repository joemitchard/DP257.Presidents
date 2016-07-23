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

        val uniqueYears = getUniqueDates(dates)

        countYearsBorn(uniqueYears, presidents)

        printResults(uniqueYears)
    }


    private fun printResults(countedYears: HashMap<Int, Int?>):Unit {

        val sorted = sortByValue(countedYears)

        println("Year with the highest president births: ${sorted[0].year}, total: ${sorted[0].counter}")

        sorted.find { it.year.equals(2016) }!!.counter = 1


    }

    fun sortByValue(map: Map<Int, Int?>): List<DateCounter> {

        var sortedList = ArrayList<DateCounter>()
        
        map.forEach { sortedList.add(DateCounter(it.key, it.value)) }

        return sortedList.sortedWith(compareBy({ it.counter })).sortedByDescending { it.counter }

    }

    private fun  countYearsBorn(uniqueYears: HashMap<Int, Int?>, presidents: ArrayList<President>) : Unit {

        for ((name, born) in presidents) {
            uniqueYears.put(born.year, uniqueYears[born.year]?.plus(1)!!)
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

    private fun getUniqueDates(dates: List<Date>): HashMap<Int, Int?> {
        val uniqueYears = HashMap<Int, Int?>()

        for (date in dates) {

            val uniqueDate = uniqueYears.getOrDefault(date.year, null)

            if (uniqueDate == null) {
                uniqueYears.putIfAbsent(date.year, 0)
            }

        }
        return uniqueYears
    }
}