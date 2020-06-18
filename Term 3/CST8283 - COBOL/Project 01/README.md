# Storage Problem

## Problem Description
A file of inventory records is to be created from data entered at the keyboard (interactively). The record description and is given below.

Part Number         	7 bytes numeric
Quantity on hand 	  4 bytes numeric;
Unit price		        4 bytes numeric.

The output file will be called `INVENT-FILE-OUT` within the program and referred to as `INVFILE.TXT` on the external medium.

The record within the program will be referred to as `INVENT-RECORD-OUT`. There is to be no editing of the output record; it is to be written to the file exactly as input.
Use meaningful names for the data fields (elementary or group).

## Processing Requirements
The program should:
•	prompt the user for each field value to be entered (see Screen Spacing below);
•	use the test data provided in Data Records below;
•	prompt the user whether a record is to be entered (see Screen Spacing below);
•	once a record is entered, the record should be written to the external file exactly as entered – no editing;

The prompt and data entry should follow the sequence noted below in Screen Spacing.
Screen Spacing 

| ELEMENT NAME / query | PROMPT position | DATA entry position |
|:--|:--|:--|
|Part Number |	 Line 4 col 5 | Line 5    col 10 | 
|Quantity on hand	| Line 6 col5 | Line 7    col 10 | 
|Unit price	| Line 8 col 5	 | Line 9   col 10 | 
|Prompt for record entry	 | Line 16 col 10 | Line 17  col 10 | 

Program control should depend on the user’s response to the prompt whether another record is to be entered.          

Each prompt message to the user should briefly but clearly indicate the type of data or response required. (e.g. Enter Price,  Record to enter ( Y or N).

## Data Records
The data you must use for the program execution (test data) is contained in the table below. 

| Part number	 | Quantity on hand	 | Unit price | 
|:--|:--|:--|
| 1111111 | 0150 | 0070 | 
| 2222222	 | 2000	 | 0080 | 
| 3333333 | 1000	 | 0090 | 
