import calculator.Calculator
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.lang.AssertionError
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculatorTest {

    init {
        println("init")
    }

    @BeforeAll
    fun prepare() {
        println("prepare")
    }

    @BeforeEach
    fun prepareTest() {
        println("prepareTest")
    }

    @AfterAll
    fun tearDown() {
        println("tearDown")
    }

    @Test
    @DisplayName("Calculator Add Test function")
    fun calcAdd() {
        println("calcAdd")
        val calculator = Calculator(15)
        calculator.add(2)
        assertEquals(
            17, calculator.get()

        )
    }

    @Test
    fun calcMul() {
        val calculator = Calculator(15)
        calculator.mul(2)
        assertEquals(
            30, calculator.get()

        )
    }

    @ParameterizedTest
    @CsvSource(
        "0, 1, 1",
        "10, 5, 15",
        "1, 100, 101"
    )
    fun paraAdd(f: Int, s: Int, r: Int) {

        val calculator = Calculator(f)
        calculator.add(s)
        assertEquals(
            r, calculator.get(),
            "$f + $s = $r"
        )

    }

    fun data(): Stream<Arguments> = Stream.of(
        Arguments.of(0, 0, 0),
        Arguments.of(0, 3, 0),
        Arguments.of(12, 6, 72),
        Arguments.of(3, 6, 18)
    )

    @ParameterizedTest(name = "{0} * {1} = {2}")
    @MethodSource("data")
    fun paraMul(f: Int, s: Int, r: Int) {

        val calculator = Calculator(f)
        calculator.mul(s)
        assertEquals(
            r, calculator.get(),
            "$f * $s = $r"
        )

    }

    @Test
    fun divisionByZeroThrowsException() {

        val calc = Calculator(10)

        val exception = assertThrows<AssertionError> {
            calc.div(0)
        }

        assertEquals("Division by zero", exception.message)

    }

    @Test
    fun combineAddAndMul() {

        val calc = Calculator(15)

        assertAll("проверка",
            {
                assertEquals(15, calc.get())
            },
            {
                calc.add(2)
                assertEquals(17, calc.get())
            },
            {
                calc.mul(2)
                assertEquals(34, calc.get())
            }
        )

    }
}