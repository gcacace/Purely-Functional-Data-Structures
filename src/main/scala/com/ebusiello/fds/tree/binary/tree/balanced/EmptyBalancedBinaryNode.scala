//package com.ebusiello.fds.com.ebusiello.fds.tree.binaryTree.balanced
//
//import scala.language.higherKinds
//
///**
// * Semi concrete empty node for a balanced com.ebusiello.fds.tree
// * @tparam T
// */
//private[binaryTree] abstract class EmptyBalancedBinaryNode[T] extends AbstractBalancedBinaryNode[T] {
//
//  override def toRight: AbstractBalancedBinaryNode[T] =
//    this
//
//  override def toLeft: AbstractBalancedBinaryNode[T] =
//    this
//
//  override def leftRelativeDepth: Int =
//    0
//
//  override def rightRelativeDepth: Int =
//    0
//
//  override def toString =
//    "E"
//
//  override def isEmpty: Boolean =
//    true
//
//  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
//    false
//
//  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
//    z
//
//}