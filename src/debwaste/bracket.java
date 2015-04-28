/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debwaste;

/**
 *
 * @author Aravind CM
 */
import java.util.Stack;

public class bracket 
{
	static Stack<Character> stack = new Stack<Character>();
	static int pop_count = 0;
	public static void main(String[] args) 
	{
		String str1="{{i{int f;eb}}}";
		int x = 0;
		for(int i=0;i<str1.length()-pop_count-1;i++)
		{
			 x=isParenthesisMatch(str1.charAt(i));
			//System.out.println(x);
			 /*if(stack.isEmpty())
				 final_size = x;*/
			for(int j=1;j<=x;j++)
				System.out.print("\t");
                        
                        while(str1.charAt(i)!=';')
                        {
                            System.out.print(str1.charAt(i));
                            i++;
                        }
                            
			

		}
		for(int k = pop_count;k>0;k--)
			System.out.print("\t");


	}
	public static int isParenthesisMatch(char str) {

		if (str != '{'&& str!='}')
			return stack.size()+1; // The statement should begin with {///



		char c;
		char t='\t';

		c = str;


		if(c == '{'){
			stack.push(t);
			return stack.size();
		}
		else if(c == '}')
		{	
			stack.pop();
			pop_count++;
			return stack.size();
		}	

		return ' ';
		
	}
	public static boolean isEmpty()
	{
		return (stack.size()==0);
	}
}
