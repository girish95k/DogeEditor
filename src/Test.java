
import a3posted.Trie;
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Girish
 */
public class Test {
    
    public static void main(String[] args)
	{	   
		ArrayList<String> list = new ArrayList<String>();

/*      Test with an input file.
 *	  Your assignment will be graded using a different input file,
 *	  If you wish to share your outputs on the given input file, that's fine. 
 *		
 *	  You will need to put a different pathname in here.
 *		
 *		String fileName = "C:\\Program Files\\eclipse\\workspace\\2012\\src\\a3\\inputFile.txt";
 *		list = readWordsFromFile(fileName);
*/
		
		//   For debugging, you may wish to use a small set of keys only.      

		Collections.addAll(list, "a", "and", "ax", "dog", "door", "dot", "dots", "", "girish", "shm", "cb", "int", "public", "pubaf");

		Trie   trie = new Trie();
		trie.loadKeys(list);
                /*

		System.out.println("list contains " + list.size() + " keys");

		// Test if contains() works, print input and output
		//
		//  e.g.: try door, an, cat (should return true, false, and false respectively)

		System.out.println();
		System.out.println("---  Test contains() method.   Correct answer shown in brackets. -----" );
		System.out.println("trie contains 'door' = " + trie.contains("door") + " (true)" );
		System.out.println("trie contains 'and' = " + trie.contains("and")   + " (true)");
		System.out.println("trie contains 'cat' = " + trie.contains("cat")   + " (false)");
		System.out.println("trie contains 'dog' = " + trie.contains("dog")   + " (true)");
		System.out.println("trie contains 'ax' = " + trie.contains("ax")     + " (true)");
		System.out.println("trie contains 'dot' = " + trie.contains("dot")   + " (true)");
		System.out.println("trie contains 'a' = " + trie.contains("a")       + " (true)");
		System.out.println("trie contains 'an' = " + trie.contains("an")     + " (false)");      

		//  Test if getLongestMatchingPrefix works, print input and output
		//    ex: try door, any, cat (should return door, an, and null respectively

		System.out.println("");
		System.out.println("-----  Test getPrefix()");		
		System.out.println("longest prefix of door = " + trie.getPrefix("door"));
		System.out.println("longest prefix of any = " + trie.getPrefix("any"));
		System.out.println("longest prefix of cat = " + trie.getPrefix("cat"));

		// Test getMatches, print input and output
		// ex: try a, do, c (should return {a, and, ax}, {dog, door, dot}, null respectively)
                    */
		System.out.println("");
		System.out.println("-----  Test getAllPrefixMatches()  i.e. autocomplete ");		
		System.out.println("autocomplete a = " + trie.getAllPrefixMatches("a"));
		System.out.println("autocomplete do = " + trie.getAllPrefixMatches("do"));
		System.out.println("autocomplete c = " + trie.getAllPrefixMatches("c"));
		System.out.println("autocomplete \"\" = " + trie.getAllPrefixMatches(""));
                System.out.println("autocomplete k = " + trie.getAllPrefixMatches("k"));
                System.out.println("autocomplete giri = " + trie.getAllPrefixMatches("gir"));
                System.out.println("autocomplete c = " + trie.getAllPrefixMatches("s"));
                System.out.println("autocomplete i = " + trie.getAllPrefixMatches("i"));
                System.out.println("autocomplete pub = " + trie.getAllPrefixMatches("pub"));

	}
}
