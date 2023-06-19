/*
	Node.cpp
	Keith Bittner
	CS360 - Spring 2020
	Assignment 4
	
	This program is the implementation of Node.h.	
*/
#include "Node.h"
#include <iostream>

/*
	Constructor
*/
Node::Node(int val) {
	value = val;
}

/*
	Destructor
*/
Node::~Node() {}

/*
	getValue()
	
	Returns the value of the node.
*/
int Node::getValue() {
	return value;
}

/*
	getLeft()
	
	Returns a pointer to the left child.
*/
Node* Node::getLeft() {
	return left;
}

/*
	getRight()
	
	Returns a pointer to the right child.
*/
Node* Node::getRight() {
	return right;
}

/*
	setLeft(Node* l)
	
	Sets the left child to the new node.
	
	Paramaters:
	l - A pointer to the new node.
*/
void Node::setLeft(Node* l) {
	left = l;
}

/*
	setRight(Node* l)
	
	Sets the right child to the new node.
	
	Paramaters:
	l - A pointer to the new node.
*/
void Node::setRight(Node* r) {
	right = r;
}
