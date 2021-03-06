package com.ebusiello.fds.tree.generic.tree

trait Tree[T] {

  def isEmpty: Boolean

  def stringify: String

  def foldTree[P](z: P)(f: (P, T) => P)(compose: (P, P) => P): P

  def depth: Int

  def length: Int

}