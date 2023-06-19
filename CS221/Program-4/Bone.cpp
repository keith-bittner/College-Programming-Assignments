/*
Keith Bittner
CS221 - Spring 2019
Program 4 - Bone.cpp

This file implements the Bone object class.
*/
#include <iostream>
#include <string>
#include <unistd.h>

// Object class header file
#include "Bone.h"

using namespace std;

/*
This is the Bone constructor. Prints out a statement of the dog saying thank you for the bone.

Return: none
*/
Bone::Bone() {
  cout << "Wow, thanks for the bone!" << endl;
}

/*
This is the Bone deconstructor. Prints out a statement that the bone is going away.

Return: none
*/
Bone::~Bone() {
  cout << "...bone is going away..." << endl;
}
