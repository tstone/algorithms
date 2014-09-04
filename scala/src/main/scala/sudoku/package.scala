package algorithms


package object sudoku {

  // outer sequence = rows, inner sequence = cols
  type GameBoard = Seq[Seq[Option[Int]]]

  implicit class GameBoardOps(board: GameBoard) {

    def row(r: Int) = board(r - 1).flatten.toSet
    def col(c: Int) = board.map { col => col(c - 1) }.flatten.toSet

    def point(x: Int, y: Int): Option[Int] =
      board(y - 1)(x - 1)

    def point(x: Int, y: Int, value: Option[Int]): GameBoard =
      board.updated(y - 1, board(y - 1).updated(x - 1, value))

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

    def possibilitiesAt(x: Int, y: Int): Set[Int] = board(y - 1)(x - 1) match {
      case Some(value)  => Set(value)
      case None         =>
        Set (1, 2, 3, 4, 5, 6, 7, 8, 9).diff (
          union (
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

    def isSolved: Boolean =
      board.forall(_.forall(_.isDefined))

    def firstUnsolvedPoint: (Int, Int) = {
      val (y, x) = board.zipWithIndex.map { case (row, r) =>
        row.zipWithIndex.collectFirst { case (None, c) => (r, c)}
      }.flatten.head
      (x + 1, y + 1)
    }

    def solve: Option[GameBoard] = {
      // pick off the low-hanging fruit by plugging in points
      // for which there is only one possiblity
      val newBoard = singlePossibilties
      if (newBoard.isSolved) Some(newBoard)
      else {
        // Start with the first unsolved point and exhaustivley
        // attempt all possibitlies for remaining unsolved points
        val (x, y) = firstUnsolvedPoint
        multiPossibilitiesFor(x, y, possibilitiesAt(x, y).toList)
      }
    }

    protected def singlePossibilties: GameBoard = {
      val (newBoard, totalChanged) = board.zipWithIndex.foldLeft((board, 0)) { case ((b, c), (col, y)) =>
        col.zipWithIndex.filter(_._1.isEmpty).foldLeft((b, c)) { case ((currentBoard, changed), (_, x)) =>
          val ps = currentBoard.possibilitiesAt(x + 1, y + 1)
          if (ps.size == 1) (currentBoard.point(x + 1, y + 1, Some(ps.toSeq(0))), changed + 1)
          else (currentBoard, changed)
        }
      }

      if (newBoard.isSolved) newBoard
      else if (totalChanged > 0) newBoard.singlePossibilties
      else newBoard
    }

    protected def multiPossibilitiesFor(x: Int, y: Int, possibilties: Seq[Int]): Option[GameBoard] = possibilties match {
      case Nil          => None
      case head :: tail =>
        val guessBoard = board.point(x, y, Some(head))
        guessBoard.solve match {
          case None     => multiPossibilitiesFor(x, y, tail)
          case solution => solution
        }
    }

  }

  private def union[A](sets: Set[A]*) =
    sets.foldLeft(Set[A]()) { case (acc, set) => acc.union(set) }

}