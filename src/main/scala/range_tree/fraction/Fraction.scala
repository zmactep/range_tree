package range_tree.fraction

import range_tree.point.Point2D

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 26.11.13
 * Time: 18:23
 */
class Fraction {
  var points : Array[Point2D] = null
  var right  : Array[Int]     = null
  var left   : Array[Int]     = null

  def this(points : Array[Point2D]) = {
    this()
    this.points = points.sortWith(_.lessByY(_))
    this.right = Array.fill[Int](points.length)(0)
    this.left = Array.fill[Int](points.length)(0)
  }

  def fillTrashLeft() : Unit = {
    (0 until left.size).foreach(i => left(i) = -1)
  }

  def fillTrashRight() : Unit = {
    (0 until right.size).foreach(i => right(i) = -1)
  }
}
