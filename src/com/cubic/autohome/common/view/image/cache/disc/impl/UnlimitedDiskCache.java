package com.cubic.autohome.common.view.image.cache.disc.impl;

import java.io.File;

import com.cubic.autohome.common.view.image.cache.disc.naming.FileNameGenerator;

public class UnlimitedDiskCache extends BaseDiskCache {

	public UnlimitedDiskCache(File paramFile1, File paramFile2,
			FileNameGenerator paramFileNameGenerator) {
		super(paramFile1, paramFile2, paramFileNameGenerator);
		
	}

}
