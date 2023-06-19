/*
Keith Bittner
CS 221 Spring 2019
Program 2 - C++

A short text based game that inputs a file with hero/enemy data, takes user input for actions, outputs results to both console and an output file.
*/

#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <cctype>
#include <stdlib.h>

using namespace std;

/*
This function calculates new health based on the random number drawn for the strength of the potion. Outputs what each player heals for and their new health value.  If player has reached limit of potions, functions will output "no potions" and cost player their turn.

outFile: battleResults.txt file for recording game data
power: amount potion will heal for
name: hero/enemy name (depends on turn)
current_HP: current health for hero/enemy (depends on turn)
start_HP: health hero/enemy starts with
limit: amount of potions hero/enemy gets to use

Return: none
*/
void healing(ofstream &outFile, int power, string name, int &current_HP, int start_HP, int &limit) {
  if (limit > 0) { // has potions left
    if ((current_HP + power) < start_HP) {
      current_HP = current_HP + power;
      cout << name << " chugs a potion and recovers " << power << " HP! New HP= " << current_HP << endl;
      outFile << name << " chugs a potion and recovers " << power << " HP! New HP= " << current_HP << endl;
      limit--;
    }

    if ((current_HP + power) >= start_HP) { // prevent over healing
      current_HP = start_HP;
      cout << name << " chugs a potion and is fully recovered! New HP= " << current_HP << endl;
      outFile << name << " chugs a potion and is fully recovered! New HP= " << current_HP << endl;
      limit--;
    }

    else if (current_HP == start_HP) { // health already full
      cout << name << " chugged a potion but nothing happened!" << endl;
      outFile << name << " chugged a potion but nothing happened!" << endl;
      limit--;
    }

  } else { // no potions
  cout << name << " is out of potions and wasted a turn" << endl;
  outFile << name << " is out of potions and wasted a turn" << endl;
  }
}

/*
This function outputs the game menu for user to choose options from.

name: current players name (hero/enemy depending on turn)
hp: current health value for player (hero/enemy depending on turn)
spell: name of spell/ability for player to use (depending on hero or enemy)

Return: none
*/
void gameDisplay (string name, int hp, string spell) {
  cout << "\n\nIt's " << name << "'s turn. Current HP = " << hp << endl;
  cout << "1: Attack" << endl;
  cout << "2: " << spell << endl;
  cout << "3: Potion" << endl;
}

/*
This functions runs the main game through the use of a switch statment based on who's turn and what option they choose.

outFile: battleResults.txt file for recording game data
menuOption: choice from userInput (determines case to run)
name1: hero name
name2: enemy name
hp_1: hero health
hp_2: enemy health
spell: hero/enemy spell/ability (depends on turn)
minAtk: minimum attack value
maxAtk: maximum attack value
minSpl: minimum spell/ability value
maxSpl: maximum spell/ability value
minPot: minimum potion value
maxPot: maximum potion value
turn: hero = true, enemy = false
startHP1: hero starting health value
startHP2: enemy starting health value
limit: number of potions player gets
temp: random generated number

Return: none
*/
void gameOptions(ofstream &outFile, char menuOption, string name1, string name2, int &hp_1, int &hp_2, string spell, int minAtk, int maxAtk, int minSpl, int maxSpl, int minPot, int maxPot, bool turn, int startHP1, int startHP2, int &limit) {
  int temp; // Value stores random generated value for calculations

  switch(menuOption) {
    case '1': // case 1 is normal attack
      if (turn) { // hero turn
        temp = (rand() % (maxAtk - minAtk + 1)) + minAtk;
        hp_2 = hp_2 - temp;
        cout << name1 << " attacks " << name2 << " for " << temp << " damage. New HP = " << hp_2 << endl;
        outFile << name1 << " attacks " << name2 << " for " << temp << " damage. New HP = " << hp_2 << endl;
      } else { // enemy turn
        temp = (rand() % (maxAtk - minAtk + 1)) + minAtk;
        hp_1 = hp_1 - temp;
        cout << name1 << " attacks " << name2 << " for " << temp << " damage. New HP = " << hp_1 << endl;
        outFile << name1 << " attacks " << name2 << " for " << temp << " damage. New HP = " << hp_1 << endl;
      }
      break;

    case '2': // case 2 is spell/ability attack
      if (turn) { // hero turn
        temp = (rand() % (maxSpl - minSpl + 1)) + minSpl;
        hp_2 = hp_2 - temp;
        cout << name1 << " casts " << spell << "! " << name2 << " takes " << temp << " damage. New HP = " << hp_2 << endl;
        outFile << name1 << " casts " << spell << "! " << name2 << " takes " << temp << " damage. New HP = " << hp_2 << endl;
      } else { // enemy turn
        temp = (rand() % (maxSpl - minSpl + 1)) + minSpl;
        hp_1 = hp_1 - temp;
        cout << name2 << " casts " << spell << "! " << name1 << " takes " << temp << " damage. New HP = " << hp_1 << endl;
        outFile << name2 << " casts " << spell << "! " << name1 << " takes " << temp << " damage. New HP = " << hp_1 << endl;
      }
      break;

    case'3': // case 3 is healing
      if (turn) { // hero turn
        temp = (rand() % (maxPot - minPot + 1)) + minPot;
        healing(outFile, temp, name1, hp_1, startHP1, limit);
      } else { // enemy turn
        temp = (rand() % (maxPot - minPot + 1)) + minPot;
        healing(outFile, temp, name2, hp_2, startHP2, limit);
      }
      break;

    default:
      cout << "~~INVALID INPUT~~ Choose a new menu option." << endl;
      break;
  }
}

