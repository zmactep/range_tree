import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import range_tree.point.{Point2DUtils, Point2D}
import range_tree.RangeTree
import scala.collection.immutable.TreeSet
import scala.util.Random

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 28.11.13
 * Time: 23:50
 */
class RangeTreeTest extends FlatSpec with ShouldMatchers {
  val sz   : Int    = 100
  val maxX : Double = 100
  val maxY : Double = 100

  "Range Tree" should "locate points right" in {
    val array = Array.fill[Point2D](sz)(null)

    (1 to 10).foreach(_ => {
      (0 until sz).foreach(i => array(i) = nextPoint)
      val tree = new RangeTree(array)

      (1 to 50).foreach(_ => {
        val (p1, p2) = (nextPoint, nextPoint)
        val in = tree.query2D(p1, p2)
        val check = inRect((p1, p2))_

        in.forall(point => check(point)) should be (true)
      })
    })
  }

  def nextPoint : Point2D = new Point2D(Random.nextDouble() * maxX, Random.nextDouble() * maxY)

  def inRect(rect : (Point2D, Point2D))(point : Point2D) : Boolean = {
    val (lt, rb) = Point2DUtils.generateRect(rect._1, rect._2)
    val b = lt.x <= point.x && point.x < rb.x && rb.y <= point.y && point.y < lt.y
//    if (!b) {
//      println((lt.x, lt.y), (rb.x, rb.y))
//      println((point.x, point.y))
//    }
    b
  }
}
