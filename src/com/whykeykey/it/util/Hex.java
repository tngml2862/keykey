package com.whykeykey.it.util;

public class Hex 
{
	static char[]hex_table= {'0','1','2','3','4','5','6','7','8','9',
		                    	'A','B','C','D','E','F'};
	public static String encode(byte[] input) 
	{
		int len=input.length;
		String text=new String(input);
		char[] text_hex=text.toCharArray();  //char로 변환
		int he=0;
		char[] sum_hex;
		sum_hex=new char[2*len];
		
		for(int i=0;i<len;i++)
			
		{
			int[] num_binary;
			num_binary=new int[2];

			int num= (int)text_hex[i];
			int count=0;

			while(num>=16)   //아스키코드값을 16진수로 변환
			{
				for(int j=0;j<2;j++)
				{
					num_binary[j]=num%16;
					count++;
					num=num/16;
				}
			}
			
			for(int j=count-1;j>=0;j--)
			{
				sum_hex[he]=hex_table[num_binary[j]];
				he++;
			}
		}
		Debug.printHex(sum_hex, input);
		//for(int i=0;i<len*2;i++)
		//{
		//	System.out.print(sum_hex[i]);
		//}
		//System.out.println();
		return text_hex.toString();
	}
	
	public static byte decode(String input)
	{
		char[] text=input.toCharArray();
		int len=input.length();
		int[] hex_dec;
		hex_dec=new int[len];
		int[] hex_todec;          // 디코딩 할 10진수
		hex_todec=new int[len/2];
		char[] hex_result;
		hex_result=new char[len/2];
		int hex=0;
		for(int i=0;i<len;i++)
		{
			for(int j=0;j<16;j++)
				if(text[i]==hex_table[j])
					hex_dec[i]=j;
		}
		
		for(int a=0;a<len/2;a++)
		{
			int num=0;
			for(int i=0;i<2;i++)
			{
				if(hex==0||hex%2==0)
				{
					num=hex_dec[hex]*16;
				}
				else
					num+=hex_dec[hex];
				hex++;
			}
			hex_todec[a]=num;
			hex_result[a]=(char)hex_todec[a];
		}
		for(int k=0;k<len/2;k++)
			System.out.print(hex_result[k]);
		return 0;
	}

}
