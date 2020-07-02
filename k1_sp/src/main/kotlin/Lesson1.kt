


data class RationalNumber(val numerator: Int, val denominator: Int)

fun Int.r(): RationalNumber = RationalNumber(this, 1)

fun Pair<Int, Int>.r(): RationalNumber = RationalNumber(first, second)


fun String?.isEmptyOrNull(): Boolean = this?.isEmpty() ?: true


fun main(args: Array<String>) {

    println(null.isEmptyOrNull())

    run { println("hellow")}

}