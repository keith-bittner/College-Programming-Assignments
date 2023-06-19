/*
Keith Bittner
CS221 - Spring 2019
Program 3 - sort.c

This program handles the switch statment, ascending and descending sorts.
*/

#include "sort.h"

/*
Switch statment to handle the 2 sorting cases. Prints sorted output to console.

sortType - what type of sort the user wants performed
array[] - array of integers
length - length of array

Return: none
*/
void sortSwitch(char sortType, int array[], int length) {

    switch(sortType){
        case 'A': // ascending
            sortAscend(array, length);
            for (int j = 0; j < length; j++) {
                printf("%d\n", array[j]);
            }
            break;

        case 'D': // descending
            sortDescend(array, length);
            for (int i = 0; i < length; i++) {
                printf("%d\n", array[i]);
            }
            break;
    }
}

/*
This function uses a bubble sort to sort an array into ascending order. Uses
recursion to ensure the array gets sorted fully.

array[] - an integer array
length - length of array
temp - temporary place holder for element that has to move
*/
void sortAscend (int array[], int length) {

    for (int i = 0; i < length - 1; i++) {
        if (array[i] > array[i + 1]) {
            int temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
        }
    }

    // call on youself to run again
    if (length - 1 > 1) {
      sortAscend (array, length - 1);
    }
}

/*
This function uses a bubble sort to sort an array into descending order. Uses
recursion to ensure the array gets sorted fully.

array[] - an integer array
length - length of array
temp - temporary place holder for element that has to move
*/
void sortDescend (int array[], int length) {

    for (int i = 0; i < length - 1; i++) {
        if (array[i] < array[i + 1]) {
            int temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
        }
    }

    // call on yourself to run again
    if (length - 1 > 1) {
      sortDescend (array, length - 1);
    }
}
