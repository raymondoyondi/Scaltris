package scaltris

import org.scalatest._
import scaltris._

class BlockTest extends FlatSpec with Matchers {
  it should "have square images" in {
    val tBlock = Block.T
    val tImg = Block.getBlockImage(tBlock)
    tImg.getHeight should be (tImg.getWidth)
  }
}
