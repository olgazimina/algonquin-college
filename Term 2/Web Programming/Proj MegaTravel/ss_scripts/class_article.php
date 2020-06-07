<?php
//requesting image processing classes
include_once("../paths.php");
include_once($BASE_DIR.$SS_DIR."/class_mysql.php");
include_once($BASE_DIR.$SS_DIR."/class_login.php");

class Article
{
    //database block
    private $db_name = "travel";
    private $db_article = "articles";
    private $db_category = "category";
    private $db_author = "authors";
    private $sql_string = null;

    //pages block
    private $total_arts = 1;        //всего статей
    private $total_pages = 1;       //всего страниц
    private $current_page = 1;      //текущая страница
    private $articles_per_page = 8; //количество статей на странице
    private $signs_in_main = 450;   //количество знаков для основной страницы
    private $signs_in_info = 1000;  //количество знаков для полезной информации
    private $signs_in_side = 180;   //количество слов в полезной информации
    private $page_limiter = 5;      //количество кнопок для листания
    private $total_block = 0;       //количество блоков для листания

    //Common Data
    public $id = array();
    public $link = array();
    public $name = array();
    public $current_category_type = null;
    public $current_category_link = null;
    public $current_category_code = null;
    public $readers = array();
    public $picture = array();
    public $content = array();
    public $category = array();
    public $comments = array();
    public $category_name = array();
    public $category_link = array();
    public $symbols_array = array();
    public $payment_array = array();
    public $current_category = array();
    public $category_description = null;
    public $article_author_id_array = array();

    //Admin and Author
    private $author_price = null;
    public $admin = null;
    public $auth_admin = null;
    public $author = null;

    //output HTML
    public $right_side_column = null;
    public $output_page = null;
    public $category_page = null;
#########################end of declaration################################

    /*######################################################################################################################
                                                 get_new_id for another article
    ######################################################################################################################*/
    public function get_new_id() {
        $_sql = "select max(`article_id`) as `amount` from `" . $this->db_name . "`.`" . $this->db_article . "` ";
        $_sql .= "where 1";

        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($_sql);
        $values = $mysql->getResult();
        $mysql->disconnect();

        echo intval($values[0]["amount"]) + 1;
    }

    /*######################################################################################################################
                                                 determine_type()
    ######################################################################################################################*/
    private function determine_types($_data) {
        $_sql = "select `category_type`, `category_link` from `$this->db_name`.`$this->db_category` ";
        $_sql.= "where `category_id` = '$_data';";

        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($_sql);
        $value = $mysql->getResult();
        $mysql->disconnect();

        $this->current_category_code = $_data;
        $this->current_category_type = $value[0]["category_type"];
        $this->current_category_link = $value[0]["category_link"];
    }

    /*######################################################################################################################
                                                 make_condition($conditional_array)
    ######################################################################################################################*/
    private function make_condition($conditional_array) {
        if (count($conditional_array) == 0) return;
        $sql_condition = " where ";
        $conditions_amount = count($conditional_array);

        for ($i = 0; $i < $conditions_amount; $i++) {
            $sql_condition .= $conditional_array[$i] . " or ";
        }
        $sql_condition = substr($sql_condition, 0, -4);
        return $sql_condition;
    }

    /*######################################################################################################################
                                                make_groupping($groupping_field)
    ######################################################################################################################*/
    private function make_groupping($groupping_field)
    {
        if (isset($groupping_field)) {
            $sql_group = " group by  `" . $groupping_field . "`";
            return $sql_group;
        }
    }

    /*######################################################################################################################
                                                  make_sorting($sorting_field, $sorting_order)
    ######################################################################################################################*/
    private function make_sorting($sorting_field, $sorting_order)
    {
        $sql_sort = " order by `" . $sorting_field . "` " . $sorting_order;
        return $sql_sort;
    }

    /*######################################################################################################################
                                                 get_category_name($category_code, $iterator)
    ######################################################################################################################*/
    private function get_category_name($category_code, $iterator)
    {
        $_sql = "select * from `" . $this->db_name . "`.`" . $this->db_category . "`";
        $_sql .= " where `category_id` = '" . $category_code . "'";

        /* mysql request */
        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($_sql);
        $values = $mysql->getResult();
        $mysql->disconnect();

        /* присваиваем категории*/
        $this->category_name[$iterator] = $values[0]["category_name"];
        $this->category_link[$iterator] = $values[0]["category_link"];
    }

    /*######################################################################################################################
                                                  get_category_description($_category)
    ######################################################################################################################*/
    private function get_category_description($_category)
    {
        $_sql = "select `category_description` from `" . $this->db_name . "`.`" . $this->db_category . "`";
        $_sql .= " where `category_id` = '" . $_category . "'";

        /* mysql request */
        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($_sql);
        $values = $mysql->getResult();
        $mysql->disconnect();

        /* присваиваем категории*/
        $this->category_description = $values[0]["category_description"];
    }

    /*######################################################################################################################
                                                get_author_data($_author_id)
    ######################################################################################################################*/
    private function get_author_data($_author_id)
    {
        $this->sql_string = "select `author_name`,`author_price` from `" . $this->db_name . "`.`" . $this->db_author;
        $this->sql_string .= "` where `author_id` = " . $_author_id . ";";

        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($this->sql_string);
        $values = $mysql->getResult();
        $mysql->disconnect();

        $this->author = $_author_id;
        $this->author_name = $values[0]['author_name'];
        $this->author_price = $values[0]['author_price'];
    }

