package rtgui.controls

import scalafx.scene.control.{Label, ToggleButton}
import rtgui.panels.DrawingPane

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 04.12.13
 * Time: 20:17
 */
class ToolControls(d : DrawingPane) {
  val drawing  = d

  lazy val toggleButton = new ToggleButton {
    text = "Draw rectangles"
  }

  lazy val coordinateLabel = new Label {
    text = "None"
  }

  def undateLabel(x : Double, y : Double) : Unit = {
    val (w, h) = drawing.pointPosition(x, y)
    coordinateLabel.text = "(%.2f, %.2f)".format(w, h)
  }
}
