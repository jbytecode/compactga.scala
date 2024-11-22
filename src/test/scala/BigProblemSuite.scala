import org.expr.Cga.* 

class BigProblemSuite extends munit.FunSuite:

    test("All ones - big problem"):
        def f(x: Vector[Int]): Double = x.map(x => Math.abs(x - 1)).sum
    
        val optresult = cga(f, 1000, 0.001)
        assertEquals(optresult.sum, 1000)