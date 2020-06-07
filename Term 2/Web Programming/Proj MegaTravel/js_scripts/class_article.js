$id                 = null;
script_add_article  = "ss_scripts/ajax_add_article.php";
script_add_image    = "ss_scripts/ajax_upload_images.php";
var picture_counter = 0;
var article_id      = 0; // значение сбрасывается при нажатии кнопок "Загрузить" или "Сброс", а также при перезагрузке страницы
var pic_type        = "main";
/*######################################################################################################################
                                            create_skyscanner_deeplink
 ######################################################################################################################*/
function create_skyscanner_deeplink(){
    var _link = prompt("Введите ссылку со Скайсканера:", "http://");
    _link  = "[LINK-http://clkuk.tradedoubler.com/click?p(232108)a(2796526)g(21113908)url("+ encodeURIComponent(_link) + ")§§скайсканере:]\r\n[LINK-http://clkuk.tradedoubler.com/click?p(232108)a(2796526)g(21113908)url(" + encodeURIComponent(_link) + ")§§[IMG-000-0]]";
    var btn = document.querySelector("#ss");
    btn.value = "Paste SkyScanner Link";
    btn.onclick = function(){
        document.querySelector("#article_content").value += _link;
        btn.value = "Get SkyScanner Link";
        btn.onclick = function(){create_skyscanner_deeplink();}
    }
}
/*######################################################################################################################
                                             create_usual_deeplink
 ######################################################################################################################*/
function create_usual_deeplink(){
    var _link = prompt("Введите ссылку:", "http://");
    _link = "[LINK-" + encodeURIComponent(_link) + ")§§TEXT]";
    var btn = document.querySelector("#nl");
    btn.value = "Paste Normal Link";
    btn.onclick = function(){
        document.querySelector("#article_content").value += _link;
        btn.value = "Get Normal Link";
        btn.onclick = function(){create_usual_deeplink();}
    }
}
/*######################################################################################################################
                                              change_type(this)
######################################################################################################################*/
function change_type(radio_btn){
    if (radio_btn == "main") {
        document.querySelector("#selector_type").innerHTML = main;
        pic_type = 'main';
    } else if (radio_btn == "dost") {
        document.querySelector("#selector_type").innerHTML = dost;
        pic_type = 'main';
    } else if (radio_btn == "fact") {
        document.querySelector("#selector_type").innerHTML = fact;
        pic_type = 'main';
    } else if (radio_btn == "info") {
        document.querySelector("#selector_type").innerHTML = info;
        pic_type = radio_btn;
    } else if (radio_btn == "immi") {
        document.querySelector("#selector_type").innerHTML = immi;
        pic_type = 'main';
    } else if (radio_btn == "blog") {
        document.querySelector("#selector_type").innerHTML = blog;
        pic_type = radio_btn;
    }
}

/*######################################################################################################################
                                              add_article()
######################################################################################################################*/
function add_article() {
    if (document.querySelector("#shadow")) return;

    Article = Create_Article("add");
    Article.new_article_id();
    Article.toggle_fields();
    Article.open_window();
    Article.enable_buttonset();
}

/*######################################################################################################################
                                              edit_article(_id)
######################################################################################################################*/
function edit_article(_id) {
    $id = _id;
    if (document.querySelector("#shadow")) return;

    Article = Create_Article("load");
    Article.article_id = $id;
    Article.toggle_fields();
    Article.open_window();
    Article.enable_buttonset();
    Article.load_article(_id);
}
/*######################################################################################################################
                                              Upload_images(flag)
 ######################################################################################################################*/
