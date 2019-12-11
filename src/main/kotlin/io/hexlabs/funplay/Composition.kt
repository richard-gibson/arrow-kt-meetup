package intro

import arrow.core.andThen
import arrow.core.compose

object Functions {

    //create a function that takes an int and incrememnts by 1
    val incrementBy1: (Int) -> Int = TODO()

    //create a function that takes an int and decrements by 1
    val decrementBy1: (Int) -> Int = TODO()

    //create a function that takes 2 ints and produces the sum
    val plus: (Int, Int) -> Int = TODO()

    /**
     * create a function that takes an a int and returns a function that
     * will take another int and return the sum of the 2
     */
    val plusCurried: (Int) -> (Int) -> Int = TODO()
    /**
     * create a function that takes an a int and returns a function that
     * will take another int and return the product of the 2
     */
    val multiply: (Int) -> (Int) -> Int = TODO()
    /**
     * create a function that takes an a int and returns a function that
     * will take another int and return the first Int minus the second
     */
    val divide: (Int) -> (Int) -> Int = TODO()
    /**
     * create a function that takes an a int and returns a function that
     * will take another int and return the first Int divided the second
     */
    val subtract: (Int) -> (Int) -> Int = TODO()


    // use subtract to give the answer for 4 - 2
    val twoTimesFour: Int = TODO()

    //use divide to give answer for 8 /4
    val eightByFour: Int = TODO()

    //create a new function from plus that will increment by 2
    val incrementBy2: Int = TODO()

    //create a new function from subtract that will decrement by 3
    val decrementBy3: (Int) -> Int = TODO()

    //create a new function from multiply that will times by 4
    val times4: (Int) -> Int  = TODO()


    /**
     * create a function machine that will take any number,
     * increment by 2 then times by 4
     */
    val functionMachine: (Int) -> Int = TODO()


    object Circle {

        //create a function that calculates the area of a circle given its radius

        val plus: (Double) -> (Double) -> Double = TODO()
        val multiply: (Double) -> (Double) -> Double = TODO()


        val byPi: (Double) -> Double = TODO()
        val sqrd: (Double) -> Double = TODO()

        val circleArea: (Double) -> Double = TODO()

    }


    //Create a function that returns the number of vowels in a given string

    val vowels = setOf('a', 'e', 'i', 'o', 'u')
    val filterVowels: (String) -> String = TODO()


    val numberOfVowels: (String) -> Int = TODO()


    //Create a calculator that will carry out a set list of calculations on any int
    val commands: List<(Int) -> Int> = TODO()

    val calculator: (List<(Int) -> Int>) -> (Int) -> Int = TODO()

    val myCalculator = calculator(commands)


}