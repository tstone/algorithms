package test.support

import org.specs2.mutable.Specification


class BaseSpec extends Specification {

  type Scope = org.specs2.specification.Scope
  case class PerformanceResult[A](value: A, time: Float)

  def time[A](block: => A): PerformanceResult[A] = {
    val t0 = System.nanoTime()
    val result = block
    val t1 = System.nanoTime()
    val time = (t1 - t0) / 1000000000f
    PerformanceResult(result, time)
  }

}