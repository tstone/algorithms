package algorithms.sorting

import algorithms.sorting.sortables._
import test.support.BaseSpec


class SortingSpec extends BaseSpec {





  TestSortingBehaviorOf(SelectionImmutable)
  TestSortingBehaviorOf(SelectionMutable)







  def TestSortingBehaviorOf(S: Sorter) = {
    val sortName = S.getClass.getSimpleName.replace("$", "") + " sort"

    sortName should {

      "order a random list" in {
        val random = Seq(18, 15, 1, 4, 19, 3, 8, 12, 15)
        val randomSorted = Seq(1, 3, 4, 8, 12, 15, 15, 18, 19)
        val r = time(sortName + " random list") { () => S.sort(random) }
        r mustEqual randomSorted
      }

      "order a backwards list" in {
        val backwards = Seq(20, 19, 18, 17, 16, 13, 11, 8, 4, 2)
        val backwardsSorted = Seq(2, 4, 8, 11, 13, 16, 17, 18, 19, 20)
        val r = time(sortName + " backwards list") { () => S.sort(backwards) }
        r mustEqual backwardsSorted
      }

      "order an already sorted list" in {
        val forwards = Seq(2, 4, 5, 6, 8, 9, 12, 22, 23, 30, 61)
        val r = time(sortName + " already sorted list") { () => S.sort(forwards) }
        r mustEqual forwards
      }

    }
  }

}