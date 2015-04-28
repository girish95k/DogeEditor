/**
 * author@Aarti J Jivrajani
 * Components: List , Hash Map(to store all the child nodes) , tree(trie) , array of nodes , ArrayList
 */
package aarti;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Trie
{
	// an array of EMPTYNODES
	private static final TrieNode[] EMPTYNODES = new TrieNode[0];
	public String keywords1[] = {"abstract","continue","for","new","switch","assert","default","goto","package",
			 "synchronized","boolean","do","if","private","this","break","double","implements"
			 ,"protected","throw","byte","else","import","public","throws","case","enum","instanceof"
			 ,"return","transient","extends","int","short","try","final","interface","static","void"
			 ,"finally","long","strictfp","volatile","float","catch","char","class","const",
			 "native","super","while"};
	//the TrieNode class is an inner class
	private static final class TrieNode implements Comparable<TrieNode>
	{
		
		private final char character;
		private boolean isWord = false;
		//this hashmap acts like an iterator for the children
		private Map<Character, TrieNode> children = null;
		//TrieNode ctor
		public TrieNode(char ch)
		{
			character = ch;
		}
		public TrieNode getOrCreateChild(char ch)
		{	
			//if no children exist(the hashmap is null ie.it doesn't exist)
			if (children == null)
			{
				children = new HashMap<>();
			}
			/**
			 * now,get the node from the children hashmap which contains the character "ch" 
			 * the get method is inbuilt. if the node is not present then a new node is made and 
			 * put into the hashmap using the put method(inbuilt in HashMap)
			 */
			TrieNode kid = children.get(ch);
			//suppose there was no node having "ch",kid will be null
			if (kid == null)
			{	//make a new TrieNode having the character "ch"
				kid = new TrieNode(ch);
				//hash method to put the kid having <character,Trienode> = <ch,kid>
				children.put(ch, kid);
			}
			return kid;
		}
		/**
		 * gets the character from the trie tree and returns the node containing the that particular character
		 * if the character is not found, then null is returned
		 */
		public TrieNode get(char ch)
		{
			return children != null ? children.get(ch) : null;
		}
		/**
		 * once the word is entered, the isWord is set to true
		 * this is so that once the whole word is entered, the isWord can be set to true
		 * this is how the program will know that the "particular" word is found.
		 */
		public void setWord()
		{
			isWord = true;
		}
		//determines if the word is there in the trie
		public boolean isWord()
		{
			return isWord;
		}

		public char getChar()
		{
			return character;
		}
		/**
		 * this method returns an array of the children nodes and then sorts them. 
		 * sorted is required for a trie tree
		 */
		public TrieNode[] getChildNodes()
		{	
			/**
			 *  if the hash is empty, just return the empty nodes array
			 *  (which is an array of "trie" node)
			 */
			if (children == null)
			{
				return EMPTYNODES;
			}
			/**
			 * the values in the hash map will be the "characters". these are taken and put into 
			 * an array. a new array. then this array is sorted. 
			 */
			TrieNode[] result = children.values().toArray(new TrieNode[children.size()]);
			Arrays.sort(result);
			//test:implement the print statement given below
			//System.out.println(result[0].character);
			return result;
		}
		@Override
		public int compareTo(TrieNode o)
		{
			// cheap way to sort alphabetically.
			return (int)character - o.character;
		}

	}//end of inner class TrieNode

	private final TrieNode root; //root should be final!! 
	private int size = 0; // how many words
	private int depth = 0; // longest word
	

	public Trie()
	{
		// root has null character.
		root = new TrieNode((char)0);
	}

	public void addWord(String word)
	{	//"node" acts as a pointer in the tree(just like how we have in linked lists)
		TrieNode node = root;
		int wdepth = 0;
		/**
		 * this for loop iterates over all the letters of the string 
		 * "word" passed as parameter.Once it gets a character, that character is put in a node
		 * and then the depth is increased
		 */
		for (char ch : word.toLowerCase().toCharArray())
		{
			node = node.getOrCreateChild(ch);
			wdepth++;
		}
		/**
		 *  if the word is not there in the trie, then only add it.
		 * so, the duplicates are taken care of over here.
		 */
		if (!node.isWord())
		{ 
			node.setWord();
			size++;
			/**
			 *  here only if the maxdepth is lesser than "word"depth, chages are made
			 *  else no change is required
			 */
			if (wdepth > depth)
			{
				depth = wdepth;
			}
		}
	}
	public void loadArray()
	{
		
		for(int i = 0;i<keywords1.length;++i)
		{
			this.addWord(keywords1[i]);
		}
		
	}
	/**
	 *  this method basically checks if the particular word exists in the trie tree.
	 *  true or false is returned based on the presence of the word in the tree
	 */
	public boolean containsWord(String word)
	{
		TrieNode node = root;
		/**
		 *  this for loop checks for the presence of each letter in the word. even if 
		 *  one "char" is not found, "node" immmediately becomes null 
		 *  and because of the "if" statement, the for loop breaks
		 */
		for (char ch : word.toLowerCase().toCharArray())
		{
			node = node.get(ch); //now node will contain a Trie Node having the character "ch"
			//here node maybe null if the character is not found
			if (node == null)
			{
				break;
			}
		}
		return node != null; //&& node.isWord();
	}
	public ArrayList<String> displayOptions(String s)
	{
            
            //s=s.replaceAll("[^a-z]", "");
		List<String> temp = this.getWords();
		//System.out.println(temp);
		ArrayList<String> ls = new ArrayList<>();
		boolean there = true;
		for(int i=0;i<temp.size() ;++i)
		{	
			there = true;
			for(int j = 0;j<s.length() && there==true;++j)
			{
				if(s.charAt(j)==temp.get(i).charAt(j) && there)
					there = true;
				else there = false;
			}
			if(there)
				ls.add(temp.get(i));
		}
		return ls;
	}
	
			
		
			
				
		
	/*def autocomplete(self, prefix):
        node = self
        for char in prefix:
            if char not in node.children:
                return set()
            node = node.children[char]
        return list(node.all_suffixes(prefix))*/

	public int size()
	{
		return size;
	}

	public List<String> getWords()
	{	//here List is an ordered collection in java (zero-based)
		// set up a recursion call.
		List<String> result = new ArrayList<>(size);
		char[] charstack = new char[depth];
		getWords(root, charstack, 0, result);
		return result;
	}
	      /** public String(char[] value,int offset,int count)
			  Allocates a new String that contains characters from a subarray
			  of the character array argument. The offset argument is the
			  index of the first character of the subarray and the count argument specifies the 
			  length of the subarray. 
			  The contents of the subarray are copied; subsequent modification of the character 
			  array does not affect the newly created string.
			  Parameters: 
			  value - Array that is the source of characters 
			  offset - The initial offset 
			  count - The length  */
	private void getWords(final TrieNode node, final char[] charstack, final int stackdepth, final List<String> result)
	{
		
		if (node == null)
		{
			return;
		}
		if (node.isWord())
		{
			result.add(new String(charstack, 0, stackdepth));
		}
		for (TrieNode kid : node.getChildNodes())
		{
			charstack[stackdepth] = kid.getChar();
			getWords(kid, charstack, stackdepth + 1, result);
		}
	}

	public static void main(String[] args)
	{
		Trie t = new Trie();
		/*t.addWord("h");
		t.addWord("ha");
		t.addWord("hhha");
		t.addWord("samsung");*/

		List<String> words = t.getWords();
		//List is iterable
		t.loadArray();
		for(String s : words)
		{
			System.out.println(s);
		}
		//t.displayOptions("pu");
		System.out.println(t.displayOptions("s"));
		
	}

}



