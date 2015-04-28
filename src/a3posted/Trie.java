/*
 * @author cadesalaberry
 */
package a3posted;

//  COMP 250 - Introduction to Computer Science - Fall 2012
//  Assignment #3 - Question 1

import java.util.*;

// Trie class.  Each node is associated with a prefix of some key 
// stored in the trie.   (Note any string is a prefix of itself.)

public class Trie {
	private TrieNode root;

	// Empty trie has just a root node. All the children are null.

	public Trie() {
		root = new TrieNode();
	}

	public TrieNode getRoot() {
		return root;
	}

	/**
	 * Returns true if key is contained in the trie (i.e. it was added by
	 * insert), false otherwise.
	 * 
	 * @return presence of key in the Trie.
	 * @author cadesalaberry
	 */
	public boolean contains(String key) {

		TrieNode lastCommonChar = this.getPrefixNode(key);

		// Checks if the chain of characters exist.
		boolean chainExists = key.equals(lastCommonChar.toString());

		// Checks if the chain is well ended.
		return chainExists && lastCommonChar.isEndOfKey();
	}

	/**
	 * Inserts key into the trie. The first step should be finding the longest
	 * prefix of key already in the trie (use getPrefixNode() below). Then add
	 * TrieNodes in such a way that the key is inserted.
	 * 
	 * @author cadesalaberry
	 */
	public void insert(String key) {

		TrieNode startNode = this.getPrefixNode(key);

		// Gets the index of the first letter that differs.
		int index = startNode.getDepth();

		char currentChar;

		// Adds nodes starting from the first non common character until the
		// end.
		while (index < key.length()) {
			currentChar = key.charAt(index);
			startNode.createChild(currentChar);
			startNode = startNode.getChild(currentChar);
			index++;
		}
		startNode.setEndOfKey(true);
	}

	// insert each key in the list (keys)

	public void loadKeys(ArrayList<String> keys) {
		for (int i = 0; i < keys.size(); i++) {
			// System.out.println("Inserting " + keys.get(i));
			insert(keys.get(i));
		}
		return;
	}
        public void loadKeys() {
            ArrayList<String> keys=null;
            //Collections.addAll(keys, "a", "and", "ax", "dog", "door", "dot", "dots", "", "girish", "shm", "cb", "int", "public", "pubaf", "int", "public", "static");
            keys.add("int");
            keys.add("public");
            for (int i = 0; i < keys.size(); i++) {
			// System.out.println("Inserting " + keys.get(i));
			insert(keys.get(i));
		}
		return;
        }

	/**
	 * Returns the TrieNode corresponding the longest prefix of a key that is
	 * found. If no prefix is found, returns the root. In the example in the
	 * PDF, running getPrefixNode("any") should return the dashed node under
	 * "n", since "an" is the longest prefix of "any" in the trie.
	 * getPrefixNode("addition") should return the node which is the first child
	 * of the root since "a" is the longest prefix of "addition" in the trie.
	 * 
	 * @return end node of longest chain of common character.
	 * 
	 * @author cadesalaberry
	 */

	private TrieNode getPrefixNode(String word) {

		TrieNode toWorkWith = this.root;
		int i = 0;
		char character;
		boolean letterMatches = true;

		/*
		 * Analyse the word letter by letter.
		 */
		while (i < word.length() && letterMatches) {

			// Loads the character to check for presence.
			character = word.charAt(i);

			// Checks if the character is in the trie.
			letterMatches = toWorkWith.getChild(character) != null;

			// If it is, loads next letter. Else, return the Node.
			toWorkWith = letterMatches ? toWorkWith.getChild(character)
					: toWorkWith;

			i++;
		}

		return toWorkWith;
	}

	// Similar to getPrefixNode() but now return the prefix as a String,
	// rather than as a TrieNode.

	public String getPrefix(String word) {
		return getPrefixNode(word).toString();
	}

	/**
	 * Returns a list of all keys in the trie that have the given prefix.
	 * 
	 */
	public ArrayList<String> getAllPrefixMatches(String prefix) {

		TrieNode prefixNode = this.getPrefixNode(prefix);

		ArrayList<String> suggestions = new ArrayList<String>();
		
		// If the prefix is not in the Trie,
		// don't bother parsing the Trie.
		if (prefixNode != root) {
			
			// Gets every endOfKeys following the prefix node,
			// then adds their string representation to the suggestions.
			for (TrieNode endOfKey : getEndsOfKeyFollowing(prefixNode)) {
				suggestions.add(endOfKey.toString());
			}
		}
		return suggestions;
	}

	/**
	 * Gathers a collection of endOfKeys following the TrieNode given.
	 * 
	 * @param current
	 * @return
	 */
	public ArrayList<TrieNode> getEndsOfKeyFollowing(TrieNode current) {

		ArrayList<TrieNode> endOfKeys = new ArrayList<TrieNode>();

		for (int i = 0; i < current.NUMCHILDREN; i++) {

			TrieNode nextNode = current.getChild((char) i);

			if (nextNode != null) {

				// Adds the word if it is the end.
				if (nextNode.isEndOfKey()) {
					endOfKeys.add(nextNode);
				}

				// Gets the words with matching prefix.
				endOfKeys.addAll(this.getEndsOfKeyFollowing(nextNode));
			}
		}
		return endOfKeys;
	}

}
