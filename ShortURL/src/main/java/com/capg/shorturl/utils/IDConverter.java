package com.capg.shorturl.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;



public class IDConverter {
	
	public static final IDConverter INSTANCE = new IDConverter();
	
	private IDConverter() {
		initializeCharToIndexTable();
		initializeIndexToCharTable();
	}
	
	private static HashMap<Character, Integer> charToIndexTable;
	private static List<Character> indexToCharTable;
	
	private void initializeCharToIndexTable() {
		
		charToIndexTable = new HashMap<>();
		//0->a, 1->b, ..... 25->z, 26->A, ....., 52->0, 61->9
		
		char c = 'a';
		for(int i = 0; i < 62; ++i) {
			
			if(i==26) c ='A';
			else if(i==52) c = '0';
			else c++;
			
			charToIndexTable.put(c, i);
			
		}
	}
	
	private void initializeIndexToCharTable() {
		//0->a, 1->b, ..., 25->z, ...., 52->0, 61->9
		
		indexToCharTable = new ArrayList<>();
		char c = 'a';
		for(int i = 0; i < 62; ++i) {
			
			if(i==26) c ='A';
			else if(i==52) c = '0';
			else c++;
			
			indexToCharTable.add(c);
			
		}
	}
	
	public String createUniqueID(Long id) {
		
		List<Integer> base62ID = convertBase10ToBase62ID(id);
		StringBuilder uniqueURLID = new StringBuilder();
		
		for(int digit : base62ID) {
			uniqueURLID.append(indexToCharTable.get(digit));
		}
		
		return uniqueURLID.toString();
	}
	
	public List<Integer> convertBase10ToBase62ID(Long id) {
		
		List<Integer> digits = new LinkedList<>();
		
		while(id > 0) {
			int remainder = (int)(id % 62);
			((LinkedList<Integer>) digits).addFirst(remainder);
			id /= 62;
		}
		
		return digits;
	}
	
	
	public Long getDictionaryKeyFromUniqueID(String uniqueID) {
		
		List<Character> base62Number = new ArrayList<>();
		
		for(int i = 0; i < uniqueID.length(); ++i) {
			base62Number.add(uniqueID.charAt(i));
		}
		
		Long dictionaryKey = convertBase62ToBase10ID(base62Number);
		return dictionaryKey;
	}
	
	public Long convertBase62ToBase10ID(List<Character> ids) {
		
		long id = 0L;
		int exp = ids.size() - 1;
		
		for(int i = 0; i < ids.size(); ++i, --exp) {
			int base10 = charToIndexTable.get(ids.get(i));
			id += (base10 * Math.pow(62.0, exp));
		}
		return id;
	}
	
}
