package leetcode

class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}

object P2 extends App {
  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    var isOverTen = false
    var node1 = l1
    var node2 = l2
    var prevNode: ListNode = null
    var firstNode: ListNode = null
    while(node1 != null || node2 != null || isOverTen) {
      var value1 = 0
      var value2 = 0
      var sum = 0
      if (node1 != null) value1 = node1.x
      if (node2 != null) value2 = node2.x
      sum = value1 + value2 + (if (isOverTen) 1 else 0)
      isOverTen = (sum / 10) > 0
      val remainder = sum % 10
      val node = new ListNode(remainder)
      if (prevNode != null) {
        prevNode.next = node
      } else {
        firstNode = node
      }
      prevNode = node

      if(node1 != null) node1 = node1.next
      if(node2 != null) node2 = node2.next
    }
    firstNode
  }

  val x1 = new ListNode(9)

  val y1 = new ListNode(1)
  val y2 = new ListNode(9)
  val y3 = new ListNode(9)
  val y4 = new ListNode(9)
  val y5 = new ListNode(9)
  val y6 = new ListNode(9)
  val y7 = new ListNode(9)
  val y8 = new ListNode(9)
  val y9 = new ListNode(9)
  val y10 = new ListNode(9)

  y1.next = y2
  y2.next = y3
  y3.next = y4
  y4.next = y5
  y5.next = y6
  y6.next = y7
  y7.next = y8
  y8.next = y9

  var n = addTwoNumbers(x1, y1)

  while(n != null) {
    println(n.x)
    n = n.next
  }
}
