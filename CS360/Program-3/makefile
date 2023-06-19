CC = g++

CFLAGS=-std=c++0x -I.

DEPS = GameBoard.h Enemy.h Goblin.h Troll.h Orc.h

OBJ = main.o GameBoard.o Enemy.o Goblin.o Troll.o Orc.o

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

game: $(OBJ)
	$(CC) -o $@ $^ $(CFLAGS)

tidy:
	rm -f *.o core

clean:
	rm -f *.o core game
