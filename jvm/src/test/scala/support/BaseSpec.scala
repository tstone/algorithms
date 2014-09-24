package test.support

import org.specs2.mutable.Specification


class BaseSpec extends Specification {

  type Scope = org.specs2.specification.Scope

  def time[A](desc: String)(block: () => A): A = {
    val t0 = System.nanoTime()

    val firstResult = block()

    for (x <- 1 to 999) { block() }
    val t1 = System.nanoTime()

    for (x <- 1 to 8999) { block() }
    val t2 = System.nanoTime()

    for (x <- 1 to 89999) { block() }
    val t3 = System.nanoTime()

    val time1k = (t1 - t0) / 1000000000f
    val time10k = (t2 - t0) / 1000000000f
    val time100k = (t3 - t0) / 1000000000f

    println(s"[info] $desc (1k runs): $time1k")
    println(s"[info] $desc (10k runs): $time10k")
    println(s"[info] $desc (100k runs): $time100k")

    firstResult
  }

}