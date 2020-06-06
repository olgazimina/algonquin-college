<?php
namespace mysql {
  class Mysql
  {
    public $num_rows = 0;
    private $db_host = "localhost";
    private $db_user = "john";
    private $db_pass = "john";
    private $db_name = "travel";
    private $port = "3306";
    private $socket = "/tmp/mysql.sock";
    private $myconn;
    private $con = false;
    private $result = array();

    // Function to make connection to database

    public function connect() {
      if (!$this->con) {
        $this->myconn = mysqli_connect($this->db_host, $this->db_user, $this->db_pass, $this->db_name, $this->port, $this->socket);
        mysqli_set_charset($this->myconn, 'utf8');
        $codepage = mysqli_query($this->myconn, "SET NAMES UTF-8");
        if ($this->myconn) {
          $this->con = true;

          return true;  // Connection has been made return TRUE
        } else {
          echo "error" . mysqli_error();
          array_push($this->result, mysqli_error());

          return false;  // Problem selecting database return FALSE
        }
      } else {
        return true; // Connection has already been made return TRUE
      }
    }

    // Function to disconnect from the database
    public function disconnect() {
      // If there is a connection to the database
      if ($this->con) {
        // We have found a connection, try to close it
        if (mysqli_close($this->myconn)) {
          // We have successfully closed the connection, set the connection variable to false
          $this->con = false;

          // Return true tjat we have closed the connection
          return true;
        } else {
          // We could not close the connection, return false
          return false;
        }
      }
    }

    public function sql($sql) {
      $query = mysqli_query($this->myconn, $sql);
      if(is_bool($query)) return false;
      if ($query) {
        // If the query returns >= 1 assign the number of rows to numResults
        $this->numResults = mysqli_num_rows($query);
        $this->num_rows = $this->numResults;
        // Loop through the query results by the number of rows returned
        for ($i = 0; $i < $this->numResults; $i++) {
          $r = mysqli_fetch_array($query);
          $key = array_keys($r);
          for ($x = 0; $x < count($key); $x++) {
            // Sanitizes keys so only alphavalues are allowed
            if (!is_int($key[$x])) {
              if (mysqli_num_rows($query) > 0) {
                $this->result[$i][$key[$x]] = $r[$key[$x]];
              } else {
                $this->result[$key[$x]] = $r[$key[$x]];
              }
            }
          }
        }

        return true; // Query was successful
      } else {
        array_push($this->result, mysqli_error());

        return false; // No rows where returned
      }
    }

    // Public function to return the data to the user
    public function getResult() {
      $val = $this->result;
      $this->result = array();

      return $val;
    }
  }
}
