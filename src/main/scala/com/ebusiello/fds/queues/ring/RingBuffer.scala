package com.ebusiello.fds.queues.ring

import com.ebusiello.fds.queues.{ GenericQueue, QueueException }

/**
 * RingBuffer is virtual, the nodes are a linked list where the last node points to an empty one
 * like in any normal queue, cyclicity is enforced by the queue which keeps track of the list length,
 * if the maximum length is reached drop the head and point to the second element.
 */
final class RingBuffer[T](size: Int, val head: RingBufferNode[T] = new EmptyRingBufferNode[T]) extends GenericQueue[T, RingBuffer, RingBufferNode] {

  /**
   * Disadvantages of using a linked list, to know if the ring has reached
   * its full size we need to traverse the list and get the length, plus the
   * last node in the list doesn't have a pointer to the first node,
   * it simply points to an empty node so the check on the ring size is implemented
   * here.
   *
   * TODO insert is O(N) because we need the length of the list.
   * TODO maybe avoid using a linked list?
   */
  override def enqueue(value: T): RingBuffer[T] =
    if (head.length() == size) new RingBuffer[T](size, head.pointer.enqueue(value))
    else new RingBuffer[T](size, head.enqueue(value))

  /**
   * Dequeue always removes the first inserted element which in our case is the head.
   */
  override def dequeue: RingBuffer[T] =
    new RingBuffer[T](size, head.pointer)

  def last: T =
    head.last

  override def isEmpty: Boolean =
    head.isEmpty

  override def top: T = head match {
    case e: EmptyRingBufferNode[T] => throw new QueueException("top on empty queue.")
    case n: RingBufferNode[T] => n.value
  }

}