# Celsius to Fahrenheit Convertor

The Procedure Division must use the PERFORM… VARYING code structure that will control the module where the Celsius temperature is converted to Fahrenheit temperature. 
Use the provided Hierarchy Chart as the base for your logic. Note my comments with the Hierarchy Chart. 
```
===============================================================
IDENTIFICATION DIVISION.
PROGRAM-ID.  TEMPERATURE-CONVERSION.
AUTHOR.  Mel Sanschagrin (AKA Captain COBOL).

DATA DIVISION.
WORKING-STORAGE SECTION.
01   TEMPERATURE-VARIABLES.
       05   CELSIUS-VALUE		      PIC 9(3) VALUE  ZERO.
       05   START-CELSIUS-TEMP    PIC 9(3)  VALUE ZERO.
       05   CELSIUS-INCREMENT     PIC  9(2)  VALUE ZERO.
01   CONVERSION-DETAIL-RECORD.
       05   CELSIUS-VALUE-OUT	    PIC 9(3).
       05   FILLER			            PIC X(3)  VALUE SPACES.
       05   FAHRENHEIT-TEMP       PIC  9(3).         
==========================================================
```
Code this line of code for the conversion of Celsius to Fahrenheit.  
```
COMPUTE   FAHRENHEIT-TEMP = CELSIUS-VALUE * (9 / 5) + 32 
```
 
The 200 Initialize module is where the initial values for the `CELCIUS-VALUE` (control variable),  `START-CELCIUS-TEMP` (initial value), and `CELCIUS-INCREMENT` (incrementing value) are set. Use the `DISPLAY` and `ACCEPT` command to enter these values.

Once a Celsius value is calculated, the record `CONVERSION-DETAIL-RECORD` should be displayed using the `DISPLAY` command.

Hint – `The PERFORM .. VARYING` statement should be in the 
100 Produce Celsius Conversions module as it controls the 201 Produce One Celsius Conversion module.
