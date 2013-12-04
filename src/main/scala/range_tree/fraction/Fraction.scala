package range_tree.fraction

import range_tree.point.Point2D

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 26.11.13
 * Time: 18:23
 */
class Fraction(_points : Array[Point2D]) {
  var points : Array[Point2D] = _points.sortWith(_.lessByY(_))
  var right  : Array[Int]     = Array.fill[Int](points.length)(0)
  var left   : Array[Int]     = Array.fill[Int](points.length)(0)

  def fillTrashLeft() : Unit = {
    (0 until left.size).foreach(i => left(i) = -1)
  }

  def fillTrashRight() : Unit = {
    (0 until right.size).foreach(i => right(i) = -1)
  }
}
