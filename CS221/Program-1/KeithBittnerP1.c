/*
Keith Bittner
CS 221 Spring 2019
Program 1 - c version

A short program that asks a user to input an Employee ID Number, the Employee's full name, and their Gross Pay for the
month.  It then calculates and displays each itemized deduction, net pay and % of take home pay.
*/

#include <stdio.h>

// defining of all used CONSTANTS
#define NAME_LENGTH 101
#define FED_TAX .175
#define ST_TAX .091
#define SS_TAX .062
#define MED_TAX .0145
#define RETIRE .0929
#define MED_INS 51.48
#define LIFE_INS 3.71

/*
This function calculates the total deductions for an employee.

grossPay - employee's gross pay from user input
percentRates - adds up the total of percentage rates (does not include MED_INS and LIFE_INS)
totalDeduct - the calculated deductions based on grossPay multiplied by the percentRates (MED_INS & LIFE_INS are added
              as a flat rate)

Return: a float value to netPay
*/
float deduct(float grossPay){
  float percentRates = FED_TAX + ST_TAX + SS_TAX + MED_TAX + RETIRE;
  float totalDeduct = (grossPay * percentRates) + MED_INS + LIFE_INS;
  return totalDeduct;
}

/*
This function calculates an individual deduction based on grossPay multiplied by a single CONSTANT (not including
MED_INS and LIFE_INS).

pay - employee's gross pay from user input
taxRate - a defined CONSTANT

Return: a float value to outputs in paycheck()
*/
float calcTax(float pay, float taxRate) {
  return pay * taxRate;
}

/*
This function formats and displays the employee's paycheck.  All calculations except netPay and take home % are sent to
helper functions.

grossPay - employee's gross pay from user input
empIdNumb - employee's id number from user input
empName - employee's name from user input
netPay - employee's gross pay minus total deductions

Return: nothing
*/
void payCheck (float grossPay, int empIdNumb, char empName[]) {
  printf("\n\nPaycheck for %s, employeeId %d", empName, empIdNumb);
  printf("\nGross amount: ................ $%7.2f", grossPay);

  printf("\n\nDeductions");
  printf("\nFederal Tax: ................. $%7.2f", calcTax(grossPay, FED_TAX));
  printf("\nState Tax: ................... $%7.2f", calcTax(grossPay, ST_TAX));
  printf("\nSocial Security Tax: ......... $%7.2f", calcTax(grossPay, SS_TAX));
  printf("\nMedicare Tax: ................ $%7.2f", calcTax(grossPay, MED_TAX));
  printf("\nRetirement Plan: ............. $%7.2f", calcTax(grossPay, RETIRE));
  printf("\nHealth Insurance: ............ $%7.2f", MED_INS);
  printf("\nLife Insurance: .............. $%7.2f", LIFE_INS);

  float netPay = grossPay - deduct(grossPay); // Calculate netPay
  printf("\n\nNet Pay: ..................... $%7.2f", netPay);
  
  float percentPay = (netPay / grossPay) * 100; // Calculate take home percentage
  printf("\nPercent take home: ........... %%%7.2f\n", percentPay);
}

int main() {
  int empIdNumb;
  char empName[NAME_LENGTH];
  float grossPay;

  printf("Enter employee's id: ");
  scanf(" %d", &empIdNumb);
  printf ("Enter employee's full name: ");
  scanf(" %100[^\n]s", empName);
  printf("Enter gross salary for %s: ", empName);
  scanf(" %f", &grossPay);

  payCheck(grossPay, empIdNumb, empName);
}
