/**
 * <h1>Enemy.cpp</h1>
 *
 * This is the implementation of the Enemy abstract class. *
 *
 * @author Keith Bittner
 * @version 2.0
 * @since 5/16/2020
 *
 */
#include "Enemy.h"
#include <cstdlib>
#include <iostream>

using namespace std;

int Enemy::idGenerator = 0; // create unique ids

/**
 * Constructor for the Enemy object.
 *
 * @param h The health value passed in during function call.
 * @param s The strength value passed in during function call.
 * @param c The constitution value passed in during function call.
 * @param x The x position value passed in during function call.
 * @param y The y position value passed in during function call.
 */
Enemy::Enemy(int h, int s, int c, int x, int y) 
  :health(h),
  currentHP(h),
  str(s),
  con(c),
  xPosition(x),
  yPosition(y),
  id(idGenerator++),
  alive(true){}

/**
 * Checks to see if enemy is alive.
 *
 * @return bool true = alive, false = dead
 */
bool Enemy::isAlive() const {
  //cout << "Im in Enemy::isAlive()" << endl;
	return alive;
}

/**
 * Sets the current x position with a new value.
 *
 * @param xx The value to add to the x position.
 */
void Enemy::setX(int x) {
	xPosition = xPosition + x;
}

/**
 * Sets the current y position with a new value.
 *
 * @param yy The value to add to the y position.
 */
void Enemy::setY(int y) {
	yPosition = yPosition + y;
}

/**
 * Gets the current enemy unique id.
 *
 * @return int The current enemy unique id.
 */
int Enemy::getId() const{
	return id;
}

/**
 * Sets the current hp value with a new value up to maxHealth
 *
 * @param hh The value the hp value will be adjusted by.
 * @param ii If the enemy was injured. true = injured, false = not injured
 *
 */
void Enemy::setHealth(int h, bool i) {
	if (i == true) { //was injured
		currentHP = currentHP - h; //subtract from health
		if (currentHP <= 0) { //is enemy no longer alive
			alive = false;
		}
	} else { //was not inured
		if (currentHP < health) {
			currentHP = currentHP + h; //add to health
			if (currentHP >= health) {
				currentHP = health;
			}
		} else {
			currentHP = health;
		}
	}
}

/**
 * Gets the current enemy hp.
 *
 * @return int The current enemy hp.
 */
int Enemy::getHealth() const {
	return currentHP;
}

/**
 * Gets the current enemy constitution.
 *
 * @return int The current enemy constitution value.
 */
int Enemy::getCon() const {
	return con;
}

/**
 * Gets the current enemy strength.
 *
 * @return int The current enemy strength value.
 */
int Enemy::getStr() const {
	return str;
}

/**
 * Gets the current x position.
 *
 * @return int The current x position.
 */
int Enemy::getX() const {
	return xPosition;
}

/**
 * Gets the current y position.
 *
 * @return int The current y position.
 */
int Enemy::getY() const {
	return yPosition;
}


