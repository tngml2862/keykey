package com.whykeykey.it.util;

public class Main {
	public static void main(String[] args) 
	{
		test_base64();
		test_debug();
		test_hex();
	}
	
	public static void test_base64()
	{
		String b641= ("milk");
		byte[] bytes1=b641.getBytes();
		System.out.println(WKKBase64.encode(bytes1));
		
		String b64 = "QUJD";
		WKKBase64.decode(b64);
	}
	
	public static void test_hex() 
	{
		String suhee="13095468616e6b20596f75"; //printable String
		byte[] suHex=WKKDer.decode(suhee);
		System.out.print(WKKDer.tag(suhee));
		System.out.println(new String(suHex));
		
		String a1="130E416e79626f64792074686572653f";
		byte[] der1=WKKDer.decode(a1);
		System.out.print(WKKDer.tag(a1));
		System.out.println(new String(der1));

		String a2="0C044B494341";
		byte[] der2=WKKDer.decode(a2);
		System.out.print(WKKDer.tag(a2));
		System.out.println(new String(der2));
		
		byte[] src = new byte[1];
		String hex1="2017-12-24 18:00:00(GMT+9)";
		byte[] bytes2=hex1.getBytes();
		String hexEnc = WKKHex.encode(bytes2);
		byte[] hexDec = WKKHex.decode(hexEnc);

		WKKDebug.printHex("dec", hexDec);
		
	}

	public static void test_debug() 
	
	{
		String input4="BFA9B7AFBAD0C0C720BFCDC0CCC5B0C5B0BCD2C7C1C6AE20C0D4BBE7B8A620C3E0C7CFC7D5B4CFB4D9";
		byte[] bytes2= WKKHex.decode(input4);
		String title="data";
		WKKDebug.printHex(title, bytes2);
	}
}
