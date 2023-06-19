/*
	BinaryTree.cpp
	Keith Bittner
	CS360 - Spring 2020
	Assignment 4
	
	This program is the implementation of BinaryTree.h.	
*/
#include "BinaryTree.h"
#include "Node.h"
#include <iostream>

using namespace std;

/*
	Constructor
*/
BinaryTree::BinaryTree() {
	root = NULL; //starting a new tree with the root node being NULL
	cout << "In BinaryTree constructor..." << endl;
}

/*
	Destructor
*/
BinaryTree::~BinaryTree() {
	cout << "In BinaryTree destructor..." << endl;
	cleanupTree(); //call the cleanupTree method
}

/*
	addNode(int value)
	
	This public function calls the private addNode.
	
	Paramaters:	
	value - Integer value being passed from main.
*/
void BinaryTree::addNode(int value) {
	cout << "Adding value: " << value << endl;
	addNode(value, root);
}

/*
	addNode(int value, Node *leaf)
	
	This function adds a new node to the BinaryTree. Checks to see if it should make the new node
	a root, left, or right node.
	
	Paramaters:
	value - Integer value being passed from main.
	*leaf - Pointer to the root node
*/
void BinaryTree::addNode(int value, Node *leaf) {
	string leafNode = "NULL";
	string left = "NULL";
	string right = "NULL";
	

	Node *temp = new Node(value);
	if (root == NULL) {//newly created tree with only the root node
		root = temp;
		leafNode = to_string(root->getValue());
	} else if (leaf->getValue() > value) {//the leaf node value is greater then the value we need to add
		leafNode = to_string(leaf->getValue());
		leafNode = to_string(leaf->getValue());
			if(leaf->getLeft() == NULL){//leaf's left child is NULL
				leaf->setLeft(temp);//set the left child to be the new node
			} else {
				addNode(value, leaf->getLeft());//left child is not NULL, call the function again
			}
			if(leaf->getRight() != NULL){//the right child is not NULL
				right = to_string(leaf->getRight()->getValue());
			}
			left = to_string(leaf->getLeft()->getValue());
		}
	else if (leaf->getValue() < value) {//the leaf node value is less than the value we want to add
		leafNode = to_string(leaf->getValue());
		if (leaf->getRight() == NULL) {//leaf's right child is NULL
			leaf->setRight(temp);//set the right child to be the new node
		} else {
			addNode(value, leaf->getRight());//right child is not NULL, call the function again
		}
		if (leaf->getLeft() != NULL) {//the left child is not NULL
			left = to_string(leaf->getLeft()->getValue());
		}
		right = to_string(leaf->getRight()->getValue());
	}
	
	cout << "Visiting node, left, right: " << leafNode << "," << left << "," << right << endl;
}

/*
	deleteNode(int value)
	
	This public function calls the private deleteNode.
	
	Paramaters:	
	value - Integer value being passed from main.
*/
void BinaryTree::deleteNode(int value) {
	cout << "Deleting value: " << value << endl;
	deleteNode(value, root);
}

/*
	deleteNode(int value, Node *leaf)
	
	This function deletes a node from the BinaryTree. Checks to see if its supposed to delete the
	root, left or right nodes. If the node has children, ensures the children are not lost when the
	root is deleted.
	
	Paramaters:
	value - Integer value being passed from main.
	*leaf - Pointer to the root node
*/
void BinaryTree::deleteNode(int value, Node *leaf) {
	string leafNode = "NULL";
	string left = "NULL";
	string right = "NULL";

	if (leaf!=NULL) {
		leafNode = to_string(leaf->getValue());
	}
	if (leaf->getLeft()!=NULL) {
		left = to_string(leaf->getLeft()->getValue());
	}
	if (leaf->getRight()!=NULL) {
		right = to_string(leaf->getRight()->getValue());
	}
	cout << "deleteNode visiting node, left, right: " << leafNode << "," << left << "," << right << endl;

	if (root != NULL) {
		if (leaf->getValue() > value) {
			deleteNode(value, leaf->getLeft());
		} else if (leaf->getValue() < value) {
			deleteNode(value, leaf->getRight());
		} else {
			Node * parent = root;//set parent to the root to start with
			bool foundParent = false;
			//let's find the parent node
			while (parent != nullptr && foundParent != true){
				if (parent->getValue() < value) {
					if (parent->getRight()!= NULL) {
						if (parent->getRight()->getValue() == value) {
							foundParent = true;//found the parent node
						} else {
							parent = parent->getRight();
						}
					}
				} else if (parent->getValue() > value) {
					if (parent->getLeft() != NULL) {
						if (parent->getLeft()->getValue() == value) {
							foundParent = true;//found the parent node
						} else {
							parent = parent->getLeft();
						}
					}
				} else if (parent->getValue() == value) {
					foundParent = true;//found the parent node
				}	
			}
			//we found the parent node
			Node * current = leaf;
			//node has no children
			if (leaf->getLeft() == NULL && leaf->getRight() == NULL) {
				if (leaf != root) {//the leaf is not the root
					if (parent->getLeft() == leaf) {
						parent->setLeft(nullptr);//set left to NULL
					} else {//the leaf is the root
						parent->setRight(nullptr);//set the right to NULL
					}
				} else {//the leaf is the root
					root = nullptr;//set the root to NULL
				}
				delete leaf;//delete the node
			//node has two chidren
			} else if (leaf->getLeft() != NULL && leaf->getRight() != NULL){
				cout << "Node has 2 children..." << endl;					
				Node * succesor = leaf->getRight();//make the replacment node

				while (succesor->getLeft() != NULL) {//find the min
					succesor = succesor->getLeft();
				}
				int value = succesor->getValue();//set value to the value of the node to be deleted
				deleteNode(value, leaf->getRight());
				Node * newNode = new Node (value);//make a new node
				newNode->setLeft(leaf->getLeft());//set the left child
				newNode->setRight(leaf->getRight());//set the right child
				if (leaf == root) {//is the newNode the new tree root
					root = newNode;
				}
				if (parent->getValue() > value) {//is the newNode less than the parent
					parent->setLeft(newNode);
				} else {//is the newNode greater then the parent
					parent->setRight(newNode);
				}
				delete leaf;//delete the node
			//node only has 1 child
			} else {
				Node * child = (leaf->getLeft())? leaf->getLeft(): leaf->getRight();//make a temp child

				if (leaf != root) {//the leaf is not the root
					if (leaf == parent->getLeft()) {
						parent->setLeft(child);//make the child the left node
					} else {
						parent->setRight(child);//make the child the right node
					}
				} else {//the leaf is the root
					root = child;//make the root the child
				}
				delete leaf;//delete the node
			}

		}
		
	}
}

