/*
Keith Bittner
CS221 - Spring 2019
Program 4 - main.cpp

This program simulates working with multiple object classes by creating a dog object and performing various methods on the object.
*/
#include <iostream>
#include <string>
#include <unistd.h>

// Object class header files
#include "Bone.h"
#include "Dog.h"

using namespace std;

/*
Main function of the program.  Calls the constructor and methods contained within the Dog and Bone classes.

Return: none
*/
int main() {
  Dog dog1("Fido");
  //dog1.addBone(); // Test to see if a bone can be added while dog is happy.
  dog1.setWeight(5.0);
  dog1.bark(3);
  dog1.setNumberOfLegs(3);
  dog1.run(20, 400);
  //dog1.bark(5); // Test to see if dog will bark while not happy.
  //dog1.run(10, 500); // Test to see if dog will run while not happy.
  //dog1.wag(5, 400); // Test to see if dog will wag tail while not happy.
  //dog1.petDog(); // Make dog happy.
  //dog1.wag(5, 600); // Test to see if dog will wag tail while happy and doesn't have a bone.
  dog1.addBone();
  dog1.wag(10, 550);
}
