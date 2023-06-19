/**
 * <h1>Goblin.h</h1>
 *
 * This is the interface for the Goblin subclass. Inherits from the Enemy class.
 *
 * @author Keith Bittner
 * @version 2.0
 * @since 5/16/2020
 *
 */
#ifndef GOBLIN_H
#define GOBLIN_H

#include "Enemy.h"

class Goblin: public Enemy {
	public:
		Goblin(int, int, int, int, int);
		void update();
		void print() const;
		void attack() const;
		void injure(int);
		const char getDisplayChar() const;
};//end of class

#endif
