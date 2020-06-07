<?php
/*######################################################################################################################
                                             вывод одной статьи
######################################################################################################################*/
global $server, $main_page, $SERVER, $IMG_DIR;

$this->output_page = '<div id="first_column">';
//отображение вывода первого столбика
$this->output_page .= '<div class="article_container">';
//картинка
$this->output_page .= '<img class="floated_image" src="' . $SERVER . $IMG_DIR . '/headers/' . $this->id[0] . '-main-image.jpg"/></a>';
$this->output_page .= '<div class="empty_space"></div>';
//заголовок
$this->output_page .= '<h2 class="title_h2">' . $this->name[0] . '</h2>';

//кнопки
$this->output_page .= '<div class="social_buttons">';
$this->output_page .= '<div class="fb-like" data-href="'.$CURPAGE.'" data-layout="button_count" data-action="like" data-show-faces="true" data-share="false" style="display: inline-block;"></div>';
$this->output_page .= '<div id="vk_like" style="display: inline-block; margin-left: 1em;"></div>';
$this->output_page .= '<script type="text/javascript">';
$this->output_page .= 'VK.Widgets.Like("vk_like", {type: "button", height: 20});';
$this->output_page .= '</script>';
//$this->output_page .= '<div class="pluso social" data-background="transparent" ';
//$this->output_page .= 'data-options="medium,square,line,horizontal,counter,theme=06"';
//$this->output_page .= 'data-services="facebook,vkontakte,twitter,odnoklassniki,google,print"></div>';
$this->output_page .= '</div>';
//кнопки

//верхний блок рекламы
$_adsense .= '<div class="ads" style="margin-top: 0px; margin-right: 0px; margin-bottom: 0px; margin-left: -8px !important; padding: 0px;">';
$_adsense .= '<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>';
$_adsense .= '<!-- mega-otpusk top -->';
$_adsense .= '<ins class="adsbygoogle"';
$_adsense .= 'style="display:block"';
$_adsense .= 'data-ad-client="ca-pub-6955574409419903"';
$_adsense .= 'data-ad-slot="8150310286"';
$_adsense .= 'data-ad-format="auto"></ins>';
$_adsense .= '<script>';
$_adsense .= '(adsbygoogle = window.adsbygoogle || []).push({});';
$_adsense .= '</script>';
$_adsense .= '</div>';
//верхний блок рекламы

//основной текст - реклама после первого абзаца
$_full_article = $this->content[0];
$find_me = '</p>';
$pos = strpos($_full_article, $find_me);
$_first_block = substr($_full_article, 0, ($pos + 4));
$_last_block = substr($_full_article, ($pos + 4));
$_content = $_first_block . $_adsense . $_last_block;
//основной текст - реклама сверху
//$_content = $this->content[0];
//вывод основного текста
$this->output_page .= '<article class="main_article">' . $_content . '</article>';
$this->output_page .= '<div class="horiz_line"></div>';
//оконцовка
/*$this->output_page .= '<p id="last_note">';
$this->output_page .= '<span id="last_note_text">';
$this->output_page .= 'Выгодная акция, но не для вас?';
$this->output_page .= 'Сообщите о ней своим друзьям!';
$this->output_page .= '</span>';
//кнопки
$this->output_page .= '<div class="pluso" data-background="transparent" ';
$this->output_page .= 'data-options="big,square,line,horizontal,counter,theme=08"';
$this->output_page .= 'data-services="facebook,vkontakte,twitter,odnoklassniki,google,print"></div>';
//кнопки
//$this->output_page .= '<div class="empty_space"></div>';
*/
/*кнопка фейсбука
$this->output_page .= '<div class="fb-like social"';
$this->output_page .= 'data-href="'.$CURPAGE.'"';
$this->output_page .= 'data-layout="button_count"';
$this->output_page .= 'data-action="like"';
$this->output_page .= 'data-show-faces="true"';
$this->output_page .= 'data-share="false">';
$this->output_page .= '</div>';
//кнопка фейсбука
//кнопка вконтакте
$this->output_page .= '<span id="vk_share_button"></span>';
$this->output_page .= '<script>document.querySelector(\'#vk_share_button\').innerHTML = ';
$this->output_page .= 'VK.Share.button(false,';
$this->output_page .= "{";
$this->output_page .= "type: 'custom', ";
$this->output_page .= "text: '<img src=\"" . $SERVER . $IMG_DIR . "/vk.png\" />'";
$this->output_page .= "});";
$this->output_page .= '</script>';
//кнопка вконтакте

//$this->output_page .= '</span>';
$this->output_page .= '</p>';
//закрытие контейнера
$this->output_page .= '<div class="horiz_line"></div>';
*/
$this->output_page .= '</div>';

