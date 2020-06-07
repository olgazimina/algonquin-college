<?php
//requesting image processing classes
include_once("../paths.php");
include_once($BASE_DIR.$SS_DIR."/class_files.php");

//creating a new class with Images
$new_image = new Upload;

//in case the uploaded image is not main
//that's not necessary to change it - just upload and save
$new_image->path = $BASE_DIR.$IMG_DIR."/article_images/";
$new_image->upload_file();

//if this image is intended to ba the main one
//so it's necessary to reformat it
if ($new_image->check_for_info_image()) {
    $new_image->set_dimensions(150, 150);
    $new_image->path = $BASE_DIR . $IMG_DIR . "/headers/";
    $new_image->reformat_image("info");
}
if ($new_image->check_for_main_image()) {
    $new_image->set_dimensions(700, 250);
    $new_image->path = $BASE_DIR . $IMG_DIR . "/headers/";
    $new_image->reformat_image("main");
} else {
    $new_image->reformat_image("general");
}
//deleting the original image
$new_image->cleaner();

//echoing the name of uploaded file and closing the connection
echo $new_image->name;
unset($new_image);

?>