package rtgui.panels

import scalafx.stage.Stage
import scalafx.scene.layout.Pane
import scalafx.scene.shape.Rectangle
import rtgui.controls.{ModelAdapter, ToolControls}
import rtgui.model.RangeTreeModel
import javafx.scene.input.MouseEvent
import javafx.event.EventHandler

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 04.12.13
 * Time: 20:53
 */
class DrawingPane(s : Stage, m : RangeTreeModel) {
  val stage    = s
  val width    = 100.0
  val controls = new ToolControls(this)
  val adapter  = new ModelAdapter(m, this)

  lazy val rect = new Rectangle() {
    width  <== stage.width * 0.82
    height <== stage.height
  }

  rect.onMouseMoved = new EventHandler[MouseEvent] {
    def handle(me: MouseEvent): Unit = {
      controls.undateLabel(me.getX, me.getY)
    }
  }

  rect.onMouseClicked = new EventHandler[MouseEvent] {
    def handle(me: MouseEvent): Unit = {
      if (!controls.toggleButton.selected.value) {
        adapter.addPoint(me.getX, me.getY)
        update
      }
    }
  }

  rect.onMousePressed = new EventHandler[MouseEvent] {
    def handle(me: MouseEvent): Unit = {
      if (controls.toggleButton.selected.value) {
        adapter.start = (me.getX, me.getY)
      }
    }
  }

  rect.onMouseReleased = new EventHandler[MouseEvent] {
    def handle(me: MouseEvent): Unit = {
      if (controls.toggleButton.selected.value) {
        adapter.end = (me.getX, me.getY)
        adapter.makeQuery(adapter.start, adapter.end)
        update
      }
    }
  }

  lazy val pane = new Pane {
    content = rect
  }

  def update : Unit = pane.content = List(rect) ++ adapter.getView

  def pointPosition(x : Double, y : Double) : (Double, Double) = {
    val pixelsize = width / pane.width.value
    (x * pixelsize, y * pixelsize)
  }

  def pointVisualPosition(x : Double, y : Double) : (Double, Double) = {
    val pixelsize = width / pane.width.value
    (x / pixelsize, y / pixelsize)
  }
}
