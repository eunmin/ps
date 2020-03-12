package leetcode

object P146 extends App {
  class LRUCache(_capacity: Int) {
    import scala.collection.mutable

    private case class Node(key: Int, var value: Int, var prev: Option[Node] = None, var next: Option[Node] = None)
    private var first: Option[Node] = None
    private var last: Option[Node] = None
    private var cache = mutable.Map[Int, Node]()

    private def popLast(): Option[Node] = {
      val node = last
      node.foreach(_.prev.foreach { prev =>
        prev.next = None
        last = Some(prev)
      })
      node
    }

    def moveFirst(node: Node): Unit = {
      if (node.prev.isEmpty && node.next.isEmpty) return
      if (first.getOrElse(Node(-1,-1)).key == node.key) return
      node.next.fold {
        last = node.prev
      }(_.prev = node.prev)
      node.prev.foreach(_.next = node.next)
      node.next = first
      first.foreach(_.prev = Some(node))
      node.prev = None
      first = Some(node)
    }

    def put(key: Int, value: Int): Unit =
      cache.get(key).fold {
        val node = Node(key, value)
        node.next = first
        first.foreach(_.prev = Some(node))
        first = Some(node)

        last.fold {
          last = Some(node)
        } { lastNode =>
          if (cache.size == _capacity) {
            popLast()
            cache -= lastNode.key
          }
        }
        cache += (key -> node)
        ()
      } { node =>
        moveFirst(node)
        node.value = value
        ()
      }


    def get(key: Int): Int =
      cache.get(key).fold {
        -1
      } { node =>
        moveFirst(node)
        node.value
      }
  }

//  var cache = new LRUCache(2)
//  cache.put(1, 1)
//  cache.put(2, 2)
//  println(cache.get(1))
//  cache.put(3, 3)
//  println(cache.get(2))
//  cache.put(4, 4)
//  println(cache.get(1))
//  println(cache.get(3))
//  println(cache.get(4))

//  var cache = new LRUCache(2)
//  cache.put(2, 1)
//  cache.put(1, 1)
//  cache.put(2, 3)
//  cache.put(4, 1)
//  println(cache.get(1))
//  println(cache.get(2))

  var cache = new LRUCache(2)
  cache.put(2, 1)
  cache.put(3, 2)
  println(cache.get(3))
  println(cache.get(2))
  cache.put(4, 3)
  println(cache.get(2))
  println(cache.get(3))
  println(cache.get(4))
}