    /*######################################################################################################################
                                                  make_array_data($_value)
    ######################################################################################################################*/
    private function make_array_data($_value)
    {
        $values_lenght = count($_value);

        for ($i = 0; $i < $values_lenght; $i++) {

            /* заполняем массивы с данными */
            $this->id[$i] = $_value[$i]["article_id"];
            $this->link[$i] = $_value[$i]["article_link"];
            $this->name[$i] = $_value[$i]["article_name"];
            $this->readers[$i] = $_value[$i]["article_reads"];
            $this->picture[$i] = $_value[$i]["article_picture"];
            $this->content[$i] = nl2br($_value[$i]["article_content"]);
            $this->current_category[$i] = $_value[$i]["article_category_0"];
            $this->comments[$i] = $_value[$i]["article_comments"];
            $this->created[$i] = date("d.m.Y, G:i", $_value[$i]["article_date"]);
            $this->symbols_array[$i] = $_value[$i]["article_symbols"];
            $this->payment_array[$i] = $_value[$i]["article_price"];
            $this->article_author_id_array[$i] = $_value[$i]["article_author_id"];

            //$current_category = $_value[$i]["article_category"];
            $this->get_category_name($this->current_category[$i], $i);
        }
    }

    /*######################################################################################################################
                                                 get_category_data($category)
    ######################################################################################################################*/
    private function get_category_data($category)
    {
        // запрашиваем категорию целиком
        if ($this->admin) $amount = 100;
        else $amount = $this->articles_per_page;
        //необходимо для условия LIMIT  в запросе SQL
        $_page_sql = ($this->current_page - 1) * $this->articles_per_page;

        //всего бывает несколько случаев запросов:
        //0000, 0001 - все категории сразу - на главной странице - критерия нет
        //2000, 3000 - основные подкатегории - критерий - category_type, причем если main - or, другие - and
        //2001, 3001 - конкретные категории - критерий - category_id

        //creating variables for two SQL requests
        $sql_0 = null;
        $sql_1 = null;
        $request = array(
            0 => '*',
            1 => 'count(`article_id`) as amount'
        );

        if ($category == "0000" or $category == "0001") {
            for ($iter = 0; $iter < count($request); $iter++) {
                ${"sql_" . $iter}  = "select $request[$iter] from `$this->db_name`.`$this->db_article`";
                ${"sql_" . $iter} .= $this->make_sorting("article_date", "desc");
            }
            //adding limit condition to sql_0 only
            $sql_0 .= " limit " . $_page_sql . "," . $amount . "";
        }
        //случай № 2 - подкатегория
        elseif (substr($category,1,3) == "000") {
            if ($this->current_category_type == "main")
                $additional_condition = null;
            else {
                if ($this->current_category_type == "type") $article_num = "1";
                elseif ($this->current_category_type == "tick") $article_num = "2";
                else $article_num = "0";
                $additional_condition = ") and `$this->db_name`.`$this->db_article`.`article_category_$article_num` in ";
                $additional_condition.= "(select `category_id` from `$this->db_name`.`$this->db_category` ";
                $additional_condition.= "where `$this->db_name`.`$this->db_category`.`category_type` = '$this->current_category_type')";
            }
            for ($iter = 0; $iter < count($request); $iter++) {
                ${"sql_" . $iter}  = "select $request[$iter] from `$this->db_name`.`$this->db_article` ";
                ${"sql_" . $iter} .= "where (`$this->db_name`.`$this->db_article`.`article_category_0` in ";
                ${"sql_" . $iter} .= "(select `category_id` from `$this->db_name`.`$this->db_category` ";
                ${"sql_" . $iter} .= "where `$this->db_name`.`$this->db_category`.`category_type` = '$this->current_category_type') or";
                ${"sql_" . $iter} .= " `$this->db_name`.`$this->db_article`.`article_category_1` in ";
                ${"sql_" . $iter} .= "(select `category_id` from `$this->db_name`.`$this->db_category` ";
                ${"sql_" . $iter} .= "where `$this->db_name`.`$this->db_category`.`category_type` = '$this->current_category_type') or";
                ${"sql_" . $iter} .= " `$this->db_name`.`$this->db_article`.`article_category_2` in ";
                ${"sql_" . $iter} .= "(select `category_id` from `$this->db_name`.`$this->db_category` ";
                ${"sql_" . $iter} .= "where `$this->db_name`.`$this->db_category`.`category_type` = '$this->current_category_type')";
                ${"sql_" . $iter} .= $additional_condition;
                ${"sql_" . $iter} .= $this->make_sorting("article_date", "desc");
            }
            //adding limit condition to sql_0 only
            $sql_0 .= " limit " . $_page_sql . "," . $amount . "";
        }
        //случай № 3 - конкретная категория
        else {

            //creating conditions statements
            $conditions = array();
            if ($category != "0000" && $category != "0001") {
                $conditions = array(
                    0 => "`article_category_0` = '" . $category . "'",
                    1 => "`article_category_1` = '" . $category . "'",
                    2 => "`article_category_2` = '" . $category . "'"
                );
            }

            for ($iter = 0; $iter < count($request); $iter++) {
                ${"sql_" . $iter}  = "select $request[$iter] from `$this->db_name`.`$this->db_article` ";
                ${"sql_" . $iter} .= $this->make_condition($conditions);
                ${"sql_" . $iter} .= $this->make_sorting("article_date", "desc");
            }
            //adding limit condition to sql_0 only
            $sql_0 .= " limit " . $_page_sql . "," . $amount . "";
        }

        //makes two mysql requests
        $mysql = new mysql\Mysql;
        $mysql->connect();
        //first one with limit to show 10...100...whatever
        $mysql->sql($sql_0);
        $val_one = $mysql->getResult();
        //making the second request
        $mysql->sql($sql_1);
        $val_two = $mysql->getResult();
        $mysql->disconnect();

        //запрашиваем описание категории
        $this->get_category_description($category);
        //формируем данные в объект для выдачи
        $this->make_array_data($val_one);
        //подсчитываем количество статей
        $this->total_arts = intval($val_two[0]["amount"]);
    }

