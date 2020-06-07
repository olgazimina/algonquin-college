<?php
function get_data($link){

    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $link);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
    $out = curl_exec($ch);
    curl_close($ch);
    return $out;
}
$request = "https://oauth.vk.com/access_token?client_id=5148526&client_secret=Wd5Soz8JHy3OQYPjRA1g&redirect_uri=http://www.mega-otpusk.ru/vklogin&code=" . $_GET['code'];

$output = get_data($request);
$data = json_decode($output);
if (isset($data->access_token)) {
    $request = "https://api.vk.com/method/users.get?user_id=".$data->user_id."&v=5.41&access_token=" . $data->access_token;
    $output = get_data($request);
    $user_data = json_decode($output);
    if ($data->user_id == 197022322 or
        $data->user_id == 1246143){
        setcookie("auth_admin", "1", 0, "/", "mega-otpusk.ru");
        setcookie("lname", $user_data->response[0]->last_name, 0, "/", "mega-otpusk.ru");
        setcookie("fname", $user_data->response[0]->first_name, 0, "/", "mega-otpusk.ru");
        header("Location: http://www.mega-otpusk.ru/editors");
    } else {
        setcookie("auth_admin", "0", time() - 3600, "/", "mega-otpusk.ru");
        setcookie("lname", "", time() - 3600, "/", "mega-otpusk.ru");
        setcookie("fname", "", time() - 3600, "/", "mega-otpusk.ru");
        header("Location: http://www.mega-otpusk.ru/");
        echo "other user";
    }
} else {
    setcookie("auth_admin", "0", time() - 3600, "/", "mega-otpusk.ru");
    setcookie("lname", "", time() - 3600, "/", "mega-otpusk.ru");
    setcookie("fname", "", time() - 3600, "/", "mega-otpusk.ru");
    header("Location: http://www.mega-otpusk.ru/");
    echo "wrong pass";
}
?>