<?php
global $SERVER;

//is this is an admin page?
$cycle_iterator = min($this->articles_per_page, count($this->id));
if ($this->admin) $cycle_iterator = min(100, count($this->id));

//building HTML
$this->output_page = '<div id="first_column_info">';
$this->output_page .= '<div class="empty_space"></div>';
//верхний блок рекламы
$this->output_page .= '<div class="ads_900">';
$this->output_page .= '<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>';
$this->output_page .= '<!-- mega-otpusk top -->';
$this->output_page .= '<ins class="adsbygoogle"';
$this->output_page .= 'style="display:inline-block;width:970px;height:90px"';
$this->output_page .= 'data-ad-client="ca-pub-6955574409419903"';
$this->output_page .= 'data-ad-slot="4654800284"';
$this->output_page .= 'data-ad-format="auto"></ins>';
$this->output_page .= '<script>';
$this->output_page .= '(adsbygoogle = window.adsbygoogle || []).push({});';
$this->output_page .= '</script>';
$this->output_page .= '</div>';

# отображение записей на главной странице
for ($i = 0; $i < $cycle_iterator; $i++) {
    #place ads block after the second  article if total of them are 4
    if ($i == 2) {
        $this->output_page .= '<div class="empty_space"></div>';
        //нижний блок рекламы
        $this->output_page .= '<div class="ads_900">';
        $this->output_page .= '<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>';
        $this->output_page .= '<!-- mega-otpusk bottom -->';
        $this->output_page .= '<ins class="adsbygoogle"';
        $this->output_page .= 'style="display:inline-block;width:970px;height:90px"';
        $this->output_page .= 'data-ad-client="ca-pub-6955574409419903"';
        $this->output_page .= 'data-ad-slot="4654800284"';
        $this->output_page .= 'data-ad-format="auto"></ins>';
        $this->output_page .= '<script>';
        $this->output_page .= '(adsbygoogle = window.adsbygoogle || []).push({});';
        $this->output_page .= '</script>';
        $this->output_page .= '</div>';
    }

    # отображение заголовка и формирование контейнеров
    $this->output_page .= '<div class="article_container_info">';
    $this->output_page .= '<div class="admin_data">';
    $this->output_page .= '<h2 class="title_h2">';
    $this->output_page .= "<a href=\"" . $SERVER . "/" . $this->category_link[$i] . "/" . $this->link[$i] . ".html\" class=\"title_h2_link\">";
    $this->output_page .= $this->name[$i];
    $this->output_page .= "</a>";
    $this->output_page .= "</h2>";

    # включение режима редактирования и вывод администраторского блока
    /*if ($this->admin) {
        if ($_GET['user_editor'] == $this->article_author_id_array[$i]) {
            $this->output_page .= '<span class="symbols">' . $this->symbols_array[$i] . '</span>&nbsp;&nbsp;';
            $this->output_page .= '<span class="price">' . $this->readers[$i] . '</span>&nbsp;&nbsp;';
            $this->output_page .= '<span class="edit_articles" onclick="edit_article(' . $this->id[$i] . ');">Edit</span>';
            $this->output_page .= '<input type="hidden" id="author_id" value="' . $_GET['user_editor'] . '"/>';
        }
    }*/
    $this->output_page .= '</div>';

    //ограничение вывода в тексте ссылок
    $_pattern = "/<.+?>/";
    $_replacement = " ";
    $_curr_article = preg_replace($_pattern, $_replacement, $this->content[$i]);
    //ограничить вывод на начальную страницу всего 300 симоволов, но целыми словами
    //для этого после 300 символов ищем первый пробел - это и будет конец слова
    $_cut_position = strpos($_curr_article, " ", $this->signs_in_info);
    $_curr_article = substr($_curr_article, 0, $_cut_position);
    //эта система считает количество слов
    //$_whole_article = explode(' ', $this->content[$i]);
    //$_cut_article = array_slice($_whole_article, 0, $this->words_in_info);
    //$_curr_article = implode(" ", $_cut_article);

    //посмтроение контейнера для гибкой ширины колонки
    //$this->output_page .= "<div class='article_picture_container'>";

    //вывод главной картинки
    $this->output_page .= "<a href=\"".$SERVER."/".$this->category_link[$i]."/".$this->link[$i].".html\" class='link_image_150'>";
    $this->output_page .= "<img class=\"image_150\" src=\"site_pictures/headers/" . $this->id[$i] . "-info-image.jpg\"/>";
    $this->output_page .= "</a>";

    # вывод самой статьи в сокращенном варианте
    $this->output_page .= "<article class='main_article_info'><span class='article_package'>";
    $this->output_page .= "$_curr_article...</span></article>";

    //завершение контейнера для картинки и статьи
    //$this->output_page .= "</div>";

    # завершение вывода страницы и закрытие контейнеров
    if (mb_strlen($this->content[$i]) > mb_strlen($_curr_article)) {
        //вывод количества прочтений данной статьи
        //$this->output_page .= '<span class="symbols">' . $this->readers[$i] . '</span>';

        $this->output_page .= '<div class="read_more">';
        $this->output_page .= "<a href=\"".$SERVER."/".$this->category_link[$i]."/".$this->link[$i].".html\" class='read_more'>";
        $this->output_page .= "&nbsp;Читать полностью&nbsp;";
        $this->output_page .= "</a>";
        $this->output_page .= "</div>";
    }
    $this->output_page .= '<div class="empty_space"></div>';
    $this->output_page .= "<div class=\"horiz_line\"></div>";
    $this->output_page .= "</div>";
}

