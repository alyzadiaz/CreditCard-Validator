package edu.wit.dcsn.comp1050.lab01;
/*
 * Alyza Diaz Rodriguez
 * Comp 1050-05
 * Lab 1
 */
import java.util.Scanner;
public class CreditCardValidator{
	public static void main( String[] args ){
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter a credit card number as a long integer: ");
		long creditCard = in.nextLong();
		in.close();
		
		if(isValid(creditCard)){
			System.out.println(creditCard + " is valid");
		}else{
			System.out.println(creditCard + " is invalid");
		}
	}
	public static boolean isValid(long number){
		int checkSum = (sumOfDoubleEvenPlace(number)+sumOfOddPlace(number));
		
		if((prefixMatched(number,4) || prefixMatched(number,5) || prefixMatched(number,37) || prefixMatched(number,6)) && checkSum%10==0 
				&& getSize(number)>=13 && getSize(number)<=16){	//check the card has a valid prefix, length, and sum of digits
			return true;
		}else{
			return false;
		}
	}
	public static int sumOfDoubleEvenPlace(long number){
		String n = Long.toString(number);	//convert long into string
		int sum = 0; //sum of the even
		
		for(int i=0;i<n.length();i+=2){
			sum = sum + (getDigit(Integer.parseInt(n.substring(i, i+1))*2));
		}
		return sum;
	}
	public static int getDigit(int number){
		if(number%10==number){	//separate two digits into single and add
			return number;
		}else{
			return (number/10)+(number%10);
		}
	}
	public static int sumOfOddPlace(long number){
		String n = Long.toString(number); //convert long to string
		int sum = 0; //sum of the odd
		
		for(int i=1;i<n.length();i+=2){
			sum = sum + (getDigit(Integer.parseInt(n.substring(i, i+1))));
		}
		return sum;
	}
	public static boolean prefixMatched(long number, int prefix){
		if(getPrefix(number,1)==prefix){
			return true;
		}else{
			return false;
		}
	}
	public static int getSize(long number){
		String n = Long.toString(number);
		return n.length();
	}
	public static long getPrefix(long number, int k){	//gets the prefix number
		String n = Long.toString(number);	//convert long to string
		long pfix = Long.parseLong(n.substring(0,k));	//takes the first number of the string
		
		if(pfix==3){	//taking the second number of the string as well to manage the amex prefix 37
			pfix = Long.parseLong(n.substring(0,k+1));
		}
		return pfix;
	}
}