                           CIDR Set Comparison
==============================================================================
This README file explains how to run the code in this directory.

This program will return a matrix, which indicates the degree of interaction
between any n number of sets of CIDR values.

To run this program run the following commands:
javac CIDR.java CompareSets.java TestCompare.java [or javac *.java]
java TestCompare [input file]
______________________________________________________________________________
CIDR.java
______________________________________________________________________________
This class models a CIDR value with three fields:
   1) A string value of the CIDR for easy printing
   2) An int to hold to IP address
   3) An int bit mask to compute the range of IP addresses the CIDR contains

The constuctor takes in a string in the format:
"(0-255).(0-255).(0-255).(0-255)/(0-32)". For example "192.168.0.1/28". The 
constructor will read in this value to initialize the three fields.

CIDR.contains(CIDR)
   fuction that returns a boolean of if the CIDR contains another CIDR
______________________________________________________________________________
CompareSets.java
______________________________________________________________________________
This class has all of the functions needed for calculating the interaction 
matrix as well as displaying the information in a presentable way.

CompareSets.returnInteractions(List<List<CIDR>>)
   Function to return the matrix of levels of interaction between sets of 
   CIDR values

CompareSets.printMatrix(int[][])
   Prints out the interactions in matrix form.

CompareSets.printInteractions(int[][])
   Print out the interactions in list form. Skips comparison between sets
   and itself

CompareSets.printLists(List<List<CIDR>>)
   Prints out a list of all of the sets.

______________________________________________________________________________
TestCompare.java
______________________________________________________________________________
This is the testing class of the other classes and function. It reads in CIDR
sets from a file specified in a command line argument. 

The format of the input file is as follows:
   * Each line in the input file represents a set
   * Each CIDR value in a line is comma separated

Input File Example:
10.10.0.0/16, 10.20.0.0/16, 192.168.5.0/24
109.142.213.1/3, 188.165.12.0/23

This file is read in as two sets of CIDR values.
Set 1: {10.10.0.0/16, 10.20.0.0/16, 192.168.5.0/24}
Set 2: {109.142.213.1/3, 188.165.12.0/23}

The main will read in the sets and then display them in the console. It will 
then prompt the user if they want to see a list of the interactions or a 
table representation of the matrix or both.