/*
	search(int value)
	
	This public function calls the private search.
	
	Paramaters:	
	value - Integer value being passed from main.
*/
Node *BinaryTree::search(int value) {
	cout << "Searching for value: " << value << endl;
	return search(value, root);
}

/*
	search(int value, Node *leaf)
	
	This function searches for a node in the BinaryTree. Returns the node regardless if found.
	Prints a statment if it was found or not.
	
	Paramaters:
	value - Integer value being passed from main.
	*leaf - Pointer to the root node
*/
Node *BinaryTree::search(int value, Node *leaf) {
	string leafValue = "NULL";
	if (leaf != NULL) {
		leafValue = to_string(leaf->getValue());
	}
	cout << "BinaryTree::search(n,val): " << leafValue << "," << value << endl;
	Node *temp = NULL;
	if (leaf == NULL) {//leaf is NULL
		cout << "Value not found..." << endl;
		return leaf;
	}
		if (leaf->getValue() == value) {//leaf value is the same as search value
		cout << "Found it..." << endl;
		return leaf;
	}
	if (leaf->getValue() > value) {//leaf value is greater than search value
		temp = search(value, leaf->getLeft());//call yourself again starting at left child
	}
	if (leaf->getValue() < value) {//leaf value is less than search value
		temp = search(value, leaf->getRight());//call yourself again starting at right child
	}
	return temp;
}

/*
	cleanupTree()
	
	This function is called from the destructor and calls the private cleanupTree.
*/
void BinaryTree::cleanupTree() {
	cleanupTree(root);
}

/*
	cleanupTree(Node *leaf)
	
	This function searches for a node in the BinaryTree. Returns the node regardless if found.
	Prints a statment if it was found or not.
	
	Paramaters:
	*leaf - Pointer to the root node
*/
void BinaryTree::cleanupTree(Node *leaf) {
	if (leaf != NULL) {//leaf is not NULL
		cleanupTree(leaf->getLeft());//call yourself to clean the left child
		cleanupTree(leaf->getRight());//call yourself to clean the right child
		cout << "Cleaning node: " << leaf->getValue() << endl;
		delete leaf;//delete the root node
	}
}

/*
	printTree(bool ascending)
	
	This function calls either the private printTreeAscending or printTreeDescending.
	
	Paramaters:
	ascending - TRUE for ascending, FALSE for desecnding
*/
void BinaryTree::printTree(bool ascending) {
	if (ascending) {//TRUE
		cout << "Printing in ascending=========================" << endl;
		printTreeAscending(root);
	} else {//FALSE
		cout << "Printing in descending=========================" << endl;
		printTreeDescending(root);
	}
		cout << "Done printing tree..." << endl;
}

/*
	printTreeAscending(Node *leaf)
	
	Prints the BinaryTree is ascending order based on the root node.
	
	Paramaters:
	*leaf - Pointer to the root node
*/
void BinaryTree::printTreeAscending(Node *leaf) {
	string right = "NULL";
	string left = "NULL";
	if (leaf != NULL) {//leaf is not NULL
		printTreeAscending(leaf->getLeft());//find the left most child
		if (leaf->getLeft() != NULL) {//left child is not NULL
			left = to_string(leaf->getLeft()->getValue());
		}
		if (leaf->getRight() != NULL) {//right child is not NULL
			right = to_string(leaf->getRight()->getValue());
		}
		cout << "val: " << leaf->getValue()<< "       l: " << left << "        r: "<< right << endl;
		printTreeAscending(leaf->getRight());//find the right most child
	}
}

/*
	printTreeDescending(Node *leaf)
	
	Prints the BinaryTree is descending order based on the root node.
	
	Paramaters:
	*leaf - Pointer to the root node
*/
void BinaryTree::printTreeDescending(Node *leaf) {
	string right = "NULL";
	string left = "NULL";
	if (leaf != NULL) {//leaf is not NULL
		printTreeDescending(leaf->getRight());//find the right most child
		if (leaf->getLeft() != NULL) {//left child is not NULL
			left = to_string(leaf->getLeft()->getValue());
		}
		if (leaf->getRight() != NULL) {//right child is not NULL
			right = to_string(leaf->getRight()->getValue());
		}
		cout << "val: " << leaf->getValue()<< "       l: " << left << "        r: "<< right << endl;
		printTreeDescending(leaf->getLeft()); //find the left most child
	}
}
