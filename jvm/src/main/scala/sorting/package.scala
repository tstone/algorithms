package algorithms

import scala.collection.mutable.{Seq => MutableSeq}

package object sorting {

  sealed trait Comparison
  case object ComparLessThan extends Comparison
  case object Equal extends Comparison
  case object ComparGreaterThan extends Comparison

  trait Sortable[A] {
    def compare(other: A): Comparison
  }

  trait Sorter {
    def sort[A <% Sortable[A]](in: Seq[A]): Seq[A]
  }

  trait ImmutableSorter extends Sorter {
    implicit class SeqOps[A](seq: Seq[A]) {
      def removeAt(index: Int): Seq[A] = {
        val (start, _ :: end) = seq.splitAt(index)
        start ++ end
      }
    }
  }

  trait MutableSorter extends Sorter {
    def sortMutable[A <% Sortable[A]](in: MutableSeq[A]): MutableSeq[A]
    def sort[A <% Sortable[A]](in: Seq[A]) = {
      val mut = MutableSeq(in: _*)
      val out = sortMutable(mut)
      Seq(out: _*)
    }

    def exch[A](seq: MutableSeq[A], i: Int, j: Int) = {
      val buffer = seq(i)
      seq(i) = seq(j)
      seq(j) = buffer
    }
  }
}
