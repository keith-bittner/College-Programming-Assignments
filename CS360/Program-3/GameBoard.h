/**
 * <h1>GameBoard.h</h1>
 *
 * This is the interface for the GameBoard abstract class.
 *
 * @author Keith Bittner
 * @version 1.0
 * @since 5/16/2020
 *
 */
#ifndef GAMEBOARD_H
#define GAMEBOARD_H

#include "Enemy.h"
#include "Goblin.h"
#include "Troll.h"
#include "Orc.h"
#include <vector>

using namespace std;

class GameBoard {
	private:
		int xMax, yMax, x, y, xMin, yMin, xSize, ySize;
		vector<vector<int>> myEnemies;
    	vector<int> coords;
    	char **myBoard;

	public:
		GameBoard(int x, int y);
		void addGamePiece(Enemy* enemy);
		void resetBoard();
		void printBoard() const;
    	void checkBorder(int &x, int &y);
    	bool validMove(const int x, const int y) const;
};

#endif

