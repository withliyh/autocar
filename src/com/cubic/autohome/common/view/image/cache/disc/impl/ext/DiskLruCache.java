package com.cubic.autohome.common.view.image.cache.disc.impl.ext;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class DiskLruCache implements Closeable {
	static final Pattern LEGAL_KEY_PATTERN = Pattern
			.compile("[a-z0-9_-]{1,64}");
	private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream() {
		public void write(int paramAnonymousInt) throws IOException {
		}
	};
	private final int appVersion;
	private final Callable<Void> cleanupCallable = new Callable() {
		public Void call() throws Exception {
			synchronized (DiskLruCache.this) {
				if (DiskLruCache.this.journalWriter == null)
					return null;
				DiskLruCache.this.trimToSize();
				DiskLruCache.this.trimToFileCount();
				if (DiskLruCache.this.journalRebuildRequired()) {
					DiskLruCache.this.rebuildJournal();
					DiskLruCache.this.redundantOpCount = 0;
				}
				return null;
			}
		}
	};
	private final File directory;
	final ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, 1,
			60L, TimeUnit.SECONDS, new LinkedBlockingQueue(10),
			new ThreadPoolExecutor.DiscardOldestPolicy());
	private int fileCount = 0;
	private final File journalFile;
	private final File journalFileBackup;
	private final File journalFileTmp;
	private Writer journalWriter;
	private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap(
			0, 0.75F, true);
	private int maxFileCount;
	private long maxSize;
	private long nextSequenceNumber = 0L;
	private int redundantOpCount;
	private long size = 0L;
	private final int valueCount;

	private DiskLruCache(File paramFile, int paramInt1, int paramInt2,
			long paramLong, int paramInt3) {
		this.directory = paramFile;
		this.appVersion = paramInt1;
		this.journalFile = new File(paramFile, "journal");
		this.journalFileTmp = new File(paramFile, "journal.tmp");
		this.journalFileBackup = new File(paramFile, "journal.bkp");
		this.valueCount = paramInt2;
		this.maxSize = paramLong;
		this.maxFileCount = paramInt3;
	}

	private void checkNotClosed() {
		if (this.journalWriter == null)
			throw new IllegalStateException("cache is closed");
	}

	private void completeEdit(Editor paramEditor, boolean paramBoolean)
			throws IOException {
		Entry localEntry;
		try {
			localEntry = paramEditor.entry;
			if (localEntry.currentEditor != paramEditor)
				throw new IllegalStateException();
		} finally {
		}
		int i;
		if ((paramBoolean) && (!localEntry.readable)) {
			i = 0;
			if (i >= this.valueCount)
				break label403;
		}
		while (true) {
			long l1;
			if (i >= this.valueCount) {
				this.redundantOpCount += 1;
				localEntry.currentEditor = null;
				if (!(localEntry.readable | paramBoolean))
					break label356;
				localEntry.readable = true;
				this.journalWriter.write("CLEAN " + localEntry.key
						+ localEntry.getLengths() + '\n');
				if (paramBoolean) {
					l1 = this.nextSequenceNumber;
					this.nextSequenceNumber = (1L + l1);
					localEntry.sequenceNumber = l1;
				}
			}
			while (true) {
				this.journalWriter.flush();
				if ((this.size > this.maxSize)
						|| (this.fileCount > this.maxFileCount)
						|| (journalRebuildRequired()))
					this.executorService.submit(this.cleanupCallable);
				while (true) {
					return;
					if (paramEditor.written[i] == 0) {
						paramEditor.abort();
						throw new IllegalStateException(
								"Newly created entry didn't create value for index "
										+ i);
					}
					if (localEntry.getDirtyFile(i).exists())
						break;
					paramEditor.abort();
				}
				paramEditor = localEntry.getDirtyFile(i);
				if (paramBoolean) {
					if (!paramEditor.exists())
						break label418;
					File localFile = localEntry.getCleanFile(i);
					paramEditor.renameTo(localFile);
					l1 = localEntry.lengths[i];
					long l2 = localFile.length();
					localEntry.lengths[i] = l2;
					this.size = (this.size - l1 + l2);
					this.fileCount += 1;
					break label418;
				}
				deleteIfExists(paramEditor);
				break label418;
				label356: this.lruEntries.remove(localEntry.key);
				this.journalWriter.write("REMOVE " + localEntry.key + '\n');
			}
			label403: i = 0;
			continue;
			i += 1;
			break;
			label418: i += 1;
		}
	}

	private static void deleteIfExists(File paramFile) throws IOException {
		if ((paramFile.exists()) && (!paramFile.delete()))
			throw new IOException();
	}

	private Editor edit(String paramString, long paramLong) throws IOException {
		Editor localEditor1 = null;
		while (true) {
			Entry localEntry;
			try {
				checkNotClosed();
				validateKey(paramString);
				localEntry = (Entry) this.lruEntries.get(paramString);
				if (paramLong != -1L) {
					localObject = localEditor1;
					if (localEntry != null) {
						long l = localEntry.sequenceNumber;
						if (l != paramLong)
							localObject = localEditor1;
					} else {
						return localObject;
					}
				}
				if (localEntry == null) {
					localObject = new Entry(paramString, null);
					this.lruEntries.put(paramString, localObject);
					localEditor1 = new Editor((Entry) localObject, null);
					((Entry) localObject).currentEditor = localEditor1;
					this.journalWriter.write("DIRTY " + paramString + '\n');
					this.journalWriter.flush();
					localObject = localEditor1;
					continue;
				}
			} finally {
			}
			Editor localEditor2 = localEntry.currentEditor;
			Object localObject = localEntry;
			if (localEditor2 != null)
				localObject = localEditor1;
		}
	}

	private boolean journalRebuildRequired() {
		return (this.redundantOpCount >= 2000)
				&& (this.redundantOpCount >= this.lruEntries.size());
	}

	public static DiskLruCache open(File paramFile, int paramInt1,
			int valueCount, long maxSize, int maxFileCount) throws IOException {
		if (maxSize <= 0L)
			throw new IllegalArgumentException("maxSize <= 0");
		if (maxFileCount <= 0)
			throw new IllegalArgumentException("maxFileCount <= 0");
		if (valueCount <= 0)
			throw new IllegalArgumentException("valueCount <= 0");
		File localObject = new File(paramFile, "journal.bkp");
		File localFile;
		if (localObject.exists()) {
			localFile = new File(paramFile, "journal");
			if (!localFile.exists())
				;
			localObject.delete();
		}

		DiskLruCache lruCache;
		lruCache = new DiskLruCache(paramFile, paramInt1, valueCount, maxSize,
				maxFileCount);
		if (lruCache.journalFile.exists())
			try {
				lruCache.readJournal();
				lruCache.processJournal();
				lruCache.journalWriter = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(
								lruCache.journalFile, true), Util.US_ASCII));
				return lruCache;
			} catch (IOException localIOException) {
				System.out.println("DiskLruCache " + paramFile
						+ " is corrupt: " + localIOException.getMessage()
						+ ", removing");
				lruCache.delete();
			}

		paramFile.mkdirs();
		DiskLruCache diskLruCache = new DiskLruCache(paramFile, paramInt1,
				valueCount, maxSize, maxFileCount);
		diskLruCache.rebuildJournal();
		return diskLruCache;
	}

	private void processJournal() throws IOException {
		deleteIfExists(this.journalFileTmp);
		Iterator localIterator = this.lruEntries.values().iterator();
		Entry localEntry;
		while (true) {
			if (!localIterator.hasNext())
				return;
			localEntry = (Entry) localIterator.next();
			if (localEntry.currentEditor != null)
				break;
			i = 0;
			while (i < this.valueCount) {
				this.size += localEntry.lengths[i];
				this.fileCount += 1;
				i += 1;
			}
		}
		localEntry.currentEditor = null;
		int i = 0;
		while (true) {
			if (i >= this.valueCount) {
				localIterator.remove();
				break;
			}
			deleteIfExists(localEntry.getCleanFile(i));
			deleteIfExists(localEntry.getDirtyFile(i));
			i += 1;
		}
	}

	// ERROR //
	private void readJournal() throws IOException {
		// Byte code:
		// 0: new 415
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/StrictLineReader
		// 3: dup
		// 4: new 417 java/io/FileInputStream
		// 7: dup
		// 8: aload_0
		// 9: getfield 126
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:journalFile
		// Ljava/io/File;
		// 12: invokespecial 419 java/io/FileInputStream:<init>
		// (Ljava/io/File;)V
		// 15: getstatic 353
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/Util:US_ASCII
		// Ljava/nio/charset/Charset;
		// 18: invokespecial 422
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/StrictLineReader:<init>
		// (Ljava/io/InputStream;Ljava/nio/charset/Charset;)V
		// 21: astore_1
		// 22: aload_1
		// 23: invokevirtual 425
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/StrictLineReader:readLine
		// ()Ljava/lang/String;
		// 26: astore_2
		// 27: aload_1
		// 28: invokevirtual 425
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/StrictLineReader:readLine
		// ()Ljava/lang/String;
		// 31: astore_3
		// 32: aload_1
		// 33: invokevirtual 425
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/StrictLineReader:readLine
		// ()Ljava/lang/String;
		// 36: astore 4
		// 38: aload_1
		// 39: invokevirtual 425
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/StrictLineReader:readLine
		// ()Ljava/lang/String;
		// 42: astore 5
		// 44: aload_1
		// 45: invokevirtual 425
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/StrictLineReader:readLine
		// ()Ljava/lang/String;
		// 48: astore 6
		// 50: ldc_w 427
		// 53: aload_2
		// 54: invokevirtual 433 java/lang/String:equals (Ljava/lang/Object;)Z
		// 57: ifeq +54 -> 111
		// 60: ldc_w 435
		// 63: aload_3
		// 64: invokevirtual 433 java/lang/String:equals (Ljava/lang/Object;)Z
		// 67: ifeq +44 -> 111
		// 70: aload_0
		// 71: getfield 117
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:appVersion
		// I
		// 74: invokestatic 440 java/lang/Integer:toString (I)Ljava/lang/String;
		// 77: aload 4
		// 79: invokevirtual 433 java/lang/String:equals (Ljava/lang/Object;)Z
		// 82: ifeq +29 -> 111
		// 85: aload_0
		// 86: getfield 136
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:valueCount
		// I
		// 89: invokestatic 440 java/lang/Integer:toString (I)Ljava/lang/String;
		// 92: aload 5
		// 94: invokevirtual 433 java/lang/String:equals (Ljava/lang/Object;)Z
		// 97: ifeq +14 -> 111
		// 100: ldc_w 442
		// 103: aload 6
		// 105: invokevirtual 433 java/lang/String:equals (Ljava/lang/Object;)Z
		// 108: ifne +73 -> 181
		// 111: new 147 java/io/IOException
		// 114: dup
		// 115: new 207 java/lang/StringBuilder
		// 118: dup
		// 119: ldc_w 444
		// 122: invokespecial 210 java/lang/StringBuilder:<init>
		// (Ljava/lang/String;)V
		// 125: aload_2
		// 126: invokevirtual 217 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 129: ldc_w 446
		// 132: invokevirtual 217 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 135: aload_3
		// 136: invokevirtual 217 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 139: ldc_w 446
		// 142: invokevirtual 217 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 145: aload 5
		// 147: invokevirtual 217 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 150: ldc_w 446
		// 153: invokevirtual 217 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 156: aload 6
		// 158: invokevirtual 217 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 161: ldc_w 448
		// 164: invokevirtual 217 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 167: invokevirtual 227 java/lang/StringBuilder:toString
		// ()Ljava/lang/String;
		// 170: invokespecial 449 java/io/IOException:<init>
		// (Ljava/lang/String;)V
		// 173: athrow
		// 174: astore_2
		// 175: aload_1
		// 176: invokestatic 453
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/Util:closeQuietly
		// (Ljava/io/Closeable;)V
		// 179: aload_2
		// 180: athrow
		// 181: iconst_0
		// 182: istore 7
		// 184: aload_0
		// 185: aload_1
		// 186: invokevirtual 425
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/StrictLineReader:readLine
		// ()Ljava/lang/String;
		// 189: invokespecial 456
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:readJournalLine
		// (Ljava/lang/String;)V
		// 192: iload 7
		// 194: iconst_1
		// 195: iadd
		// 196: istore 7
		// 198: goto -14 -> 184
		// 201: astore_2
		// 202: aload_0
		// 203: iload 7
		// 205: aload_0
		// 206: getfield 83
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:lruEntries
		// Ljava/util/LinkedHashMap;
		// 209: invokevirtual 319 java/util/LinkedHashMap:size ()I
		// 212: isub
		// 213: putfield 177
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:redundantOpCount
		// I
		// 216: aload_1
		// 217: invokestatic 453
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/Util:closeQuietly
		// (Ljava/io/Closeable;)V
		// 220: return
		//
		// Exception table:
		// from to target type
		// 22 111 174 finally
		// 111 174 174 finally
		// 184 192 174 finally
		// 202 216 174 finally
		// 184 192 201 java/io/EOFException
	}

	private void readJournalLine(String paramString) throws IOException {
		int i = paramString.indexOf(' ');
		if (i == -1)
			throw new IOException("unexpected journal line: " + paramString);
		int j = i + 1;
		int k = paramString.indexOf(' ', j);
		Object localObject2;
		Object localObject1;
		if (k == -1) {
			localObject2 = paramString.substring(j);
			localObject1 = localObject2;
			if (i != "REMOVE".length())
				break label112;
			localObject1 = localObject2;
			if (!paramString.startsWith("REMOVE"))
				break label112;
			this.lruEntries.remove(localObject2);
		}
		label112: do {
			return;
			localObject1 = paramString.substring(j, k);
			Entry localEntry = (Entry) this.lruEntries.get(localObject1);
			localObject2 = localEntry;
			if (localEntry == null) {
				localObject2 = new Entry((String) localObject1, null);
				this.lruEntries.put(localObject1, localObject2);
			}
			if ((k != -1) && (i == "CLEAN".length())
					&& (paramString.startsWith("CLEAN"))) {
				paramString = paramString.substring(k + 1).split(" ");
				((Entry) localObject2).readable = true;
				((Entry) localObject2).currentEditor = null;
				((Entry) localObject2).setLengths(paramString);
				return;
			}
			if ((k == -1) && (i == "DIRTY".length())
					&& (paramString.startsWith("DIRTY"))) {
				((Entry) localObject2).currentEditor = new Editor(
						(Entry) localObject2, null);
				return;
			}
		} while ((k == -1) && (i == "READ".length())
				&& (paramString.startsWith("READ")));
		throw new IOException("unexpected journal line: " + paramString);
	}

	private void rebuildJournal() throws IOException {
		while (true) {
			Entry localEntry;
			try {
				if (this.journalWriter != null)
					this.journalWriter.close();
				BufferedWriter localBufferedWriter = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(
								this.journalFileTmp), Util.US_ASCII));
				try {
					localBufferedWriter.write("libcore.io.DiskLruCache");
					localBufferedWriter.write("\n");
					localBufferedWriter.write("1");
					localBufferedWriter.write("\n");
					localBufferedWriter
							.write(Integer.toString(this.appVersion));
					localBufferedWriter.write("\n");
					localBufferedWriter
							.write(Integer.toString(this.valueCount));
					localBufferedWriter.write("\n");
					localBufferedWriter.write("\n");
					Iterator localIterator = this.lruEntries.values()
							.iterator();
					boolean bool = localIterator.hasNext();
					if (!bool) {
						localBufferedWriter.close();
						if (this.journalFile.exists())
							renameTo(this.journalFile, this.journalFileBackup,
									true);
						renameTo(this.journalFileTmp, this.journalFile, false);
						this.journalFileBackup.delete();
						this.journalWriter = new BufferedWriter(
								new OutputStreamWriter(new FileOutputStream(
										this.journalFile, true), Util.US_ASCII));
						return;
					}
					localEntry = (Entry) localIterator.next();
					if (localEntry.currentEditor != null) {
						localBufferedWriter.write("DIRTY " + localEntry.key
								+ '\n');
						continue;
					}
				} finally {
					localBufferedWriter.close();
				}
			} finally {
			}
			localObject1.write("CLEAN " + localEntry.key
					+ localEntry.getLengths() + '\n');
		}
	}

	private static void renameTo(File paramFile1, File paramFile2,
			boolean paramBoolean) throws IOException {
		if (paramBoolean)
			deleteIfExists(paramFile2);
		if (!paramFile1.renameTo(paramFile2))
			throw new IOException();
	}

	private void trimToFileCount() throws IOException {
		while (true) {
			if (this.fileCount <= this.maxFileCount)
				return;
			remove((String) ((Map.Entry) this.lruEntries.entrySet().iterator()
					.next()).getKey());
		}
	}

	private void trimToSize() throws IOException {
		while (true) {
			if (this.size <= this.maxSize)
				return;
			remove((String) ((Map.Entry) this.lruEntries.entrySet().iterator()
					.next()).getKey());
		}
	}

	private void validateKey(String paramString) {
		if (!LEGAL_KEY_PATTERN.matcher(paramString).matches())
			throw new IllegalArgumentException(
					"keys must match regex [a-z0-9_-]{1,64}: \"" + paramString
							+ "\"");
	}

	public void close() throws IOException {
		while (true) {
			try {
				Object localObject1 = this.journalWriter;
				if (localObject1 == null)
					return;
				localObject1 = new ArrayList(this.lruEntries.values())
						.iterator();
				if (!((Iterator) localObject1).hasNext()) {
					trimToSize();
					trimToFileCount();
					this.journalWriter.close();
					this.journalWriter = null;
					continue;
				}
			} finally {
			}
			Entry localEntry = (Entry) localObject2.next();
			if (localEntry.currentEditor != null)
				localEntry.currentEditor.abort();
		}
	}

	public void delete() throws IOException {
		close();
		Util.deleteContents(this.directory);
	}

	public Editor edit(String paramString) throws IOException {
		return edit(paramString, -1L);
	}

	// ERROR //
	public Snapshot get(String paramString) throws IOException {
		// Byte code:
		// 0: aconst_null
		// 1: astore_3
		// 2: aload_0
		// 3: monitorenter
		// 4: aload_0
		// 5: invokespecial 293
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:checkNotClosed
		// ()V
		// 8: aload_0
		// 9: aload_1
		// 10: invokespecial 296
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:validateKey
		// (Ljava/lang/String;)V
		// 13: aload_0
		// 14: getfield 83
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:lruEntries
		// Ljava/util/LinkedHashMap;
		// 17: aload_1
		// 18: invokevirtual 299 java/util/LinkedHashMap:get
		// (Ljava/lang/Object;)Ljava/lang/Object;
		// 21: checkcast 18
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache$Entry
		// 24: astore 5
		// 26: aload 5
		// 28: ifnonnull +9 -> 37
		// 31: aload_3
		// 32: astore_2
		// 33: aload_0
		// 34: monitorexit
		// 35: aload_2
		// 36: areturn
		// 37: aload_3
		// 38: astore_2
		// 39: aload 5
		// 41: invokestatic 199
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache$Entry:access$0
		// (Lcom/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache$Entry;)Z
		// 44: ifeq -11 -> 33
		// 47: aload_0
		// 48: getfield 136
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:valueCount
		// I
		// 51: anewarray 119 java/io/File
		// 54: astore_2
		// 55: aload_0
		// 56: getfield 136
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:valueCount
		// I
		// 59: anewarray 546 java/io/InputStream
		// 62: astore 4
		// 64: iconst_0
		// 65: istore 7
		// 67: aload_0
		// 68: getfield 136
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:valueCount
		// I
		// 71: istore 8
		// 73: iload 7
		// 75: iload 8
		// 77: if_icmplt +94 -> 171
		// 80: aload_0
		// 81: aload_0
		// 82: getfield 177
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:redundantOpCount
		// I
		// 85: iconst_1
		// 86: iadd
		// 87: putfield 177
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:redundantOpCount
		// I
		// 90: aload_0
		// 91: getfield 144
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:journalWriter
		// Ljava/io/Writer;
		// 94: new 207 java/lang/StringBuilder
		// 97: dup
		// 98: ldc_w 548
		// 101: invokespecial 210 java/lang/StringBuilder:<init>
		// (Ljava/lang/String;)V
		// 104: aload_1
		// 105: invokevirtual 217 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 108: bipush 10
		// 110: invokevirtual 224 java/lang/StringBuilder:append
		// (C)Ljava/lang/StringBuilder;
		// 113: invokevirtual 227 java/lang/StringBuilder:toString
		// ()Ljava/lang/String;
		// 116: invokevirtual 551 java/io/Writer:append
		// (Ljava/lang/CharSequence;)Ljava/io/Writer;
		// 119: pop
		// 120: aload_0
		// 121: invokespecial 169
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:journalRebuildRequired
		// ()Z
		// 124: ifeq +15 -> 139
		// 127: aload_0
		// 128: getfield 108
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:executorService
		// Ljava/util/concurrent/ThreadPoolExecutor;
		// 131: aload_0
		// 132: getfield 113
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:cleanupCallable
		// Ljava/util/concurrent/Callable;
		// 135: invokevirtual 242 java/util/concurrent/ThreadPoolExecutor:submit
		// (Ljava/util/concurrent/Callable;)Ljava/util/concurrent/Future;
		// 138: pop
		// 139: new 21
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache$Snapshot
		// 142: dup
		// 143: aload_0
		// 144: aload_1
		// 145: aload 5
		// 147: invokestatic 304
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache$Entry:access$8
		// (Lcom/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache$Entry;)J
		// 150: aload_2
		// 151: aload 4
		// 153: aload 5
		// 155: invokestatic 271
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache$Entry:access$7
		// (Lcom/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache$Entry;)[J
		// 158: aconst_null
		// 159: invokespecial 554
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache$Snapshot:<init>
		// (Lcom/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache;Ljava/lang/String;J[Ljava/io/File;[Ljava/io/InputStream;[JLcom/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache$Snapshot;)V
		// 162: astore_2
		// 163: goto -130 -> 33
		// 166: astore_1
		// 167: aload_0
		// 168: monitorexit
		// 169: aload_1
		// 170: athrow
		// 171: aload 5
		// 173: iload 7
		// 175: invokevirtual 263
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache$Entry:getCleanFile
		// (I)Ljava/io/File;
		// 178: astore 6
		// 180: aload_2
		// 181: iload 7
		// 183: aload 6
		// 185: aastore
		// 186: aload 4
		// 188: iload 7
		// 190: new 417 java/io/FileInputStream
		// 193: dup
		// 194: aload 6
		// 196: invokespecial 419 java/io/FileInputStream:<init>
		// (Ljava/io/File;)V
		// 199: aastore
		// 200: iload 7
		// 202: iconst_1
		// 203: iadd
		// 204: istore 7
		// 206: goto -139 -> 67
		// 209: astore_1
		// 210: iconst_0
		// 211: istore 7
		// 213: aload_3
		// 214: astore_2
		// 215: iload 7
		// 217: aload_0
		// 218: getfield 136
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/DiskLruCache:valueCount
		// I
		// 221: if_icmpge -188 -> 33
		// 224: aload_3
		// 225: astore_2
		// 226: aload 4
		// 228: iload 7
		// 230: aaload
		// 231: ifnull -198 -> 33
		// 234: aload 4
		// 236: iload 7
		// 238: aaload
		// 239: invokestatic 453
		// com/cubic/autohome/common/view/image/cache/disc/impl/ext/Util:closeQuietly
		// (Ljava/io/Closeable;)V
		// 242: iload 7
		// 244: iconst_1
		// 245: iadd
		// 246: istore 7
		// 248: goto -35 -> 213
		//
		// Exception table:
		// from to target type
		// 4 26 166 finally
		// 39 64 166 finally
		// 67 73 166 finally
		// 80 139 166 finally
		// 139 163 166 finally
		// 171 180 166 finally
		// 186 200 166 finally
		// 215 224 166 finally
		// 234 242 166 finally
		// 67 73 209 java/io/FileNotFoundException
		// 171 180 209 java/io/FileNotFoundException
		// 186 200 209 java/io/FileNotFoundException
	}

	public boolean remove(String paramString)
    throws IOException
  {
    while (true)
    {
      Entry localEntry;
      int i;
      try
      {
        checkNotClosed();
        validateKey(paramString);
        localEntry = (Entry)this.lruEntries.get(paramString);
        if (localEntry != null)
        {
          localObject = localEntry.currentEditor;
          if (localObject == null);
        }
        else
        {
          bool = false;
          return bool;
        }
        i = 0;
        if (i >= this.valueCount)
        {
          this.redundantOpCount += 1;
          this.journalWriter.append("REMOVE " + paramString + '\n');
          this.lruEntries.remove(paramString);
          if (!journalRebuildRequired())
            break label221;
          this.executorService.submit(this.cleanupCallable);
          break label221;
        }
        Object localObject = localEntry.getCleanFile(i);
        if ((((File)localObject).exists()) && (!((File)localObject).delete()))
          throw new IOException("failed to delete " + localObject);
      }
      finally
      {
      }
      this.size -= localEntry.lengths[i];
      this.fileCount -= 1;
      localEntry.lengths[i] = 0L;
      i += 1;
      continue;
      label221: boolean bool = true;
    }
  }

	public final class Editor {
		private boolean committed;
		private final DiskLruCache.Entry entry;
		private boolean hasErrors;
		private final boolean[] written;

		private Editor(DiskLruCache.Entry arg2) {
			DiskLruCache.Entry localEntry;
			this.entry = localEntry;
			if (DiskLruCache.Entry.access$0(localEntry))
				;
			for (this$1 = null;; this$1 = new boolean[DiskLruCache.this.valueCount]) {
				this.written = DiskLruCache.this;
				return;
			}
		}

		public void abort() throws IOException {
			DiskLruCache.this.completeEdit(this, false);
		}

		public void commit() throws IOException {
			if (this.hasErrors) {
				DiskLruCache.this.completeEdit(this, false);
				DiskLruCache.this.remove(DiskLruCache.Entry
						.access$2(this.entry));
			}
			while (true) {
				this.committed = true;
				return;
				DiskLruCache.this.completeEdit(this, true);
			}
		}

		public OutputStream newOutputStream(int paramInt) throws IOException {
			synchronized (DiskLruCache.this) {
				if (DiskLruCache.Entry.access$1(this.entry) != this)
					throw new IllegalStateException();
			}
			if (!DiskLruCache.Entry.access$0(this.entry))
				this.written[paramInt] = true;
			File localFile = this.entry.getDirtyFile(paramInt);
			try {
				Object localObject2 = new FileOutputStream(localFile);
				localObject2 = new FaultHidingOutputStream(
						(OutputStream) localObject2, null);
				return localObject2;
			} catch (FileNotFoundException localFileNotFoundException1) {
				while (true) {
					DiskLruCache.this.directory.mkdirs();
					try {
						FileOutputStream localFileOutputStream = new FileOutputStream(
								localFile);
					} catch (FileNotFoundException localFileNotFoundException2) {
						OutputStream localOutputStream = DiskLruCache.NULL_OUTPUT_STREAM;
						return localOutputStream;
					}
				}
			}
		}

		private class FaultHidingOutputStream extends FilterOutputStream {
			private FaultHidingOutputStream(OutputStream arg2) {
				super();
			}

			public void close() {
				try {
					this.out.close();
					return;
				} catch (IOException localIOException) {
					DiskLruCache.Editor.this.hasErrors = true;
				}
			}

			public void flush() {
				try {
					this.out.flush();
					return;
				} catch (IOException localIOException) {
					DiskLruCache.Editor.this.hasErrors = true;
				}
			}

			public void write(int paramInt) {
				try {
					this.out.write(paramInt);
					return;
				} catch (IOException localIOException) {
					DiskLruCache.Editor.this.hasErrors = true;
				}
			}

			public void write(byte[] paramArrayOfByte, int paramInt1,
					int paramInt2) {
				try {
					this.out.write(paramArrayOfByte, paramInt1, paramInt2);
					return;
				} catch (IOException paramArrayOfByte) {
					DiskLruCache.Editor.this.hasErrors = true;
				}
			}
		}
	}

	private final class Entry {
		private DiskLruCache.Editor currentEditor;
		private final String key;
		private final long[] lengths;
		private boolean readable;
		private long sequenceNumber;

		private Entry(String arg2) {
			Object localObject;
			this.key = localObject;
			this.lengths = new long[DiskLruCache.this.valueCount];
		}

		private IOException invalidLengths(String[] paramArrayOfString)
				throws IOException {
			throw new IOException("unexpected journal line: "
					+ Arrays.toString(paramArrayOfString));
		}

		private void setLengths(String[] paramArrayOfString) throws IOException {
			if (paramArrayOfString.length != DiskLruCache.this.valueCount)
				throw invalidLengths(paramArrayOfString);
			int i = 0;
			try {
				while (true) {
					if (i >= paramArrayOfString.length)
						return;
					this.lengths[i] = Long.parseLong(paramArrayOfString[i]);
					i += 1;
				}
			} catch (NumberFormatException localNumberFormatException) {
			}
			throw invalidLengths(paramArrayOfString);
		}

		public File getCleanFile(int paramInt) {
			return new File(DiskLruCache.this.directory, this.key + "."
					+ paramInt);
		}

		public File getDirtyFile(int paramInt) {
			return new File(DiskLruCache.this.directory, this.key + "."
					+ paramInt + ".tmp");
		}

		public String getLengths() throws IOException {
			StringBuilder localStringBuilder = new StringBuilder();
			long[] arrayOfLong = this.lengths;
			int j = arrayOfLong.length;
			int i = 0;
			while (true) {
				if (i >= j)
					return localStringBuilder.toString();
				long l = arrayOfLong[i];
				localStringBuilder.append(' ').append(l);
				i += 1;
			}
		}
	}

	public final class Snapshot implements Closeable {
		private File[] files;
		private final InputStream[] ins;
		private final String key;
		private final long[] lengths;
		private final long sequenceNumber;

		private Snapshot(String paramLong, long arg3, File[] paramArrayOfInputStream, InputStream[] paramArrayOfLong, long[] arg7)
    {
      this.key = paramLong;
      this.sequenceNumber = ???;
      this.files = paramArrayOfInputStream;
      this.ins = paramArrayOfLong;
      Object localObject;
      this.lengths = localObject;
    }

		public void close() {
			InputStream[] arrayOfInputStream = this.ins;
			int j = arrayOfInputStream.length;
			int i = 0;
			while (true) {
				if (i >= j)
					return;
				Util.closeQuietly(arrayOfInputStream[i]);
				i += 1;
			}
		}

		public File getFile(int paramInt) {
			return this.files[paramInt];
		}
	}
}