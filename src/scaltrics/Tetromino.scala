package scaltris

import scala.util.Random

class Tetromino(val block: Block.Value,
                var position: Tuple2[Int, Int] = (Board.Width / 2, 1),
                var orientation: Int = 0) {

  /**
    * Return a random Tetromino
    */
  def this() = {
    this(Block.nextBlock)
  }

  def getBlockPositions: Array[(Int, Int)] = {
    Block.getPositions(block)(orientation).map {
      blockPosition => (blockPosition._1 + position._1, blockPosition._2 + position._2)
    }
  }

  def withRotation: Tetromino = {
    copy(orientation = (orientation + 1) % Block.getPositions(block).size)
  }

  def withMoveLeft: Tetromino = {
    copy(position = (position._1 - 1, position._2))
  }

  def withMoveRight: Tetromino = {
    copy(position = (position._1 + 1, position._2))
  }

  def withMoveDown: Tetromino = {
    copy(position = (position._1, position._2 + 1))
  }

  def copy(block: Block.Value = block,
           position: Tuple2[Int, Int] = position,
           orientation: Int = orientation): Tetromino = new Tetromino(block,
                                                                      position,
                                                                      orientation)
}
