package range_tree.fraction

import range_tree.node.Node
import range_tree.point.Point2D

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 28.11.13
 * Time: 22:32
 */
object FractionUtils {
  def makeFractions(root : Node, points : Array[Point2D]) : Array[Fraction] = {
    val fractions = Array.fill[Fraction](points.size)(null)

    def build(node : Node) : Unit = {
      val index : Int = node.index
      val (start, end) : (Int, Int) = (node.start, node.end)

      if (node.lc() != null) {
        fractions(node.index) = new Fraction(points.slice(node.start, node.end))
        link(fractions(index), fractions(node.index), fractions(index).left)
        build(node)
      }
      else {
        fractions(index).fillTrashLeft()
      }

      node.change(start, end)

      if (node.rc() != null) {
        fractions(node.index) = new Fraction(points.slice(node.start, node.end))
        link(fractions(index), fractions(node.index), fractions(index).right)
        build(node)
      }
      else {
        fractions(index).fillTrashRight()
      }
    }

    def link(fraction : Fraction, child : Fraction, parents : Array[Int]) : Unit = {
      var (p, c) : (Int, Int) = (0, 0)
      while (p < parents.size && c < child.points.size) {
        if (child.points(c).y >= fraction.points(p).y) {
          parents(p) = c
          p += 1
        }
        else {
          c += 1
        }
      }
      (p until parents.size).foreach(i => parents(i) = -1)
    }

    fractions(root.index) = new Fraction(points.slice(0, points.size))
    build(root)
    fractions
  }
}
