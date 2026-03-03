package scaltris

import java.awt.Color
import java.awt.image.BufferedImage
import java.nio.file.Files
import java.nio.file.Paths
import javax.imageio.ImageIO
import java.io.File
import scala.collection.mutable.HashMap
import scala.swing.Graphics2D
import scala.util.Random

/**
  * Block types and related information.
  */
object Block extends Enumeration {
  type Block = Value
  val T, S, Z, O, I, L, J, EMPTY = Value

  /**
    * @return a random Block that is not EMPTY
    */
  def nextBlock: Block = Block.apply(Random.nextInt(Block.values.size - 1))

  val BlockSize = 32
  private val spriteSheetPath = "img/tetblocks.png"
  private val spriteMap: HashMap[Block.Value, BufferedImage] =
    if (Files.exists(Paths.get(spriteSheetPath))) {
    val spriteSheet = ImageIO.read(new File(spriteSheetPath))
    HashMap(
      Block.values.toSeq.zipWithIndex.map {
        b => b._1 -> {
          val x = b._2 * BlockSize
          if (x + BlockSize > spriteSheet.getWidth) {
            getBlockFallbackImage(b._1)
          } else {
            spriteSheet.getSubimage(x, 0, BlockSize, BlockSize)
          }
        }
      }: _*
    )
  } else {
    new HashMap[Block, BufferedImage]
  }

  def getBlockImage(block: Block): BufferedImage = {
    val maybeImage = spriteMap.get(block)
    if (maybeImage.isEmpty) {
      val fallbackImg = getBlockFallbackImage(block)
      spriteMap.put(block, fallbackImg)
      fallbackImg
    } else {
      maybeImage.get
    }
  }


  private def getBlockFallbackImage(block: Block): BufferedImage = {
    val img = new BufferedImage(BlockSize, BlockSize, BufferedImage.TYPE_INT_ARGB)
    val graphics = img.createGraphics()
    graphics.setPaint(getBlockColor(block))
    graphics.fillRect(0, 0, img.getWidth, img.getHeight)
    img
  }

  private def getBlockColor(block: Block): Color = block match {
    case T => Color.red
    case S => Color.yellow
    case Z => Color.green
    case O => Color.blue
    case I => Color.magenta
    case L => Color.orange
    case J => Color.lightGray
    case EMPTY => Color.black
  }


  def getPositions(block: Block): Array[Array[Tuple2[Int, Int]]] = block match {
    case T => Array(Array((0, 0), (-1, 0), (1, 0), (0, -1)),
                    Array((0, 0), (0, 1), (0, -1), (-1, 0)),
                    Array((0, 0), (1, 0), (-1, 0), (0, 1)),
                    Array((0, 0), (0, -1), (0, 1), (1, 0)))
    case S => Array(Array((0, 0), (1, 0), (0, -1), (-1, -1)),
                    Array((0, 0), (0, 1), (1, 0), (1, -1)),
                    Array((0, 0), (1, 0), (0, -1), (-1, -1)),
                    Array((0, 0), (0, 1), (1, 0), (1, -1)))
    case Z => Array(Array((0, 0), (-1, 0), (0, -1), (1, -1)),
                    Array((0, 0), (0, 1), (-1, 0), (-1, -1)),
                    Array((0, 0), (-1, 0), (0, -1), (1, -1)),
                    Array((0, 0), (0, 1), (-1, 0), (-1, -1)))
    case O => Array(Array((0, 0), (1, 0), (0, -1), (1, -1)),
                    Array((0, 0), (1, 0), (0, -1), (1, -1)),
                    Array((0, 0), (1, 0), (0, -1), (1, -1)),
                    Array((0, 0), (1, 0), (0, -1), (1, -1)))
    case I => Array(Array((0, 0), (-1, 0), (1, 0), (2, 0)),
                    Array((0, 0), (0, -1), (0, 1), (0, 2)),
                    Array((0, 0), (-1, 0), (1, 0), (2, 0)),
                    Array((0, 0), (0, -1), (0, 1), (0, 2)))
    case L => Array(Array((0, 0), (1, 0), (-1, 0), (-1, -1)),
                    Array((0, 0), (0, -1), (0, 1), (-1, 1)),
                    Array((0, 0), (-1, 0), (1, 0), (1, 1)),
                    Array((0, 0), (0, 1), (0, -1), (1, -1)))
    case J => Array(Array((0, 0), (-1, 0), (1, 0), (1, -1)),
                    Array((0, 0), (0, 1), (0, -1), (-1, -1)),
                    Array((0, 0), (1, 0), (-1, 0), (-1, 1)),
                    Array((0, 0), (0, -1), (0, 1), (1, 1)))
  }
}
