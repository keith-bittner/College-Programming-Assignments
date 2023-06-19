/**
 * <h1>Goblin.h</h1>
 *
 * This is the interface for the Goblin subclass. Inherits from the Enemy class.
 *
 * @author Keith Bittner
 * @version 1.0
 * @since 4/26/2020
 *
 */
#ifndef GOBLIN_H
#define GOBLIN_H

#include "Enemy.h"

class Goblin : public Enemy {
	private:
		int myId;

	public:
		Goblin(int h, int s, int c, int x, int y); //constructor
		void update(); //setter
		void attack();
		void injure(int d);
		void print() const;	
};

#endif
