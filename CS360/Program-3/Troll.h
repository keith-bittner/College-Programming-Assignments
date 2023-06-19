/**
 * <h1>Troll.h</h1>
 *
 * This is the interface for the Troll subclass. Inherits from the Enemy class.
 *
 * @author Keith Bittner
 * @version 2.0
 * @since 5/16/2020
 *
 */
#ifndef TROLL_H
#define TROLL_H

#include "Enemy.h"

class Troll: public Enemy {
	private:
		int maxHealth;

	public:
		Troll(int, int, int, int, int);
		void update();
		void print() const;
		void attack() const;
		void injure(int);
		const char getDisplayChar() const;
};//end of class

#endif

