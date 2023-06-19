/**
 * <h1>Enemy.h</h1>
 *
 * This is the interface for the Enemy abstract class.
 *
 * @author Keith Bittner
 * @version 2.0
 * @since 5/16/2020
 *
 */
#ifndef ENEMY_H
#define ENEMY_H

class Enemy	{
	protected:
		int id;
		int health;
		int str;
		int con;
		int xPosition;
		int yPosition;
		bool alive;
		static int idGenerator;
		int currentHP;

	public:
		Enemy(int, int, int, int, int);
		bool isAlive() const;
		virtual void update() = 0;
		virtual void print() const = 0;
		virtual void attack() const = 0;
		virtual void injure(int) = 0;
		virtual const char getDisplayChar() const = 0;
		virtual void setX(int x);//use if needed
		virtual void setY(int y);//use if needed

		int getId() const;
		int getHealth() const;
		int getStr() const;
		int getCon() const;
		int getX() const;
		int getY() const;
		void setHealth(int h, bool i);

}; //end of class

#endif