/*
Main functions. Initializes all variables. Opens, validates, stores and closes input file. Opens, writes to, and closes output file. Handles user input and validation of user input.

hero_Name: hero name
monster_Name: enemy name
hero_Spell: hero spell/ability name
monster_Spell: enemy spell/ability name
userInput: user choice of displayed menu options
hero_HP: hero health value (changes during game)
monster_HP: enemy health value (changes during game)
minAtk: minimum attack value
maxAtk: maximum attack value
min_Spell: minimum spell/ability value
max_Spell: maximum spell/ability value
min_Potion: minimum potion value
max_Potion: maximum potion value
heroStartHP: starting health value for hero
monStartHP: starting health value for enemy
menuOption: options representing the cases in switch statement for user to choose
hero_Turn: determines player turn, hero = trun, enemy = false
inFile: input file that contains character data
outFile: output file that has the battle results
hero_Potion_Limit: hero potion limit count
mon_Potion_Limit: enemy potion count
*/
int main() {
  //Initialization of variables
  string hero_Name, monster_Name, hero_Spell, monster_Spell, string userInput;
  int hero_HP, monster_HP, min_Atk, max_Atk, min_Spell, max_Spell, min_Potion, max_Potion, heroStartHP, monStartHP;
  char menuOption;
  bool hero_Turn = true;

  ifstream inFile;
  ofstream outFile;

  // Structure to open/store data/close input file
  inFile.open("characterInfo.txt"); // Opens input file
  if (inFile.is_open()) { // Checks if file was actually opened
    inFile >> hero_Name >> hero_HP >> hero_Spell;
    inFile >> monster_Name >> monster_HP >> monster_Spell;
    inFile >> min_Atk >> max_Atk;
    inFile >> min_Spell >> max_Spell;
    inFile >> min_Potion >> max_Potion;

    cout << "Game data loaded successfully." << endl;
    inFile.close(); // Close input file since not needed anymore
  } else {
    cout << "Game data not loaded, try again." << endl;
  }

  // Potion related variables, must come after input file is loaded and stored
  heroStartHP = hero_HP;
  monStartHP = monster_HP;
  // incase you want different limits for the hero and enemy potion count
  int hero_Potion_Limit = 3;
  int mon_Potion_Limit = 3;

  // Open output file
  outFile.open("battleResults.txt");

  // Main game structure
  if (outFile.is_open()) {
    while (hero_HP > 0 && monster_HP > 0) {
      if (hero_Turn) { // start hero turn
        gameDisplay (hero_Name, hero_HP, hero_Spell);
        cout << "Enter action: ";
        cin >> userInput;

        if (userInput.length() > 1) { // lets validate the input
          cout << "~~INVALID INPUT~~ Choose a new menu option." << endl;
          cin >> userInput;
        } else {
          menuOption = userInput[0]; // good input now turn to char for switch statement
        }

        gameOptions(outFile, menuOption, hero_Name, monster_Name, hero_HP, monster_HP, hero_Spell, min_Atk, max_Atk, min_Spell, max_Spell, min_Potion, max_Potion, hero_Turn, heroStartHP, monStartHP, hero_Potion_Limit);
        hero_Turn = false; // end hero turn and set turn bool to false so enemy can go

      } else { // start enemy turn
        gameDisplay (monster_Name, monster_HP, monster_Spell);
        cout << "Enter action: ";
        cin >> userInput;

        if (userInput.length() > 1) { // lets validate the input
          cout << "~~INVALID INPUT~~ Choose a new menu option." << endl;
          cin >> userInput;
        } else {
          menuOption = userInput[0]; // good input now turn to char for switch statement
        }

        gameOptions(outFile, menuOption, hero_Name, monster_Name, hero_HP, monster_HP, monster_Spell, min_Atk, max_Atk, min_Spell, max_Spell, min_Potion, max_Potion, hero_Turn, heroStartHP, monStartHP, mon_Potion_Limit);
        hero_Turn = true; // end enemy turn and set turn bool to true so hero can go
      }
    }

    if (hero_HP <= 0) { // our poor hero has died
      cout << monster_Name << " has humiliated and killed our hero. Let it be written " << hero_Name << " failed!" << endl;
      outFile << monster_Name << " has humiliated and killed our hero. Let it be written " << hero_Name << " failed!" << endl;
      outFile.close(); // close output file
    } else { // the nasty enemy has died
      cout << hero_Name << " has eliminated " << monster_Name << ". Let our hero's name become legend!" << endl;
      outFile << hero_Name << " has eliminated " << monster_Name << ". Let our hero's name become legend!" << endl;
      outFile.close(); // Close output file
    }
  }
}
