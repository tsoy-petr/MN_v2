package calculator

class Calculator(var result: Int = 0) {

    fun get() = result
    fun add(other: Int): Unit { result += other}
    fun mul(other: Int) { result *= other}
    fun sub(other: Int) { result -= other}
    fun div(other: Int) {
        assert(other != 0) {"Division by zero"}
        result /= other
    }
}

fun main(args: Array<String>) {

    val calc = Calculator(12)
    calc.add(25)
    println(calc.get())

}