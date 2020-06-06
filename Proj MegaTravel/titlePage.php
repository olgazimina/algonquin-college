<?php
error_reporting(0);//E_ALL & ~E_NOTICE & ~E_WARNING);//(E_ALL & ~E_NOTICE);
ini_set("display_errors", 0);

date_default_timezone_set("Europe/Moscow");
#################################################################################
include_once("paths.php");
include_once($BASE_DIR . $SS_DIR . "/class_mysql.php");
include_once($BASE_DIR . $SS_DIR . "/class_article.php");

//создание объктов
$Article = new Article;

//проверка прав администратора (возможность добавления редактирования статей)
$Article->check_for_admin();
if ($Article->admin) setcookie("counter", "1", time() + 604800, "/", "mega-otpusk.ru");

//вычисляется категория и статья для поиска в б/д
$category_array = $Article->get_chosen_category_code();
$category_code = $category_array['category_id'];
$category_keywords = $category_array['category_keywords'];
$category_name = $category_array['category_name'];
$category_link = $category_array['category_link'];
$category_image = "http://discounts-online.info/travel/site_pictures/header_logo.jpg";
$category_short_descr = "Хотите путешествовать и не знаете как сделать это дешево и качественно? Мы научим вас!";

$article_array = $Article->get_chosen_article_code();
$article_code = $article_array['article_id'];
$article_keywords = $article_array['article_keywords'];
$article_name = $article_array['article_name'];
$article_image = $article_array['article_picture'];
$article_short_descr = $article_array['article_short_descr'];

#проверка - категория или статья
if (isset($article_keywords) && !empty($article_keywords)) {
    #статья
    $keywords = $article_keywords;
    $title = $article_name;
    $show_square_ad = true;
    $show_image = "http://www.mega-otpusk.ru/site_pictures/headers/" . $article_image;
    $short_description = $article_short_descr;
} else {
    #категория
    $keywords = $category_keywords;
    $title = "МегаОтпуск :: " . $category_name;
    $show_square_ad = false;
    $show_image = $category_image;
    $short_description = $category_short_descr;
}
?>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta content="text/html" charset="utf-8">
    <?php if ($keywords != "") echo '<meta name="keywords" content="' . $keywords . '">'; ?>

    <meta name="description" content="<?php echo $short_description; ?>"/>
    <meta property="og:url" content="<?php echo $CURPAGE ?>"/>
    <meta property="og:type" content="website"/>
    <meta property="og:title" content="<?php echo $title; ?>"/>
    <meta property="og:description" content="<?php echo $short_description; ?>"/>
    <meta property="og:image" content="<?php echo $show_image; ?>"/>
    <meta name="author" content="ZiLo Ltd.">
    <meta name="robots" content="index,follow">

    <title><?php echo $title; ?></title>
    <link rel="stylesheet" type="text/css" href="css_styles/title_layout.css">
    <link rel="stylesheet" type="text/css" href="css_styles/site_colors.css">
    <link rel="stylesheet" type="text/css" href="css_styles/site_images.css">
    <link rel="stylesheet" type="text/css" href="css_styles/site_fonts.css">


    <?php
    if ($Article->admin) {
        echo '
        	<script type="text/javascript" src="js_scripts/class_article.js"></script>
        	<link rel="stylesheet" type="text/css" href="css_styles/class_article.css">
        	<script type="text/javascript" src="js_scripts/ajax.js"></script>';
    }
    ?>
    <script src="js_scripts/counters.js" type="text/javascript"></script>
    <script src="http://vk.com/js/api/openapi.js" type="text/javascript"></script>
    <script src="http://vk.com/js/api/share.js?93" type="text/javascript"></script>
    <script>VK.init({apiId: 5148526, onlyWidgets: true});</script>
</head>

<body onload="start_counters();">
<header>
    <div id="header_container">
        <div id="header_name_container">
            <h1 id="site_name">МЕГА ОТПУСК</h1>
            <h1 id="site_call">путешествуй дёшево</h1>
        </div>
    </div>
