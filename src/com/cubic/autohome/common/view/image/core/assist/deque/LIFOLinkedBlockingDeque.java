package com.cubic.autohome.common.view.image.core.assist.deque;

import java.util.concurrent.LinkedBlockingDeque;



public class LIFOLinkedBlockingDeque<T> extends LinkedBlockingDeque<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6824059041288035974L;

	@Override
	public boolean offer(T e) {
		return super.offer(e);
	}
	
	@Override
	public T remove() {
		return super.remove();
	}
}
