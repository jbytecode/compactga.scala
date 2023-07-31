package org.expr

import org.expr.Cga.cga

object Main:

  def main(s: Array[String]): Unit =
    def f(x: List[Int]): Double = x.sum * -1.0
    val optresult = cga(f, 10, 0.001)
    println(optresult)


