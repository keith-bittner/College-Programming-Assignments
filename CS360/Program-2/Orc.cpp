/**
 * <h1>Orc.cpp</h1>
 *
 * This is the implementation of the Orc subclass. Inherits majority of
 * functionality from the Enemy abstract class.
 *
 * @author Keith Bittner
 * @version 1.0
 * @since 4/26/2020
 *
 */
#include "Enemy.h"
#include "Orc.h"
#include <iostream>
#include <cstdlib>

using namespace std;

/**
 * Constructor for the Orc object.
 *
 * @param h The health value passed in during function call.
 * @param s The strength value passed in during function call.
 * @param c The constitution value passed in during function call.
 * @param x The x position value passed in during function call.
 * @param y The y position value passed in during function call.
 */
Orc::Orc(int h, int s, int c, int x, int y)
	: Enemy(h, s, c, x, y) { //calling Enemy constructor first
		maxHealth = h; //setting health value
		myStr = s; //setting str value
		myCon = c; //setting con value
		xPos = x; //setting x position
		yPos = y; //setting y position
		myId = Enemy::getID(); //setting unique id
		print();
}

/**
 * Prints the enemy name, unique id, current location (x,y), and current health.
 */
void Orc::print() const{
	cout << "Orc " << myId << " @ (" << Enemy::getX() << ", " << Enemy::getY() <<
		") HP = " << Enemy::getHP();
	cout << endl;
}

/**
 * Moves the enemy based on a random generated value for both the x and y values.
 * x and y is the given the same random value between -5 and 5
 */
void Orc::update() {
	int randXY = (rand() % 11 + (-5));
	Enemy::setX(randXY);
	Enemy::setY(randXY);
}

/**
 * Attacks a random passerby. Attack value is generated randomly then added to
 * the enemy's str to determine the damage the passerby recieves.
 */
void Orc::attack() {
	int dmgMod = Enemy::getStr(); //get str value
	int damage = (rand() % 6 + 1) + dmgMod;
	cout << "Orc " << myId << " attacks a random passerby for " << damage << " damage!";
	cout << endl;
}

/**
 * Allows a random passerby to attempt to fight the enemy back. If succesfull
 * the enemy takes damage. If the enemy is reduced to 0 health, enemy is killed.
 *
 * @param d The value of the passerby's attack,
 */
void Orc::injure(int d) {
	int npcAtk = d; //passerby attack
	int shield = Enemy::getCon(); //can block his con value in damage
	bool isHit = false; //was the enemy hit
	int health;

	if (npcAtk <= shield) { //enemy blocks all damage
		cout << "The passerby tries to attack Orc " << myId << ", but it's not very effective...";
		cout << endl;
	} else { //enemycan't block all damage
		isHit = true; //was hit
		int takeDmg = npcAtk - shield; //actual damage enemy takes
		Enemy::setHP(takeDmg, isHit); //set enemy's new hp value
		health = Enemy::getHP();
		cout << "Orc " << myId << " takes " << takeDmg << " damage! Current HP = "
			<< health;
		cout << endl;
		if (health <= 0) { //enemy killed
			cout << "Orc " << myId << " has been slain!";
			cout << endl;
		}
	}
}
