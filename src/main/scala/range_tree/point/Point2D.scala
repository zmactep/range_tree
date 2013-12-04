package range_tree.point

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 26.11.13
 * Time: 17:54
 */
class Point2D(_x : Double, _y : Double) extends Ordered[Point2D] {
  val x = _x
  val y = _y

  def compare(b : Point2D) : Int = {
    x.compare(b.x) match {
      case 0 => y.compare(b.y)
      case r => r
    }
  }

  def lessByX(b : Point2D) : Boolean = this < b

  def lessByY(b : Point2D) : Boolean = {
    y < b.y match {
      case false => x < b.x
      case true  => true
    }
  }
}
