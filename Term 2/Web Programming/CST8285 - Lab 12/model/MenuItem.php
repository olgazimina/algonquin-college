<?php


class MenuItem
{
    private $itemName;
    private $description;
    private $price;

    /**
     * MenuItem constructor.
     * @param $itemName
     * @param $description
     * @param $price
     */
    public function __construct($itemName, $description, $price)
    {
        $this->itemName = $itemName;
        $this->description = $description;
        $this->price = $price;
    }

    /**
     * @return mixed
     */
    public function getItemName()
    {
        return $this->itemName;
    }

    /**
     * @param mixed $itemName
     */
    public function setItemName($itemName): void
    {
        $this->itemName = $itemName;
    }

    /**
     * @return mixed
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param mixed $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @return mixed
     */
    public function getPrice()
    {
        return $this->price;
    }

    /**
     * @param mixed $price
     */
    public function setPrice($price)
    {
        $this->price = $price;
    }


}

?>
