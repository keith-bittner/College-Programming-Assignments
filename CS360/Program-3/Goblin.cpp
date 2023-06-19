/**
 * <h1>Goblin.cpp</h1>
 *
 * This is the implementation of the Goblin subclass. Inherits majority of
 * functionality from the Enemy abstract class.
 *
 * @author Keith Bittner
 * @version 2.0
 * @since 5/16/2020
 *
 */
#include "Enemy.h"
#include "Goblin.h"
#include <iostream>
#include <cstdlib>

using namespace std;

/**
 * Constructor for the Goblin object.
 *
 * @param h The health value passed in during function call.
 * @param s The strength value passed in during function call.
 * @param c The constitution value passed in during function call.
 * @param x The x position value passed in during function call.
 * @param y The y position value passed in during function call.
 */
Goblin::Goblin(int h, int s, int c, int x, int y)
	: Enemy(h, s, c, x, y) { //calling Enemy constructor first
		health = h; //setting health value
		str = s; //setting str value
		con = c; //setting con vlaue
		xPosition = x; //setting x position
		yPosition = y; //setting y position
		print();
}

/**
 * Moves the enemy based on a random generated value for both the x and y values.
 * x is random between -3 and 3, y is random between -2 and 2
 */
void Goblin::update() {
  //cout << "Im in GOblin::update()" << endl;
	xPosition = (rand() % 7 + (-3)); //# of values to choose from + starting value
	yPosition = (rand() % 5 + (-2));
  //cout << "I left GOblin::update()" << endl;
}

/**
 * Prints the enemy name, unique id, current location (x,y), and current health.
 */
void Goblin::print() const{
  //cout << "Im in GOblin::print()" << endl;
	cout << "Goblin " << Goblin::getId() << " @ (" << Goblin::getX() << ", " << Goblin::getY() <<
		") HP = " << Goblin::getHealth();
	cout << endl;
  //cout << "I left GOblin::print()" << endl;
}

/**
 * Attacks a random passerby. Attack value is generated randomly then added to
 * the enemy's str to determine the damage the passerby recieves.
 */
void Goblin::attack() const{
  //cout << "Im in GOblin::attack()" << endl;
	int dmgMod = Goblin::getStr(); //get the str value
	int damage = (rand() % 4 + 1) + dmgMod;
	cout << "Goblin " << Goblin::getId() << " attacks a random passerby for " << damage << " damage!";
	cout << endl;
  //cout << "I left GOblin::attack()" << endl;
}

/**
 * Allows a random passerby to attempt to fight the enemy back. If succesfull
 * the enemy takes damage. If the enemy is reduced to 0 health, enemy is killed.
 *
 * @param d The value of the passerby's attack,
 */
void Goblin::injure(int d){
  //cout << "Im in GOblin::injure()" << endl;
	int npcAtk = d; //passerby attack
	int shield = (Goblin::getCon()) / 2; //enemy can block his half his con in damage
	bool isHit = false; //was the enemy hit

	if (npcAtk <= shield) { //enemy blocks all damage
		cout << "The passerby tries to attack Goblin " << Goblin::getId() << ", but it's not very effective...";
		cout << endl;
	} else { //enemy can't block all damage
		isHit = true; //was hit
		int takeDmg = npcAtk - shield; //actual damage enemy takes

		//this automatically sets the health
		health = Goblin::getHealth() - npcAtk;
		cout << "Goblin " << Goblin::getId() << " takes " << takeDmg << " damage! Current HP = "
			<< health;
		cout << endl;
		if (health <= 0) { //enemy killed
			alive = false;  //the goblin has been died
			cout << "Goblin " << Goblin::getId() << " has been slain!";
			cout << endl;
		}
	}
}

/**
 * Sends a char identifier to its caller based on if the enemy is alive or dead.
 *
 * @Return char 'G' = alive, 'g' = dead
 */
const char Goblin::getDisplayChar() const {
	if (Goblin::isAlive() == true) {
		return 'G';
	} else {
		return 'g';
	}
}
