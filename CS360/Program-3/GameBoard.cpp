/**
 * <h1>GameBoard.cpp</h1>
 *
 * This is the implementation of the GameBoard class. Creates a n * n board
 * which displays where the current enemy is located and if alive or dead.
 *
 * @author Keith Bittner
 * @version 1.0
 * @since 5/16/2020
 *
 */
#include "GameBoard.h"
#include "Enemy.h"
#include "Goblin.h"
#include "Troll.h"
#include "Goblin.h"
#include <vector>
#include <ctype.h>
#include <iostream>

using namespace std;

/**
 * Constructor for the GameBoard object.
 *
 * @param x The max x coordinate.
 * @param y The max y coordinate.
 */
GameBoard::GameBoard(int x, int y) {
 	this->xMax = x;
	this->yMax = y;

	//we have max lets have min
	xMin = -x;
	yMin = -y;

	//setting the size for the 2d array
	this->xSize = (x * 2) + 1;
	this->ySize = (y * 2) + 1;
  
	//make the 2d char array
	myBoard = new char*[xSize];
	for (int i = 0; i < xSize; ++i) {
		myBoard[i] = new char[ySize];
	}

	//fill the 2d char array
	for (int i = 0; i < xSize; i++) {
		for (int j = 0; j < ySize; j++) {
			if (i == xMax) {
				myBoard[i][j] = '-';
				if (j == (yMax)){
					myBoard[i][j] = '+';
				}
			}else if (j == yMax) {
				myBoard[i][j] = '|';
			} else {
				myBoard[i][j] = '.';
			}
		}
	}
}

/**
 * This function adds a char representing an enemy on the board.
 *
 * @param enemy A pointer to an enemy object.
 */
void GameBoard::addGamePiece(Enemy* enemy) {
	//set some temp variables
	int tempId = enemy->getId();
	int tempX = enemy->getX();
	int tempY = enemy-> getY();
	bool imAlive = enemy->isAlive();
	char enemyChar = enemy->getDisplayChar();

	//converts the coordinates to array coordinates
	int arrX = xMax - tempY;
	int arrY = yMax + tempX;

	if (imAlive == false) { //enemy dead show char
		myBoard[arrX][arrY] = enemy->getDisplayChar();
	} else { //enemy alive, more to do
		checkBorder(tempX, tempY); //check if move is inside the border

		if (validMove(tempX, tempY)) { //check if the move is blocked by another
			myBoard[arrX][arrY] = enemy->getDisplayChar();
		} else { //move is blocked
			bool imValid = false;
			while(imValid == false) {
				//randomize a new location within 2 places of previous
				tempX = ((rand() % 5 + (-2)) + tempX);
				tempY = ((rand() % 5 + (-2) + tempY));
				checkBorder(tempX, tempY); //check if new coords are in border
				if (validMove(tempX, tempY)) { // no enemy already resides here
					arrX = xMax - tempY;
					arrY = yMax + tempX;	
					coords.push_back(x);
					coords.push_back(y);
					myEnemies.push_back(coords);
					coords.clear();
					myBoard[arrX][arrY] = enemy->getDisplayChar();
					imValid = true;
				}
			}
		}
	}
}//end of addGamePiece Method

/**
 * This function checks to see if a enemy's x and y move is within the GameBoard.
 *
 * @param &x A reference to the enemy's xPosition.
 * @param &y A reference to the enemy's yPosition.
 */
void GameBoard::checkBorder(int &x, int &y) {
  if (x < xMin) { //negative
    x = xMin;
  } else if (x > xMax) { //positive
    x = xMax;
  }

  if (y < yMin) { //negative
    y = yMin;
  } else if (y > yMax) { //positive
    y = yMax;
  }
}

/**
 * This function checks to see if the x and y position it wants to move to are
 * empty or contain an enemy.
 *
 * @param x The eney's xPosition
 * @param y The enemy's yPosition
 * @return bool Returns true if the spot is unoccupied by another.
 */
bool GameBoard::validMove(const int x, const int y) const {
  int tempX = ((((xMax * 2) + 1) / 2) + y);
  int tempY = ((((yMax * 2) + 1) / 2) + x);

  return (myBoard[tempX][tempY] == '.' || myBoard[tempX][tempY] == '|' || myBoard[tempX][tempY] == '-' || myBoard[tempX][tempY] == '+');
}

/**
 * This function prints the gameboard.
 *
 */
void GameBoard::printBoard() const{
	for (int i = 0; i < xSize; i++) {
		for (int j = 0; j < ySize; j++) {
		  cout << myBoard[i][j];
		}
	  cout << endl;
	}
}

/**
 * This function clears the game board of all enemy objects.
 *
 */
void GameBoard::resetBoard() {
	for (int i = 0; i < xSize; i++) {
		for (int j = 0; j < ySize; j++) {
			if (i == xMax) {
				myBoard[i][j] = '-';
				if (j == (yMax)){
					myBoard[i][j] = '+';
				}
			}else if (j == yMax) {
				myBoard[i][j] = '|';
			} else {
				myBoard[i][j] = '.';
			}
		}
	}
}
