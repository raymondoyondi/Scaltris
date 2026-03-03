package scaltris

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.Timer
import scala.swing.Reactor
import scala.swing.event.Key
import scala.swing.event.KeyPressed

class BoardController(val parent: TetrisPanel) extends Reactor {

  var board = new Board
  var currentTetromino = new Tetromino
  var nextTetromino = new Tetromino

  val StartTickInterval = 600

  var score = 0

  private val gameOverAnimation = new GameOverAnimation(this)

  var gameRunning = true
  var gameOver = false

  def getTickInterval(score: Int): Int = StartTickInterval / (Math.sqrt(score/5).toInt + 1)

  def tryMove(tetromino: Tetromino): Unit = {
    if (!gameRunning) return
    if (board.isLegal(tetromino)) {
      currentTetromino = tetromino
    }
    parent.repaint
  }

  def droppedTetromino: Tetromino = {
    var tetromino = currentTetromino
    while (board.isLegal(tetromino.withMoveDown)) {
      tetromino = tetromino.withMoveDown
    }
    tetromino
  }

  def dropTetromino: Unit = {
    if (!gameRunning) return
    currentTetromino = droppedTetromino
    placeTetromino
  }

  def placeTetromino: Unit = {
    board = board.withTetromino(currentTetromino)
    setScore(score + board.clearFullRows)
    currentTetromino = nextTetromino
    nextTetromino = new Tetromino
  }

  private def setScore(newScore: Int): Unit = {
    score = newScore
    tetrisTick.setDelay(getTickInterval(score))
  }

  reactions += {
    case KeyPressed(_, Key.Down, _, _) => tryMove(currentTetromino.withMoveDown)

    case KeyPressed(_, Key.Space, _, _) => dropTetromino

    case KeyPressed(_, Key.Left, _, _) => tryMove(currentTetromino.withMoveLeft)

    case KeyPressed(_, Key.Right, _, _) => tryMove(currentTetromino.withMoveRight)

    case KeyPressed(_, Key.Up, _, _) => tryMove(currentTetromino.withRotation)

    case KeyPressed(_, Key.P, _, _) => togglePause

    case KeyPressed(_, Key.N, _, _) => newGame
  }

  val gameLoop = new ActionListener {
    override def actionPerformed(e: ActionEvent) {
      val newTetromino = currentTetromino.withMoveDown
      if (board.isLegal(newTetromino)) {
        currentTetromino = newTetromino

      } else {
        placeTetromino
        if (!board.isLegal(currentTetromino)) {
          gameOver = true
          gameOverAnimation.restart
          pauseGame
        }
      }
      parent.repaint
    }
  }

  def pauseGame: Unit = {
    gameRunning = false
    tetrisTick.stop
  }

  def resumeGame: Unit = {
    if (!gameOver) {
      gameRunning = true
      tetrisTick.start
    }
  }

  def newGame: Unit = {
    placeTetromino
    board = new Board
    setScore(0)
    gameOver = false
    resumeGame
  }

  def togglePause = {
    if (gameRunning) {
      pauseGame
    } else {
      resumeGame
    }
  }

  def repaint = parent.repaint

  val tetrisTick: Timer = new Timer(getTickInterval(score), gameLoop)

  tetrisTick.start
}
