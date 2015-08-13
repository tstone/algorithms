module Sudoku

export rowset, colset, subboardset, possibilities

rowset(board::Array{Int,2}, rowindex::Int) = Set(board[rowindex, 1:end])
colset(board::Array{Int,2}, colindex::Int) = Set(board[1:end, colindex])

function subboardset(board::Array{Int,2}, x::Int, y::Int)
  xmin = (floor(x/3) * 3) + 1
  xmax = xmin + 2
  ymin = (floor(y/3) * 3) + 1
  ymax = ymin + 2
  Set(board[ymin:ymax, xmin:xmax])
end

function possibilities(board::Array{Int,2}, x::Int, y::Int)
  all = Set(1, 2, 3, 4, 5, 6, 7, 8, 9)
  setdiff(all, union(
    rowset(board, y),
    colset(board, x),
    subboardset(board, x, y)
  ))
end

end
