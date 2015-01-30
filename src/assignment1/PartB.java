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
import java.util.Map;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Map.Entry;

// Takes a text file as input and prints the frequency of tokens in decreasing order.
public class PartB {
	public static void main(String[] args) {
		try{
			//Long Start = System.currentTimeMillis();
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter the file path.");
			String path = scan.nextLine();
			BufferedReader TextFile = new BufferedReader(new FileReader(path));
			//FileInputStream fileIn = new FileInputStream("C:/Users/suhas/Desktop/test4.txt");
			//BufferedReader TextFile = new BufferedReader(new InputStreamReader(fileIn));
			List<String> TempList = new ArrayList<String>();
			Hashtable<String, Integer> table = new Hashtable<String, Integer>();
			try {
				String TempLine;
				while((TempLine = TextFile.readLine()) != null){
					if (!TempLine.isEmpty()){
						table = ComputeWordFrequencies(TokenizeFile(TempLine, TempList), table);
						TempList.clear();
					}
				}
				Print(table);
				PrintWordCount(table);
				//Long End = System.currentTimeMillis();
				//System.out.println("Time: " + (End-Start)/1000);
			}
			catch (IOException e) {
				System.out.println("Error in reading file!!!");
			}
		}
		catch (FileNotFoundException e){
			System.out.println("File not found!!!");
		}
	}
	
	// Prints out the total word count in table.
	public static void PrintWordCount(Hashtable<String, Integer> table) {
		int result = 0;
		for (Integer value : table.values()){
			result = result + value;
		}
		System.out.println("Total count in table: " + result);		
	}

	//Take a string and list as input and return a list of tokens containing only alphanumeric characters.
	public static List<String> TokenizeFile(String TempLine, List<String> TempList){
		TempLine = TempLine.replaceAll("[^a-zA-Z0-9]", " ");
		StringTokenizer fileIn = new StringTokenizer(TempLine);	
		
		while (fileIn.hasMoreTokens()){
			TempList.add(fileIn.nextToken());
		}
		return TempList;
		
	}
	
	// Print out the table in decreasing frequency. Referred from net.
	public static void Print(Hashtable<String, Integer> table) {
		List<Map.Entry<String, Integer>> obj = new ArrayList<Entry<String, Integer>>(table.entrySet());
		Collections.sort(obj, new Comparator<Map.Entry<String, Integer>>(){
			public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
	            Integer ent1 = entry1.getValue();
	            Integer ent2 = entry2.getValue();
				return ent2.compareTo(ent1);
	        }});
		
		for (Map.Entry<String, Integer> entry : obj){
			System.out.printf("%-25s%d%n", entry.getKey(), entry.getValue());
		}
	}
	
	// For tokens in list, compute their frequency and put in table.
	public static Hashtable<String, Integer> ComputeWordFrequencies(List<String> List, Hashtable<String, Integer> table) {
		for (String a : List){
			a = a.toLowerCase();
			if (table.containsKey(a)){
				Integer value = (Integer)table.get(a);
				value += 1;
				table.put(a, value);
			}
			else{
				table.put(a, 1);
			}
		}
		return table;		
	}
}
