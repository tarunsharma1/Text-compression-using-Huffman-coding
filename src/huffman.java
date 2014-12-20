

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.*;
import java.io.*;

public class huffman {
	char a[]=new char[256];
	int freq[]=new int[256];
	int index=0;
	String s="";
	String text;
	int counter=0;
	int i=0;
	Dlnode temp_main;
	String[] compress=new String[256];
	Priority P=new Priority();
	
		//FileOutputStream f = new FileOutputStream("C:\\Users\\tarun_000\\Desktop\\hello.txt");;
	 
	
	public void count(String s)
	{
		
		text=s;
		for(int i=0;i<s.length();i++)
		{
			int count=0;
			char ch=s.charAt(i);
			for(int k=0;k<index;k++){
				if(a[k]!=ch)
					count++;
			}
			if(count==index) //element not present
			{
				
				a[index]=ch;
				freq[index]=1;
				index++;
			}
			else          //element present
			{
				int j;
				for( j=0;j<index;j++)
				{
					if(a[j]==ch)
						break;
				}
				freq[j]++;
				
			}
			
		}
		
	}
	public void display()
	{
		for(int i=0;i<index;i++)
		{
			System.out.println(a[i]+"-"+freq[i]);
		}
	}
	public void insert()
	{
		
		for(int i=0;i<index;i++)
		{
			P.insert((int)a[i], freq[i]);
		}
		P.display();
	}
	public void pop()
	{
		int sum=0;
		Dlnode temp2=null;
		Dlnode temp=new Dlnode();
		while(P.size>1)
		{
		 sum=0;
		 temp=new Dlnode();
		for(int i=0;i<2;i++)  //pop first two and add and again insert
		{
			temp2=null;
		try {
			temp2=P.removeMin();
			//System.out.println(P.removeMin());
			sum=sum+temp2.getkey();
			if(i==0)
				temp.left=temp2;
			else if(i==1)
				temp.right=temp2;
		} catch (ListEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		//System.out.println(sum);
		P.insert(123, sum,temp);
		
		
		}
		//sum contains root of tree now
		
		System.out.println("root of tree is " + sum);
		
		//assign(temp);
		
		
		//postorder(temp);
		temp_main=temp;
		buildPath(temp,""); //works !!
	   print();
	}
	
	
	
	void buildPath(Dlnode n, String path) {
		   if (n.left != null) buildPath(n.left, path+"0");
		   if (n.right != null) buildPath(n.right, path+"1");
		   if (n.left == null && n.right == null) {
			   //System.out.println((char)n.data+""+path);
			   compress[i]=path;
			   a[i]=(char)n.data;
			   i++;// a child node
		      // process the path given to this node
		   }
		}
	public void print()
	{
		BST bst=new BST();
		for(int i=0;i<index;i++)
		{
			
		 System.out.println(compress[i]+" "+a[i]); //new code for each character
			
			//int x=Integer.parseInt(compress[i]);
			bst.insert((int)a[i],compress[i]);
			
		}
		//System.out.println(text);
		String s="";
		StringBuffer str=new StringBuffer();
		
		for(int i=0;i<text.length();i++)
		{
			int x=text.charAt(i);
			//System.out.println(bst.search(x));
			str.append(bst.search(x));
			
		}
		s=str.toString();
		
		//System.out.println("code is "+s);//encoded string
		int l=s.length()%8;
		if(l!=0)
		{
		l=8-l;
		String s2="";
		switch(l)
		{
		case 0:
			s2="000";
			break;
		case 1:
			s2="001";
			break;
		case 2:
			s2="010";
			break;
		case 3:
			s2="011";
			break;	
		case 4:
			s2="100";
			break;
		case 5:
			s2="101";
			break;	
		case 6:
			s2="110";
			break;
		case 7:
			s2="111";
			break;	
		
		}
		
		
		s=s2+s;
		}
		else
		{
			s="000"+s;
		}
		
		//System.out.println("final code is "+s);
		write_to_file(s);
			
	}
	private void write_to_file(String s)
	{
		//FileOutputStream f= new FileOutputStream("C:\\Users\\tarun_000\Desktop\\hello.txt");
		String s2=s.substring(0, 3);
		int a=Integer.parseInt(s2);
		int count=0;
		switch(a)
       {
		case 0:
			count=0;
			break;
		case 1:
			count=1;
			break;
		case 10:
			count=2;
			break;
		case 11:
			count=3;
			break;
		case 100:
			count=4;
			break;	
		case 101:
			count=5;
			break;	
		case 110:
			count=6;
			break;	
		case 111:
			count=7;
			break;	
		default:
			count=598;
		
		}
		System.out.println(count);
		counter=count;
		s=s.substring(3);//start dividing code in bytes here
		//int i=0;
		//System.out.println("checking:code is "+s);
		int length=s.length();
		FileOutputStream f=null;
		try {
			 f=new FileOutputStream("C:\\Users\\tarun_000\\Desktop\\DSJ project\\hello.bin");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
			
 
		
		for(int k=0;k<s.length();k=k+8)
		{
			
			int abcd=0;
			for(int j=k,h=7;j<Math.min(k+8, s.length());j++,h--)
			{
				int ab=s.charAt(j);
				int abc=ab-48;
				
				abcd+=abc*Math.pow(2,h);
				
				
				//System.out.println(s.charAt(j));
				
			}
			//System.out.println(abcd);
			//file.write(abcd);
			//f.out(abcd);
			//write to a file
			
			try {
				
				
				f.write(abcd);
				f.flush();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		try {
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}    //WORKS !!!
	
	private void decode()
	{
		String str="";
		String str2="";
		StringBuffer str3=new StringBuffer();
		
		try {
			FileInputStream f= new FileInputStream("C:\\Users\\tarun_000\\Desktop\\DSJ project\\hello.bin");
			int num=f.available();
			for(int i=0;i<num;i++)
			{
				int a=f.read();
				//System.out.println("testing---"+a);
				str=Integer.toBinaryString(a);
				
				while(str.length()!=8)
				{
					str='0'+str;
					
				}
				str3.append(str);
				
				
				
			}
			str2=str3.toString();
			
			str2=str2.substring(0,str2.length()-counter);
			//System.out.println("testing---"+str2);   //final decoded string
			
			f.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recursion(str2);
	}
	/*private void convert_to_text(String s)
	{
		String str="";
		int i=1;
		while(recursion()!=0)
		{
			str=s.substring(0,i);
			recursion(str);
			
		}
		
	}
	*/
	void recursion(String s)
	{
		char ch;
		String decoded="";
		StringBuffer decoder=new StringBuffer();
		Dlnode nptr = temp_main;
		String temp="";
		
		for(int i=0;i<s.length();i++)
		{
			temp+=s.charAt(i);
			ch=s.charAt(i);
		int move=ch-48;
		
		switch(move)
		{
		case 0:
			if(nptr.left!=null)
			     nptr=nptr.left;
			
			break;
		case 1:
	    	if(nptr.right!=null)
			    nptr=nptr.right;
		}
		if(nptr!=null)
		{
			
		if(nptr.data!=123)
		{
			//decoded+= (char)nptr.data;
			decoder.append((char)nptr.data);
			temp="";
			nptr=temp_main;
			continue;
		}
		else{
			
			continue;
		}
		}
		else
		{
			continue;
		}
		}
		decoded=decoder.toString();
		//System.out.println(decoded);
		try {
			File file = new File("C:\\Users\\tarun_000\\Desktop\\DSJ project\\decoded.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(decoded);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String args[])
	{
		long start=System.nanoTime();
		huffman H = new huffman();
		BufferedReader br =null;
		String everything="";
	    try {
	    	 br = new BufferedReader(new FileReader("C:\\Users\\tarun_000\\Desktop\\DSJ project\\typo.txt"));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	         everything = sb.toString();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
		H.count(everything);
		//H.display();
		H.insert();
		H.pop();
		long end=System.nanoTime()-start;
		System.out.println("encoding time is "+ end+" nano seconds");
		long start2=System.nanoTime();
		H.decode();
		long end2=System.nanoTime()-start2;
		System.out.println("decoding time is "+ end2+" nano seconds");
 
		
	}

}
