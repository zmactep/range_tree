package range_tree


import range_tree.node.{NodeUtils, Node}
import range_tree.point.{Point2DUtils, Point2D}
import scala.collection.mutable.ArrayBuffer
import range_tree.fraction.{FractionUtils, Fraction}

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 26.11.13
 * Time: 17:43
 */
class RangeTree(_points : Iterable[Point2D]) {
  private val points    : Array[Point2D]  = _points.toArray.sortWith(_.lessByX(_))
  private val fractions : Array[Fraction] = FractionUtils.makeFractions(rootNode, this.points)

  def getPoints : Iterable[Point2D] = points

  def query2D(p1 : Point2D, p2 : Point2D) : Iterable[Point2D] = {
    val (left_top, right_bottom) = Point2DUtils.generateRect(p1, p2)
    val result : ArrayBuffer[Point2D] = ArrayBuffer.empty[Point2D]

    def goUpDown(node : Node, value : Double, lowest : Int, up : Boolean) : Unit = {
      if (node != null && lowest != -1) {
        val fraction = fractions(node.index)
        val point = points(node.index)
        if ((point.x < value && !up) || (point.x >= value && up)) {
          val start = node.start
          val end = node.end

          if (right_bottom.y <= point.y && point.y < left_top.y) {
            result += point
          }

          goUpDown(if (up) node.lc() else node.rc(), value, if (up) fraction.left(lowest) else fraction.right(lowest), up)
          node.change(start, end)
          go(if (up) node.rc() else node.lc(), if (up) fraction.right(lowest) else fraction.left(lowest))
        }
        else {
          goUpDown(if (up) node.rc() else node.lc(), value, if (up) fraction.right(lowest) else fraction.left(lowest), up)
        }
      }
    }

    def go(node : Node, lowest : Int) : Unit = {
      if (node != null && lowest != -1) {
        val point_array = fractions(node.index).points
        (lowest until point_array.size).foreach(i => {
          if (right_bottom.y <= point_array(i).y && point_array(i).y < left_top.y) {
            result += point_array(i)
          }
        })
      }
    }

    val node = splitNode(left_top.x, right_bottom.x)
    if (node != null) {
      val fraction = fractions(node.index)
      val lowest = Point2DUtils.binarySearch(fraction.points, new Point2D(Double.NegativeInfinity, right_bottom.y),
        (a, b) => a.lessByY(b))
      if (lowest != fraction.points.size) {
        val point = points(node.index)
        if (right_bottom.y <= point.y && point.y < left_top.y) {
          result += point
        }
        val lc = new Node(node.start, node.end).lc()
        val rc = new Node(node.start, node.end).rc()

        goUpDown(rc, right_bottom.x, fraction.right(lowest), up = false)
        goUpDown(lc, left_top.x, fraction.left(lowest), up = true)
      }
    }
    result
  }

  private[this] def splitNode(start : Double, end : Double) : Node = {
    def splitNodeBy(node : Node, start : Double, end : Double) : Node = {
      node match {
        case null => null
        case _    => {
          val point = points(node.index)
          if (point.x >= end) {
            splitNodeBy(node.lc(), start, end)
          }
          else if (point.x < start) {
            splitNodeBy(node.rc(), start, end)
          }
          else {
            node
          }
        }
      }
    }

    splitNodeBy(rootNode, start, end)
  }

  private[this] def rootNode : Node = NodeUtils.makeNode(0, points.length)
}
