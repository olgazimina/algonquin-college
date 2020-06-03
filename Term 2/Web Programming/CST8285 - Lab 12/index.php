<!DOCTYPE html>
<html>
<?php
include("model/MenuItem.php");
$i = 0;
$menuItems = array();

while ($i < 2) {
    $name = "";
    $description = "";
    $price = "";
    $stars = "*";

    for ($col = 1; $col <= $i; $col++) {
        $stars .= '*';
    }

    if ($i % 2 == 0) {
        $name = "WP Kebabs" . " " . $stars . ($i + 1);
        $description = "Tender cuts of beef and chicken, served with your choice of side";
        $price = "$14";
    } else {
        $name = "The WP Burger" . " " . $stars . ($i + 1);
        $description = "Freshly made all-beef patty served up with homefries";
        $price = "$17";
    }

    $menuItems[] = new MenuItem($name, $description, $price);
    $i++;
}
include("css/header.php") ?>
<div id="content" class="clearfix">
    <aside>
        <h2><?php echo date("l"); ?>'s Specials</h2>
        <hr>
        <?php
        foreach ($menuItems as $item) {
            if (strpos($item->getItemName(), 'WP Kebabs') !== false) {
                echo '<img src="images/kebobs.jpg" alt="WP Kebabs" title="' . date("l") . '\'s Special - Kebabs">';
            } else {
                echo '<img src="images/burger_small.jpg" alt="WP Burger" title="' . date("l") . '\'s Special - Burger">';
            }
            echo '<h3>' . $item->getItemName() . '</h3>';
            echo '<p>' . $item->getDescription() . ' - ' . $item->getPrice() . '</p>';
            echo '<hr>';
        }
        ?>
    </aside>
    <div class="main">
        <h1>Welcome</h1>
        <img src="images/dining_room.jpg" alt="Dining Room" title="The WP Eatery Dining Room" class="content_pic">
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
            ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
            nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit
            anim id est laborum</p>
        <h2>Book your Christmas Party!</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
            ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
            nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit
            anim id est laborum</p>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
            ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
            nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit
            anim id est laborum</p>
    </div><!-- End Main -->
</div><!-- End Content -->
<?php include("css/footer.php") ?>
</body>
</html>
