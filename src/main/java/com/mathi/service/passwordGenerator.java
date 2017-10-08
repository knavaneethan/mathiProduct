package com.mathi.service;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;

public class passwordGenerator {
	public static void main(String[] args) {
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		Object salt = rng.nextBytes();

		//Now hash the plain-text password with the random salt and multiple 
		//iterations and then Base64-encode the value (requires less space than Hex): 
		String hashedPasswordBase64 = new Sha256Hash("pass", salt, 1024).toBase64();
		System.out.println("hashedPasswordBase64:"+hashedPasswordBase64);
		System.out.println("salt:"+salt);
	}

}
