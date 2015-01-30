package assignment1;

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

//Takes a text file as input and prints the frequency of 2Grams in decreasing order.
public class PartD {

	public static void main(String[] args) {
		try {
			//Long Start = System.currentTimeMillis();
			//FileInputStream fileIn = new FileInputStream("C:/Users/suhas/Desktop/test2.txt");
			//BufferedReader TextFile = new BufferedReader(new InputStreamReader(fileIn));
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter the file path.");
			String path = scan.nextLine();
			BufferedReader TextFile = new BufferedReader(new FileReader(path));
			List<String> TempList = new ArrayList<String>();
			List<String> List = new ArrayList<String>();
			Hashtable<String, Integer> table = new Hashtable<String, Integer>();
			
			try {
				String TempLine;
				while((TempLine = TextFile.readLine()) != null){
					if (!TempLine.isEmpty()){
						TempList = PartA.tokenizeFile(TempLine, TempList);
						if (List.isEmpty()){
							List = ComputePalindromeFrequencies(TempList, table).List;
						}
						else{
							List.addAll(TempList);
							TempList.clear();
							TempList.addAll(List);
							List.clear();
							List = ComputePalindromeFrequencies(TempList, table).List;
						}
						TempList.clear();
					}					
				}
				if (!List.isEmpty()){
					LastSection(List,table);
				}
				PartB.Print(table);
				PartB.PrintWordCount(table);
				//Long End = System.currentTimeMillis();
				//System.out.println("Time: " + (End-Start)/1000);
			} 
			catch (IOException e) {
				System.out.println("Error in reading file!!!");
			}	
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!!!");
		}
	}
	
	// Special case for last part of file, to detect palindromes in that.
	private static void LastSection(List<String> list, Hashtable<String, Integer> table) {
		for (int i=0; i<list.size(); i++){
			int flag = 0;
			int j = i;
			String str = list.get(i);
			while (flag == 0){
				if (CheckPalindrome(str)){
					str = str.replaceAll(" ", "");
					if (table.containsKey(str)){
						Integer value = (Integer)table.get(str);
						value += 1;
						table.put(str, value);
					}
					else{
						table.put(str, 1);
					}
				}
				j = j + 1;
				if (j<list.size()){
					str = str + " " + list.get(j).toLowerCase();
				}
				else{
					flag = 1;
					break;
				}
			}
		}		
	}

	// For tokens in list, detect palindromes and compute their frequency and put in table.
	private static ReturnObject ComputePalindromeFrequencies(List<String> TempList, Hashtable<String, Integer> table) {
		List<String> List = new ArrayList<String>();
		String temp = "";
		int flag = 0;
		int flag1 = 0;
		for (int i=0; i<TempList.size(); i++){
			if (flag==1 || flag1 == 1){
				break;
			}			
			else{
				List.clear();
				int j = i;
				temp = TempList.get(i).toLowerCase();
				List.add(TempList.get(i).toLowerCase());
				while(temp.length() <= 20){
					j = j + 1;
					if (j<TempList.size()){
						temp = temp + " " + TempList.get(j).toLowerCase();
						List.add(TempList.get(j).toLowerCase());
					}
					else{
						flag1 = 1;
						//List.remove(0);
						break;
					}
				}
				if (flag1 == 0){
					temp = TempList.get(i).toLowerCase();
					j = i;
					List.clear();
					List.add(temp);
					while(temp.length() <= 20 && flag==0){				
						if (CheckPalindrome(temp)){
							temp = temp.replaceAll(" ", "");
							if (table.containsKey(temp)){
								Integer value = (Integer)table.get(temp);
								value += 1;
								table.put(temp, value);
							}
							else{
								table.put(temp, 1);
							}
						}
						j = j + 1;
						if (j<TempList.size()){
							temp = temp + " " + TempList.get(j).toLowerCase();
							List.add(TempList.get(j).toLowerCase());
						}
						else{
							flag = 1;
							List.remove(0);
							break;
						}
					}
				}
			}
	}
		return new ReturnObject(List, table);
	}

	// Takes a string as input and detects if it's palindrome or not.
	private static boolean CheckPalindrome(String temp) {
		temp = temp.replaceAll(" ", "");
		StringBuffer obj = new StringBuffer(temp);
		if (temp.equals(obj.reverse().toString())){
			return true;
		}
		else{
			return false;
		}		
	}
}
