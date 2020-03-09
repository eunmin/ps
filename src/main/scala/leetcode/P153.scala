package leetcode

object P153 extends App {
  def findMin(nums: Array[Int]): Int = {
    if (nums.isEmpty) throw new IllegalStateException("nums is empty")
    var start = 0
    var end = nums.length - 1
    while (start < end) {
      if (end - start == 1) return Math.min(nums(start), nums(end))
      val mid = (start+end)/2
      if (nums(start) < nums(mid) && nums(mid) < nums(end)) { // left
        end = mid
      } else if (nums(start) > nums(mid) && nums(mid) < nums(end)) { // left
        end = mid
      } else if (nums(start) < nums(mid) && nums(mid) > nums(end)) { // right
        start = mid
      } else {
        throw new IllegalStateException(s"nums is not sorted")
      }
    }
    nums(start)
  }

  println(findMin(Array(3,4,5,1,2)))


}
