/**
 * <h1>Troll.h</h1>
 *
 * This is the interface for the Troll subclass. Inherits from the Enemy class.
 *
 * @author Keith Bittner
 * @version 1.0
 * @since 4/26/2020
 *
 */
#ifndef TROLL_H
#define TROLL_H

#include "Enemy.h"

class Troll : public Enemy {
	private:
		int maxHealth, myId;

	public:
		Troll(int h, int s, int c, int x, int y); //constructor
		void update(); //setter
		void attack();
		void injure(int d);
		void print() const;	
};

#endif
