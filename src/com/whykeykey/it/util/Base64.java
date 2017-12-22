package com.whykeykey.it.util;

import java.io.*;
import java.util.Scanner;
import java.lang.Math;

public class Base64 
{
	public static void main(String[] args)
	{
		String input,input2,input3,input4;
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Base64 ���ڵ� �� ���ڸ� �Է����ּ���");
		input=scan.nextLine();  	//String input="ABC";
		byte[] bytes=input.getBytes();
		encode(bytes);		
		
		Scanner scan2=new Scanner(System.in);
		System.out.println("Base64 ���ڵ� �� ���ڸ� �Է����ּ���");
		input2=scan.nextLine();  	//String input="ABC";
		decode(input2);	
		
		Scanner scan3=new Scanner(System.in);
		System.out.println("Hex ���ڵ� �� ���ڸ� �Է����ּ���");
		input3=scan.nextLine();
		byte[] bytes2= input3.getBytes();
        Hex.encode(bytes2);
        
        Scanner scan4=new Scanner(System.in);
        System.out.println();
		System.out.println("Hex ���ڵ� �� ���ڸ� �Է����ּ���");
		input4=scan4.nextLine();		
		Hex.decode(input4);
	}
	
	public static String encode(byte[] input)
	{
		String text=new String(input);
		int sb=0;
		char[] text_ch=text.toCharArray(); //�ƽ�Ű�ڵ� ������ ��ȯ
	
		int sb_size= (text_ch.length)*8;
		int[] sum_binary;
		sum_binary=new int[sb_size];

		for(int i=0;i<text_ch.length;i++)
		{
			int[] num_binary;
			num_binary=new int[8];

			int num= (int)text_ch[i];
			int count=0;

			while(num>=2)   //�ƽ�Ű�ڵ尪�� 2������ ��ȯ
			{
				for(int j=0;j<8;j++)
				{
					num_binary[j]=num%2;
					count++;
					num=num/2;
				}
			}
			
			for(int j=count-1;j>=0;j--)    //2���� ��ġ��
			{
				sum_binary[sb]=num_binary[j];
				sb++;
			}
		}
		int SbSize=sb_size;
		int row=(int)Math.ceil(sb_size/6);
		int[][] six_binary;
		six_binary=new int[row][6];
		
		while(sb_size>0)     //6��Ʈ�� �ڸ���
		{
			int sixdigit=0;
			for(int k=0;k<row;k++)
			{
				for(int l=0;l<6;l++)
				{
					if( SbSize%6!=0&&sb_size==0&&sixdigit>row+6)  //�е�
					{
						int pedding_num=6-((6*row)-SbSize);
						for(int q=pedding_num;q<6;q++)
						{
							six_binary[row-1][q]=0;
						}
					}
					else
					{
						six_binary[k][l]=sum_binary[l+sixdigit];
						sb_size--;
					}
				}
				sixdigit+=6;
			}
		}
		int[] sum_decimal;
		sum_decimal=new int[row];
		
		for(int k=0;k<row;k++)       //10������ �ٽú�ȯ
		{
			int n1,n2,n3=0;
			int num_six=6;
			for(int l=0;l<6;l++)
			{
				n1=(int)Math.pow(2, num_six-1);
				n2=six_binary[k][l]*n1;
				n3+=n2;
				num_six--;
			}
			sum_decimal[k]=n3;
		}
		int[][] count_decimal;
		count_decimal=new int[row][64];
		
		for(int jj=0;jj<row;jj++)
		{
			for(int j=0;j<64;j++)
			{
				if(sum_decimal[jj]==j)
					count_decimal[jj][j]=1;
				else
					count_decimal[jj][j]=0;
			}
		}
		
		char[] basetable= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N',
				           'O','P','Q','R','S','T','U','V','W','X','Y','Z',
				           'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
				           'o','p','q','r','s','t','u','v','w','x','y','z',
				           '0','1','2','3','4','5','6','7','8','9','+','/'};
		
		for(int k=0;k<row;k++)	
		{
			for(int kk=0;kk<64;kk++)
			{
				if(count_decimal[k][kk]==1)
				{
					System.out.print(basetable[kk]);
				}
			}
		}
		System.out.println();
		if(((text_ch.length)-1/3)==0)
			System.out.print("==");
		if(((text_ch.length)-2/3)==0)
			System.out.print("=");
		return "";
	}
	
	public static byte decode(String input)
	{
		int dc_size= input.length();
		int full_dc=dc_size*6;
		int[] sum_dc;
		sum_dc=new int[full_dc];
		int sb2=0;
		char[] text=input.toCharArray();
		char[] basetable= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N',
		           'O','P','Q','R','S','T','U','V','W','X','Y','Z',
		           'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
		           'o','p','q','r','s','t','u','v','w','x','y','z',
		           '0','1','2','3','4','5','6','7','8','9','+','/'};
		int[] to_dem;
		to_dem=new int[dc_size];
		
		for(int i=0;i<dc_size;i++)   //base table�� ��ġ
		{
			for(int j=0;j<64;j++)
			{
				if(text[i]==basetable[j])
					to_dem[i]=j;
			}
		}
		
		for(int i=0;i<to_dem.length;i++)
		{
			int[] dc_binary;
			dc_binary=new int[8];

			int num= (int)to_dem[i];
			int count=0;

			while(num>=2)   //�ƽ�Ű�ڵ尪�� 2������ ��ȯ
			{
				for(int j=0;j<6;j++)
				{
					dc_binary[j]=num%2;
					count++;
					num=num/2;
				}
			}
			for(int k=count-1;k>=0;k--)
			{
				sum_dc[sb2]=dc_binary[k];
				sb2++;
			}
		}
		int SbSize=full_dc;
		int row=(int)Math.ceil(full_dc/8);
		int[][] eight_binary;
		eight_binary=new int[row][8];
				
		while(full_dc>0)     //8��Ʈ�� �ڸ���
		{
			int eightdigit=0;
			for(int k=0;k<row;k++)
			{
				for(int l=0;l<8;l++)
				{
					if( SbSize%8!=0&&full_dc==0&&eightdigit>row+6)  //�е�
					{
						int pedding_num=8-((8*row)-SbSize);
						for(int q=pedding_num;q<8;q++)
						{
							eight_binary[row-1][q]=0;
						}
					}
					else
					{
						eight_binary[k][l]=sum_dc[l+eightdigit];
						full_dc--;
					}
				}
				eightdigit+=8;
			}
		}
		int[] dc_decimal;
		dc_decimal=new int[row];
		
		for(int k=0;k<row;k++)       //10������ �ٽú�ȯ
		{
			int n1,n2,n3=0;
			int num_eight=8;
			for(int l=0;l<8;l++)
			{
				n1=(int)Math.pow(2, num_eight-1);
				n2=eight_binary[k][l]*n1;
				n3+=n2;
				num_eight--;
			}
			dc_decimal[k]=n3;
		}
		
		for(int k=0;k<row;k++)
		{
			System.out.print((char)dc_decimal[k]);
		}
		System.out.println();
		return 0;
	}

}
