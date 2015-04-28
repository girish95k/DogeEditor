package a3solution;

//  COMP 250 - Introduction to Computer Science - Fall 2012
//  Assignment #3 - Question 1

import java.util.*;

// Trie class.  Each node is associated with a prefix of some key 
// stored in the trie.   (Note any string is a prefix of itself.)

public class Trie
{
   private TrieNode root;

   // Empty trie has just a root node.  All the children are null.

   public Trie() 
   {
	   root = new TrieNode();
   }

   public TrieNode getRoot(){
	   return root;
   }
   
   // Insert key into the trie.  The first step should be finding the longest 
   // prefix of key already in the trie (use getPrefixNode() below).  
   // Then add TrieNodes in such a way that the key is inserted.

   public void insert(String key)
   {
	   //  ADD YOUR CODE BELOW HERE
	   
      TrieNode prefixNode = getPrefixNode(key);
      for (int curDepth = prefixNode.getDepth(); curDepth < key.length(); curDepth++){ 	  
    	  prefixNode = prefixNode.createChild(key.charAt(curDepth));
      }
      prefixNode.setEndOfKey(true);
      
	   //  ADD YOUR ABOVE HERE
   }
   
   // insert each key in the list (keys)

   public void loadKeys(ArrayList<String> keys)
   {
      for (int i = 0; i < keys.size(); i++)
      {
         //System.out.println("Inserting " + keys.get(i));
         insert(keys.get(i));
      }
      return;
   }

   // Return the TrieNode corresponding the longest prefix of a key that is found. 
   // If no prefix is found, return the root. 
   // In the example in the PDF, running getPrefixNode("any") should return the
   // dashed node under "n", since "an" is the longest prefix of "any" in the trie. 

   private TrieNode getPrefixNode(String word)
   {
	   //   ADD YOUR CODE BELOW HERE
	   
	   TrieNode cur = root;
	   int i=0;
	   while ( i < word.length()){
		   if (cur.getChild(word.charAt(i)) == null)
			   return cur;    // only a strict prefix exists which is a path
		   else
			   cur = cur.getChild(word.charAt(i));
		   i++;
	   }
	   return cur;   //  word is already a rooted path in the trie i.e. a path starting at root

	   //   return null  //  REPLACE THIS STUB
	   //
	   //   ADD YOUR CODE ABOVE HERE

   }

   // Similar to getPrefixNode() but now return the prefix as a String, 
   // rather than as a TrieNode.

   public String getPrefix(String word)
   {
	   return getPrefixNode(word).toString();
   }

   // Return true if key is contained in the trie (i.e. it was added by insert), false otherwise

   public boolean contains(String key)
   {  
	   //   ADD YOUR CODE BELOW HERE
	   
      TrieNode prefixNode = getPrefixNode(key);
      return (prefixNode != null && 
    		  prefixNode.getDepth() == key.length() &&
    		  prefixNode.isEndOfKey());
      
	   //   ADD YOUR CODE ABOVE HERE
   }
      
   // Return a list of all keys in the trie that have the given prefix. 
   // (Or you can return null -- since the original posting of this code said
   //  that's what you should do (in the AutoComplete class).
   
   public ArrayList<String> getAllPrefixMatches( String prefix )
   {
	   //  ADD YOUR CODE BELOW 
	   
	   TrieNode node = getPrefixNode(prefix);
	   ArrayList<String> stringList = new ArrayList<String>();
	   if (node.getDepth() == prefix.length()) // make sure whole prefix is matched
		   preOrderTraverse(node,stringList);
	   
	   //  ADD YOUR CODE ABOVE HERE
	   
	   return stringList;
   }
 
   //  HELPER METHOD    DELETE THIS FOR POSTED CODE.
   
   // A recursive preOrderTraversal implementation.
   // At each node visited, if the node is the end of a key, then 
   // that key is added to the list.
   // Note that a reference to the list is passed as a parameter and that
   // the method does not return anything.  
   
   private void preOrderTraverse(TrieNode node, ArrayList<String> list){
	   if ( node.isEndOfKey() ){
		   list.add(node.toString());
	   }
	   for (int i = 0; i < TrieNode.NUMCHILDREN; i++){
		   if (node.getChild((char) i) != null){
			   preOrderTraverse(node.getChild((char) i),list);
		   }
	   }
   }
   //    END DELETE HELPER METHOD FOR POSTED CODE
      
}
