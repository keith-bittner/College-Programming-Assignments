/**
 * <h1>Troll.cpp</h1>
 *
 * This is the implementation of the Troll subclass. Inherits majority of
 * functionality from the Enemy abstract class.
 *
 * @author Keith Bittner
 * @version 2.0
 * @since 5/16/2020
 *
 */
#include "Enemy.h"
#include "Troll.h"
#include <iostream>
#include <cstdlib>

using namespace std;

/**
 * Constructor for the Troll object.
 *
 * @param h The health value passed in during function call.
 * @param s The strength value passed in during function call.
 * @param c The constitution value passed in during function call.
 * @param x The x position value passed in during function call.
 * @param y The y position value passed in during function call.
 */
Troll::Troll(int h, int s, int c, int x, int y)
	: Enemy(h, s, c, x, y) { //calling Enemy constructor first
		health = h; //setting health value
		str = s; //setting str value
		con = c; //setting con vlaue
		xPosition = x; //setting x position
		yPosition = y; //setting y position
		print();
}

/**
 * Moves the enemy based on a random generated value for the x value either
 * left or right. Allows enemy to restore health based on constitution value.
 * x is random between -10 and -7 or is random between 7 and 10
 */
void Troll::update() {
	int currentHealth = Troll::getHealth(); //get current hp value

	if (currentHealth < maxHealth) { //only heal if current hp is less than max
		int trollCon = Troll::getCon();
		bool injured = false; //bool to tell function to run heal block
		Troll::setHealth(trollCon, injured);
		cout << "Troll " << Troll::getId() << " has recovered " << trollCon << " health. current HP: "
			<< Troll::getHealth();
		cout << endl;
	}

	int choice = (rand() % 2 + 1); //choice to determine if moving left or right

	if (choice == 1) { //moving left
		int leftX = (rand() % 4 + (-10));
		Troll::setX(leftX);
	} else { //moving right
		int rightX = (rand() % 4 + 7);
		Troll::setX(rightX);
	}
}

/**
 * Prints the enemy name, unique id, current location (x,y), and current health.
 */
void Troll::print() const{
	cout << "Troll " << Troll::getId() << " @ (" << Troll::getX() << ", " << Troll::getY() <<
		") HP = " << Troll::getHealth();
	cout << endl;
}

/**
 * Attacks a random passerby. Attack value is generated randomly then added to
 * the enemy's str to determine the damage the passerby recieves.
 */
void Troll::attack() const{
	int dmgMod = Troll::getStr(); //get the str value
	int damage = (rand() % 8 + 1) + dmgMod;
	cout << "Troll " << Troll::getId() << " attacks a random passerby for " << damage << " damage!";
	cout << endl;
}

/**
 * Allows a random passerby to attempt to fight the enemy back. If succesfull
 * the enemy takes damage. If the enemy is reduced to 0 health, enemy is killed.
 *
 * @param d The value of the passerby's attack,
 */
void Troll::injure(int d) {
	int npcAtk = d; //passerby attack
	int shield = (int)((Troll::getCon()) * 1.5); //can block con times 1.5 in damage
	bool isHit = false; //was the enemy hit
	int myHealth;

	if (npcAtk <= shield) { //enemy blocks all damage
		cout << "The passerby tries to attack Troll " << Troll::getId() << ", but it's not very effective...";
		cout << endl;
	} else { //enemy can't block all damage
		isHit = true; //was hit
		int takeDmg = npcAtk - shield; //actual damage enemy takes
		Troll::setHealth(takeDmg, isHit); //set enemy's new hp value
		myHealth = Troll::getHealth();
		cout << "Troll " << Troll::getId() << " takes " << takeDmg << " damage! Current HP = "
			<< myHealth;
		cout << endl;
		if (myHealth <= 0) { //enemy killed
			cout << "Troll " << Troll::getId() << " has been slain!";
			cout << endl;
		}
	}
}

/**
 * Sends a char identifier to its caller based on if the enemy is alive or dead.
 *
 * @Return char 'T' = alive, 't' = dead
 */
const char Troll::getDisplayChar() const {
	if (Troll::isAlive() == true) {
		return 'T';
	} else {
		return 't';
	}
}
