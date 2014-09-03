package algorithms

package object sudoku {

  // outer sequence = rows, inner sequence = cols
  type GameBoard = Seq[Seq[Option[Int]]]

  implicit class GameBoardOps(board: GameBoard) {

    def row(c: Int) = board(c - 1).flatten.toSet
    def col(r: Int) = board.map { col => col(r - 1) }.flatten.toSet

    def subBoardAt(x: Int, y: Int): Set[Int] = {
      def translate(pos: Int) = (pos - 1) * 3
      val (boardX, boardY) = board.whichSubBoard(x, y)
      val top = translate(boardY)
      val left = translate(boardX)

      Seq(board(top), board(top + 1), board(top + 2))
        .foldLeft(Seq[Option[Int]]()) { case (acc: Seq[Option[Int]], row: Seq[Option[Int]]) =>
          acc ++ Seq(row(left), row(left + 1), row(left + 2))
        }
        .flatten
        .toSet
    }

    def whichSubBoard(x: Int, y: Int): (Int, Int) =
      (Math.floor((x - 1) / 3).toInt + 1, Math.floor((y - 1) / 3).toInt + 1)

    def possibilitiesAt(x: Int, y: Int): Set[Int] =
      Set(1, 2, 3, 4, 5, 6, 7, 8, 9)
        .diff(board.row(y))
        .diff(board.col(x))
        .diff(board.subBoardAt(x, y))
  }

}
