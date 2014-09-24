package algorithms.sudoku;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


class Sudoku2DArrayJ {

    private int[][] m_board;

    public Sudoku2DArrayJ(int[][] board) {
        m_board = board;
    }

    public Set<Integer> getRow(int rowIndex) {
        Set<Integer> set = new HashSet<Integer>();
        int[] row = m_board[rowIndex - 1];

        for(int c = 0; c < row.length; c++) {
            if (row[c] != 0)
                set.add(row[c]);
        }

        return set;
    }

    public Set<Integer> getColumn(int colIndex) {
        Set<Integer> set = new HashSet<Integer>();
        int col = colIndex - 1;

        for(int r = 0; r < m_board.length; r++) {
            if (m_board[r][col] != 0)
                set.add(m_board[r][col]);
        }

        return set;
    }

    private int translate(int i) {
        return (i - 1) * 3;
    }

    public Set<Integer> getSubBoardAt(Point at) {
        Point loc = whichSubboard(at.x, at.y);
        int top = translate(loc.y);
        int topMax = top + 3;
        int left = translate(loc.x);
        int leftMax = left + 3;

        Set<Integer> set = new HashSet<Integer>();
        for(int r = top; r < topMax; r++) {
            for(int c = left; c < leftMax; c++) {
                int val = m_board[r][c];
                if (val != 0)
                    set.add(val);
            }
        }

        return set;
    }

    public Set<Integer> getPossibilitiesAt(Point at) {
        Set<Integer> set = new HashSet<Integer>();
        int currentVal = m_board[at.y - 1][at.x - 1];

        if (currentVal != 0) {
            set.add(currentVal);
            return set;
        }
        else {
            Set<Integer> rowColSubBoard = new HashSet<Integer>();
            rowColSubBoard.addAll(getRow(at.y));
            rowColSubBoard.addAll(getColumn(at.x));
            rowColSubBoard.addAll(getSubBoardAt(at));

            Set<Integer> remaining = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            remaining.removeAll(rowColSubBoard);

            return remaining;
        }
    }

    public static Point whichSubboard(int x, int y) {
        int boardX = (int)Math.floor((x - 1) / 3) + 1;
        int boardY = (int)Math.floor((y - 1) / 3) + 1;
        return new Point(boardX, boardY);
    }
}