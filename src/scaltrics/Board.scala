package scaltris

object Board {
  val Width = 10
  val Height = 24
  val EmptyBoardRow = Array.fill[Block.Value](Width)(Block.EMPTY)

  def emptyBoard: Array[Array[Block.Value]] = {
    Array.fill[Array[Block.Value]](Height)(EmptyBoardRow)
  }
}

class Board(var board: Array[Array[Block.Value]]) {

  def this() = this(Board.emptyBoard)

  def withTetromino(tetromino: Tetromino): Board = {
    val boardCopy = clone
    val positions = tetromino.getBlockPositions
    positions.foreach {
      position => boardCopy.board(position._2)(position._1) = tetromino.block
    }

    boardCopy
  }

  def overlap(tetromino: Tetromino): Boolean = {
    val positions = tetromino.getBlockPositions
    !positions.forall {
      position => board(position._2)(position._1).equals(Block.EMPTY)
    }
  }

  private def legalX = (0 until Board.Width)
  private def legalY = (0 until Board.Height)

  def isLegal(tetromino: Tetromino): Boolean = {
    val positions = tetromino.getBlockPositions
    positions.forall {
      position => legalX.contains(position._1) && legalY.contains(position._2)
    } && !overlap(tetromino)
  }

  def clearFullRows: Int = {
    val clearedBoard = board.filter(_.contains(Block.EMPTY))
    val clearedRows = Board.Height - clearedBoard.size
    board = Array.fill[Array[Block.Value]](clearedRows)(Board.EmptyBoardRow) ++ clearedBoard
    clearedRows
  }

  override def clone: Board = new Board(board.map(_.clone))
}
