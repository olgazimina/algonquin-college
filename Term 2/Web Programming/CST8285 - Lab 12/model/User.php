<?php
	class User{
		private $id;
		private $userName;
		private $phoneNumber;
		private $emailAddress;
		private $deletedFlag;
		private $referrer;

        /**
         * User constructor.
         * @param $userName
         * @param $phoneNumber
         * @param $emailAddress
         * @param $deletedFlag
         * @param $referrer
         */
        public function __construct($userName, $phoneNumber, $emailAddress, $deletedFlag, $referrer)
        {
            $this->id = intval(substr(sprintf("%u", crc32($emailAddress)), 0, 8));
            $this->userName = $userName;
            $this->phoneNumber = $phoneNumber;
            $this->emailAddress = $emailAddress;
            $this->deletedFlag = $deletedFlag;
            $this->referrer = $referrer;
        }

        /**
         * @return mixed
         */
        public function getId()
        {
            return $this->id;
        }

        /**
         * @param mixed $id
         */
        public function setId($id)
        {
            $this->id = $id;
        }

        /**
         * @return mixed
         */
        public function getUserName()
        {
            return $this->userName;
        }

        /**
         * @param mixed $userName
         */
        public function setUserName($userName)
        {
            $this->userName = $userName;
        }

        /**
         * @return mixed
         */
        public function getPhoneNumber()
        {
            return $this->phoneNumber;
        }

        /**
         * @param mixed $phoneNumber
         */
        public function setPhoneNumber($phoneNumber)
        {
            $this->phoneNumber = $phoneNumber;
        }

        /**
         * @return mixed
         */
        public function getEmailAddress()
        {
            return $this->emailAddress;
        }

        /**
         * @param mixed $emailAddress
         */
        public function setEmailAddress($emailAddress)
        {
            $this->emailAddress = $emailAddress;
        }

        /**
         * @return mixed
         */
        public function getDeletedFlag()
        {
            return $this->deletedFlag;
        }

        /**
         * @param mixed $deletedFlag
         */
        public function setDeletedFlag($deletedFlag)
        {
            $this->deletedFlag = $deletedFlag;
        }

        /**
         * @return mixed
         */
        public function getReferrer()
        {
            return $this->referrer;
        }

        /**
         * @param mixed $referrer
         */
        public function setReferrer($referrer)
        {
            $this->referrer = $referrer;
        }
		
	}
?>
