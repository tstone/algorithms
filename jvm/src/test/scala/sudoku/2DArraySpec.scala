package algorithms.sudoku

import test.support.BaseSpec
import Sudoku2DSeq._



class Sudoku2DSeqSpec extends BaseSpec {

  "GameBoard" should {

    /*
        Game Board:

        1  2  3   /  /  /   /  /  /
        4  5  6   1  2  3   /  /  /
        7  8  9   /  /  /   /  /  /

        /  /  /   /  /  /   /  /  /
        /  /  /   /  /  /   /  /  /
        /  /  /   /  /  /   /  /  /

        /  /  /   /  /  /   /  /  /
        /  /  /   /  /  /   /  /  /
        /  /  /   /  /  /   /  /  /

     */


    val board = Seq[Seq[Option[Int]]](
      Seq(Some(1), Some(2), Some(3), None, None, None, None, None, None),
      Seq(Some(4), Some(5), Some(6), Some(1), Some(2), Some(3), None, None, None),
      Seq(Some(7), Some(8), Some(9), None, None, None, None, None, None),
      Seq(None, None, None, None, None, None, None, None, None),
      Seq(None, None, None, None, None, None, None, None, None),
      Seq(None, None, None, None, None, None, None, None, None),
      Seq(None, None, None, None, None, None, None, None, None),
      Seq(None, None, None, None, None, None, None, None, None),
      Seq(None, None, None, None, None, None, None, None, None)
    )

    // An actual sudoku board I found out on the internet
    // http://news.nd.edu/assets/80986/sudoku300.jpg

    val board2 = Seq[Seq[Option[Int]]](
      Seq(Some(8), None, None, Some(4), None, Some(6), None, None, Some(7)),
      Seq(None, None, None, None, None, None, Some(4), None, None),
      Seq(None, Some(1), None, None, None, None, Some(6), Some(5), None),
      Seq(Some(5), None, Some(9), None, Some(3), None, Some(7), Some(8), None),
      Seq(None, None, None, None, Some(7), None, None, None, None),
      Seq(None, Some(4), Some(8), None, Some(2), None, Some(1), None, Some(3)),
      Seq(None, Some(5), Some(2), None, None, None, None, Some(9), None),
      Seq(None, None, Some(1), None, None, None, None, None, None),
      Seq(Some(3), None, None, Some(9), None, Some(2), None, None, Some(5))
    )

    val solved = Seq[Seq[Option[Int]]](
      Seq(Some(2), Some(9), Some(6), Some(3), Some(1), Some(8), Some(5), Some(7), Some(4)),
      Seq(Some(5), Some(8), Some(4), Some(9), Some(7), Some(2), Some(6), Some(1), Some(3)),
      Seq(Some(7), Some(1), Some(3), Some(6), Some(4), Some(5), Some(2), Some(8), Some(9)),
      Seq(Some(6), Some(2), Some(5), Some(8), Some(9), Some(7), Some(3), Some(4), Some(1)),
      Seq(Some(9), Some(3), Some(1), Some(4), Some(2), Some(6), Some(8), Some(5), Some(7)),
      Seq(Some(4), Some(7), Some(8), Some(5), Some(3), Some(1), Some(9), Some(2), Some(6)),
      Seq(Some(1), Some(6), Some(7), Some(2), Some(5), Some(3), Some(4), Some(9), Some(8)),
      Seq(Some(8), Some(5), Some(9), Some(7), Some(6), Some(4), Some(1), Some(3), Some(2)),
      Seq(Some(3), Some(4), Some(2), Some(1), Some(8), Some(9), Some(7), Some(6), Some(5))
    )

    "return the row set" in {
      val r = time("2D Seq row") { () => board2.row(6) }
      r mustEqual Set(1, 2, 3, 4, 8)

      board.row(9) mustEqual Set.empty
      board2.row(9) mustEqual Set(2, 3, 5, 9)
      solved.row(6) mustEqual Set(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    "return the column set" in {
      val r = time("2D Seq col") { () => board.col(4) }
      r mustEqual Set(1)

      board.col(1) mustEqual Set(1, 4, 7)
      board.col(9) mustEqual Set.empty

      board2.col(4) mustEqual Set(4, 9)

      solved.col(2) mustEqual Set(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    "return the sub board set" in {
      val r = time("2D Seq subBoardAt") { () => board.subBoardAt(4, 1) }
      r mustEqual Set(1, 2, 3)

      board.subBoardAt(3, 3) mustEqual Set(1, 2, 3, 4, 5, 6, 7, 8, 9)
      board.subBoardAt(9, 9) mustEqual Set.empty

      board2.subBoardAt(9, 9) mustEqual Set(5, 9)
      board2.subBoardAt(1, 1) mustEqual Set(1, 8)

      solved.subBoardAt(2, 7) mustEqual Set(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    "return the possibilities based on the input data" in {
      val r = time("2DSeq possibilitiesAt") { () => board.possibilitiesAt(4, 1) }
      r mustEqual Set(4, 5, 6, 7, 8, 9)

      board.possibilitiesAt(3, 3) mustEqual Set(9)
      board.possibilitiesAt(9, 9) mustEqual Set(1, 2, 3, 4, 5, 6, 7, 8, 9)

      board2.possibilitiesAt(5, 1) mustEqual Set(1, 5, 9)
      board2.possibilitiesAt(4, 2) mustEqual Set(1, 2, 3, 5, 7, 8)

      solved.possibilitiesAt(3, 7) mustEqual Set(7)
    }

  }
}