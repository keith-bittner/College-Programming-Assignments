/**
 * <h1>Orc.h</h1>
 *
 * This is the interface for the Orc subclass. Inherits from the Enemy class.
 *
 * @author Keith Bittner
 * @version 1.0
 * @since 4/26/2020
 *
 */
#ifndef ORC_H
#define ORC_H

#include "Enemy.h"

class Orc : public Enemy {
	private:
		int myId;

	public:
		Orc(int h, int s, int c, int x, int y); //constructor
		void update(); //setter
		void attack();
		void injure(int d);
		void print() const;	
};

#endif
