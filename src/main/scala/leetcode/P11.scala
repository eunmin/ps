package leetcode

object P11 extends App {
  def maxArea(height: Array[Int]): Int = {
    val n = height.length
    var max = 0
    for (i <- height.indices) {
      val x = height(i)
      val posibleMax = x * (n - i - 1)
      if (posibleMax > max) {
        for (j <- i until n) {
          val y = height(j)
          val area = Math.min(x, y) * Math.abs(i - j)
          if (area > max) {
            max = area
          }
        }
      }
    }
    max
  }
}
