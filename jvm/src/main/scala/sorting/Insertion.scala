package algorithms.sorting

import scala.collection.mutable.{Seq => MutableSeq}


object Insertion extends MutableSorter {

  def sortMutable[A <% Sortable[A]](seq: MutableSeq[A]) = {
    val len = seq.length - 1
    for (i <- 1 to len) {
      for (visiting <- i to 1 by -1) {
        println("VISITING", visiting)
        exch(seq, visiting, visiting - 1)
//        println("VISITING", i, visiting.toString)
//        exch(seq, visiting, visiting - 1)
      }
    }
    seq
  }

}