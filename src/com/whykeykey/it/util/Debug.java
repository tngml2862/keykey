package com.whykeykey.it.util;

public class Debug
{
	public static void printHex(char[] title, byte[] input)
	{
		int len=input.length;
		System.out.println("data[]  "  + len  +"  bytes");
		for(int i=1;i<=16*3;i++)
		{
			System.out.print('-');
		}
		System.out.println();
		for(int i=1;i<=16;i++)
		{
			if(i<10)
				System.out.print(" "+ i + ":");
			else
				System.out.print(i +":");
		}
		System.out.println();
		for(int i=1;i<=16*3;i++)
		{
			System.out.print('=');
		}
		System.out.println();
		for(int i=0;i<len*2;i++)
		{
			if(i%(16*2)==0&&i!=0)
				System.out.println();
			if(i%2==1)
			{
				System.out.print(title[i] + ":");
			}
			else
				System.out.print(title[i]);	
		}
		System.out.println();
		for(int i=1;i<=16*3;i++)
		{
			System.out.print('-');
		}
	}

}
