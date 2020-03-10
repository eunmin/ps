package leetcode

object P74 extends App {

  def searchMatrix(matrix: Array[Array[Int]], target: Int): Boolean = {
    var start = 0;
    var end = if (matrix.isEmpty) {
      return false
    } else {
      (matrix.length * matrix(0).length)-1
    }
    def valueByIndex(index: Int): Int = {
      val m = matrix(0).length
      matrix(index/m)(index%m)
    }
    while(start <= end) {
      var mid = (start+end)/2
      if (valueByIndex(mid) < target) { // right
        start = mid + 1
      } else if (valueByIndex(mid) > target) { // left
        end = mid - 1
      } else { // found
        return true
      }
    }
    false
  }

  println(searchMatrix(Array(Array(1,2,3,4),Array(5,6,7,8)), 8))
}
