<?php
require_once('abstractDAO.php');
require_once('./model/User.php');

class UserDAO extends abstractDAO
{

    function __construct()
    {
        try {
            parent::__construct();
        } catch (mysqli_sql_exception $e) {
            throw $e;
        }
    }

    public function getActiveUsers()
    {
        $result = $this->mysqli->query('SELECT * FROM mailingList where deletedCustomerNames=\'\'');
        $users = Array();

        if ($result->num_rows >= 1) {
            while ($row = $result->fetch_assoc()) {
                $user = new User($row['customerName'], $row['phoneNumber'], $row['emailAddress'], $row['deletedCustomerNames'], $row['referrer']);
                $users[] = $user;
            }
            $result->free();
            return $users;
        }
        $result->free();
        return false;
    }

    public function getUnsubscribedUsers()
    {
        $result = $this->mysqli->query('SELECT * FROM mailingList where deletedCustomerNames!=\'\'');
        $users = Array();

        if ($result->num_rows >= 1) {
            while ($row = $result->fetch_assoc()) {
                $user = new User($row['customerName'], $row['phoneNumber'], $row['emailAddress'], $row['deletedCustomerNames'], $row['referrer']);
                $users[] = $user;
            }
            $result->free();
            return $users;
        }
        $result->free();
        return false;
    }

    public function getUser($id)
    {
        $query = 'SELECT * FROM mailingList WHERE _id = ?';
        $stmt = $this->mysqli->prepare($query);
        $stmt->bind_param('i', $id);
        $stmt->execute();
        $result = $stmt->get_result();
        if ($result->num_rows == 1) {
            $row = $result->fetch_assoc();
            $user = new User($row['customerName'], $row['phoneNumber'], $row['emailAddress'], $row['deletedCustomerNames'], $row['referrer']);
            $result->free();
            return $user;
        }
        $result->free();
        return false;
    }

    public function isSubscribed($emailAddress)
    {
        $query = 'SELECT _id FROM mailingList WHERE emailAddress = ?';
        $stmt = $this->mysqli->prepare($query);
        $stmt->bind_param('s', $emailAddress);
        $stmt->execute();
        $result = $stmt->get_result();
        if ($result->num_rows == 1) {
            $result->free();
            return true;
        }
        $result->free();
        return false;
    }

    public function addUser($user)
    {
        if (!is_numeric($user->getId())) {
            return 'ID must be a number.';
        }
        if (!$this->mysqli->connect_errno) {
            $query = 'INSERT INTO mailingList VALUES (?,?,?,?,?,?)';
            $stmt = $this->mysqli->prepare($query);
            $userId = $user->getId();
            $userName = $user->getUserName();
            $userPhoneNumber = $user->getPhoneNumber();
            $userEmailAddress = $user->getEmailAddress();
            $userDeleteFlag = $user->getDeletedFlag();
            $userReferrer = $user->getReferrer();
            $stmt->bind_param('isssss',
                $userId,
                $userName,
                $userPhoneNumber,
                $userEmailAddress,
                $userDeleteFlag,
                $userReferrer);
            //Execute the statement
            $stmt->execute();
            //If there are errors, they will be in the error property of the
            //mysqli_stmt object.
            if ($stmt->error) {
                return $stmt->error;
            } else {
                return 'User ' . $user->getUserName() . ' added successfully!';
            }
        } else {
            return 'Could not connect to Database.';
        }
    }

    public function deleteSubscription($user)
    {
        if (!$this->mysqli->connect_errno) {
            $query = 'UPDATE mailingList SET deletedCustomerNames=? WHERE _id = ?';
            $stmt = $this->mysqli->prepare($query);
            $fullUser = $user->getUserName() . ',' . $user->getPhoneNumber() . ',' . $user->getEmailAddress();
            $id = $user->getId();
            $stmt->bind_param('si', $fullUser, $id);
            $stmt->execute();
            if ($stmt->error) {
                return $stmt->error;
            } else {
                return 'User ' . $user->getUserName() . ' unsubscribed successfully!';
            }
        } else {
            return false;
        }
    }

    public function addSubscription($user)
    {
        if (!$this->mysqli->connect_errno) {
            $query = 'UPDATE mailingList SET deletedCustomerNames=\'\' WHERE _id = ?';
            $stmt = $this->mysqli->prepare($query);
            $id = $user->getId();
            $stmt->bind_param('i', $id);
            $stmt->execute();
            if ($stmt->error) {
                return $stmt->error;
            } else {
                return 'User ' . $user->getUserName() . ' subscribed again successfully!';
            }
        } else {
            return false;
        }
    }

}

?>
