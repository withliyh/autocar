package com.cubic.autohome.common.view.image.core;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import com.cubic.autohome.common.view.image.cache.disc.DiskCache;
import com.cubic.autohome.common.view.image.core.imageaware.ImageAware;

public class ImageLoaderEngine {

	private final Map<Integer, String> cacheKeysForImageAwares;
	final ImageLoaderConfiguration configuration;
	private final AtomicBoolean networkDenied;
	private final Object pauseLock = new Object();
	private final AtomicBoolean paused;
	private final AtomicBoolean slowNetwork;
	private Executor taskDistributor;
	private Executor taskExecutor;
	private Executor taskExecutorForCachedImages;
	private final Map<String, ReentrantLock> uriLocks;

	ImageLoaderEngine(ImageLoaderConfiguration configuration) {
		this.cacheKeysForImageAwares = Collections.synchronizedMap(new HashMap<Integer,String>());
		this.uriLocks = new WeakHashMap<>();
		this.paused = new AtomicBoolean();
		this.networkDenied = new AtomicBoolean();
		this.slowNetwork = new AtomicBoolean();
		
		this.configuration = configuration;
		this.taskExecutor = configuration.taskExecutor;
		this.taskExecutorForCachedImages = configuration.taskExecutorForCachedImages;
		this.taskDistributor = DefaultConfigurationFactory
				.createTaskDistributor();
	}

	private Executor createTaskExecutor() {
		return DefaultConfigurationFactory.createExecutor(
				this.configuration.threadPoolSize,
				this.configuration.threadPriority,
				this.configuration.tasksProcessingType);
	}

	private void initExecutorsIfNeed() {
		if (!this.configuration.customExecutor) { 
			ExecutorService server = (ExecutorService) this.taskExecutor;	
			if (server.isShutdown()) {
				this.taskExecutor = createTaskExecutor();
			}
		}
		if (!this.configuration.customExecutorForCachedImages) {
			ExecutorService server = (ExecutorService) this.taskExecutorForCachedImages;
			if(server.isShutdown()) {
				this.taskExecutorForCachedImages = createTaskExecutor();
			}
		}

	}

	void cancelDisplayTaskFor(ImageAware imageAware) {
		this.cacheKeysForImageAwares.remove(Integer.valueOf(imageAware
				.getId()));
	}

	void fireCallback(Runnable r) {
		this.taskDistributor.execute(r);
	}

	String getLoadingUriForView(ImageAware imageAware) {
		return this.cacheKeysForImageAwares.get(Integer
				.valueOf(imageAware.getId()));
	}

	ReentrantLock getLockForUri(String uri) {
		ReentrantLock lock = this.uriLocks
				.get(uri);
		if (lock == null) {
			lock = new ReentrantLock();
			this.uriLocks.put(uri, lock);
		}
		return lock;
	}

	AtomicBoolean getPause() {
		return this.paused;
	}

	Object getPauseLock() {
		return this.pauseLock;
	}

	boolean isNetworkDenied() {
		return this.networkDenied.get();
	}

	boolean isSlowNetwork() {
		return this.slowNetwork.get();
	}

	void prepareDisplayTaskFor(ImageAware imageAware, String memoryCacheKey) {
		this.cacheKeysForImageAwares.put(
				Integer.valueOf(imageAware.getId()), memoryCacheKey);
	}

	void submit(final LoadAndDisplayImageTask task) {
		this.taskDistributor.execute(new Runnable() {
			public void run() {
				DiskCache diskCache = configuration.diskCache;
				String uri = task.getLoadingUri();
				File file = diskCache.get(uri);
				if (file != null) {
					if (file.exists()) {
						initExecutorsIfNeed();
						taskExecutorForCachedImages.execute(task);
						return;
					}
				}
				taskExecutor.execute(task);
			}
		});
	}

	void submit(ProcessAndDisplayImageTask task) {
		initExecutorsIfNeed();
		this.taskExecutorForCachedImages
				.execute(task);
	}
}
