package com.cubic.autohome.common.view.image.cache.disc.naming;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5FileNameGenerator implements FileNameGenerator {

	@Override
	public String generate(String paramString) {
		return new BigInteger(getMD5(paramString.getBytes())).abs().toString(36);
	}
	
	private byte[] getMD5(byte[] bytes) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(bytes);
			return messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
