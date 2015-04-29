package com.cubic.autohome.common.view.image.cache.disc.naming;

public class HashCodeFileNameGenerator implements FileNameGenerator {

	@Override
	public String generate(String paramString) {
		return String.valueOf(paramString.hashCode());
	}

}
