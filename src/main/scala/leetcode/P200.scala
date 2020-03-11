package leetcode

object P200 extends App {
  import scala.collection.mutable.Set

  def numIslands(grid: Array[Array[Char]]): Int = {
    if (grid.isEmpty) return 0

    val visited = Array.fill(grid.length)(Array.fill(grid(0).length)(false))

    def collect(y: Int, x: Int): Set[(Int, Int)] = {
      if (x < 0 || y < 0 || y > grid.length-1 || x > grid(0).length-1) return Set()
      if (visited(y)(x)) return Set()

      visited(y)(x) = true

      if (grid(y)(x) == '0') return Set()
      val result: Set[(Int, Int)] = Set()

      result.add((y, x))

      val right = collect(y, x+1)
      val down = collect(y+1, x)
      val left = collect(y, x-1)
      val up = collect(y-1, x)

      result ++ right ++ down ++ left ++ up
    }

    var result = 0
    for(y <- grid.indices) {
      for (x <- grid(0).indices) {
        if (!visited(y)(x)) {

          if(collect(y, x).nonEmpty) {
            result += 1
          }
        }
      }
    }
    result
  }

  println(
    numIslands(
      Array(
        Array('1','1','1','1'),
        Array('1','1','1','0'),
        Array('0','1','0','0'),
        Array('1','0','1','1')
      )
    )
  )
}
