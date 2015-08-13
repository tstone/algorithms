module SudokuTests

using Sudoku
using FactCheck

game = [ 8 0 0  4 0 6  0 0 7;
         0 0 0  0 0 0  4 0 0;
         0 1 0  0 0 0  6 5 0;

         5 0 9  0 3 0  7 8 0;
         0 0 0  0 7 0  0 0 0;
         0 4 8  0 2 0  1 0 3;

         0 5 2  0 0 0  0 9 0;
         0 0 1  0 0 0  0 0 0;
         3 0 0  9 0 2  0 0 5 ]

facts("rowset") do
  @fact rowset(game, 2) --> Set(0, 4)
  @fact rowset(game, 4) --> Set(0, 3, 5, 7, 8, 9)
end

facts("colset") do
  @fact colset(game, 2) --> Set(0, 1, 4, 5)
  @fact colset(game, 5) --> Set(0, 2, 3, 7)
end

facts("subboardset") do
  @fact subboardset(game, 5, 5) --> Set(0, 2, 3, 7)
  @fact subboardset(game, 7, 1) --> Set(0, 4, 5, 6, 7)
end

facts("possibilities") do
  @fact possibilities(game, 1, 5) --> Set(1, 2, 6)
  @fact possibilities(game, 4, 8) --> Set(3, 5, 6, 7, 8)
end

end
