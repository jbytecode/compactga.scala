import org.expr.Cga.* 

class Sampling extends munit.FunSuite:

  test("Initial probability vector"):
    val p = initial_prob_vector(10)
    assertEquals(p, Vector.fill(10)(0.5))

  test("Sample from probability vector (All ones)"):
    val p = Vector.fill(10)(1.0)
    val sampled = sample(p)
    assertEquals(sampled, Vector.fill(10)(1))

  test("Sample from probability vector (All zeros)"):
    val p = Vector.fill(10)(0.0)
    val sampled = sample(p)
    assertEquals(sampled, Vector.fill(10)(0))

  test("Sample from probability vector (random)"):
    val p = initial_prob_vector(10)
    val sampled = sample(p)
    val result = sampled.count(x => x == 0 || x == 1)
    assertEquals(result, 10)

  test("Converged"):
    assertEquals(converged(Vector(1.0, 1, 1, 0, 0, 0), 0.1), true)
    assertEquals(converged(Vector(0.9, 0.9, 0.9, 0.1, 0, 0), 0.1), true)
    assertEquals(converged(Vector(0.8, 0.9, 0.9, 0.1, 0, 0), 0.1), false)