</header>
<div id="body_container">
    <nav id="top_line">
        <ul><?php echo $Article->build_menu(); ?></ul>
        <!-- Put this script tag to the place, where the Share button will be -->
        <!--<a href="/">Поиск по сайту</a>-->
    </nav>
    <div id="columns">

        <?php
        echo $Article->show_page($category_code, $article_code);
        ?>

    </div>
</div>
<div class="empty_space"></div>
<div class="empty_space"></div>
<footer>
    <div id="copyrights">2015 (c) ZiLo Ltd.</div>
    <div id="footer_container">
        <div id="footer_data">Копирование либо иное использование информации с сайта без указания полной ссылки на сайт запрещено.</div>
        <div id="footer_socials_container"></div>
    </div>
</footer>

<script>
    <?php
    if (isset($_COOKIE['auth_admin']) and $_COOKIE['auth_admin'] == 1 or
        $_SERVER["HTTP_HOST"] == "homemac.local" or
        $_SERVER["HTTP_HOST"] == "192.168.1.2")
    {
        echo "main = '" . $Article->build_selections("main") . "';";
        echo "dost = '" . $Article->build_selections("dost") . "';";
        echo "fact = '" . $Article->build_selections("fact") . "';";
        echo "info = '" . $Article->build_selections("info") . "';";
        echo "immi = '" . $Article->build_selections("immi") . "';";
        echo "blog = '" . $Article->build_selections("blog") . "';";
        echo 'code = \'<table id="tbl" border="0" cellpadding="0" cellspacing="5"><tr><td width="20%">Тип статьи</td><td colspan="2"><input id="i_news" type="radio" name="article_type" value="main" onclick="change_type(this.value)" checked="checked"/><label for="i_news">&nbsp;Новость&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="i_dost" type="radio" name="article_type" value="dost" onclick="change_type(this.value)"/><label for="i_dost">&nbsp;Дост.&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="i_fact" type="radio" name="article_type" value="fact" onclick="change_type(this.value)"/><label for="i_fact">&nbsp;Факты&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="i_info" type="radio" name="article_type" value="info" onclick="change_type(this.value)"/><label for="i_info">&nbsp;Инфо&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="i_immi" type="radio" name="article_type" value="immi" onclick="change_type(this.value)"/><label for="i_immi">&nbsp;Имми&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="i_blog" type="radio" name="article_type" value="blog" onclick="change_type(this.value)"/><label for="i_blog">&nbsp;Блог&nbsp;&nbsp;&nbsp;&nbsp;</label></td></tr><tr><td width="20%">Категория статьи</td><td id="selector_type" colspan="2">' . $Article->build_selections("main") . '</td></tr><tr><td colspan="1">Название статьи</td><td colspan="2"><input type="text" name="article_name" id="article_name" value=""></td></tr><tr><td>Картинки:</td><td><input id="file-id" type="file" onchange="upload_images();"/></td><td><div id="progress"></div></td></tr><tr><td>Загружено:</td><td colspan="2"><div id="uploaded_pictures"></div></td></tr><td>Текст статьи</td><td><input id="ss" type="button" value="SkyScanner Link" onclick="create_skyscanner_deeplink();"/></td><td><input id="nl" type="button" value="Normal Link" onclick="create_usual_deeplink();"/></td></tr><tr><td colspan="3"><textarea name="article_content" id="article_content"></textarea></td></tr><tr><td colspan="3">Ключевые слова (через запятую):</td></tr><tr><td colspan="3"><textarea name="article_keywords" id="article_keywords">Не пишите сюда ничего!</textarea></td></tr><tr><td colspan="3"><input type="button" id="my_send" value="Загрузить"/><input type="button" id="cancel" value="Сбросить"/></td></tr></table><input type="hidden" id="author_id" value="' . $Article->author . '"></div>\';';
        if (!empty($_COOKIE["counter"])) echo "cook = 1"; else "cook = 0";
    }
    ?>
</script>
<!-- /Yandex.Metrika counter NOSCRIPT-->
<noscript>
    <div><img src="//mc.yandex.ru/watch/33602264" style="position:absolute; left:-9999px;" alt=""/></div>
</noscript>
<!-- /Yandex.Metrika counter NOSCRIPT-->

<div id="fb-root"></div>
<script>(function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/ru_RU/sdk.js#xfbml=1&version=v2.5";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
</script>
</body>
</html>
