package com.whykeykey.it.cypher;

import com.whykeykey.it.cypher.*;
import com.whykeykey.it.util.WKKDebug;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class CymCipher
{
	public static byte[] AESEncrypt(byte[] plain,byte[] key, byte[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		int remain = plain.length % 16;
		int padLength = 16 - remain;		
		int needLength = plain.length + padLength;

		byte[] padPlain = new byte[needLength];
		int plain_count = padPlain.length/16;
		
		byte[][] Plain_Cbc = new byte[plain_count][16];
		byte result[] = new byte[needLength];
		
		if(plain_null_check(plain)==true && key_length_check(key)==true && iv_length_check(iv)==true)
		{
			padPlain=PKCS5_pedding(plain);
			
			int count=0;
			for(int i=0;i<plain_count;i++)
			{
				for(int j=0;j<16;j++)
				{
					Plain_Cbc[i][j] = padPlain[count+j];
				}
				count+=16;
			}
			
			byte[][] Xor_Cbc = new byte[plain_count][16];          
			byte[][] BlockCE = new byte[plain_count][16];
			
			for(int j=0;j<16;j++)
				Xor_Cbc[0][j] = (byte)(Plain_Cbc[0][j] ^ iv[j]) ; //첫번째 블록  ^ Iv
			
			SecretKey secretKey = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE,secretKey);
			BlockCE[0] = cipher.doFinal(Xor_Cbc[0]);
			
			
			int b=0;
			for(int i=1;i<plain_count;i++)
			{
				for(int j=0;j<16;j++)
					Xor_Cbc[i][j] = (byte)(Plain_Cbc[i][j] ^ BlockCE[b][j]) ;
				b++;
				BlockCE[i] = cipher.doFinal(Xor_Cbc[i]);
			}
			
			int c=0;
			for(int k=0;k<plain_count;k++)
			{
				for(int l=0;l<16;l++)
				{
					result[c]=BlockCE[k][l];
					c++;
				}
			}
			WKKDebug.printHex("Cypher_Encrypt_result ", result);
		}
		else
			System.out.println("There is somthing wrong");
	
		return result;
	}
	
	public static boolean plain_null_check(byte[] plain)
	{
		if(plain==null)
			return false;
		else
			return true;
	}
	
	public static boolean key_length_check(byte[] mkey)
	{
		if(mkey.length==16)
			return true;
		else
			return false;
	}
	
	public static boolean iv_length_check(byte[] iv)
	{
		if(iv.length==16)
			return true;
		else
			return false;
	}
	
	public static byte[] PKCS5_pedding(byte[] plain)
	{
		int remain = plain.length % 16;
		int padLength = 16 - remain;
		int needLength = plain.length + padLength;
		
		byte[] padPlain = new byte[needLength];
		System.arraycopy(plain, 0, padPlain, 0, plain.length);
		
		for(int i=(padPlain.length - padLength); i<padPlain.length; i++) 
		{
			padPlain[i] = (byte)padLength;
		}
		
		return padPlain;		
	}
	
	public static byte[] AESDecrypt(byte[] CipherMessage,byte[] key, byte[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		int totalLength = CipherMessage.length;  //48 bytes
		int block_count = totalLength/16;   //3
		
		byte result[] = new byte[totalLength];
		
		byte Cipher_block[][] = new byte[block_count][16];
		
		if(plain_null_check(CipherMessage)==true && key_length_check(key)==true && iv_length_check(iv)==true)
		{
			int count=0;
			for(int i=0;i<block_count;i++)    //block 크기로 cut 
			{
				for(int j=0;j<16;j++)
				{
				Cipher_block[i][j] = CipherMessage[count++];
				}
			}
		
			byte BlockCD[][] = new byte[block_count][16];
			for(int i=0;i<block_count;i++)
			{
				SecretKey secretKey = new SecretKeySpec(key,"AES");
				Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
				cipher.init(Cipher.DECRYPT_MODE,secretKey);
				BlockCD[i] = cipher.doFinal(Cipher_block[i]);
				WKKDebug.printHex("blockCD i", BlockCD[i]);
			}
			byte plainM[][] = new byte[block_count][16];
			for(int i=block_count-1;i>0;i--)
			{
				for(int j=0;j<16;j++)
					plainM[i][j] = (byte)(BlockCD[i][j] ^ Cipher_block[i-1][j]);
			}
			for(int j=0;j<16;j++)                       //first block ^ iv
				plainM[0][j] = (byte)(BlockCD[0][j] ^ iv[j]);
			
			int c=0;
			for(int k=0;k<block_count;k++)
			{
				for(int l=0;l<16;l++)
					result[c++] = plainM[k][l];
			}
			WKKDebug.printHex("Cipher_Decrypt_result", result);
		}	
		else
			System.out.println("There is something wrong");
		
		return result;
	}
}
