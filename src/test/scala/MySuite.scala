import org.expr.Cga.* 

class CGATestSuilte extends munit.FunSuite:

  test("Optimize function (all zeros)"):
    def f(x: Vector[Int]): Double = x.sum * 1.0
    val optresult = cga(f, 10, 0.001)
    assertEquals(optresult, Vector.fill(10)(0))

  test("Optimize function (all ones)"):
    def f(x: Vector[Int]): Double = x.sum * -1.0
    val optresult = cga(f, 10, 0.001)
    assertEquals(optresult, Vector.fill(10)(1))

  test("Optimize complex function"):
    def f(x: Vector[Int]): Double = Math.abs(x.sum - 5)
    val optresult = cga(f, 10, 0.001)
    assertEquals(optresult.sum, 5)

  test("Optimize complex and large function"):
    def f(x: Vector[Int]): Double = Math.abs(x.sum - 50)
    val optresult = cga(f, 100, 0.001)
    assertEquals(optresult.sum, 50)

  test("0, 1, 0, 1, 0, 1, 0, 1, 0, 1 optimization"):
    val n = 100
    val expected = Vector.range(0, n).map(a => if a % 2 == 0 then 0 else 1)

    def f(x: Vector[Int]): Double = x.zip(expected).map((a, b) => Math.abs(a - b)).sum
    val optresult = cga(f, n, 0.001)
    assertEquals(optresult, expected)
      

