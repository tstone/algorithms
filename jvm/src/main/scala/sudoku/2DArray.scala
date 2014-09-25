package algorithms.sudoku


object Sudoku2DSeq {

  // outer sequence = rows, inner sequence = cols
  type GameBoard = Seq[Seq[Option[Int]]]

  implicit class GameBoardOps(board: GameBoard) {

    def row(r: Int) = board(r - 1).flatten.toSet

    def col(c: Int): Set[Int] = board.foldLeft(Set[Int]()) { case (acc, col) =>
      col(c - 1) match {
        case Some(value)  => acc + value
        case None         => acc
      }
    }

    def subBoardAt(x: Int, y: Int): Set[Int] = {
      val top = whichSubBoard(y) * 3
      val left = whichSubBoard(x) * 3

      board.slice(top, top + 3).foldLeft(Set[Int]()) { case (acc, row) =>
        acc ++ row.slice(left, left + 3).foldLeft(Set[Int]()) { case (acc2, col) =>
          col match {
            case Some(value)  => acc2 + value
            case None         => acc2
          }
        }
      }
    }

    private def whichSubBoard(i: Int): Int = Math.floor((i - 1) / 3).toInt

    def possibilitiesAt(x: Int, y: Int): Set[Int] = board(y - 1)(x - 1) match {
      case Some(value) => Set(value)
      case None =>
        Set(1, 2, 3, 4, 5, 6, 7, 8, 9).diff(
          board.row(y) ++ board.col(x) ++ board.subBoardAt(x, y)
        )
    }

    def toPrettyString = board.zipWithIndex.map { case (col, x) =>
      col.zipWithIndex.map { case (point, y) =>
        val padding = if (y % 3 == 0) "  " else ""
        val break = if (x % 3 == 0 && y == 0) "\n" else ""
        break + padding + point.getOrElse("/")
      }.mkString(" ")
    }.mkString("\n") + "\n"

  }
}