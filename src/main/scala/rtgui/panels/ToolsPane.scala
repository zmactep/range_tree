package rtgui.panels

import scalafx.scene.layout.{VBox, Pane}
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color
import scalafx.stage.Stage
import scalafx.scene.control.{Button, ToggleButton}
import scalafx.geometry.{Pos, Insets}
import rtgui.controls.ToolControls

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 04.12.13
 * Time: 1:20
 */
class ToolsPane(d : DrawingPane) {
  val stage    = d.stage
  val controls = d.controls

  lazy val pane = new VBox {
    padding = Insets(10, 10, 10, 10)
    alignment = Pos.TOP_CENTER
    content = List(
      controls.toggleButton,
      controls.coordinateLabel
    )
  }
}