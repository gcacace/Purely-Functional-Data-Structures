package com.ebusiello.fds.tree.generic.tree

import scala.language.higherKinds

trait SortableTree[T, S[_]] extends Tree[T] {
  def sort(direction: Direction)(implicit ord: Ordering[T]): S[T]
}

sealed abstract class Direction(val desc: Boolean) {
  def reverse: Direction
}

case object ASC extends Direction(false) {
  def reverse = DESC
}

case object DESC extends Direction(true) {
  def reverse = ASC
}