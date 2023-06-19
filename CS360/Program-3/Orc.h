#ifndef ORC_H
#define ORC_H

#include "Enemy.h"

class Orc: public Enemy	{
	public:
		Orc(int, int, int, int, int);
		void update();
		void print() const;
		void attack() const;
		void injure(int);
		const char getDisplayChar() const;
	
};//end of class

#endif

