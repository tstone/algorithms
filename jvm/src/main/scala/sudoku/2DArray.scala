package algorithms.sudoku


object Sudoku2DArray {

  // outer sequence = rows, inner sequence = cols
  type GameBoard = Seq[Seq[Option[Int]]]

  implicit class GameBoardOps(board: GameBoard) {

    def row(r: Int) = board(r - 1).flatten.toSet

    def col(c: Int) = board.map { col => col(c - 1)}.flatten.toSet

    def subBoardAt(x: Int, y: Int): Set[Int] = {
      def translate(pos: Int) = (pos - 1) * 3
      val (boardX, boardY) = board.whichSubBoard(x, y)
      val top = translate(boardY)
      val left = translate(boardX)

      Seq(board(top), board(top + 1), board(top + 2))
        .foldLeft(Seq[Option[Int]]()) { case (acc, row) =>
        acc ++ Seq(row(left), row(left + 1), row(left + 2))
      }
        .flatten
        .toSet
    }

    def whichSubBoard(x: Int, y: Int): (Int, Int) =
      (Math.floor((x - 1) / 3).toInt + 1, Math.floor((y - 1) / 3).toInt + 1)

    def possibilitiesAt(x: Int, y: Int): Set[Int] = board(y - 1)(x - 1) match {
      case Some(value) => Set(value)
      case None =>
        Set(1, 2, 3, 4, 5, 6, 7, 8, 9).diff(
          union(
            board.row(y),
            board.col(x),
            board.subBoardAt(x, y)
          )
        )
    }

    def toPrettyString = board.zipWithIndex.map { case (col, x) =>
      col.zipWithIndex.map { case (point, y) =>
        val padding = if (y % 3 == 0) "  " else ""
        val break = if (x % 3 == 0 && y == 0) "\n" else ""
        break + padding + point.getOrElse("/")
      }.mkString(" ")
    }.mkString("\n") + "\n"

    private def union[A](sets: Set[A]*) =
      sets.foldLeft(Set[A]()) { case (acc, set) => acc.union(set)}

  }
}