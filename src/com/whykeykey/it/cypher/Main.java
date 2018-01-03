package com.whykeykey.it.cypher;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.whykeykey.it.util.WKKDebug;
import com.whykeykey.it.util.WKKDer;
import com.whykeykey.it.util.WKKHex;

public class Main

{
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{	
		test();
	}
	
	public static void test() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		byte[] plain = WKKHex.decode("000102030405060708090A0B0C0D0E0F000102030405060708090A0B0C0D0E0F0001020304050607");
		WKKDebug.printHex("plain", plain);
		CymCipher.PKCS5_pedding(plain);
		
		byte[] key=WKKHex.decode("01010101010101010101010101010101");
		WKKDebug.printHex("key", key);
		
		byte[] iv=WKKHex.decode("02020202020202020202020202020202");
		
		CymCipher.AESEncrypt(plain , key,  iv);			
	}

}
