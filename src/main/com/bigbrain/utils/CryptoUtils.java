package com.bigbrain.utils;

import org.jasypt.util.text.BasicTextEncryptor;

public class CryptoUtils {

	public static String encrypt(String text) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("myEncryptionPassword");
        return textEncryptor.encrypt(text);
	}
	
	public static String decrypt(String encryptedText) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("myEncryptionPassword");
        return textEncryptor.decrypt(encryptedText);
	}
	
   public static void main(String[] args) {
	   System.out.println(decrypt(encrypt("test")));
	   System.out.println(encrypt("test"));
   }
	
}
