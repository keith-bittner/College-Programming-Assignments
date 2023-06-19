/*
Keith Bittner
CS221 - Spring 2019
Program 4 - Dog.h

This file implements the Dog object class and uses the Bone object class.
*/
#include <iostream>
#include <string>
#include <unistd.h>

// Object class header files
#include "Bone.h"
#include "Dog.h"

using namespace std;

/*
This method constructs the Dog object. Prints out a statment say dog is alive.

dogName - name of the dog
eyeColor - color of dog's eyes (defaults to green)
furColor - color of the dog's fur (defaults to black and tan)
weight - weight of the dog (defaults to 1.0)
isHappy - dog's happiness boolean (defaults to true)
hasBone - dog's bone boolean (defaults to false)

Return: none
*/
Dog::Dog(string name) {
  dogName = name;
  eyeColor = "Green";
  furColor = "Black and Tan";
  weight = 1.0;
  isHappy = true;
  hasBone = false;
  cout << dogName << " is alive!" << endl;
}

/*
This method deconstructs the Dog object when all finished with it. Prints out a statement saying dog is gone.

Return: none
*/
Dog::~Dog() {
  cout << dogName << " is no longer with us." << endl;
}

/*
This method sets the weight of the dog.

weight - user inputted integer

Return: none
*/
void Dog::setWeight(double weight) {
  this->weight = weight;
}

/*
This methods sets the number of legs the dog has.

numLegs - user inputted integer
numberOfLegs - redefining numLegs for use in other methods

Return: none
*/
void Dog::setNumberOfLegs(int numLegs) {
  numberOfLegs = numLegs;
}

/*
This method makes the dog bark a set number of times. Adjusts the type of bark according to weight of the dog. Will not bark of the dog is not happy. Uses usleep(milliseconds) to adjusts the speed of the bark according to the size of the dog. Ensures that isHappy is true at the end of method.

numTimes - number of times for the dog to bark
isHappy - dog's happiness boolean
dogName - name of the dog
weight - weight of the dog

Return: none
*/
void Dog::bark(int numTimes) {
  if (!isHappy) { // Dog isn't happy.
    cout << dogName << " isn't happy right now. Try scratching my ears first." << endl;
  } else { // Dog is happy.
    cout << "I'm " << dogName << " and I'm here to say:" << endl;

    if (weight < 100) { // If dog's weight is less than 100.
      for (int i = 1; i <= numTimes; i++) {
        cout << "yap!!" << flush;
        usleep(250 * 1000); // Small dogs bark faster.
        cout << "\n";
      }
    } else { // If dog's weight is greater than or equal to 100.
      for (int i = 1; i <= numTimes; i++) {
        cout << "WOOF!!" << flush;
        usleep(500 * 1000); // Large dogs bark slower
        cout << "\n";
      }
    }
    isHappy = true; // Let's make sure he is still happy.
  }
}

/*
This method sets the happiness boolean to true. Currently not being uses anywhere in the program. But included because documentation specifies it as a method.

isHappy - dog's happiness boolean

Return: none
*/
void Dog::setHappiness(bool isHappy) {
  isHappy = true;
}

/*
This method makes the dog run. If the dog has less than 3 legs, it prints out a statement and moves onto the next method called. If the dog has 3 or more legs it checks to see if the dog is happy. If the dog is happy, method will allow the dog to run. When running if the dog has only 3 legs, the dog will run at 75% speed. If the dog has 4 legs, the dog will run at full speed. If the dog has more than 3 legs but is currently not happy, the dog will refuse to run. Uses usleep(milliseconds) to display running speed. Set's the dog's happiness to false if you tried to run him with less than 3 legs. Sets the dog's happiness to false after succesfully running.

howFar - how far the dog has to run 0-1000
howFast - how fast the dog has to run 0-1000
isHappy - dog's happiness boolean
dogName - name of the dog
numberOfLegs - number of legs the dog has 0-4

Return: none
*/
void Dog::run(int howFar, int howFast) {
  if (howFast > 1000) { // Set howFast to zero if out of range.
    howFast = 0;
  }

  if (numberOfLegs < 3) { // Dog doesn't have enough legs.
    cout << "You're so mean! " << dogName << " can't run with this many legs! Now " << dogName << " is not happy." << endl;
  } else { // Dog has enough legs but isn't happy.
    if ((howFar < 0 || howFar > 1000) || !isHappy) {
      cout << dogName << " refuses to run right now!" << endl;
    } else { // Dog has enough legs and is happy.
      cout << dogName << " is running to catch squirrels on " << numberOfLegs << " legs. | run: " << howFar << " sleep: " << howFast << "ms" << endl;
      if (numberOfLegs == 3) { // Dog has 3 of it's legs.
        for (int i = 0; i < howFar; i++) {
          cout << "#" << flush;
          usleep((howFast * 1000) * .75); // Run at 75% speed.
        }
        cout << " " << endl;
      }
      if (numberOfLegs > 3) { // Dog has all of it's legs.
        for (int i = 0; i < howFar; i++) {
          cout << "#" << flush;
          usleep(howFast * 1000); // Run full speed.
        }
        cout << "\n";
      }
    cout << dogName << " is tired now. My tail is dragging." << endl;
    }
  }
  usleep(howFast * 1000); // Pause
  isHappy = false; // Set isHappy to false.
}

