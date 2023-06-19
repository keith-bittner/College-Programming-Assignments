/**
 * <h1>Enemy.cpp</h1>
 *
 * This is the implementation of the Enemy abstract class. *
 *
 * @author Keith Bittner
 * @version 1.0
 * @since 4/26/2020
 *
 */
#include "Enemy.h"
#include <cstdlib>

/**
 * Constructor for the Enemy object.
 *
 * @param h The health value passed in during function call.
 * @param s The strength value passed in during function call.
 * @param c The constitution value passed in during function call.
 * @param x The x position value passed in during function call.
 * @param y The y position value passed in during function call.
 */
Enemy::Enemy(int h, int s, int c, int x, int y) {
	this->maxHealth, currentHP = h; //setting health values
	this->myStr = s; //setting str values
	this->myCon = c; //setting con values
	this->xPos = x; //setting xPos
	this->yPos =y; //setting yPos
	idNum = setID(); //setting unique id
	is_Alive = true; //giving enemy life
}

/**
 * Checks to see if enemy is alive.
 *
 * @return bool true = alive, false = dead
 */
bool Enemy::isAlive() const {
	return is_Alive;
}

/**
 * Gets the current x position.
 *
 * @return int The current x position.
 */
int Enemy::getX() const {
	return xPos;
}

/**
 * Gets the current y position.
 *
 * @return int The current y position.
 */
int Enemy::getY() const {
	return yPos;
}

/**
 * Gets the current enemy hp.
 *
 * @return int The current enemy hp.
 */
int Enemy::getHP() const {
	return currentHP;
}

/**
 * Sets the current x position with a new value.
 *
 * @param xx The value to add to the x position.
 */
void Enemy::setX(int xx) {
	xPos = xPos + xx;
}

/**
 * Sets the current y position with a new value.
 *
 * @param yy The value to add to the y position.
 */
void Enemy::setY(int yy) {
	yPos = yPos + yy;
}

/**
 * Sets the current hp value with a new value up to maxHealth
 *
 * @param hh The value the hp value will be adjusted by.
 * @param ii If the enemy was injured. true = injured, false = not injured
 *
 */
void Enemy::setHP(int hh, bool ii) {
	if (ii == true) { //was injured
		currentHP = currentHP - hh; //subtract from health
		if (currentHP <= 0) { //is enemy no longer alive
			is_Alive = false;
		}
	} else { //was not inured
		if (currentHP < maxHealth) {
			currentHP = currentHP + hh; //add to health
			if (currentHP >= maxHealth) {
				currentHP = maxHealth;
			}
		} else {
			currentHP = maxHealth;
		}
	}
}

/**
 * Gets the current enemy constitution.
 *
 * @return int The current enemy constitution value.
 */
int Enemy::getCon() const {
	return myCon;
}

/**
 * Gets the current enemy strength.
 *
 * @return int The current enemy strength value.
 */
int Enemy::getStr() const {
	return myStr;
}

/**
 * UNIQUE ID EXPLANATION
 *
 * The following method creates a unique id for each enemy created. It takes
 * the size of the idStorage array and creates a random number between 1 and
 * the size of the idStorage array.
 *
 * It checks the idStorage array to ensure the id is unique. If the idStorage
 * array already contains the generated id, it will recursively call itself
 * until a id not already stored in the array is obtained. It then stores the
 * new id into the current empty index of the array and returns the id to the
 * constructor.
 *
 * Additionally, functionality can be changed to auto increase the size of the
 * idStorage array as new Enemy objects are created, therefore minimizing the
 * the amount of memory need to initially store the array. So, when first
 * declaring the array, set the size to be 1. Then the size of the idStorage
 * array will only increase by 1 when the setID function is called in the
 * constructor.
 *
 * @return int The generated unique id.
 */
int Enemy::setID() {
	size = (sizeof(idStorage) / sizeof(idStorage[0]));
	int temp = rand() % size + 1;

	for (int i = 0; i < size; i++) {
		if (idStorage[i] == temp) {
			setID();
		} else {
			idStorage[i] = temp;
			return temp;
		}
	}
}

/**
 * Gets the current enemy unique id.
 *
 * @return int The current enemy unique id.
 */
int Enemy::getID() {
	return idNum;
}
