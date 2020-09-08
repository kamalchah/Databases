This is a tree based data structure database project in which there is a word file containing approx. 
2000 words and the program parses through that info, adds it to a hashset of strings. 
My program then parses through this file and adds all of the words to a tree based data structure.

The tree based data structure has this unique property that the key for any node contained within a subtree
rooted at a node N has the key that would lead to N as a prefix. As examples, “zoo”, “zone”, and
“zoom” all begin with the character ‘z’ and are all contained within the subtree rooted at the node
you would reach if you looked up the key ‘z’. If another string “zombie” was added to the above
TBDS, it would be contained in the subtrees of the keys “z” and “zo” as well. With this property
we can search for a set of keys and values that start with a particular prefix. 


