package com.whykeykey.it.util;

public class WKKDer
{	
	static char[]hex_table= {'0','1','2','3','4','5','6','7','8','9',
            'A','B','C','D','E','F'};

	public static byte[] decode(String input)
	{
		char[] text=input.toCharArray();
	    int len=input.length();
	    int hex_length;
	    int[] hex_dec;
	    hex_dec=new int[len];
	    
	    for(int i=0;i<len;i++)
	    {
	       for(int j=0;j<16;j++)
	          if(text[i]==hex_table[j])
	             hex_dec[i]=j;
	    }
	    
	    hex_length=hex_dec[2]*10+hex_dec[3];
	    System.out.println(hex_length);
	    
	    int n=0;
	    char[] s;
	    s=new char[hex_length*2];
	    
	    for(int j=4;j<4+(hex_length*2);j++)
	    {
	    	s[n]=text[j];
	    	n++;
	    }
	    byte[] suHex=WKKHex.decode(new String(s));  //length¸¸Å­ decoding
	
	    return suHex;
	}
	
	public static String tag (String input)
	{
		char[] text=input.toCharArray();

		String tag = null ;
	    if(text[0]==hex_table[1]&&text[1]==hex_table[3])
	    {
	    	tag= "printable String  ";
	    }
	    
	    else if(text[0]==hex_table[0]&&text[1]==hex_table[12])
	    {
	    	tag="UTF8 String  ";
	    }
		return tag;		
	}
}

