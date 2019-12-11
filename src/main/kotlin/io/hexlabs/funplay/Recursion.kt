package intro

object Recursion {

    //create a function that will produce a string concatenated N times
    tailrec fun concatN(n: Int, str: String): String = TODO()

    //create a function that find the sum of all Ints in a list recursively
    fun sumRec(l: List<Int>): Int = TODO()

    //create a function that find the sum of all Ints in a list using tail recursively
    fun sumTailRec(l: List<Int>): Int = TODO()

    //create a functin that find the product of all Ints in a list using tail recursively
    fun productRec(l: List<Int>): Int = TODO()

    //create a function that can calulate the sum or product of a list
    fun <A> HOFApplyRec(l: List<A>, zero: A, f:(A, A) -> A): A = TODO()

    //create a function that returns the factorial of a given number
    fun factorial(n: Int): Int = TODO()

    //create a function that returns its roman numeral representation e.g. 1 -> I,  6 -> VI, 10 -> X
    fun romanNumerals(i: Int): String {
        val numerals = listOf("M" to 1000, "D" to 500, "C" to 100, "L" to 50, "X" to 10, "V" to 5, "I" to 1)
        tailrec fun _romanNumerals(number: Int, numerals: List<Pair<String, Int>>, numAcc: String = ""): String =
                TODO()
        return _romanNumerals(i, numerals)
    }

}