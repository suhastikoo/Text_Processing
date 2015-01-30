package assignment1;

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
//import java.util.StringTokenizer;
import java.util.Map.Entry;

// Takes a text file as input and prints the frequency of 2Grams in decreasing order.
public class PartC {
	public static void main(String[] args) {
		try{
			//Long Start = System.currentTimeMillis();
			//FileInputStream fileIn = new FileInputStream("C:/Users/suhas/Desktop/test4.txt");
			//BufferedReader TextFile = new BufferedReader(new InputStreamReader(fileIn));
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter the file path.");
			String path = scan.nextLine();
			BufferedReader TextFile = new BufferedReader(new FileReader(path));
			List<String> TempList = new ArrayList<String>();
			Hashtable<Token, Integer> table = new Hashtable<Token, Integer>();
			try {
				String TempLine;
				String TokenBuffer = "";
				while((TempLine = TextFile.readLine()) != null){
					if (!TempLine.isEmpty()){
						if (TokenBuffer==""){
							TempList = PartB.TokenizeFile(TempLine, TempList);
							TokenBuffer = TempList.get(TempList.size() - 1);
							table = ComputeTwoGramFrequencies(TempList, table);
						}
						else{						
							TempList = PartB.TokenizeFile(TempLine, TempList);
							TempList.add(0, TokenBuffer);
							TokenBuffer = TempList.get(TempList.size() - 1);
							table = ComputeTwoGramFrequencies(TempList, table);
						}
						TempList.clear();
					}
				}
				Print(table);
				Print2GramCount(table);
				//Long End = System.currentTimeMillis();
				//System.out.println("Time: " + (End-Start)/1000);
				
			} catch (IOException e) {
				System.out.println("Error in reading file!!!");
			}
		}
		catch (FileNotFoundException e){
			System.out.println("File not found!!!");
		}
	}
	
	// Prints out the total 2Gram count in table.
	private static void Print2GramCount(Hashtable<Token, Integer> table) {
		int result = 0;
		for (Integer value : table.values()){
			result = result + value;
		}
		System.out.println("Total 2Gram count in table: " + result);		
	}
	
	// Print out the table in decreasing frequency.
	private static void Print(Hashtable<Token, Integer> table) {
		List<Map.Entry<Token, Integer>> obj = new ArrayList<Entry<Token, Integer>>(table.entrySet());
		Collections.sort(obj, new Comparator<Map.Entry<Token, Integer>>(){
			public int compare(Map.Entry<Token, Integer> entry1, Map.Entry<Token, Integer> entry2) {
	            Integer ent1 = entry1.getValue();
	            Integer ent2 = entry2.getValue();
				return ent2.compareTo(ent1);
	        }});
		
		for (Map.Entry<Token, Integer> entry : obj){
			//System.out.printf("%-25s%d%n", entry.getKey().token1, entry.getKey().token1, entry.getValue());
			System.out.print(entry.getKey().token1 + "   " + entry.getKey().token2 + "  ");
			System.out.println(" --> " + entry.getValue());
		}
		
	}
	
	// For tokens in list, compute 2Gram frequency and put in table.
	private static Hashtable<Token, Integer> ComputeTwoGramFrequencies(List<String> List, Hashtable<Token, Integer> table) {
		int i = 0;
		while(i < List.size()-1){
			Token obj = new Token();
			obj.token1 = List.get(i).toLowerCase();
			obj.token2 = List.get(i+1).toLowerCase();
			if (table.containsKey(obj)){
				Integer value = (Integer)table.get(obj);
				value += 1;
				table.put(obj, value);
			}
			else{
				table.put(obj, 1);
			}
			i++;
		}
		return table;
	}
}
