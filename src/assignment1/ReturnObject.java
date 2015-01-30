package assignment1;

import java.util.Hashtable;
import java.util.List;

public class ReturnObject {
	List<String> List;
	Hashtable<String, Integer> table;
	public ReturnObject(List<String> List, Hashtable<String, Integer> table){
		this.List = List;
		this.table = table;
	}
	public ReturnObject TempFunction(List<String> List, Hashtable<String, Integer> table){
		return new ReturnObject(List,table);
	}

}
