package algorithms

package object sudoku {

  // outer sequence = rows, inner sequence = cols
  type GameBoard = Seq[Seq[Option[Int]]]

  implicit class GameBoardOps(board: GameBoard) {

    def row(c: Int) = board(c - 1).flatten.toSet
    def col(r: Int) = board.map { col => col(r - 1) }.flatten.toSet

    def subBoardAt(x: Int, y: Int): Set[Int] = {
      // Determine the sub-board position (3x3 grid)
      val subBoardMasterX = Math.floor(x / 3).toInt + 1
      val subBoardMasterY = Math.floor(y / 3).toInt + 1
      // Translate the sub-board position into the
      // top left coordinate relative to the entire board
      def masterCoordToOverallCoord(pos: Int) = (pos - 1) * 3
      val subBoardOverallX = masterCoordToOverallCoord(subBoardMasterX)
      val subBoardOverallY = masterCoordToOverallCoord(subBoardMasterY)
      // Extract rows, then extract cols building a new set out of their contents
      Seq(board(subBoardOverallX), board(subBoardOverallX + 1), board(subBoardOverallX + 2))
        .foldLeft(Seq[Option[Int]]()) { case (acc: Seq[Option[Int]], row: Seq[Option[Int]]) =>
          acc ++ Seq(row(subBoardOverallY), row(subBoardOverallY + 1), row(subBoardOverallY + 2))
        }
        .flatten
        .toSet
    }

    def possibilitiesAt(x: Int, y: Int): Set[Int] =
      Set(1, 2, 3, 4, 5, 6, 7, 8, 9)
        .diff(board.row(y))
        .diff(board.col(x))
        .diff(board.subBoardAt(x, y))
  }

}
