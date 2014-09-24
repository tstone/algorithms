package algorithms.sudoku

import java.awt.Point
import test.support.BaseSpec
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._


class Sudoku2DArrayJSpec extends BaseSpec {


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


    val board = Array(
      Array(1, 2, 3, 0, 0, 0, 0, 0, 0),
      Array(4, 5, 6, 1, 2, 3, 0, 0, 0),
      Array(7, 8, 9, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 0, 0)
    )

    // An actual sudoku board I found out on the internet
    // http://news.nd.edu/assets/80986/sudoku300.jpg

    val board2 = Array(
      Array(8, 0, 0, 4, 0, 6, 0, 0, 7),
      Array(0, 0, 0, 0, 0, 0, 4, 0, 0),
      Array(0, 1, 0, 0, 0, 0, 6, 5, 0),
      Array(5, 0, 9, 0, 3, 0, 7, 8, 0),
      Array(0, 0, 0, 0, 7, 0, 0, 0, 0),
      Array(0, 4, 8, 0, 2, 0, 1, 0, 3),
      Array(0, 5, 2, 0, 0, 0, 0, 9, 0),
      Array(0, 0, 1, 0, 0, 0, 0, 0, 0),
      Array(3, 0, 0, 9, 0, 2, 0, 0, 5)
    )

    "return the row set" in {
      val subject = new Sudoku2DArrayJ(board2)
      val r = time("2D Array row (Java)") { () => subject.getRow(6) }
      r mustEqual Set(1, 2, 3, 4, 8).asJava
    }

    "return the column set" in {
      val subject = new Sudoku2DArrayJ(board)
      val r = time("2D Array col (Java)") { () => subject.getColumn(1) }
      r mustEqual Set(1, 4, 7).asJava
    }

    "return the sub board location" in {
      Sudoku2DArrayJ.whichSubboard(4, 1) mustEqual new Point(2, 1)
      Sudoku2DArrayJ.whichSubboard(2, 3) mustEqual new Point(1, 1)
      Sudoku2DArrayJ.whichSubboard(5, 5) mustEqual new Point(2, 2)
    }

    "return the sub board set" in {
      val subject = new Sudoku2DArrayJ(board)
      val p = new Point(4, 1)
      val r = time("2D Array subboard (Java)") { () => subject.getSubBoardAt(p) }
      r mustEqual Set(1, 2, 3).asJava
    }

    "return the possibilities based on the input data" in {
      val subject = new Sudoku2DArrayJ(board)
      val p = new Point(4, 1)
      val r = time("2D Array possibilties (Java)") { () => subject.getPossibilitiesAt(p) }
      r mustEqual Set(4, 5, 6, 7, 8, 9).asJava
    }
  }
}