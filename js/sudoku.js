
module.exports = {

  row: function(board, r) {
    return board[r - 1].reduce(function(acc, item) {
      if (item != 0) { acc.push(item); }
      return acc;
    }, []);
  },

  col: function(board, c) {
    var realC = c - 1;
    return board.reduce(function(acc, row){
      if (row[realC] != 0) { acc.push(row[realC]); }
      return acc;
    }, []);
  },

  subboard: function(board, x, y) {
    var translate = function(i) { return (i - 1) * 3; };

    var loc = this.whichSubboard(x, y);
    var top = translate(loc.y);
    var topMax = top + 3;
    var left = translate(loc.x);
    var leftMax = left + 3;

    var set = [];
    for(var r = top; r < topMax; r++) {
      for(var c = left; c < leftMax; c++) {
        var val = board[r][c];
        if (val != 0) { set.push(val); }
      }
    }

    return set;
  },

  possibilitiesFor: function(board, x, y) {
    var all = [1, 2, 3, 4, 5, 6, 7, 8, 9];

    var row = this.row(board, y);
    var remaining = all.reduce(this.boardContains(row), []);

    if (remaining.length > 0) {
      var col = this.col(board, x);
      remaining = remaining.reduce(this.boardContains(col), []);

      if (remaining.length > 0) {
        var subboard = this.subboard(board, x, y);
        remaining = remaining.reduce(this.boardContains(subboard), []);
        return remaining;
      }
    }

    return remaining;
  },

  boardContains: function(values) {
    return function(acc, item) {
      if (values.indexOf(item) == -1) { acc.push(item); }
      return acc;
    }
  },

  whichSubboard: function(x, y) {
    return {
      x: Math.floor((x - 1) / 3) + 1,
      y: Math.floor((y - 1) / 3) + 1
    };
  }

}
