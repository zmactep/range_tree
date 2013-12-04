package range_tree.point

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 26.11.13
 * Time: 20:58
 */
object Point2DUtils {
  def binarySearch(array : Array[Point2D], v : Point2D, less : (Point2D, Point2D) => Boolean) : Int = {
    var (left, right) = (0, array.size - 1)
    while (left <= right) {
      val mid = left + (right - left) / 2
      if (right - left == 1) {
        return right
      }
      else {
        if (less(array(mid), v)) {
          left = mid + 1
        }
        else {
          right = mid - 1
        }
      }
    }
    0
  }

  def generateRect(p1 : Point2D, p2 : Point2D) : (Point2D, Point2D) = {
    val xs = Array(p1.x, p2.x).sorted
    val ys = Array(p1.y, p2.y).sorted

    (new Point2D(xs(0), ys(1)), new Point2D(xs(1), ys(0)))
  }
}