/*
This method gives the dog a Bone object.  If dog isn't happy dog will accept the Bone.  If dog is happy he will not accept the bone.

isHappy - dog's happiness boolean
dogBone - Bone object
hasBone - dog's bone boolean

Return: none
*/
void Dog::addBone() {
  usleep(500 * 1000); // Pause.
  if (!isHappy) { // Dog currently isnt happy.
    isHappy = true; // Sets isHappy back to true.
    dogBone = new Bone(); // Make the bone.
    hasBone = true; // Sets hasBone to true
  } else if (isHappy && !hasBone) { // Dog is currently happy
    cout << "I'm already happy, I don't need a bone right now." << endl;
  }
  usleep(500 * 1000); // Pause.
}

/*
This method removes the Bone object from the dog.

hasBone - dog's bone boolean
isHappy - dog's happiness boolean

Return: none
*/
void Dog::removeBone() {
  delete dogBone; // delete the Bone object
  usleep(750 * 1000); // Pause.
  hasBone = false; // Set hasBone.
  cout << "...bone is gone..." << endl;
  usleep(750 * 1000); // Pause.
  cout << "I'm " << dogName << ", and I'm done being happy." << endl;
  usleep(500 * 1000); // Pause.
  cout << "...all done..." << endl;
  isHappy = false; // Set isHappy.
  usleep(500 * 1000); // Pause.
}

/*
This method makes the dog wag it's tail. If the dog isn't happy it will not wag tail. If the dog is happy but doesn't have a bone, it will not wag tail. If the dog is happy and has a bone, it will wag its tail. Calls the removeBOne() after the dog is done wagging tail. Uses usleep(milliseconds) to display tail wagging.

numTimes - number of times the dog will wags its tail
howFast - howFast the dog will wag it's tail
isHappy - dog's happiness boolean
hasBone - dog's bone boolean
dogName - name of the dog

Return: none
*/
void Dog::wag(int numTimes, int howFast) {
  usleep(500 * 1000); // Pause.
  if (!isHappy) { // Dog is not happy.
    cout << dogName << " isn't happy enough to wag my tail. Try scratching my ears." << endl;
  } else if (isHappy && !hasBone) { // Dog is happy but doesn't have a bone.
    cout << "Give me a bone and I'll wag my tail." << endl;
    isHappy = false;
  } else if (isHappy && hasBone) { // Dog is happy and has a bone.
    cout << "I'm " << dogName << ", and I'm happy. I'm wagging my tail! | wags: " << numTimes << ", sleep: " << howFast << "ms" << endl;
    usleep(howFast * 1000); // Pause.
    for (int i = 0; i < numTimes; i++) {
      if (i % 2 == 0) { // Wag tail left.
        cout << "\\ " << flush;
        usleep(howFast * 1000); // Speed of wag.
      } else if (i % 2 != 0) { // Wag tail right.
        cout << "/ " << flush;
        usleep(howFast * 1000); // Speed of wag.
      }
    }
    cout << "\n";
    usleep(howFast * 1000); // Pause.
    removeBone(); // Take the bone away.
  }
}

/*
This method sets the dog's happiness to true.

dogName - name of the dog
isHappy - dog's happiness boolean

Return: none
*/
void Dog::petDog() {
  usleep(500 * 1000); // Pause.
  cout << dogName << " loves being petted and is now happy!" << endl;
  usleep(500 * 1000); // Pause.
  isHappy = true; // Set isHappy
}
