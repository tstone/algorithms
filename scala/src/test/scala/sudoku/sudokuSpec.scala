package algorithms.sudoku

import test.support.BaseSpec

class sudokuSpec extends BaseSpec {

  "GameBoard" should {

    /*
        Game Board:

        1  2  3   N  N  N   N  N  N
        4  5  6   1  2  3   N  N  N
        7  8  9   N  N  N   N  N  N

        N  N  N   N  N  N   N  N  N
        N  N  N   N  N  N   N  N  N
        N  N  N   N  N  N   N  N  N

        N  N  N   N  N  N   N  N  N
        N  N  N   N  N  N   N  N  N
        N  N  N   N  N  N   N  N  N

     */


    val board = Seq(
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

    val board2 = Seq(
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

    "return the row set" in {
      board.row(1) mustEqual Set(1, 2, 3)
      board.row(2) mustEqual Set(1, 2, 3, 4, 5, 6)
      board.row(9) mustEqual Set.empty

      board2.row(9) mustEqual Set(2, 3, 5, 9)
    }

    "return the column set" in {
      board.col(4) mustEqual Set(1)
      board.col(1) mustEqual Set(1, 4, 7)
      board.col(9) mustEqual Set.empty

      board2.col(4) mustEqual Set(4, 9)
    }

    "return the sub board location" in {
      board.whichSubBoard(4, 1) mustEqual (2, 1)
      board.whichSubBoard(2, 3) mustEqual (1, 1)
      board.whichSubBoard(5, 5) mustEqual (2, 2)
    }

    "return the sub board set" in {
      board.subBoardAt(4, 1) mustEqual Set(1, 2, 3)
      board.subBoardAt(3, 3) mustEqual Set(1, 2, 3, 4, 5, 6, 7, 8, 9)
      board.subBoardAt(9, 9) mustEqual Set.empty

      board2.subBoardAt(9, 9) mustEqual Set(5, 9)
      board2.subBoardAt(1, 1) mustEqual Set(1, 8)
    }

    "return the possibilities based on the input data" in {
      board.possibilitiesAt(4, 1) mustEqual Set(4, 5, 6, 7, 8, 9)
      board.possibilitiesAt(3, 3) mustEqual Set.empty
      board.possibilitiesAt(9, 9) mustEqual Set(1, 2, 3, 4, 5, 6, 7, 8, 9)

      board2.possibilitiesAt(5, 1) mustEqual Set(1, 5, 9)
      board2.possibilitiesAt(4, 2) mustEqual Set(1, 2, 3, 5, 7, 8)
    }

  }
}