    /*######################################################################################################################
                                                  get_article_data($article)
    ######################################################################################################################*/
    private function get_article_data($article)
    {

        /* запрашиваем статью целиком */
        $conditions = array();
        $conditions = array(0 => "`article_id` = '" . $article . "'");
        $this->sql_string = "select * from `" . $this->db_name . "`.`" . $this->db_article . "`";
        $this->sql_string .= $this->make_condition($conditions);

        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($this->sql_string);
        $values = $mysql->getResult();
        $mysql->disconnect();

        //выясняем автора данного опуса
        $this->get_author_data($values[0]['article_author_id']);

        //готовим данные в массив к выдаче
        $this->make_array_data($values);


        //увеличиваем счётчик прочтений статьи для всех, кроме админов
        if(empty($_COOKIE["counter"])) {
            $article_reads = $values[0]['article_reads'] + 1;
            $this->sql_string = "update `" . $this->db_name . "`.`" . $this->db_article . "` ";
            $this->sql_string .= "set article_reads = " . $article_reads . " where article_id = " . $values[0]['article_id'];

            $mysql = new mysql\Mysql;
            $mysql->connect();
            $mysql->sql($this->sql_string);
            $mysql->disconnect();
        }
    }

    /*######################################################################################################################
                                      получение данных для правого столбца в виде array
    ######################################################################################################################*/
    private function get_right_column_data($number, $records, $category, $time)
    {
        //$_week_ago = time() - $time;

        //получение собственно статей из базы
        $_sql = "select `article_name`, `article_content`, `article_link`, `category_link` from ";
        $_sql .= "`$this->db_name`.`$this->db_article` inner join `$this->db_name`.`$this->db_category` ";
        $_sql .= "on `$this->db_name`.`$this->db_article`.`article_category_0` = `$this->db_name`.`$this->db_category`.`category_id` ";
        //$_sql .= " where `$this->db_name`.`$this->db_article`.`article_date` < $_week_ago ";
        //$_sql .= " and `$this->db_name`.`$this->db_article`.`article_id` < ".$this->id[0]." ";
        if (!empty($category)) $_sql .= "and `$this->db_name`.`$this->db_article`.`article_category_0` = $category";
        $_sql .= $this->make_sorting("article_date", "desc");
        $_limit = " limit $number, $records";

        //итоговая строка запроса
        $sql_string = $_sql . $_limit;

        //выполнение запроса в базе
        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($sql_string);
        $arts = $mysql->getResult();
        $mysql->disconnect();

        //если ничего похожего не найдено, тогда просто последняя подборка за 2 месяца
        //if (empty($arts)) $arts = $this->get_right_column_data($number, $records, "", 676800);

        return $arts;
    }

    /*######################################################################################################################
                                      построение вывода правой колонки для HTML кода
    ######################################################################################################################*/
    private function build_right_column_output($_array)
    {
        //инициализация начальных переменных
        global $SERVER;
        $_output_page = "";
        $_array_length = count($_array);

        //построение HTML кода
        $_output_page .= '<article class="side_article">';
        $_output_page .= '<ul id="side_list">';
        for ($i = 0; $i < $_array_length; $i++) {
            //ограничение вывода картинок в боковую панель
            $_pattern = "/<.+?>/";
            $_replacement = " ";
            $_curr_article = preg_replace($_pattern, $_replacement, $_array[$i]['article_content']);
            //ограничить вывод на начальную страницу всего 300 симоволов, но целыми словами
            //для этого после 300 символов ищем первый пробел - это и будет конец слова
            $_cut_position = strpos($_curr_article, " ", $this->signs_in_side);
            $_curr_article = substr($_curr_article, 0, $_cut_position);
            //эта система считает количество слов
            //$_whole_article = explode(' ', $this->content[$i]);
            //$_cut_article = array_slice($_whole_article, 0, $this->words_in_info);
            //$_curr_article = implode(" ", $_cut_article);
            $_curr_article = '<span class="title_right_side">' . $_array[$i]['article_name'] . '</span><br/>' . $_curr_article;
            //код вывода страницы
            $_output_page .= '<a href="' . $SERVER . '/' . $_array[$i]['category_link'] . '/' . $_array[$i]['article_link'] . '.html" class="standart_link">';
            $_output_page .= '<li class="side_list_item">' . $_curr_article . '...</li>';
            $_output_page .= '</a>';
        }
        $_output_page .= '</ul>';
        $_output_page .= '</article>';
        return $_output_page;
    }