//вывод кнопок для листания страниц
$_pages_list = $this->build_pages_block();
$this->output_page .= $_pages_list;

//завершение вывода первого столбика
$this->output_page .= '</div>';

//отображение вертикального разделителя
//$this->output_page.= '<div id="vert_line"></div>';

//построение и вывод правого столбца и получение информации
/*$this->output_page.= '<div id="second_column">';
//проверка на возможность добавления записей
if ($this->admin) $this->output_page.= '<div id="add_article" class="category_0" onclick="add_article();">Добавить запись</div>';
//добавление категорий
//$_categories = $this->build_side_categories_block();
//$this->output_page.= $_categories;
//добавление последних предложений
$this->output_page.= '<div class="empty_space"></div>';
$this->output_page.= '<div class="right_block">';
$this->output_page.= '<h3 class="title_h3">Последние предложения:</h3>';
//построение блока "последние записи"
//get_right_column_data(с какой записи выводить, количество записей к выводу, категория для похожести);
//на первой странице показываются следующие записи
//на всех остальных - первые пять записей
if ($this->current_page == 1) $_start_record = $this->page_limiter;
else $_start_record = 0;
//$_right_column_data = $this->get_right_column_data($_start_record, 5, "", 86400);
//$_right_column_output = $this->build_right_column_output($_right_column_data);
//$this->output_page.= $_right_column_output;
$this->output_page.= '</div>';
$this->output_page.= '<div class="empty_space"></div>';
//боковой блок рекламы
$this->output_page.= '<div class="ads_v">';
$this->output_page.= '<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>';
$this->output_page.= '<!-- mega-otpusk side -->';
$this->output_page.= '<ins class="adsbygoogle"';
$this->output_page.= 'style="display:block"';
$this->output_page.= 'data-ad-client="ca-pub-6955574409419903"';
$this->output_page.= 'data-ad-slot="2103776683"';
$this->output_page.= 'data-ad-format="auto"></ins>';
$this->output_page.= '<script>';
$this->output_page.= '(adsbygoogle = window.adsbygoogle || []).push({});';
$this->output_page.= '</script>';
$this->output_page.= '</div>';
//виджет Вконтакте
$this->output_page.= '<!-- VK Widget -->';
$this->output_page.= '<div id="vk_groups"></div>';
$this->output_page.= '<script type="text/javascript">';
$this->output_page.= 'VK.Widgets.Group("vk_groups", {mode: 0, width: "300", height: "320", ';
$this->output_page.= 'color1:\'FFFFFF\', color2: \'2B587A\', color3: \'5B7FA6\'}, 90080760);';
$this->output_page.= '</script>';
//завершающий тэг
$this->output_page.= '</div>';
*/
?>