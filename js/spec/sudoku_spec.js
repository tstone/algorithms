var sudoku = require('../sudoku');

describe("sudoku", function(){

  var board = [
    [8, 0, 0, 4, 0, 6, 0, 0, 7],
    [0, 0, 0, 0, 0, 0, 4, 0, 0],
    [0, 1, 0, 0, 0, 0, 6, 5, 0],
    [5, 0, 9, 0, 3, 0, 7, 8, 0],
    [0, 0, 0, 0, 7, 0, 0, 0, 0],
    [0, 4, 8, 0, 2, 0, 1, 0, 3],
    [0, 5, 2, 0, 0, 0, 0, 9, 0],
    [0, 0, 1, 0, 0, 0, 0, 0, 0],
    [3, 0, 0, 9, 0, 2, 0, 0, 5]
  ];

  it("should return the row values", function() {
    var row = sudoku.row(board, 3);
    expect(row).toEqual([1, 6, 5]);
  });

  it("should return the column values", function() {
    var col = sudoku.col(board, 4);
    expect(col).toEqual([4, 9]);
  });

  it("should return the correct sub board from a given pair", function(){
    var subboard = sudoku.whichSubboard(4, 1);
    expect(subboard.x).toEqual(2);
    expect(subboard.y).toEqual(1);
  });

  it("should return the subboard", function(){
    var subboard = sudoku.subboard(board, 4, 1);
    expect(subboard).toEqual([4, 6]);
  });

  it("should return possibilities", function(){
    var possibilities = sudoku.possibilitiesFor(board, 4, 5);
    expect(possibilities).toEqual([1, 5, 6, 8]);
  });

  it("performance", function(){
    var start = new Date().getMilliseconds();

    for (i = 0; i < 1000; ++i) { sudoku.possibilitiesFor(board, 4, 5); }
    var t1 = new Date().getMilliseconds();

    for (i = 0; i < 9000; ++i) { sudoku.possibilitiesFor(board, 4, 5); }
    var t2 = new Date().getMilliseconds();

    for (i = 0; i < 90000; ++i) { sudoku.possibilitiesFor(board, 4, 5); }
    var t3 = new Date().getMilliseconds();

    console.log("\n\nPerf:\n");
    console.log("1k runs", (t1 - start) / 1000);
    console.log("10k runs", (t2 - start) / 1000);
    console.log("100k runs", (t3 - start) / 1000);
  });

});