    /*######################################################################################################################
                                    построение данных для вывода боковых категорий сайта
    ######################################################################################################################*/
    private function build_side_categories_block()
    {
        global $SERVER;
        $_get_cat = $_GET["category"];

        $_sql = "select `category_id`, `category_name`, `category_link` from `$this->db_name`.`$this->db_category` ";
        $_sql .= "inner join `$this->db_name`.`$this->db_article` on ";
        $_sql .= "`$this->db_name`.`$this->db_category`.`category_id`=`$this->db_name`.`$this->db_article`.`article_category_0` ";
        //$_sql .= "`$this->db_name`.`$this->db_category`.`category_id`=`$this->db_name`.`$this->db_article`.`article_category_1` or ";
        //$_sql .= "`$this->db_name`.`$this->db_category`.`category_id`=`$this->db_name`.`$this->db_article`.`article_category_2` ";
        $_sql .= "where `category_show` = 1 and ";
        $_sql .= "`category_type` = 'main' ";
        $_sql .= "group by `$this->db_name`.`$this->db_category`.`category_id` ";
        $_sql .= "order by `category_id` asc;";

        //выполнение запроса в базе
        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($_sql);
        $cats = $mysql->getResult();
        $mysql->disconnect();

        $_sql = "select `category_id`, `category_name` from `$this->db_name`.`$this->db_category`";
        $_sql .= " where `category_id` = '1000'";

        //выполнение запроса в базе
        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($_sql);
        $name = $mysql->getResult();
        $mysql->disconnect();

        $_1000 = $name[0];
        $_2000 = $name[1];
        $_3000 = $name[2];
        $_output = '';
        $inner_flag = false;

        foreach ($cats as $key => $value) {
            $_c_id = intval($value["category_id"]);
            $_c_name = $value["category_name"];
            $_c_link = $value["category_link"];

            //вывод заголовков
            if ($_c_id > 1000 and !$inner_flag) {
                $_output .= '<div class="right_block">';
                $_output .= '<h3 class="title_h3">' . $_1000["category_name"] . ':</h3>';
                $inner_flag = true;
            }

            //вывод категорий
            $_class = "title_h4";
            if ($_get_cat == $_c_link) $_class = "title_h4_selected_category";
            if (($_c_id % 1000 != 0) && ($_c_id > 1000)) {
                $_output .= '<a href="' . $SERVER . '/' . $_c_link . '/page-1/">';
                $_output .= '<div class="' . $_class . '">' . $_c_name . '</div>';
                $_output .= '</a>';
            }
        }
        $_output .= '</div>';
        return $_output;
    }


    /*######################################################################################################################
                                            построение кнопок для листания страниц
    ######################################################################################################################*/
    private function build_pages_block()
    {
        global $SERVER;
        $_page_limiter = $this->page_limiter;          //ограничитель страниц
        $_current_page = intval($this->current_page);  //текущая страница
        $_current_block = 0;                           //текущий блок
        $_output = array();                            //итоговый массив
        $_start_page = 1;                              //начальная страница
        $_last_page = $_start_page + $_page_limiter;   //последняя страница
        $_html_backward = "class=\"active_arrow\"";    //active link
        $_html_forward = "class=\"active_arrow\"";     //active link
        $_html = null;                                 //данные для возврата

        //подсчитываем общее количество страниц
        $this->total_pages = ceil($this->total_arts / $this->articles_per_page);
        //подсчитываем общее количество постраничных блоков
        $this->total_block = ceil($this->total_pages / $_page_limiter);

        //если количество страниц меньше 1 - то выходим из скрипта - блок не нужен
        if ($this->total_pages < 2) return;

        //устанавливаем ограничитель по выводу количества страниц
        if (($_current_page + 3) - $_page_limiter > 0) $_start_page = $_current_page - 2;
        if (($_start_page + 5) > $this->total_pages) {
            $_last_page = $this->total_pages;
            $__iter = $_last_page + 1;
        } else {
            $_last_page = $_start_page + $_page_limiter;
            $__iter = $_last_page;
        }
        if (($_current_page - $_page_limiter) > 0) $_backward = $_current_page - $_page_limiter; else $_backward = $_start_page;
        if (($_current_page + $_page_limiter) < $_last_page) $_forward = $_current_page + $_page_limiter; else $_forward = $_last_page;

        //формирование итогового массива с данными
        $_output[] = $_backward;
        for ($i = $_start_page; $i < $__iter; $i++) $_output[] = $i;
        $_output[] = $_forward;

        //прорисовка HTML
        $_first_el = 1;
        $_last_el = count($_output) - 1;
        $_html  = "<div class=\"arrows\">";
        $_html .= "<a href=\"" . $SERVER . "/" . $_GET['category'] . "/page-" . $_output[0] . "/\">";
        $_html .= "<span " . $_html_backward . ">Сюда</span>";
        $_html .= "</a>";
        for ($i = $_first_el; $i < ($_last_el); ++$i) {
            if ($_output[$i] == $_current_page) $selected = "id=\"selected_button\"";
            else $selected = null;
            $_html .= "<a href=\"" . $SERVER . "/" . $_GET['category'] . "/page-" . $_output[$i] . "/\">";
            $_html .= "<span " . $selected . " class=\"page_button\">" . $_output[$i] . "</span>";
            $_html .= "</a>";
        }
        $_html .= "<a href=\"" . $SERVER . "/" . $_GET['category'] . "/page-" . $_output[$_last_el] . "/\">";
        $_html .= "<span " . $_html_forward . ">Туда</span>";
        $_html .= "</a>";
        $_html .= "</div>";
        return $_html;
    }

/*######################################################################################################################
                                        get_keywords($contents,$symbol=5,$words=50)
######################################################################################################################*/
    private function get_keywords($contents, $symbol = 5, $words = 50)
    {
        $contents = mb_convert_case($contents, MB_CASE_LOWER, "UTF-8");
        $contents = @preg_replace(array("'<[\/\!]*?[^<>]*?>'si", "'([\r\n])[\s]+'si", "'&[a-z0-9]{1,6};'si", "'( +)'si"),

            array("", "\\1 ", " ", " "), strip_tags($contents));

        $rearray = array("~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "+",
            "`", '"', "№", ";", ":", "?", "-", "=", "|", "\"", "\\", "/",
            "[", "]", "{", "}", "'", ",", ".", "<", ">", "\r\n", "\n", "\t", "«", "»",
            "грамм", "грамма", "штук", "штука", "штуки");

        $adjectivearray = array("ые", "ое", "ие", "ий", "ая", "ый", "ой", "ми", "ых", "ее", "ую", "их", "ым",
            "как", "для", "что", "или", "это", "этих",
            "всех", "вас", "они", "оно", "еще", "когда",
            "где", "эта", "лишь", "уже", "вам", "нет",
            "если", "надо", "все", "так", "его", "чем",
            "при", "даже", "мне", "есть", "только", "очень",
            "сейчас", "точно", "обычно"
        );

        $contents = @str_replace($rearray, " ", $contents);
        $keywordcache = @explode(" ", $contents);
        $rearray = array();

        foreach ($keywordcache as $word) {
            if (strlen($word) >= $symbol && !is_numeric($word)) {
                $adjective = substr($word, -2);
                if (!in_array($adjective, $adjectivearray) && !in_array($word, $adjectivearray)) {
                    $rearray[$word] = (array_key_exists($word, $rearray)) ? ($rearray[$word] + 1) : 1;
                }
            }
        }

        @arsort($rearray);
        $keywordcache = @array_slice($rearray, 0, $words);
        $keywords = "";

        foreach ($keywordcache as $word => $count) {
            $keywords .= ", " . $word;
        }

        return substr($keywords, 1);
    }

