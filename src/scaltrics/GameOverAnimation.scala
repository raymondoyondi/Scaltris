package scaltris

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.Timer

/**
  * Classic Tetris game over animation
  */
class GameOverAnimation(val parent: BoardController) {
  private var rowIndex = Board.Height - 1

  val gameOverLoop = new ActionListener {
    override def actionPerformed(e: ActionEvent) {
      if (parent.gameRunning) {
        animationTimer.stop
        return
      }
      val row = parent.board.board(rowIndex)
      row.indices.foreach {
        i => row(i) = Block.nextBlock
      }
      rowIndex -= 1

      if (rowIndex < 0) {
        animationTimer.stop
      }

      parent.repaint
    }
  }

  private val animationTimer: Timer = new Timer(50, gameOverLoop)

  def restart: Unit = {
    rowIndex = Board.Height - 1
    animationTimer.start
  }
}
