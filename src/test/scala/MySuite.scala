import org.expr.Cga.* 

class CGATestSuilte extends munit.FunSuite:

  test("Initial probability vector"):
    val p = initial_prob_vector(10)
    assertEquals(p, List.fill(10)(0.5))

  test("Sample from probability vector (All ones)"):
    val p = List.fill(10)(1.0)
    val sampled = sample(p)
    assertEquals(sampled, List.fill(10)(1))

  test("Sample from probability vector (All zeros)"):
    val p = List.fill(10)(0.0)
    val sampled = sample(p)
    assertEquals(sampled, List.fill(10)(0))

  test("Sample from probability vector (random)"):
    val p = initial_prob_vector(10)
    val sampled = sample(p)
    val result = sampled.count(x => x == 0 || x == 1)
    assertEquals(result, 10)

  test("Converged"):
    assertEquals(converged(List(1.0, 1, 1, 0, 0, 0), 0.1), true)
    assertEquals(converged(List(0.9, 0.9, 0.9, 0.1, 0, 0), 0.1), true)
    assertEquals(converged(List(0.8, 0.9, 0.9, 0.1, 0, 0), 0.1), false)

  test("Optimize function (all zeros)"):
    def f(x: List[Int]): Double = x.sum * 1.0
    val optresult = cga(f, 10, 0.001)
    assertEquals(optresult, List.fill(10)(0))

  test("Optimize function (all ones)"):
    def f(x: List[Int]): Double = x.sum * -1.0
    val optresult = cga(f, 10, 0.001)
    assertEquals(optresult, List.fill(10)(1))

  test("Optimize complex function"):
    def f(x: List[Int]): Double = Math.abs(x.sum - 5)
    val optresult = cga(f, 10, 0.001)
    assertEquals(optresult.sum, 5)

  test("Optimize complex and large function"):
    def f(x: List[Int]): Double = Math.abs(x.sum - 50)
    val optresult = cga(f, 100, 0.001)
    assertEquals(optresult.sum, 50)
