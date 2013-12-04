package rtgui.model

import scala.collection.mutable.ArrayBuffer
import range_tree.point.Point2D
import range_tree.RangeTree
import scala.collection.immutable.TreeSet

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 04.12.13
 * Time: 21:43
 */
class RangeTreeModel {
  private var rangeTree : RangeTree = null

  private val points  = ArrayBuffer.empty[Point2D]
  private var last    = TreeSet.empty[Point2D]
  private var changed = true

  def makeTree() : Unit = {
    rangeTree = new RangeTree(points)
    changed = false
  }

  def addPoint(p : (Double, Double)) : Unit = {
    points += new Point2D(p._1, p._2)
    changed = true
  }

  def makeQuery(p1 : (Double, Double), p2 : (Double, Double)) : Unit = {
    if (changed) {
      makeTree()
    }
    last = TreeSet[Point2D](rangeTree.query2D(new Point2D(p1._1, p1._2), new Point2D(p2._1, p2._2)).toSeq:_*)
  }

  def getPoints : Iterable[Point2D] = points

  def getLast : TreeSet[Point2D] = last

  def isChanged : Boolean = changed
}
