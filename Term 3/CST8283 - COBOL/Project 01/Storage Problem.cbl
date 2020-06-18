       program-id. project1 as "CST8283 PROJECT1".
       author. Olga Zimina - CST8283

       environment division.
       input-output section.
       file-control.
           select INVENTORY-FILE-OUT
               assign to "D:\INVFILE.TXT"
                   organization is line sequential.
       
       data division.
       file section.
       fd INVENTORY-FILE-OUT.
      *----------------------------------------------------
      * Variable to store data in file
      *----------------------------------------------------
       01 inventory-record-out     pic 9(15).
       
       
       working-storage section.
      *----------------------------------------------------
      * Variables to store data which user entered for the row
      *----------------------------------------------------
       01  inventory-record-in.
           05  part-number-in      pic 9(7).
           05  qty-on-hand-in      pic 9(4).
           05  unit-price-in       pic 9(4).
       
      *----------------------------------------------------
      * Messages which will be shown at the screen during
      * program execution
      *----------------------------------------------------
       01  input-messages.
           05  record-prompt       pic x(46)
               value "Do you have another record? Please use: y or n".
           05  input-response      pic x(1).
           05  part-number-prompt  pic x(29)
               value "Please enter the part number:".
           05  qty-on-hand-prompt  pic x(34)
               value "Please enter the quantity on hand:".
           05  unit-price-prompt   pic x(28)
               value "Please enter the unit price:".

      *----------------------------------------------------
      * Variables to store amount of entered rows and saved rows
      * They are incresed during program execution
      *----------------------------------------------------
       01  total-records.
           05  entered-counter     pic 9(2)  value zero.
           05  written-counter     pic 9(2)  value zero.

       procedure division.
      *----------------------------------------------------
      * This is the entry point of the application and
      * main block which manage the whole program context
      *----------------------------------------------------
       100-create-inventory-file.
           perform  201-create-initial-file.
           perform  202-create-records
                    until input-response = "n".
           perform  203-close-file.

           stop run.
           
      *----------------------------------------------------
      * This block is responsible for opening file/create
      * if not exists, and showing the screen with a question
      * whether a new record should be added or not
      *----------------------------------------------------
       201-create-initial-file.
           perform  301-open-files.
           perform  302-request-data.
           
      *----------------------------------------------------
      * This block takes user input for each of the data entry
      * which needs to be put into the row and then saves 
      * data into the file
      *----------------------------------------------------
       202-create-records.
           perform 303-input-inventory-data.
           perform 304-write-inventory-record.
           perform 302-request-data.
           
      *----------------------------------------------------
      * Final block closing open file with recorded data and
      * displaying the amount of enetered records and 
      * saved records into the file
      *----------------------------------------------------
       203-close-file.
           perform  305-close-files.
           perform  306-display-total-records.
           perform  307-end-message.

      *----------------------------------------------------
      * Low level block to open file
      *----------------------------------------------------
       301-open-files.
           open output inventory-file-out.

      *----------------------------------------------------
      * Displaying a question about the next row and accept user input
      *----------------------------------------------------
       302-request-data.
           display record-prompt
               line 12 column 5.
           accept input-response
               line 13 column 5.

      *----------------------------------------------------
      * Displaying each entry for data and accept user input
      * Also increases the counter of rows added
      *----------------------------------------------------
       303-input-inventory-data.
           initialize  inventory-record-in.
           display  part-number-prompt  line 4  column 5 with blank 
           screen.
           accept   part-number-in      line 5  column 10.

           display  qty-on-hand-prompt  line 6  column 5.
           accept   qty-on-hand-in      line 7  column 10.

           display  unit-price-prompt   line 8  column 5.
           accept   unit-price-in       line 9  column 10.

           add 1 to entered-counter.
       
      *----------------------------------------------------
      * Move the value of the previously grabbed row to
      * another variable which should be outputted to the 
      * file and write it down to the file.
      * Also increases the counter of rows written
      *----------------------------------------------------
       304-write-inventory-record.
           move  inventory-record-in  to  inventory-record-out.
           write inventory-record-out.
           add 1 to written-counter.

      *----------------------------------------------------
      * Close opened file
      *----------------------------------------------------
       305-close-files.
           close  inventory-file-out.

      *----------------------------------------------------
      * Output to the screen the amount of entered rows
      * and amount of rows has been written to data file
      *----------------------------------------------------
       306-display-total-records.
           display "Entered: " line 4 column 5 with blank screen
           display entered-counter line 4 column 17.
           display "Written:" line 5 column 5.
           display written-counter line 5 column 17.

      *----------------------------------------------------
      * Displays the final message
      *----------------------------------------------------
       307-end-message.
           display "Program is completed. Thank you." line 7 column 5
           display "" line 8 column 4.
           goback.

       end program project1.
