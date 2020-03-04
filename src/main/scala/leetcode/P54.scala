package leetcode

object P54 extends App {

  def spiralOrder(matrix: Array[Array[Int]]): List[Int] = {
    object Direction extends Enumeration { val Right, Down, Left, Up = Value }
    val m = matrix.length
    val n = if (m > 0) matrix(0).length else 0
    var result = List.empty[Int]
    var direction = Direction.Right
    val history = Array.fill(m)(Array.fill(n)(false))
    def isBlocked(y: Int, x: Int): Boolean = (y > m - 1) || (x > n - 1) || (y < 0) || (x < 0) || history(y)(x)
    def visit(y: Int, x: Int): Unit = history(y)(x) = true
    var x, y = 0
    for (i <- 0 until m * n) {
      visit(y, x)
      result = result :+ matrix(y)(x)
      direction match {
        case Direction.Right =>
          if(isBlocked(y, x + 1)) {
            y = y + 1
            direction = Direction.Down
          } else { x = x + 1 }
        case Direction.Down =>
          if(isBlocked(y + 1, x)) {
            x = x - 1
            direction = Direction.Left
          } else { y = y + 1 }
        case Direction.Left =>
          if(isBlocked(y, x - 1)) {
            y = y - 1
            direction = Direction.Up
          } else { x = x - 1}
        case Direction.Up =>
          if(isBlocked(y - 1, x)) {
            x = x + 1
            direction = Direction.Right
          } else { y = y - 1 }
      }
    }
    result
  }

  println(spiralOrder(Array(
    Array(2),
    Array(3),
    Array(4),
    Array(2)
  )))
}
