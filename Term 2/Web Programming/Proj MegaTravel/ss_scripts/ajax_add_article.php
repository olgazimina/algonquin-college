<?php
header("Content-type: application/x-www-form-urlencoded; charset=utf-8");
include_once ("class_article.php");

$new_article = new Article;

if(isset($_POST)) {
	if ($_POST["flag"] == "add") {
		$new_article->add_article();
	} elseif ($_POST["flag"] == "load") {
		$new_article->load_article();
	} elseif ($_POST["flag"] == "edit") {
		$new_article->edit_article();
	} elseif ($_POST["flag"] == "na") {
		$new_article->get_new_id();
	}
}
?>