package assignment1;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

// Takes text file as input and prints out the alphanumeric tokens.
public class PartA {
	public static void main(String[] args) {
		try{
			//FileInputStream fileIn = new FileInputStream("C:/Users/suhas/Desktop/test4.txt");
			//FileOutputStream fileOut = new FileOutputStream("C:/Users/suhas/Desktop/output.txt");
			//BufferedReader TextFile = new BufferedReader(new InputStreamReader(fileIn));
			//BufferedWriter OutFile = new BufferedWriter(new OutputStreamWriter(fileOut));
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter the file path.");
			String path = scan.nextLine();
			BufferedReader TextFile = new BufferedReader(new FileReader(path));
			List<String> TempList = new ArrayList<String>();
			try {
				String TempLine;
				while((TempLine = TextFile.readLine()) != null){
					if (!TempLine.isEmpty()){
						TempList = tokenizeFile(TempLine, TempList);
						//OutFile.write(TempLine);
						Print(TempList);
						TempList.clear();
					}
				}
			} catch (IOException e) {
				System.out.println("Error in reading file!!!");
			}			
		}
		catch (FileNotFoundException e){
			System.out.println("File not found!!!");
		}
	}
	
	// Prints out the alphanumeric tokens. Invoked after reading every line.
	private static void Print(List<String> List) {
		for (int i=0; i<List.size(); i++){
			System.out.println(List.get(i));
		}		
	}
	
	//Take a string and list as input and return a list of tokens containing only alphanumeric characters.
	public static List<String> tokenizeFile(String TempLine, List<String> TempList) {
		TempLine = TempLine.replaceAll("[^a-zA-Z0-9]", " ");
		StringTokenizer fileIn = new StringTokenizer(TempLine);				
		while (fileIn.hasMoreTokens()){
			TempList.add(fileIn.nextToken());
		}			
		return TempList;	
	}
}
