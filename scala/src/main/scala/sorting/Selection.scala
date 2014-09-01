package algorithms.sorting

import scala.annotation.tailrec
import scala.collection.mutable.{Seq => MutableSeq}


object SelectionImmutable extends ImmutableSorter {
  def sort[A <% Sortable[A]](in: Seq[A]): Seq[A] = sortAcc(in)

  @tailrec
  private def sortAcc[A <% Sortable[A]](in: Seq[A], acc: Seq[A] = Seq.empty): Seq[A] = in match {
    case Nil      => acc
    case x :: Nil => acc ++ List(x)
    case xs       =>
      val smallestIndex = findSmallest(xs)
      sortAcc(xs.removeAt(smallestIndex), acc ++ List(xs(smallestIndex)))
  }

  private def findSmallest[A <% Sortable[A]](seq: Seq[A]): Int =
    seq.view.zipWithIndex.foldLeft(0){ case (standingIndex: Int, (el: A, visitingIndex: Int)) =>
      seq(standingIndex).compare(el) match {
        case ComparLessThan => visitingIndex
        case _              => standingIndex
      }
    }
}


object SelectionMutable extends MutableSorter {
  def sortMutable[A <% Sortable[A]](seq: MutableSeq[A]) = {
    val len = seq.length - 1

    for (i <- 0 to len) {
      var standing = i

      for (visiting <- (i + 1) to len) {
        standing = seq(standing).compare(seq(visiting)) match {
          case ComparLessThan => visiting
          case _              => standing
        }
      }

      if (standing != i) exch(seq, i, standing)
    }

    seq
  }
}