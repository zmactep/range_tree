package range_tree.node

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 26.11.13
 * Time: 19:11
 */
object NodeUtils {
  def makeNode(start : Int, end : Int) : Node = {
    start == end match {
      case true  => null
      case false => new Node(start, end)
    }
  }
}
