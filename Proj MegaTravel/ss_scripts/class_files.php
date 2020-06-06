<?php

class Upload_File
{
    public $path = null;
    public $name = null;
    public $file = null;
    public $image = null;
    public $target = null;
    public $tmp_file = null;
    public $moved_ok = null;
    public $main_image = true;
    public $width = 0;
    public $height = 0;

    private $maximum = 710;
    private $new_x;
    private $new_y;
    private $new_width;
    private $new_height;

#####################################################################################################
    public function upload_file() {
        //если данные переданы в файл
        if (!empty($_FILES['uploading-file']) && $this->path != null) {
            //получаем только имя файла без указания пути к нему
            $this->name = basename($_FILES['uploading-file']['name']);
            //меняем путь на нужный нам
            $this->tmp_file = $this->path . $this->name;
            //перемещаем файл в нужную папку
            $this->moved_ok = move_uploaded_file($_FILES['uploading-file']['tmp_name'], $this->tmp_file);
        }
    }

#####################################################################################################
    public function check_for_main_image() {
        $pos = strpos($this->tmp_file, "main");
        if ($pos > 0)
            return true;
        else
            $pos = strpos($this->tmp_file, "info");

        if ($pos > 0)
            return true;
        else
            return false;
    }

#####################################################################################################
    public function check_for_info_image() {
        $pos = strpos($this->tmp_file, "info");
        if ($pos > 0) return true;
        else return false;
    }

#####################################################################################################
    private function ratio($image) {
        //original dimensions
        $_width_orig = imagesx($this->image);
        $_height_orig = imagesy($this->image);

        $r1 = $_width_orig / $this->width;
        $r2 = $_height_orig / $this->height;

        if ($image == "main" or $image == "info") {
            if ($r1 > $r2) {
                $this->new_x = (($_width_orig / $r2) - $this->width) / 2;
                $this->new_y = 0;
                $this->new_width = $this->width * $r2;
                $this->new_height = $_height_orig;
            } elseif ($r1 < $r2) {
                $this->new_x = 0;
                $this->new_y = (($_height_orig / $r1) - $this->height) / 2;
                $this->new_width = $_width_orig;
                $this->new_height = $this->height * $r1;
            }
        } elseif ($_width_orig > 710) {
            $this->new_x = 0;
            $this->new_y = 0;
            $this->new_width = $_width_orig;
            $this->new_height = $_height_orig;
            $this->width = 710;
            $this->height = $_height_orig / ($_width_orig / 710);
        } else {
            $this->new_x = 0;
            $this->new_y = 0;
            $this->new_width = $_width_orig;
            $this->new_height = $_height_orig;
            $this->width = $_width_orig;
            $this->height = $_height_orig;
        }
    }

#####################################################################################################
    public function reformat_image($image) {
        //включено подавление ошибок при загрузке файла в память
        if ($this->moved_ok) $this->file = @file_get_contents($this->tmp_file);

        //переопределение переменной this->target
        $this->target = $this->path . $this->name;
        $this->image = imagecreatefromstring($this->file);

        $this->ratio($image);

        $destination_image = imagecreatetruecolor($this->width, $this->height);
        imagecopyresampled($destination_image, $this->image,
            0, 0,
            $this->new_x, $this->new_y,
            $this->width, $this->height,
            $this->new_width, $this->new_height);

        //создаём правильное имя файла
        $_name = $this->name;
        $_pos = strpos($this->tmp_file, "info");
        if ($_pos > 0 and $this->width == 700)
            $_name = str_replace("info", "main", $this->name);

        //сохраняем изображение формата jpg в нужную папку
        imagejpeg($destination_image, $this->path . $_name);
    }

#####################################################################################################
    public function cleaner() {
        //удаляем оригинал загруженного изображения, он нам больше не нужен. Задачей было - получить миниатюру.
        if ($this->path . $this->name != $this->tmp_file) @unlink($this->tmp_file);
        $this->name = substr($this->name, 0, -4);
    }

#####################################################################################################
    public
    function save_file() {
        $this->target = $this->path . $this->name;
        $binary_file = fopen($this->target, 'w');
        if ($binary_file == False) {
            print 'error (binary_file)';
            exit;
        }
        rewind($binary_file);
        if (-1 == fwrite($binary_file, $this->file)) {
            print 'error (this->file)';
            fclose($binary_file);
            exit;
        }
        ftruncate($binary_file, ftell($binary_file));
        fflush($binary_file);
        fclose($binary_file);
        print 'image uploaded ok';
    }
}

class Upload extends Upload_File
{
    public function set_dimensions($width, $height) {
        $this->width = $width;
        $this->height = $height;
    }
}

?>