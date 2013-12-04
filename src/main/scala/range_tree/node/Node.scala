package range_tree.node

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 26.11.13
 * Time: 18:24
 */
class Node(_start : Int, _end : Int) {
  var start : Int = _start
  var end   : Int = _end

  def index : Int = start + (end - start + 1) / 2 - 1

  def change(start : Int, end : Int) : Node = {
    start == end match {
      case true  => null
      case false => {
        this.start = start
        this.end = end
        this
      }
    }
  }

  def rc() : Node = {
    end - start match {
      case 1 => null
      case _ => change(index + 1, end)
    }
  }

  def lc() : Node = {
    end - start match {
      case 1 => null
      case _ => change(start, index)
    }
  }
}