//нижний блок рекламы
$this->output_page .= '<div class="ads">';
$this->output_page .= '<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>';
$this->output_page .= '<!-- mega-otpusk bottom -->';
$this->output_page .= '<ins class="adsbygoogle"';
$this->output_page .= 'style="display:block"';
$this->output_page .= 'data-ad-client="ca-pub-6955574409419903"';
$this->output_page .= 'data-ad-slot="9627043480"';
$this->output_page .= 'data-ad-format="auto"></ins>';
$this->output_page .= '<script>';
$this->output_page .= '(adsbygoogle = window.adsbygoogle || []).push({});';
$this->output_page .= '</script>';
$this->output_page .= '</div>';
//нижний блок рекламы

//завершение вывода первого столбика
//$this->output_page.= '<div class="empty_space"></div>';
//вывод комментариев Вконтакте
//заголовок для комментов
$this->output_page .= '<p id="vk_comments_note">Комментарии:</p>';
//сами комменты
$this->output_page .= '<!-- Put this div tag to the place, where the Comments block will be -->';
$this->output_page .= '<div id="vk_comments"></div>';
$this->output_page .= '<script type="text/javascript">';
$this->output_page .= 'VK.Widgets.Comments("vk_comments", {limit: 10, width: "710", attach: "*"});';
$this->output_page .= '</script>';
//окончание первого столбика
$this->output_page .= '</div>';
//отображение вертикального разделителя
$this->output_page .= '<div id="vert_line"></div>';
//построение и вывод правого столбца и получение информации
//get_right_column_data(с какой записи выводить, количество записей к выводу);
//$_right_column_data = $this->get_right_column_data(0, 5);
//$_right_column_output = $this->build_right_column_output($_right_column_data);
//формирование вывода
$this->output_page .= '<div id="second_column">';

//aviasales
$this->output_page .= '<div class="right_block">';
$this->output_page .= '<h3 class="title_h3">Поиск билетов:</h3>';
$this->output_page .= '<script charset="utf-8" src="//www.travelpayouts.com/widgets/34bd7fe00eb63623259d9ff87f92196f.js?v=638" async></script>';
$this->output_page .= '</div>';
$this->output_page .= '<div class="empty_space"></div>';
//aviasales

$this->output_page .= '<div class="right_block">';
if ($this->current_category_type == "main") {
    $this->output_page .= '<h3 class="title_h3">Похожие предложения:</h3>';
    $_right_column_data = $this->get_right_column_data(0, 5, $this->current_category_code, 86400);
    $_right_column_output = $this->build_right_column_output($_right_column_data);
} else {
    $this->output_page .= '<h3 class="title_h3">Последние предложения:</h3>';
    $_right_column_data = $this->get_right_column_data(0, 5, "", 86400);
    $_right_column_output = $this->build_right_column_output($_right_column_data);
}
$this->output_page .= $_right_column_output;
$this->output_page .= '</div>';
$this->output_page .= '<div class="empty_space"></div>';

//боковой блок рекламы
$this->output_page .= '<div class="ads_v">';
$this->output_page .= '<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>';
$this->output_page .= '<!-- mega-otpusk side -->';
$this->output_page .= '<ins class="adsbygoogle"';
$this->output_page .= 'style="display:block"';
$this->output_page .= 'data-ad-client="ca-pub-6955574409419903"';
$this->output_page .= 'data-ad-slot="2103776683"';
$this->output_page .= 'data-ad-format="auto"></ins>';
$this->output_page .= '<script>';
$this->output_page .= '(adsbygoogle = window.adsbygoogle || []).push({});';
$this->output_page .= '</script>';
$this->output_page .= '</div>';
//боковой блок рекламы

//виджет Вконтакте
$this->output_page .= '<div class="empty_space"></div>';
$this->output_page .= '<div class="right_block">';
$this->output_page .= '<h3 class="title_h3">Мы Вконтакте:</h3>';
$this->output_page .= '<!-- VK Widget -->';
$this->output_page .= '<div id="vk_groups"></div>';
$this->output_page .= '<script type="text/javascript">';
$this->output_page .= 'VK.Widgets.Group("vk_groups", {mode: 0, width: "284", height: "320", ';
$this->output_page .= 'color1:\'FFFFFF\', color2: \'2B587A\', color3: \'5B7FA6\'}, 90080760);';
$this->output_page .= '</script>';
$this->output_page .= '</div>';
//виджет Вконтакте
//завершающий тэг
$this->output_page .= '</div>';
?>