    /*######################################################################################################################
                                                     format_text()
    ######################################################################################################################*/
    private function format_text($text)
    {
        global $SERVER, $IMG_DIR;

        $pattern = array();
        $pattern[0] = "/\[IMG-(\d{1,5})-(\d{1,2})\]/";
        $pattern[1] = "/\[LINK-(.+?)§§(.+?)\]/";
        $pattern[2] = "/[\r\n]/";
        $pattern[3] = "/±±/";
        $pattern[4] = "/@(.+?)±/";
        $pattern[5] = "/; ±/";
        $pattern[6] = "/;±/";
        $pattern[7] = "/<br\/>±/";
        $pattern[8] = "/<br\/><br\/>/";
        $pattern[9] = "/±/";
        $pattern[10] = "/<p><h4>/";
        $pattern[11] = "/<\/h4><\/p>/";
        $pattern[12] = "/<p><\/p>/";
        $pattern[13] = "/  /";

        $replacement = array();
        $replacement[0] = "<img src=\"" . $SERVER . $IMG_DIR . "/article_images/$1-$2.jpg\" />";
        $replacement[1] = "<a rel=\"nofollow\" target=\"_new\" class=\"articles_links\" href=\"$1\">$2</a>";
        $replacement[2] = "±";
        $replacement[3] = "±";
        $replacement[4] = "<h4>$1</h4><p>";
        $replacement[5] = ";<br/>";
        $replacement[6] = ";<br/>";
        $replacement[7] = "<br/>";
        $replacement[8] = "<br/>#";
        $replacement[9] = "</p><p>";
        $replacement[10] = "<h4>";
        $replacement[11] = "</h4>";
        $replacement[12] = "";
        $replacement[13] = " ";

        //делается только через цикл, ибо последовательность операций важна!
        for ($i = 0; $i < count($pattern); $i++) {
            $text = preg_replace($pattern[$i], $replacement[$i], $text);
        }

        $formatted_text = trim("<p>" . $text . "</p>");
        $formatted_text = preg_replace("/<p><\/p>\s{0,}?/", "", $formatted_text);

        return $formatted_text;
    }

    /*######################################################################################################################
                                                 unformat_text()
    ######################################################################################################################*/
    private function unformat_text(&$text)
    {
        $pattern = array();
        $pattern[0] = "/<img.+?(\d{1,5})-(\d{1,2}).+?>/";
        $pattern[1] = "/<a.+?href=\"(.+?)\">(.+?)<\/a>/";
        $pattern[2] = "/<\/p><h4>(.+?)<\/h4><p>/";
        $pattern[3] = "/<\/p><p>/";
        $pattern[4] = "/<p>/";
        $pattern[5] = "/<\/p>/";

        $replacement = array();
        $replacement[0] = "[IMG-$1-$2]";
        $replacement[1] = "[LINK-$1§§$2]";
        $replacement[2] = "\r\n@$1\r\n";
        $replacement[3] = "\r\n";
        $replacement[4] = "";
        $replacement[5] = "";

        //делается только через цикл, ибо последовательность операций важна!
        for ($i = 0; $i < count($pattern); $i++) {
            $text = preg_replace($pattern[$i], $replacement[$i], $text);
        }
    }

    /*######################################################################################################################
                                                 get_article_symbols_amount($article_content)
    ######################################################################################################################*/
    private function get_article_symbols_amount($article_content)
    {
        //очищаем от тэгов
        $_text = strip_tags($article_content);
        //убираем пробелы
        $_text = preg_replace("/ /", "", $_text);
        //считаем количество символов без пробелов
        $_length = mb_strlen($_text, 'UTF-8');
        //возвращаем значение длины
        return $_length;
    }

    /*######################################################################################################################
                                                 get_article_price($article_symbols, $article_author_id)
    ######################################################################################################################*/
    private function get_article_price($article_symbols, $article_author_id)
    {
        //получаем данные автора
        $this->get_author_data($article_author_id);
        //высчитываем цену
        $_price = floatval($article_symbols) * floatval($this->author_price) * 0.001;
        //возвращаем значение
        return floatval($_price);
    }

