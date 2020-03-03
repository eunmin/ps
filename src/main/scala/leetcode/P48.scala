package leetcode

object P48 extends App {
  def rotate(matrix: Array[Array[Int]]): Unit = {
    val n = matrix.length
    for (y <- 0 until n/2) {
      for (x <- y until n - 1 - y) {
        var curY = y
        var curX = x
        var tmp = matrix(curY)(curX)
        1 to 4 foreach( j => {
          val targetY = curX
          val targetX = n - 1 - curY
          val targetValue = matrix(targetY)(targetX)
          matrix(targetY)(targetX) = tmp
          tmp = targetValue
          curY = targetY
          curX = targetX
        })
      }
    }
  }

  var input = Array(
    Array(5,1,9,11),
    Array(2,4,8,10),
    Array(13,3,6,7),
    Array(15,14,12,16)
  )

  def printMatrix(matrix: Array[Array[Int]]): Unit = {
    matrix.foreach( y => {
      y.foreach(x =>
        print(s"$x ")
      )
      println("")
    })
  }

  rotate(input)

  printMatrix(input)
}
