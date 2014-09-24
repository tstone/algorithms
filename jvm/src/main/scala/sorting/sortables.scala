package algorithms.sorting

object sortables {

  implicit def intToSortable(self: Int) = new Sortable[Int] {
    def compare(other: Int): Comparison =
      if (other > self) ComparGreaterThan
      else if (other < self) ComparLessThan
      else Equal
  }

  implicit def charToSortable(self: Char) = new Sortable[Char] {
    def compare(other: Char) =
      intToSortable(self.toInt).compare(other.toInt)
  }

}