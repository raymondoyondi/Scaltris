package scaltris

import scala.swing.Action
import scala.swing.Dialog
import scala.swing.Dimension
import scala.swing.MainFrame
import scala.swing.Menu
import scala.swing.MenuBar
import scala.swing.MenuItem
import scala.swing.Separator
import scala.swing.SimpleSwingApplication

object Scaltris extends SimpleSwingApplication {
  val boardPanel = new TetrisPanel

  def top = new MainFrame {
    title = "Scaltris"

    contents = boardPanel

    val HelpText = """Left: Move left
                     |Right: Move right
                     |Up: Rotate
                     |Down: Move down
                     |Space: Drop all the way down
                     |P: Toggle pause
                     |N: New game""".stripMargin

    def showHelp: Unit = {
      boardPanel.controller.pauseGame
      Dialog.showMessage(boardPanel, HelpText, "Scaltris help", Dialog.Message.Plain)
      boardPanel.controller.resumeGame
    }

    menuBar = new MenuBar {
      contents += new Menu("Game") {
        contents += new MenuItem(Action("New game") { boardPanel.controller.newGame })
        contents += new MenuItem(Action("Pause") { boardPanel.controller.togglePause })
        contents += new MenuItem(Action("Help") { showHelp })
        contents += new Separator
        contents += new MenuItem(Action("Exit") { sys.exit(0) })
      }
    }

    pack

    boardPanel.requestFocus
  }
}
