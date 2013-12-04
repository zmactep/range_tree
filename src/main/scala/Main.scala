import rtgui.model.RangeTreeModel
import rtgui.panels.{ToolsPane, DrawingPane}
import scalafx.application.JFXApp
import scalafx.scene.layout.BorderPane
import scalafx.scene.Scene

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 26.11.13
 * Time: 16:49
 */
object Main extends JFXApp {
  val WIDTH  = 800
  val HEIGHT = 600

  stage = new JFXApp.PrimaryStage {
    title = "Range Tree GUI"
    minWidth = WIDTH
    minHeight = HEIGHT
    maxWidth = WIDTH
    maxHeight = HEIGHT
  }

  val model   = new RangeTreeModel()
  val drawing = new DrawingPane(stage, model)
  val tools   = new ToolsPane(drawing)

  stage.scene = new Scene {
    root = new BorderPane {
      center = drawing.pane
      right = tools.pane
    }
  }


}
