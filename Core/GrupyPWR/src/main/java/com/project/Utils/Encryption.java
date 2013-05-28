package com.project.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Encryption {
	public static String encrypt(String source) {
		String md5 = null;
		try {
			MessageDigest mdEnc = MessageDigest.getInstance("MD5");
			mdEnc.update(source.getBytes(), 0, source.length());
			md5 = new BigInteger(1, mdEnc.digest()).toString(16);
		} catch (Exception ex) {
			return null;
		}

		int len = md5.length();
		if (len < 16) {
			return "0" + md5;
		} else {
			return md5;
		}

	}
}
