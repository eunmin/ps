package leetcode

object P33 extends App {
  def search(nums: Array[Int], target: Int): Int = {
    var start = 0
    var end = nums.length - 1
    while (start <= end) {
      val center = (start + end) / 2
      val low = nums(start)
      val mid = nums(center)
      val high = nums(end)

      if (low == target) return start
      if (high == target) return end
      if (mid == target) return center

      if (low < mid && mid < high) {
        if (target > mid) { // right
          start = center + 1
        } else { // left
          end = center - 1
        }
      } else if (low > mid && mid < high) {
        if (target > mid && target < high) { // right
          start = center + 1
        } else { // left
          end = center - 1
        }

      } else if (low < mid && mid > high) {
        if (target < mid && target >low) { // left
          end = center - 1
        } else { // right
          start = center + 1
        }
      } else {
        start = center + 1
      }
    }
    -1
  }

  println(search(Array(3,4,5,6,0,1,2), 0))

}
