/*
Keith Bittner
CS221 - Spring 2019
Program 3 - main.c

This program builds and sorts an array of positive and/or negative integers
through the use of command-line processing.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "sort.h"

/*
This is the main function of the program.  It handles the command-line processing,
validates the amount of command-line arguments, prints a usage statement if program
is used improperly, builds the array from command-line arguments and sends the array
to other functions for sorting.

argc - number of command-line arguments
argv[] - char array of command-line arguments: argv[0] - executable
duplicate - a string duplicate
arrLength - length of array
anArray - array of integers
sort - sort type (A or D) for switch statement cases

Return: none
*/
int main (int argc, char *argv[]) {

    // usage statement and exit code
    if (argc > 34 || argc <= 2) {
        fprintf (stderr, "Error: %s missing correct argument count.\n\n", argv[0]);
        fprintf (stderr, "usage: %s A [D ...]\n", argv[0]);
        fprintf (stderr, "\tA: ascending, D: descending, etc.: + or - integers\n");
        fprintf (stderr, "\tMaximum length of integer array: 32\n");
        exit (1);
    }

    char * duplicate = strdup(argv[1]); // duplicate argv[1] for switch statment
    int arrLength = argc - 2; // set length of array
    int anArray[arrLength]; // initialize array

    // fill array with the command-line arguments begining at argv[2]
    for (int i = 2; i < argc; i++) {
        if (i < argc) {
            anArray[i - 2] = atoi (argv[i]); // convert to integer
        }
    }

    char sort = duplicate[0]; // making our argv[1] duplicate usable as a char
    sortSwitch(sort, anArray, arrLength); // switch statment function
}
