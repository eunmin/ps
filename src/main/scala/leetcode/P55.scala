package leetcode

object P55 extends App {
  def canJump(nums: Array[Int]): Boolean = {
    val memo: Array[Option[Boolean]] = Array.fill(nums.length)(None)
    def find(index: Int): Boolean = {
      memo(index).fold {
        if (index == nums.length - 1) return true
        for (i <- nums(index) to 1 by -1) {
          if (index + i < nums.length) {
            if (find(index + i)) return true
          }
        }
        memo(index) = Some(false)
        false
      } { cache => cache }
    }
    find(0)
  }

  println(canJump(Array(3,2,1,0,4)))
}
