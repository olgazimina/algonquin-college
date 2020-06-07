<?php
class Login{
    //private $vk_login_button = '<a href="https://oauth.vk.com/authorize?client_id=5148526&display=page&redirect_uri=http://www.mega-otpusk.ru/vklogin&scope=friends&response_type=code&v=5.41" title="Зайти через ВКонтакте"><div id="add_article" class="category_0">Зайти через ВКонтакте</div></a>';

    public function create_buttons() {
        $administer = '<div id="add_article" class="category_0" onclick="add_article();">Добавить запись</div>';

        $vk_login_button  = '<a href="https://oauth.vk.com/authorize?client_id=5148526&display=page&redirect_uri=';
        $vk_login_button .= 'http://www.mega-otpusk.ru/vklogin&scope=friends&response_type=code&v=5.41" ';
        $vk_login_button .= 'title="Зайти через ВКонтакте"><div id="add_article" class="category_0">Зайти через ВКонтакте</div></a>';

        if (isset($_COOKIE['auth_admin']) and $_COOKIE['auth_admin'] == 1 or
            $_SERVER["HTTP_HOST"] == "homemac.local" or
            $_SERVER["HTTP_HOST"] == "192.168.1.2")
            //if (isset($_COOKIE['auth_admin']) and $_COOKIE['auth_admin'] == 1 or
            //    $_SERVER["HTTP_HOST"] == "homemac.local")
            $_data = $administer;
        else
            $_data = $vk_login_button;

        return $_data;
    }
}
?>