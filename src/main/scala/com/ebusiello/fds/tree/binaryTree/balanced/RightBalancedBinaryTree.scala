package com.ebusiello.fds.tree.binaryTree.balanced

import com.ebusiello.fds.tree.{ASC, DESC, Direction, SortableTree}

/**
 * A variation of a AVL tree (Adelson-Velskii and Landis tree http://en.wikipedia.org/wiki/AVL_tree), the balancing has preference towards the right side.
 */
final class RightBalancedBinaryTree[T](head: AbstractBalancedBinaryNode[T]) extends AbstractBalancedBinaryTree[T, RightBalancedBinaryTree, RightBalancedBinaryTree](head) with SortableTree[T, RightBalancedBinaryTree] {

  override def toRight: RightBalancedBinaryTree[T] = head match {
    case balancedNode: BalancedBinaryNode[T] => new RightBalancedBinaryTree[T](balancedNode.toRight)
  }

  override def toLeft: LeftBalancedBinaryTree[T] = head match {
    case balancedNode: BalancedBinaryNode[T] => new LeftBalancedBinaryTree[T](balancedNode.toLeft)
  }

  override def map[V](f: (T) => V): RightBalancedBinaryTree[V] = head match {
    case balancedNode: BalancedBinaryNode[T] => new RightBalancedBinaryTree[V](balancedNode.map(f))
  }

  override def insert(mValue: T)(implicit ord: Ordering[T]): RightBalancedBinaryTree[T] =
    if (isEmpty) new RightBalancedBinaryTree[T](new RightBalancedBinaryNode[T](mValue, new RightBalancedEmptyNode[T], new RightBalancedEmptyNode[T]))
    else new RightBalancedBinaryTree[T](head.insert(mValue))

  override def sort(direction: Direction)(implicit ord: Ordering[T]): RightBalancedBinaryTree[T] = {
    val folded = foldTree(List[T]())((acc, curr) => acc :+ curr)((s1, s2) => s1 ++ s2)
    direction match {
      case DESC => RightBalancedBinaryTree.fromColl(folded.sorted)
      case ASC => RightBalancedBinaryTree.fromColl(folded.sorted(ord.reverse))
    }
  }

}

object RightBalancedBinaryTree {

  def fromColl[T, S <: TraversableOnce[T]](coll: S)(implicit ord: Ordering[T]): RightBalancedBinaryTree[T] =
    coll.foldLeft(RightBalancedBinaryTree.emptyTree[T])(_.insert(_))

  /**
   * Easily create an empty tree.
   */
  def emptyTree[T] = new RightBalancedBinaryTree[T](new RightBalancedEmptyNode[T])
}