/**
 * <h1>Goblin.cpp</h1>
 *
 * This is the implementation of the Goblin subclass. Inherits majority of
 * functionality from the Enemy abstract class.
 *
 * @author Keith Bittner
 * @version 1.0
 * @since 4/26/2020
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
		maxHealth = h; //setting health value
		myStr = s; //setting str value
		myCon = c; //setting con vlaue
		xPos = x; //setting x position
		yPos = y; //setting y position
		myId = Enemy::getID(); //setting unique id
		print();
}

/**
 * Prints the enemy name, unique id, current location (x,y), and current health.
 */
void Goblin::print() const{
	cout << "Goblin " << myId << " @ (" << Enemy::getX() << ", " << Enemy::getY() <<
		") HP = " << Enemy::getHP();
	cout << endl;
}

/**
 * Moves the enemy based on a random generated value for both the x and y values.
 * x is random between -3 and 3, y is random between -2 and 2
 */
void Goblin::update() {
	int randX = (rand() % 7 + (-3)); //# of values to choose from + starting value
	int randY = (rand() % 5 + (-2));
	Enemy::setX(randX);
	Enemy::setY(randY);
}

/**
 * Attacks a random passerby. Attack value is generated randomly then added to
 * the enemy's str to determine the damage the passerby recieves.
 */
void Goblin::attack() {
	int dmgMod = Enemy::getStr(); //get the str value
	int damage = (rand() % 4 + 1) + dmgMod;
	cout << "Goblin " << myId << " attacks a random passerby for " << damage << " damage!";
	cout << endl;
}

/**
 * Allows a random passerby to attempt to fight the enemy back. If succesfull
 * the enemy takes damage. If the enemy is reduced to 0 health, enemy is killed.
 *
 * @param d The value of the passerby's attack,
 */
void Goblin::injure(int d) {
	int npcAtk = d; //passerby attack
	int shield = (Enemy::getCon()) / 2; //enemy can block his half his con in damage
	bool isHit = false; //was the enemy hit
	int health;

	if (npcAtk <= shield) { //enemy blocks all damage
		cout << "The passerby tries to attack Goblin " << myId << ", but it's not very effective...";
		cout << endl;
	} else { //enemy can't block all damage
		isHit = true; //was hit
		int takeDmg = npcAtk - shield; //actual damage enemy takes
		Enemy::setHP(takeDmg, isHit); //set enemy's new hp value
		health = Enemy::getHP();
		cout << "Goblin " << myId << " takes " << takeDmg << " damage! Current HP = "
			<< health;
		cout << endl;
		if (health <= 0) { //enemy killed
			cout << "Goblin " << myId << " has been slain!";
			cout << endl;
		}
	}
}
