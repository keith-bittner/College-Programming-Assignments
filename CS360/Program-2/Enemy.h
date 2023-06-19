/**
 * <h1>Enemy.h</h1>
 *
 * This is the interface for the Enemy abstract class.
 *
 * @author Keith Bittner
 * @version 1.0
 * @since 4/26/2020
 *
 */
#ifndef ENEMY_H
#define ENEMY_H

class Enemy{
	private:
		int idNum, currentHP, size;
		bool is_Alive;
		int idStorage[100]; //setting to 100 for testing, adjust as needed

	protected:
		int maxHealth, myStr, myCon, xPos, yPos; //object attributes
	
	public:
		Enemy(int h, int s, int c, int x, int y); //constructor
		bool isAlive() const;

		//pure virtual functions
		virtual void update() = 0;
		virtual void attack() = 0;
		virtual void injure(int d) = 0;
		virtual void print() const = 0;

		//getters
		int getX() const; //getter
		int getY() const; //getter
		int getHP() const; //getter
		int getCon() const; //getter
		int getStr() const; //getter
		int getID(); //getter

		//setters
		void setX(int xx); //setter
		void setY(int yy); //setter
		void setHP(int hh, bool ii); //setter
		int setID(); //setter
};

#endif