    /*######################################################################################################################
                                                 current_payment()
    ######################################################################################################################*/
    private function current_payment()
    {
        $_sql = "select sum(`article_price`) as `total` from `" . $this->db_name . "`.`" . $this->db_article . "` ";
        $_sql .= "where `article_author_id` = " . $_GET['user_editor'];

        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($_sql);
        $values = $mysql->getResult();
        $mysql->disconnect();

        return floatval($values[0]['total']);
    }

    /*######################################################################################################################
                                                 check_for_admin()
    ######################################################################################################################*/
    public function check_for_admin()
    {
        if (isset($_GET['user_editor'])) {
            $_payment = $this->current_payment();
            $this->right_side_column = "<div class=\"gradient_line\"></div>";
            $this->right_side_column .= "<div id=\"to_pay\">К выплате: " . $_payment . " рублей</div>";
            $this->right_side_column .= "<div class=\"gradient_line\"></div>";
            $this->right_side_column .= "<div id=\"add_article\" class=\"category_0\" onclick=\"add_article();\">Добавить запись</div>";
            $this->right_side_column .= "<div class=\"gradient_line\"></div>";

            $this->get_author_data($_GET['user_editor']);

            $this->admin = true;
            return true;
        }
        $this->admin = false;
        return false;
    }

    /*######################################################################################################################
                                                 check_for_admin()
    ######################################################################################################################*/
    public function check_for_auth()
    {
        if (isset($_COOKIE['auth_admin']) and $_COOKIE['auth_admin'] == 1) {
            $this->auth_admin = true;
            return true;
        }
        $this->auth_admin = false;
        return false;
    }

    /*######################################################################################################################
                                              get_chosen_article_code()
    ######################################################################################################################*/
    public function get_chosen_article_code()
    {
        if (!isset($_GET['article']) || empty($_GET['article'])) {
            $_article = null;
            return $_article;
        } elseif (strpos($_GET['article'], "/")) {
            $_article = null;
            return $_article;
        }
        $_article = $_GET['article'];

        $this->sql_string = "select article_id, article_keywords, article_name, article_picture, article_content from `";
        $this->sql_string .= $this->db_name . "`.`" . $this->db_article;
        $this->sql_string .= "` where `article_link` = '" . $_article . "';";

        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($this->sql_string);
        $values = $mysql->getResult();
        $mysql->disconnect();

        //для построения краткого описания статьи - просто берется первый абзац
        //должны быть исключения - все тэги - они откидываются, берется только текст
        $_full_article = $values[0]['article_content'];
        $find_me = '</p>';
        $pos = strpos($_full_article, $find_me);
        $_short_description = substr($_full_article, 3, ($pos - 3));
        //случай если есть картинка или другие тэги
        //$find_me            = '<';
        //$pos                = strpos($_full_article, $find_me);
        //$_short_description = "МегаОтпуск - все к нам!";

        $values[0]['article_short_descr'] = $_short_description;

        return $values[0];
    }

    /*######################################################################################################################
                                             get_chosen_category_code()
    ######################################################################################################################*/
    public function get_chosen_category_code() {
        if (!isset($_GET['category'])) {
            $_category = null;
            return $_category;
        }
        if (strpos($_GET['category'], "age-")) {
            $_GET['page'] = substr($_GET['category'], 5);
            $_GET['category'] = 'all_travels';
        }
        //вычисление ткущего номера страницы
        if(!empty($_GET['page'])){
            $this->current_page = $_GET['page'];
        } else
            $this->current_page = 1;

        $_category = $_GET['category'];

        $this->sql_string = "select category_id, category_name, category_link, category_keywords from `" . $this->db_name . "`.`" . $this->db_category;
        $this->sql_string .= "` where `category_link` = '" . $_category . "';";

        //echo $this->sql_string;

        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($this->sql_string);
        $values = $mysql->getResult();
        $mysql->disconnect();

        return $values[0];
    }

    /*######################################################################################################################
                                                add_article($array)
    ######################################################################################################################*/
    public function add_article()
    {
        extract($_POST);

        //присваиваем значения переменным
        $article_main_id = $id;
        $article_author_id = $author;
        $article_name = $name;
        $article_picture = $picture . ".jpg";
        $article_content = $this->format_text($content);
        $article_category_0 = $category_0;
        $article_category_1 = $category_1;
        $article_category_2 = $category_2;
        $article_link = $link;
        $article_date = time();
        $article_reads = 0;
        $article_comments = 0;
        $article_rating = 0;
        $article_keywords = $this->get_keywords($article_content);
        $article_symbols = $this->get_article_symbols_amount($article_content);
        $article_price = floatval($this->get_article_price($article_symbols, $article_author_id));

        // проверяем наличие дубликатов имен и исправляем их в случае необходимости
        //проверка проводится для 20 дубликатов
        $_sql_string = "select article_id from `" . $this->db_name . "`.`" . $this->db_article . "` ";
        $_sql_string .= "where `article_link` in ('" . $link . "', ";
        for ($i = 1; $i < 20; $i++) $_sql_string .= "'" . $link . '-' . $i . "', ";
        $_sql_string .= "'" . $link . '-' . "20');";

        /* mysql request */
        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($_sql_string);
        $val = $mysql->getResult();
        $mysql->disconnect();

        // Есть дубликаты? Тогда добавляем одну букву
        if (count($val) > 0) $article_link .= '-' . count($val);

        //формируем MySQL строку и добавляем в базу данных
        $this->sql_string = "insert into";
        $this->sql_string .= " `" . $this->db_name . "`.`" . $this->db_article . "` ";
        $this->sql_string .= "(article_id, article_author_id, article_name, article_picture, article_content, ";
        $this->sql_string .= "article_category_0, article_category_1, article_category_2, ";
        $this->sql_string .= "article_link, article_date, article_reads, article_comments, article_rating, article_keywords, ";
        $this->sql_string .= "article_symbols, article_price) values (";
        $this->sql_string .= $article_main_id . ", " . $article_author_id . ", '" . $article_name . "', '" . $article_picture . "', '";
        $this->sql_string .= $article_content . "', '" . $article_category_0 . "', '" . $article_category_1 . "', '" . $article_category_2 . "', '";
        $this->sql_string .= $article_link . "', " . $article_date . ", ";
        $this->sql_string .= $article_reads . ", " . $article_comments . ", " . $article_rating . ", '" . $article_keywords . "',";
        $this->sql_string .= $article_symbols . ", " . $article_price . ");";

        /* mysql request */
        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($this->sql_string);
        $mysql->disconnect();
    }