function upload_images() {
    Article = Create_Article("img");
    if(article_id == 0) Article.new_article_id();
    Article.upload_picture();
}
/*######################################################################################################################
                                              Create_Article(flag)
######################################################################################################################*/
function Create_Article(flag) {
    var obj = new Object();

    obj.flag = flag;

    obj.new_article_id = function(){
        var server_script = script_add_article;
        var request = "flag=na";    //na - means "new article", not N/A

        var ajax = new Ajax(server_script, request);
        ajax.send_request(function ($response) {
            $data = $response;
            article_id = $data;
        });
    }

    obj.toggle_fields = function() {
        var inputs = document.getElementsByTagName('input');
        for (var i = inputs.length, n = 0; n < i; n++) {
            inputs[n].disabled = !inputs[n].disabled;
        }
    }

    obj.open_window = function() {

        var Window = new Object();
        Window.shadow = document.createElement("div");
        Window.shadow.id = "shadow";
        Window.main = document.createElement("div");
        Window.main.id = "main_window";
        Window.main.innerHTML = code;

        //enabling fading effect which doesn't work with
        //standart transition effect in css. Need setTimeout
        document.body.appendChild(Window.shadow);
        document.body.appendChild(Window.main);
        setTimeout(function() {
            Window.shadow.className = "enabled_shadow";
        }, 1);
    }

    obj.load_article = function(_id) {
        article_id = _id;
        var server_script = script_add_article;
        var request = "id=" + _id;
        request += "&flag=" + obj.flag;

        var ajax = new Ajax(server_script, request);
        ajax.send_request(function($response) {
            $data = JSON.parse($response);

            if ($data[0].article_category_0 > 1000 && $data[0].article_category_0 < 4000){
                document.querySelectorAll('[name=article_type]')[0].checked = true;
                change_type('main');
            } else if ($data[0].article_category_0 > 5000 && $data[0].article_category_0 < 6000){
                document.querySelectorAll('[name=article_type]')[1].checked = true;
                change_type('dost');
            } else if ($data[0].article_category_0 > 6000 && $data[0].article_category_0 < 7000){
                document.querySelectorAll('[name=article_type]')[2].checked = true;
                change_type('fact');
            } else if ($data[0].article_category_0 > 7000 && $data[0].article_category_0 < 8000){
                document.querySelectorAll('[name=article_type]')[3].checked = true;
                change_type('info');
            } else if ($data[0].article_category_0 > 8000 && $data[0].article_category_0 < 9000){
                document.querySelectorAll('[name=article_type]')[4].checked = true;
                change_type('immi');
            } else if ($data[0].article_category_0 > 9000 && $data[0].article_category_0 < 10000){
                document.querySelectorAll('[name=article_type]')[5].checked = true;
                change_type('blog');
            }
            cat_0 = document.querySelector("#article_category_0");
            if (cat_0 != null) cat_0.value = $data[0].article_category_0;
            cat_1 = document.querySelector("#article_category_1");
            if (cat_1 != null) cat_1.value = $data[0].article_category_1;
            cat_2 = document.querySelector("#article_category_2");
            if (cat_2 != null) cat_2.value = $data[0].article_category_2;

            document.querySelector("#article_name").value = $data[0].article_name;
            document.querySelector("#article_content").value = $data[0].article_content;
            document.querySelector("#article_keywords").value = $data[0].article_keywords;
            document.querySelector("#author_id").value = $data[0].article_author_id;
        });
        // флаг необходимо поменять на запись
        obj.flag = "edit";
    }

    obj.enable_buttonset = function() {
        upload_button = document.querySelector("#my_send");
        cancel_button = document.querySelector("#cancel");
        upload_button.addEventListener("click", function() {obj.button_ok();});
        cancel_button.addEventListener("click", function() {obj.button_cancel();});
    }

    //если нажата кнопка ОК
    obj.button_ok = function() {
        var Window = obj.grab_data(); if (Window == false) return;
        var Picture = article_id + '-' + picture_counter + '.jpg';
        obj.mysql_request(Window);
        Window.w_status = obj.close_window();
    }

    //если нажата кнопка CANCEL
    obj.button_cancel = function() {
        obj.close_window();
        article_id = 0;
    }

    obj.change_quotes = function(text){
        var el = document.createElement("DIV");
        el.innerHTML = text;
        for(var i=0, l=el.childNodes.length; i<l; i++){
            if (el.childNodes[i].hasChildNodes() && el.childNodes.length>1){
                el.childNodes[i].innerHTML = changeQuotes(el.childNodes[i].innerHTML);
            }
            else{
                el.childNodes[i].textContent = el.childNodes[i].textContent.replace(/\x27/g, '\x22').replace(/(\w)\x22(\w)/g, '$1\x27$2').replace(/(^)\x22(\s)/g, '$1»$2').replace(/(^|\s|\()"/g, "$1«").replace(/"(\;|\!|\?|\:|\.|\,|$|\)|\s)/g, "»$1");
            }
        }
        return el.innerHTML;
    }

    obj.translit = function(ru_string) {
        //Если с английского на русский, то передаём вторым параметром true.
        var
            rus = "«#»#\"#!#'#;#:#?#.#,#-#щ####ш##ч##ц##ю##я##ё##ж##ъ##ы##э##а#б#в#г#д#е#з#и#й#к#л#м#н#о#п#р#с#т#у#ф#х##ь# ".split(/#+/g),
            eng = "`#`#`##`#`#`#`#`#`#`#-#shсh#sh#ch#ts#yu#ya#yo#zh#`##y##e##a#b#v#g#d#e#z#i#y#k#l#m#n#o#p#r#s#t#u#f#kh#`#-".split(/#+/g);
        return function(text, engToRus) {
            var x;
            engToRus = false;
            for (x = 0; x < rus.length; x++) {
                if (eng[x] == "`") eng[x] = "";
                text = text.split(engToRus ? eng[x] : rus[x]).join(engToRus ? rus[x] : eng[x]);
                text = text.split(engToRus ? eng[x].toUpperCase() : rus[x].toUpperCase()).join(engToRus ? rus[x] : eng[x]);
            }
            return text;
        }
    }();

    obj.grab_data = function() {
        var _data = new Object();
        var reset_flag = false;

        _data.category_0 = function() {
            var element_0 = document.querySelector("#article_category_0");
            var category_0 = element_0.options[element_0.selectedIndex].value;
            if (category_0 == "01" || category_0.substr(0, 1) == 0) {
                element_0.style.borderColor = "#d44";
                reset_flag = true;
            }
            return category_0;
        }();

        _data.category_1 = function() {
            var element_1 = document.querySelector("#article_category_1");
            if (element_1 != null) {
                var category_1 = element_1.options[element_1.selectedIndex].value;
                element_1.style.borderColor = "#d44";
                return category_1;
            }
        }();

        _data.category_2 = function() {
            var element_2 = document.querySelector("#article_category_2");
            if (element_2 != null) {
                var category_2 = element_2.options[element_2.selectedIndex].value;
                element_2.style.borderColor = "#d44";
                return category_2;
            }
        }();

        _data.art_name = function() {
            var name = document.querySelector("#article_name");
            if (name.value == "") {
                name.style.borderColor = "#d44";
                reset_flag = true;
            }
            return name.value;
        }();

        _data.timestamp = function() {
            _timestamp = Date.now() / 1000 | 0;
            return _timestamp;
        }();

        _data.art_link = function() {
            var _link = obj.translit(_data.art_name);
            return _link;
        }();

        _data.a_author = document.querySelector("#author_id").value;

        _data.apicture = function(){
            var picture = document.querySelector("#file-id").value;
            if (obj.flag == "add") {
                if (picture == '' || typeof picture == 'undefined') {
                    reset_flag = true;
                    alert("Не выбрана картинка");
                }
            }
            return picture;
        }();

        _data.acontent = function() {
            var content = document.querySelector("#article_content");
            //замена кавычек
            content.value = obj.change_quotes(content.value);
            if (content.value == "") {
                content.style.borderColor = "#d44";
                reset_flag = true;
            }
            return content.value;
        }();

        _data.keywords = function() {
            var keywords = document.querySelector("#article_keywords");
            if (keywords.value == "") {
                keywords.style.borderColor = "#d44";
                reset_flag = true;
            }
            return keywords.value;
        }();        

        _data.w_status = true;

        _data.callback = false;

        if (reset_flag) return false;
        if (!reset_flag) return _data;
    }

    obj.mysql_request = function(_data) {
        var server_script = script_add_article;
        var request = [];
        request.push("id="          + encodeURIComponent(article_id));
        request.push("category_0="  + encodeURIComponent(_data.category_0));
        request.push("category_1="  + encodeURIComponent(_data.category_1));
        request.push("category_2="  + encodeURIComponent(_data.category_2));
        request.push("content="     + encodeURIComponent(_data.acontent));
        request.push("name="        + encodeURIComponent(_data.art_name));
        request.push("author="      + encodeURIComponent(_data.a_author));
        request.push("link="        + encodeURIComponent(obj.translit(_data.art_name)));
        request.push("flag="        + encodeURIComponent(obj.flag));
        request.push("keywords="    + encodeURIComponent(_data.keywords));
        if (_data.apicture != '') request.push("picture=" + encodeURIComponent(article_id + '-main-image'));

        var ajax = new Ajax(server_script, request);
        ajax.send_request("");
    }

    obj.upload_picture = function() {
        //для первой картинки уснавливаем особое имя файла
        if (picture_counter == 0) {picture_counter = pic_type + "-image";}
        else if (picture_counter == pic_type + "-image") {picture_counter = 0;}
        //для каждой последующей картинки - имя файля идёт с инкрементом
        if (picture_counter != pic_type + "-image") {picture_counter++;}

        var send_request = function sendXHRequest(formData, uri) {
            var xhr = new XMLHttpRequest();
            xhr.upload.addEventListener('loadstart', start_handler, false);
            xhr.upload.addEventListener('progress', progress_handler, false);
            xhr.upload.addEventListener('load', end_handler, false);
            xhr.addEventListener('readystatechange', response_handler, false);
            xhr.open('POST', uri, true);
            xhr.send(formData);
        }
        var start_handler = function onloadstartHandler(evt) {
        }

        var end_handler = function onloadHandler(evt) {
        }

        var progress_handler = function onprogressHandler(evt) {
            var div = document.querySelector('#progress');
            var percent = evt.loaded/evt.total*10;
            div.innerHTML = '';
            for(i = 0; i < percent; i++) {
                div.innerHTML += '<span class="progress"></span>';
            }
        }

        var response_handler = function onreadystatechangeHandler(evt) {
            var status, text, readyState;
            try {
                readyState = evt.target.readyState;
                text = evt.target.responseText;
                status = evt.target.status;
            }
            catch(e) {
                return;
            }
            if (readyState == 4 && status == '200' && evt.target.responseText) {
                var list = document.querySelector('#uploaded_pictures');

                //this shows the filename for putting it into an editor window
                list.innerHTML += '[IMG-' + evt.target.responseText + "]" + '&nbsp;&nbsp;';
                var inputsFile = document.querySelector('#file-id');
                var mainButton = document.querySelector('#my_send');
                inputsFile.disabled = false;
                mainButton.disabled = false;
            }
        }

        var formData = new FormData();
        var action = script_add_image;
        var fileInput = document.querySelector('#file-id');
        var mainButton = document.querySelector('#my_send');
        var file = fileInput.files[0];
        var fileName = article_id + '-' + picture_counter + '.jpg';
        formData.append('uploading-file', file, fileName);
        //выключаем форму до конца загрузки файла
        fileInput.disabled = true;
        mainButton.disabled = true;
        send_request(formData, action);
    }

    obj.close_window = function() {
        var Window = new Object();
        Window.shadow = document.querySelector("#shadow");
        Window.main = document.querySelector("#main_window");
        document.querySelector("#my_send").removeEventListener("click", function() {
            obj.button_ok();
        });
        document.querySelector("#cancel").removeEventListener("click", function() {
            obj.button_cancel();
        });
        document.body.removeChild(Window.main);
        document.body.removeChild(Window.shadow);
        obj.toggle_fields();

        // возвращает статус окна - закрыто или открыто
        return false;
    }
    return obj;
}