package rtgui.controls

import rtgui.model.RangeTreeModel
import scalafx.scene.shape.Rectangle
import rtgui.panels.DrawingPane
import scalafx.scene.paint.Color
import scala.collection.mutable.ArrayBuffer

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 04.12.13
 * Time: 22:21
 */
class ModelAdapter(m : RangeTreeModel, d : DrawingPane) {
  val model   = m
  val drawing = d

  var start : (Double, Double) = (0, 0)
  var end   : (Double, Double) = (0, 0)

  def addPoint(x : Double, y : Double) : Unit = {
    val pt = drawing.pointPosition(x, y)
    model.addPoint(pt)

    start = (0, 0)
    end = (0, 0)
  }

  def makeQuery(p1 : (Double, Double), p2 : (Double, Double)) : Unit = {
    val pt1 = drawing.pointPosition(p1._1, p1._2)
    val pt2 = drawing.pointPosition(p2._1, p2._2)
    model.makeQuery(pt1, pt2)
  }

  def getView : Iterable[Rectangle] = {
    val array = ArrayBuffer[Rectangle](getQuery)

    m.getPoints.foreach(point => {
      val coord = drawing.pointVisualPosition(point.x, point.y)
      val radius = 3
      array += new Rectangle {
        x = coord._1 - radius/2
        y = coord._2 - radius/2
        width = radius
        height = radius
        fill = if (!m.getLast.contains(point)) Color.RED else Color.GREEN
      }
    })

    array
  }

  def getQuery : Rectangle = new Rectangle {
    x = math.min(start._1, end._1)
    y = math.min(start._2, end._2)
    width = math.abs(start._1 - end._1)
    height = math.abs(start._2 - end._2)
    fill = Color.YELLOW
  }
}
