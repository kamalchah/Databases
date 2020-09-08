
//Note: All of your TBDSInterface method implementations must function recursively
//I have left the method signatures from my own solution, which may be useful hints in how to approach the problem
//You are free to change/remove/etc. any of the methods, as long as your class still supports the TBDSInterface

import java.util.ArrayList;
import java.util.HashMap;

public class TBDS implements TBDSInterface {
  TBDSNode root;

  public TBDS() {
    root = new TBDSNode();
  }

  //Indirectly recursive method to meet definition of interface
  public void add(String key, String value) { add(root,key,value); }

  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public void add(TBDSNode current, String curKey, String value) {
    //create children hashmap of current, get character at index 0
    HashMap<Character, TBDSNode>  childrenMap = current.getChildren();
    char currentKey = curKey.charAt(0);
    if(childrenMap.containsKey(currentKey)) {//if childrenmap contains that key
      if(curKey.length()==1) {//check if length is 1, if it is, set the value and return nothing
        childrenMap.get(currentKey).setValue(value);
        return;
      }
    }
    else {
      //doesnt contain currentKey(character), we need to add it
      if(curKey.length()==1) {
        current.addChildren(currentKey);
        //need to update value with value
        current.getChildren().get(currentKey).setValue(value);
        return;
      }//if curkey is length 1, call recursively
      else {
        current.addChildren(currentKey);
      }
    }
    add(current.getChildren().get(currentKey),curKey.substring(1),value);
  }

  //Indirectly recursive method to meet definition of interface
  public String get(String key) { return get(root,key); }

  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public String get(TBDSNode current, String curKey) {
    //recurse through with the key
    //letter by letter, if key doesnt exist, return null
    if(!current.getChildren().containsKey(curKey.charAt(0))) {
      return null;
    }
    else {//if key length is 1, return the value of its children at that string letter
      if(curKey.length()==1) {
        return current.getChildren().get(curKey.charAt(0)).getValue();
      }//return get of childnode of current
      TBDSNode childNode = current.getChildren().get(curKey.charAt(0));

      return get(childNode,curKey.substring(1));
    }
  }

  //Indirectly recursive method to meet definition of interface
  public boolean containsKey(String key) { return containsKey(root,key); }

  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public boolean containsKey(TBDSNode current, String curKey) {
    if(!current.getChildren().containsKey(curKey.charAt(0))) {
      return false;
    }
    else {
      if(curKey.length()==1) {
        return true;
      }
    }
    String currentKey = curKey.substring(1);
    return containsKey(current.getChildren().get(curKey.charAt(0)),currentKey);
  }

  //Indirectly recursive method to meet definition of interface
  public ArrayList<String> getKeysForPrefix(String prefix) { return getKeysForPrefix(prefix,root); }

  //Function calls getsubtreekeys for the node from findnode which takes the root as node and prefix
  //and returns all the appropriate lists for prefix given.
  public ArrayList<String> getKeysForPrefix(String prefix, TBDSNode node) { return getSubtreeKeys(findNode(node,prefix), new ArrayList<String>()); }

  //Recursive helper function to find node that matches a prefix
  //Note: only a suggestion, you may solve the problem in any recursive manner
  public TBDSNode findNode(TBDSNode current, String curKey) {//add d node
    if(!current.getChildren().containsKey(curKey.charAt(0))) {
      return null;
    }//if the nodes children dont contain the character, return null
    else {//else, if the length of the string is 1, return its children at that character
      if(curKey.length()==1) {
        return current.getChildren().get(curKey.charAt(0));
      }
      else {//else, check if it contains the next character, if it does, get its children
        if(current.getChildren().containsKey(curKey.charAt(0))) {
          TBDSNode childNode = current.getChildren().get(curKey.charAt(0));
          return findNode(childNode,curKey.substring(1));
        }//and call findNode recursively
        else {//else, return null because its children dont contian the same string(char)
          return null;
        }
      }
    }
  }

  //Recursive helper function to get all keys in a node's subtree
  //Note: only a suggestion, you may solve the problem in any recursive manner
  public ArrayList<String> getSubtreeKeys(TBDSNode current, ArrayList<String> keyStringsList) {
    //get node, check all its children and loops and get all values not null
    //for loop of nodes
    if(current!=null) {//if node and its value are not null, add its value to arraylist
      if (current.getValue() != null) {
        keyStringsList.add(current.getValue());
      }//if currents children are not empty, loop through all its children and call this function again
      if(!current.getChildren().values().isEmpty()) {
        for (TBDSNode node: current.getChildren().values()) {
          getSubtreeKeys(node,keyStringsList);
        }
      }
    }
    //if it is null, return a new empty arraylist
    if(current==null) {
      return new ArrayList<String>();
    }
    ArrayList<String> keyArray = new ArrayList<String>();
    if(current.getValue()!=null) {//if value is not null of this node, add its value to the new arraylist
      keyArray.add(current.getValue());
      keyStringsList.addAll(keyArray);//add all string elements in keyarray to this list and return it
    }
    return keyStringsList;
  }

  //Indirectly recursive method to meet definition of interface
  public void print() { print(root); }//calls print for root

  //Recursive method to print values in tree
  public void print(TBDSNode current) {//takes node
    //we need to loop through all keys in its hashmap and only print the ones with not null values
    HashMap<Character, TBDSNode>  childrenMap = current.getChildren();
    if(current!=null) {
      for(TBDSNode node: childrenMap.values()) {//look through children of current as values (nodes)
        if(node.getValue()!=null) {//if their value isnt null, print it
          System.out.println(node.getValue());
        }
        print(node);//calls recursively until we have printed all until we reach null nodes
      }
    }
    else {
      return;
    }

  }

  public static void main(String[] args) {
    //You can add some code in here to test out your TBDS initially
    //The TBDSTester includes a more detailed test
  }
}