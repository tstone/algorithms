using Compat

# include("sudoku.jl")

tests = [ "sudoku" ]

for t in tests
    include(joinpath(dirname(@__FILE__), "../src/$(t).jl"))
    include("$(t).jl")
end
