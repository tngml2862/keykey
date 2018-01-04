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
		
		byte[] CipherM = WKKHex.decode("7065AB7B96594B2ABC0801B26A32F25F7B94E007EB175BF05436364C5CF0B653FEE1958847C7379BDCEDB38EEB2E8922"); 
		WKKDebug.printHex("CipherMessage", CipherM);
		
		byte[] key=WKKHex.decode("01010101010101010101010101010101");

		byte[] iv=WKKHex.decode("02020202020202020202020202020202");
		
		CymCipher.AESEncrypt(plain , key,  iv);	
		CymCipher.AESDecrypt(CipherM, key, iv);
	}

}
