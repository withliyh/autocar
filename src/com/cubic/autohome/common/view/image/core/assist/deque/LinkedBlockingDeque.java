package com.cubic.autohome.common.view.image.core.assist.deque;

import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedBlockingDeque<T> extends AbstractQueue<T> implements
		BlockingDeque<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3525396820817550981L;

	private final int capacity;
	private transient int count;
	transient Node<T> first;
	transient Node<T> last;
	final ReentrantLock lock = new ReentrantLock();
	private final Condition notEmpty = this.lock.newCondition();
	private final Condition notFull = this.lock.newCondition();

	public LinkedBlockingDeque(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

	public LinkedBlockingDeque() {
		this(Integer.MAX_VALUE);
	}

	static final class Node<T> {
		T item;
		Node<T> next;
		Node<T> prev;

		Node(T paramT) {
			this.item = paramT;
		}
	}

	private boolean linkFirst(Node<T> node) {
		if (this.count >= this.capacity) {
			return false;
		}
		Node<T> firstNode = this.first;
		node.next = firstNode;
		this.first = node;
		if (this.last == null) {
			this.last = node;
		}
		firstNode.prev = node;
		this.count += 1;
		this.notEmpty.signal();
		return true;
	}

	private boolean linkLast(Node<T> node) {
		if (this.count >= this.capacity) {
			return false;
		}
		Node<T> lastNode = this.last;
		node.prev = lastNode;
		this.last = node;
		if (this.first == null) {
			this.first = node;
		}
		lastNode.next = node;
		this.count += 1;
		this.notEmpty.signal();
		return true;
	}

	private T unlinkFirst() {
		Node<T> node1 = this.first;
		if (node1 == null) {
			return null;
		}
		Node<T> node2 = node1.next;
		T item = node1.item;
		node1.item = null;
		node1.next = node1;
		this.first = node2;
		if (node2 == null) {
			this.last = null;
		} else {
			node2.prev = null;
		}

		this.count -= 1;
		this.notFull.signal();
		return item;
	}

	private T unlinkLast() {
		Node<T> lastNode = this.last;
		if (lastNode == null) {
			return null;
		}
		Node<T> node = lastNode.prev;
		T item = lastNode.item;
		lastNode.item = null;
		lastNode.prev = lastNode;
		this.last = node;
		if (node == null) {
			this.first = null;
		}
		this.count -= 1;
		node.next = null;
		this.notFull.signal();
		return item;
	}

	public boolean add(T node) {
		addLast(node);
		return true;
	}

	public void addLast(T node) {
		if (!offerLast(node)) {
			throw new IllegalStateException("Deque full");
		}
	}

	public void clear() {
		ReentrantLock reentrantLock = this.lock;
		reentrantLock.lock();
		try {
			Node<T> node = null;
			for (Node<T> curNode = this.first;; curNode = node) {
				if (first == null) {
					this.last = null;
					this.first = null;
					this.count = 0;
					this.notFull.signalAll();
					return;
				}
				curNode.item = null;
				node = curNode.next;
				curNode.prev = null;
				curNode.next = null;
			}
		} finally {
			reentrantLock.unlock();
		}
	}

	@Override
	public boolean contains(Object t) {
		if (t == null) {
			return false;
		}
		ReentrantLock lock = this.lock;
		lock.lock();
		try {
			for (Node<T> node = this.first;; node = node.next) {
				if (node == null) {
					return false;
				}
				boolean bool = t.equals(node.item);
				if (bool) {
					return true;
				}
			}
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean offer(T e) {
		return offerLast(e);
	}

	@Override
	public T poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T peek() {
		return peekFirst();
	}

	public T peekFirst() {
		ReentrantLock localReentrantLock = this.lock;
		localReentrantLock.lock();
		try {
			Node<T> node = this.first;
			if (node == null) {
				return null;
			}
			return node.item;
		} finally {
			localReentrantLock.unlock();
		}
	}

	@Override
	public void put(T e) throws InterruptedException {
		putLast(e);
	}

	public void putLast(T paramE) throws InterruptedException {
		if (paramE == null)
			throw new NullPointerException();
		Node<T> localNode = new Node<T>(paramE);
		this.lock.lock();
		try {
			while (true) {
				boolean bool = linkLast(localNode);
				if (bool)
					return;
				this.notFull.await();
			}
		} finally {
			this.lock.unlock();
		}
	}

	@Override
	public boolean offer(T e, long timeout, TimeUnit unit)
			throws InterruptedException {
		return offerLast(e, timeout, unit);
	}

	public boolean offerFirst(T node) {
		if (node == null) {
			throw new NullPointerException();
		}
		Node<T> tmp = new Node<T>(node);
		this.lock.lock();
		;
		try {
			boolean b = linkFirst(tmp);
			return b;
		} finally {
			this.lock.unlock();
		}
	}

	public boolean offerLast(T node) {
		if (node == null) {
			throw new NullPointerException();
		}
		Node<T> tmp = new Node<T>(node);
		this.lock.lock();
		try {
			boolean b = linkLast(tmp);
			return b;
		} finally {
			this.lock.unlock();
		}
	}

	public boolean offerLast(T node, long timeout, TimeUnit unit)
			throws InterruptedException {
		if (node == null) {
			throw new NullPointerException();
		}
		Node<T> tmp = new Node<T>(node);
		long longtimeout = unit.toNanos(timeout);
		this.lock.lockInterruptibly();
		try {
			while (true) {
				boolean b = linkLast(tmp);
				if (b) {
					return true;
				}
				if (longtimeout <= 0L) {
					return false;
				}
				longtimeout = this.notFull.awaitNanos(longtimeout);
			}
		} finally {
			this.lock.unlock();
		}
	}

	@Override
	public T take() throws InterruptedException {
		return takeFirst();
	}

	public T takeFirst() throws InterruptedException {
		ReentrantLock localReentrantLock = this.lock;
		localReentrantLock.lock();
		try {
			while (true) {
				T localObject1 = unlinkFirst();
				if (localObject1 != null)
					return localObject1;
				this.notEmpty.await();
			}
		} finally {
			localReentrantLock.unlock();
		}
	}

	@Override
	public T poll(long timeout, TimeUnit unit) throws InterruptedException {
		return pollFirst(timeout, unit);
	}

	public T pollFirst() {
		ReentrantLock localReentrantLock = this.lock;
		localReentrantLock.lock();
		try {
			T localObject1 = unlinkFirst();
			return localObject1;
		} finally {
			localReentrantLock.unlock();
		}
	}

	public T pollFirst(long timeout, TimeUnit timeUnit)
			throws InterruptedException {
		long timeoutUnit = timeUnit.toNanos(timeout);
		this.lock.lockInterruptibly();
		try {
			while (true) {
				T localObject1 = unlinkFirst();
				if (localObject1 != null)
					return localObject1;
				if (timeoutUnit <= 0L)
					return null;
				timeoutUnit = this.notEmpty.awaitNanos(timeoutUnit);
			}
		} finally {
			this.lock.unlock();
		}
	}

	@Override
	public int remainingCapacity() {
		ReentrantLock localReentrantLock = this.lock;
		localReentrantLock.lock();
		try {
			int i = this.capacity;
			int j = this.count;
			return i - j;
		} finally {
			localReentrantLock.unlock();
		}
	}

	@Override
	public int drainTo(Collection<? super T> c) {
		return drainTo(c, Integer.MAX_VALUE);
	}

	@Override
	public int drainTo(Collection<? super T> c, int maxElements) {
		if (c == null) {
			throw new NullPointerException();
		}
		if (c == this) {
			throw new IllegalArgumentException();
		}
		ReentrantLock reentrantLock = this.lock;
		reentrantLock.lock();
		try {
			int i = Math.min(maxElements, this.count);
			for (int a = 0; a < i; a++) {
				c.add(this.first.item);
				unlinkFirst();
			}
			return i;
		} finally {
			reentrantLock.unlock();
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Itr();
	}

	@Override
	public T element() {
		return getFirst();
	}

	public T getFirst() {
		T t = peekFirst();
		if (t == null) {
			throw new NoSuchElementException();
		}
		return t;
	}

	public T remove() {
		return removeFirst();
	}

	public boolean remove(Object paramObject) {
		return removeFirstOccurrence(paramObject);
	}

	public T removeFirst() {
		T localObject = pollFirst();
		if (localObject == null)
			throw new NoSuchElementException();
		return localObject;
	}

	public boolean removeFirstOccurrence(Object paramObject) {
		if (paramObject == null)
			return false;
		ReentrantLock localReentrantLock = this.lock;
		localReentrantLock.lock();
		try {
			for (Node<T> localNode = this.first;; localNode = localNode.next) {
				if (localNode == null)
					return false;
				if (paramObject.equals(localNode.item)) {
					unlink(localNode);
					return true;
				}
			}
		} finally {
			localReentrantLock.unlock();
		}
	}

	void unlink(Node<T> paramNode) {
		Node<T> localNode1 = paramNode.prev;
		Node<T> localNode2 = paramNode.next;
		if (localNode1 == null) {
			unlinkFirst();
			return;
		}
		if (localNode2 == null) {
			unlinkLast();
			return;
		}
		localNode1.next = localNode2;
		localNode2.prev = localNode1;
		paramNode.item = null;
		this.count -= 1;
		this.notFull.signal();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	private abstract class AbstractItr implements Iterator<T> {
		private LinkedBlockingDeque.Node<T> lastRet;
		LinkedBlockingDeque.Node<T> next;
		T nextItem;

		AbstractItr() {
			ReentrantLock localReentrantLock = LinkedBlockingDeque.this.lock;
			localReentrantLock.lock();
			try {
				this.next = firstNode();
//				if (this.next == null)
//					;
//				for (this$1 = null;; this$1 = this.next.item) {
//					this.nextItem = LinkedBlockingDeque.this;
//					return;
//				}
			} finally {
				localReentrantLock.unlock();
			}
		}

		private LinkedBlockingDeque.Node<T> succ(
				LinkedBlockingDeque.Node<T> paramNode) {
			while (true) {
				LinkedBlockingDeque.Node<T> localNode = nextNode(paramNode);
				T localObject;
				if (localNode == null)
					localObject = null;
//				do {
//					return localObject;
//				} while (localNode.item != null);
				if (localNode == paramNode)
					return firstNode();
				paramNode = localNode;
			}
		}

		void advance() {
			ReentrantLock localReentrantLock = LinkedBlockingDeque.this.lock;
			localReentrantLock.lock();
			try {
				this.next = succ(this.next);
				if (this.next == null)
					;
				for (T localObject1 = null;; localObject1 = this.next.item) {
					this.nextItem = localObject1;
					return;
				}
			} finally {
				localReentrantLock.unlock();
			}
		}

		abstract LinkedBlockingDeque.Node<T> firstNode();

		public boolean hasNext() {
			return this.next != null;
		}

		public T next() {
			if (this.next == null)
				throw new NoSuchElementException();
			this.lastRet = this.next;
			T localObject = this.nextItem;
			advance();
			return localObject;
		}

		abstract LinkedBlockingDeque.Node<T> nextNode(
				LinkedBlockingDeque.Node<T> paramNode);

		public void remove() {
			LinkedBlockingDeque.Node<T> localNode = this.lastRet;
			if (localNode == null)
				throw new IllegalStateException();
			this.lastRet = null;
			ReentrantLock localReentrantLock = LinkedBlockingDeque.this.lock;
			localReentrantLock.lock();
			try {
				if (localNode.item != null)
					LinkedBlockingDeque.this.unlink(localNode);
				return;
			} finally {
				localReentrantLock.unlock();
			}
		}
	}

	private class Itr extends LinkedBlockingDeque<T>.AbstractItr {
		private Itr() {
			super();
		}

		LinkedBlockingDeque.Node<T> firstNode() {
			return LinkedBlockingDeque.this.first;
		}

		LinkedBlockingDeque.Node<T> nextNode(
				LinkedBlockingDeque.Node<T> paramNode) {
			return paramNode.next;
		}
	}

}
