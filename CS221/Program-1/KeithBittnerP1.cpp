/*
Keith Bittner
CS 221 Spring 2019
Program 1 - c++ version

A short program that asks a user to input an Employee ID Number, the Employee's full name, and their Gross Pay for the
month.  It then calculates and displays each itemized deduction, net pay and % of take home pay.
*/

#include <iostream>
#include <iomanip>
#include <string>

// defining of all used CONSTANTS
#define NAME_LENGTH 101
#define FED_TAX .175
#define ST_TAX .091
#define SS_TAX .062
#define MED_TAX .0145
#define RETIRE .0929
#define MED_INS 51.48
#define LIFE_INS 3.71

using namespace std;

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
  cout << fixed;
  cout << setprecision(2);
  cout << "\n\nPaycheck for " << empName << ", employeeId " << empIdNumb << endl;
  cout << "Gross Amount: ................ $" << setfill(' ') << setw(7) << grossPay << endl;

  cout << "\nDeductions" << endl;
  cout << "Federal Tax: ................. $" << setfill(' ') << setw(7) << calcTax(grossPay, FED_TAX) << endl;
  cout << "State Tax: ................... $" << setfill(' ') << setw(7) << calcTax(grossPay, ST_TAX) << endl;
  cout << "Social Security Tax: ......... $" << setfill(' ') << setw(7) << calcTax(grossPay, SS_TAX) << endl;
  cout << "Medicare Tax: ................ $" << setfill(' ') << setw(7) << calcTax(grossPay, MED_TAX) << endl;
  cout << "Retirement Plan: ............. $" << setfill(' ') << setw(7) << calcTax(grossPay, RETIRE) << endl;
  cout << "Health Insurance: ............ $" << setfill(' ') << setw(7) << MED_INS << endl;
  cout << "Life Insurance: .............. $" << setfill(' ') << setw(7) << LIFE_INS << endl;

  float netPay = grossPay - deduct(grossPay); // Calculate netPay
  cout << "\nNet Pay: ..................... $" << setfill(' ') << setw(7) << netPay << endl;
  cout << "Percent take home: ........... %" << setfill(' ') << setw(7) << (netPay / grossPay) * 100 << endl;

}

int main() {
  int empIdNumb;
  char empName[NAME_LENGTH];
  float grossPay;

  cout << "Enter employee's id: ";
  (cin >> empIdNumb).get(); // Clear input after getting employee ID number
  cout << "Enter employee's full name: ";
  cin.getline(empName, NAME_LENGTH);
  cout << "Enter gross salary for " << empName << ": ";
  cin >> grossPay;

  payCheck(grossPay, empIdNumb, empName);
}
