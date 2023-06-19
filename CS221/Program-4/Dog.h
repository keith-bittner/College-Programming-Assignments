/*
Keith Bittner
CS221 - Spring 2019
Program 4 - Dog.h

This header file lays out the structure and methods related to the Dog class.
*/
using namespace std;

/*
This builds the structure for the Dog class both private and public properties/methods.

Private variables:
  numberOfLegs - integer representing the number of legs the dog has
  eyeColor - string representing the color of the dog's eyes
  furColor - string representing the color of the dog's fur
  weight - double float representing the weight of the dog
  isHappy - boolean value representing whether the dog is happy or not
  dogName - string representing the name of the dog
  hasBone - boolean representing whether the dog currently has a bone or not
  *dogBone - pointer representing a bone object created
  setHappiness(isHappy) - sets the dog's happiness to true

Public methods:
  Dog(name) - object constructor
  ~Dog() - object deconstructor
  run(howFar, howFast) - makes the dog run both param: 0-1000
  bark(numTimes) - makes the dog bark
  wag(numTimes, howFast) - makes the dog wag it's tail both param: 0-1000
  setWeight(weight) - sets the weight of the dog 0-200
  setNumberOfLegs(numLegs) - sets the number of legs the dog can have 0-4
  addBone() - gives the dog a bone object
  removeBone() - takes away the bone object from the dog
  petDog() - pets the dog to make happy

Return: none
*/
class Dog {
  private:
    int numberOfLegs;
    string eyeColor;
    string furColor;
    double weight;
    bool isHappy;
    string dogName;
    bool hasBone;
    Bone *dogBone;
    void setHappiness(bool isHappy);

  public:
    Dog(string name);
    ~Dog();
    void run(int howFar, int howFast);
    void bark(int numTimes);
    void wag(int numTimes, int howFast);
    void setWeight(double weight);
    void setNumberOfLegs(int numLegs);
    void addBone();
    void removeBone();
    void petDog();
};
