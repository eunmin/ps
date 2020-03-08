package leetcode

object P34 extends App {
  def searchRange(nums: Array[Int], target: Int): Array[Int] = {
    var start = 0
    var end = nums.length
    while (start < end) {
      val mid = (start+end)/2
      val value = nums(mid)
      if (target < value) end = mid
      else if (target > value) start = mid + 1
      else {
        // find min
        var resultStart = mid
        var minStart = start
        var minEnd = mid
        while(minStart < minEnd) {
          val minMid = (minStart+minEnd)/2
          val minValue = nums(minMid)
          if (minValue == target) {
            resultStart = minMid
            minEnd = minMid
          } else minStart = minMid + 1
        }
        // find max
        var resultEnd = mid
        var maxStart = mid
        var maxEnd = end
        while(maxStart < maxEnd) {
          val maxMid = (maxStart+maxEnd)/2
          val maxValue = nums(maxMid)
          if (maxValue == target) {
            resultEnd = maxMid
            maxStart = maxMid + 1
          } else {
            maxEnd = maxMid
          }
        }
        return Array(resultStart, resultEnd)
      }
    }
    Array(-1, -1)
  }

  searchRange(Array(0,1,1,1), 1).foreach( value => print(s"$value "))
}