    /*######################################################################################################################
                                              load_article($array)
    ######################################################################################################################*/
    public function load_article()
    {
        extract($_POST);

        $_sql = "select * from `" . $this->db_name . "`.`" . $this->db_article . "` ";
        $_sql .= "where `article_id`=" . $id;

        /* mysql request */
        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($_sql);
        $_article = $mysql->getResult();
        $mysql->disconnect();

        //производим обратное форматирование текста
        $this->unformat_text($_article[0]['article_content']);
        //вывод в формате JSON
        echo json_encode($_article);
    }

    /*######################################################################################################################
                                                edit_article($array)
    ######################################################################################################################*/
    public function edit_article()
    {
        extract($_POST);

        //форматирование текста для апдейта базы
        $content = $this->format_text($content);
        $keywords = $this->get_keywords($content);

        //формирование sql запроса
        $this->sql_string = "update `" . $this->db_name . "`.`" . $this->db_article . "` set ";
        $this->sql_string .= "`article_category_0`='" . $category_0 . "', ";
        $this->sql_string .= "`article_category_1`='" . $category_1 . "', ";
        $this->sql_string .= "`article_category_2`='" . $category_2 . "', ";
        $this->sql_string .= "`article_content`='" . $content . "', ";
        if (isset($picture)) $this->sql_string .= "`article_picture`='" . $picture . ".jpg', ";
        $this->sql_string .= "`article_name`='" . $name . "', ";
        $this->sql_string .= "`article_keywords`='" . $keywords . "' ";
        $this->sql_string .= "where `article_id`=" . $id . ";";

        /* mysql request */
        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($this->sql_string);
        $mysql->disconnect();
    }

    /*######################################################################################################################
                                    построение и вывод страницы в зависимости от типа
    ######################################################################################################################*/
    private function show_category()
    {
        if ($this->current_category_type == "main" or
            $this->current_category_type == "type" or
            $this->current_category_type == "tick" or
            $this->current_category_type == "dost" or
            $this->current_category_type == "fact" or
            $this->current_category_type == "immi") $this->show_main();
        if ($this->current_category_type == "info") $this->show_info();
        if ($this->current_category_type == "blog") $this->show_info();
    }

    /*######################################################################################################################
                                    построение и вывод страницы с перечислением статей
    ######################################################################################################################*/
    private function show_main()
    {
        global $BASE_DIR, $SS_DIR;
        include_once($BASE_DIR . $SS_DIR . "/show_main.php");
    }

    /*######################################################################################################################
                                построение и вывод страницы с перечислением полезной информации
    ######################################################################################################################*/
    private function show_info()
    {
        global $BASE_DIR, $SS_DIR;
        include_once($BASE_DIR . $SS_DIR . "/show_info.php");
    }

    /*######################################################################################################################
                                    построение и вывод страницы с перечислением статей
    ######################################################################################################################*/
    private function show_article()
    {
        global $BASE_DIR, $SS_DIR;
        include_once($BASE_DIR . $SS_DIR . "/show_article.php");
    }

