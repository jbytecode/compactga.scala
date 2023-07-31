object CGA:

  type FunctionType = (List[Int]) => Double
  type Candidate = List[Int]
  type ProbabilityVector = List[Double]

  def initial_prob_vector(n: Int): ProbabilityVector = List.fill(n)(0.5)

  def sample(p: ProbabilityVector): Candidate =
    p.map(x => if Math.random() < x then 1 else 0)

  def updatesingleprob(
      p: Double,
      winner: Int,
      loser: Int,
      mut: Double
  ): Double =
    if winner != loser then if winner == 1 then p + mut else p - mut
    else p

  def updateby(
      p: ProbabilityVector,
      winner: Candidate,
      loser: Candidate,
      mut: Double
  ): ProbabilityVector =
    val data = p.zip(winner.zip(loser))
    def updatehelper(x: (Double, (Int, Int))): Double =
      val (p, (w, l)) = x
      updatesingleprob(p, w, l, mut)
    data.map(updatehelper)

  def converged(p: ProbabilityVector, mut: Double): Boolean =
    p.count(x => (x - mut <= 0.0) || (x + mut >= 1.0)) == p.length

  def singlestep(f: FunctionType, p: List[Double], mut: Double): List[Double] =
    val parent1 = sample(p)
    val parent2 = sample(p)
    val cost1 = f(parent1)
    val cost2 = f(parent2)
    if cost1 < cost2 then updateby(p, parent1, parent2, mut)
    else updateby(p, parent2, parent1, mut)

  def cga(f: FunctionType, n: Int, mut: Double): Candidate =
    var probvector = initial_prob_vector(n)
    while !(converged(probvector, mut)) do {
      probvector = singlestep(f, probvector, mut)
    }
    sample(probvector)

    /*
    Sample use: (see unit tests)
        def f(x: List[Int]) : Double = x.sum * 1.0


        def main(args: Array[String]): Unit =
            val result = CGA.cga(f, 20, 0.001)
            println(result)
     */