    /*######################################################################################################################
                                            show_page($_category, $_article)
    ######################################################################################################################*/
    public function show_page($_category, $_article)
    {
        //determine type of the page
        $this->determine_types($_category);
        //category is always defined, so script tries to define
        //what to show on article basis. If empty - then category
        if (!isset($_article) || empty($_article)) {
            //grab all data
            $this->get_category_data($_category);
            //create HTML code
            $this->show_category();
            return $this->output_page;
        }
        //if there is article variable defined and it's not empty
        //then the script is requesting one article to show
        elseif (isset($_article) && !empty($_article)) {
            //grab all data
            $this->get_article_data($_article);
            //create HTML code
            $this->show_article();
            return $this->output_page;
        }
    }

/*######################################################################################################################
                              построение полного списка SELECT для окна редактирования статей
######################################################################################################################*/
    public function build_selections($type) {

        //категории типа 0000 - категории закголовки - ничего не выводится, категория не присвоена
        //категории типа 1000, 2000, 3000 ... etc. - категории заголовки - являются заголовками в столбце
        //категории типа 1001 - 1999, 2001 - 2999 ... etc. - собственно подкатегории статей

        //категории типа 1000 - направления путешествий (не выводятся в главном меню - только в боковом)
        //категории типа 2000 - вид отдыха
        //категории типа 3000 - тип путешествий
        //категории типа 4000 - достопримечательности
        //категории типа 5000 -
        //категории типа 6000 -
        //категории типа 7000 -
        //категории типа 8000 -
        //категории типа 9000 -

        if ($type == 'main') $type = "'main','type','tick'";
        elseif ($type == 'info') $type = "'info','tick'";
        elseif ($type == 'dost') $type = "'dost','main'";
        elseif ($type == 'immi') $type = "'immi','main','tick'";
        else $type = "'".$type."'";

        $select_sql = "select * from `$this->db_name`.`$this->db_category";
        $select_sql .= "` where `category_type` in ($type) and `category_show` = true and `category_id` <> '0000' ";
        $select_sql .= "ORDER BY FIELD(`category_type`, $type)";

        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($select_sql);
        $values = $mysql->getResult();
        $mysql->disconnect();

        $array_lenght = count($values);
        $counter = 0;
        $select_category = ["", "", ""];
        $curr_category = 0;

        while ($counter < $array_lenght) {
            $select_category[$curr_category] = "<select id=\"article_category_" . $curr_category . "\" name=\"article_category\">";
            $select_category[$curr_category] .= "<option class=\"option\" value=\"0001\">---</option>";
            //начало группы выбора
            $select_category[$curr_category] .= "<optgroup label=\"" . $values[$counter]["category_name"] . "\">";
            //переход на следующую запись с заголовка группы
            $counter++;
            //составление списка выбора
            while (substr($values[$counter]["category_id"], 1, 3) != "000" and $counter < $array_lenght) {
                $select_category[$curr_category] .= "<option class=\"option\" value=\"";
                $select_category[$curr_category] .= $values[$counter]["category_id"];
                $select_category[$curr_category] .= "\">";
                $select_category[$curr_category] .= $values[$counter]["category_name"];
                $select_category[$curr_category] .= "</option>";
                //переход на следующую запись с заголовка группы
                $counter++;
            }
            $select_category[$curr_category] .= "</optgroup>";
            $select_category[$curr_category] .= "</select>";
            $curr_category++;
        }

        //формирование выдачи как одной строки
        $return_val = "";
        for ($i = 0; $i < count($select_category); $i++)
            $return_val .= $select_category[$i];

        //выдача
        return $return_val;
    }
/*######################################################################################################################
                                            построение меню "Полезная информация"
######################################################################################################################*/
    public function build_menu()
    {
        global $SERVER;
        $_html = "<li><a href=\"$SERVER/\">Новости</a></li>";

        //this request get the list of used categories in articles
        //but that's not the only data we need -
        //SubCategories in this request are missing
        $_sql = "select `category_id`, `category_name`, `category_link` from `$this->db_name`.`$this->db_category` ";
        $_sql .= "inner join `$this->db_name`.`$this->db_article` on ";
        $_sql .= "`$this->db_name`.`$this->db_category`.`category_id`=`$this->db_name`.`$this->db_article`.`article_category_0` or ";
        $_sql .= "`$this->db_name`.`$this->db_category`.`category_id`=`$this->db_name`.`$this->db_article`.`article_category_1` or ";
        $_sql .= "`$this->db_name`.`$this->db_category`.`category_id`=`$this->db_name`.`$this->db_article`.`article_category_2` ";
        $_sql .= "where `category_show` = true and ";
        $_sql .= "`category_id` > '1999' ";
        $_sql .= "group by `$this->db_name`.`$this->db_category`.`category_id` ";
        $_sql .= "order by `category_id` asc;";

        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($_sql);
        $values = $mysql->getResult();
        $mysql->disconnect();

        //the second reguest gets the SubCategories for the main data
        $_headers = array(); //$_headers[] = "2000";
        for($i = 0; $i < count($values); $i++){
            $_tmp = 1000 * intval(substr($values[$i]["category_id"], 0, 1));
            $tmp = (string) $_tmp;
            if (end($_headers) != $_tmp) $_headers[] = $tmp;
        }

        //build the sql request
        $_sql = "select `category_id`, `category_name`, `category_link` from `$this->db_name`.`$this->db_category` ";
        $_sql.= "where `category_id` in (";
        for ($i = 0; $i < count($_headers); $i++) $_sql .= "'".$_headers[$i]."',";
        $_sql = substr($_sql,0,-1) . ");";

        $mysql = new mysql\Mysql;
        $mysql->connect();
        $mysql->sql($_sql);
        $headers = $mysql->getResult();
        $mysql->disconnect();

        //finally I have two arrays: $headers and $values
        //so the next step is to show them on a top line

        $counter_h = count($headers);
        $counter = 0;

        for($iter = 0; $iter < $counter_h; $iter++){
            $_html.= "<li><a href=\"$SERVER/".$headers[$iter]["category_link"]."/\">".$headers[$iter]["category_name"]."</a>";

            //откуда докуда будет внутренний перебор подкатегорий
            $low_limit = $headers[$iter]["category_id"];
            $top_limit = strval(intval($headers[$iter]["category_id"]) + 1000);
            $inner_flag = false;
            //собственно перебор и построение подкатегорий
            while($values[$counter]["category_id"] > $low_limit and $values[$counter]["category_id"] < $top_limit){
                //если первый проход - открыть категорию
                if (!$inner_flag) $_html .= "<ul>";
                $_html.= "<li><a href=\"$SERVER/".$values[$counter]["category_link"]."/\">".$values[$counter]["category_name"]."</a></li>";
                $inner_flag = true;
                $counter++;
            }
            //если были внутренние подкатегории - тогда поставить закрывающий тэг
            if ($inner_flag) $_html .= "</ul>";
            //закрыть категорию
            $_html.= "</li>";
        }
        return $_html;
    }
}
?>