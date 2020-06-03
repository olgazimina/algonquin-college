<?php require_once('./dao/UserDAO.php'); ?>
<!DOCTYPE html>
<html>
<?php include("css/header.php") ?>
<div id="content" class="clearfix">
    <aside>
        <h2>Mailing Address</h2>
        <h3>
            1385 Woodroffe Ave<br> Ottawa, ON K4C1A4
        </h3>
        <h2>Phone Number</h2>
        <h3>(613)727-4723</h3>
        <h2>Fax Number</h2>
        <h3>(613)555-1212</h3>
        <h2>Email Address</h2>
        <h3>info@wpeatery.com</h3>
    </aside>
    <div class="main">
        <h1>Subscription status</h1>

        <form name="frmNewsletter" id="frmNewsletter" method="post"
              action="ContactForm.php">
            <table>
                <?php
                $userDAO = new UserDAO();
                $hasError = false;
                $messages = Array();

                if ($_POST["customerName"] == "") {
                    $messages[] = "<tr><td>Username cannot be empty!</td></tr>";
                }
                if ($_POST["phoneNumber"] == "") {
                    $messages[] = "<tr><td>Phone number cannot be empty!</td></tr>";
                } elseif (!preg_match("/^[\d-]+$/u", $_POST["phoneNumber"])) {
                    $messages[] = "<tr><td>Phone number is not correct. It should be: 123-123-1234!</td></tr>";
                }

                if ($_POST["emailAddress"] == "") {
                    $messages[] = "<tr><td>Email address cannot be empty!</td></tr>";
                } elseif (!filter_var($_POST["emailAddress"], FILTER_VALIDATE_EMAIL)) {
                    $messages[] = "<tr><td>Email is not in correct format - please check!</td></tr>";
                } else {
                    if ($userDAO->isSubscribed($_POST["emailAddress"])) {
                        $messages[] = "<tr><td>This email is already registered. No duplicates allowed!</td></tr>";
                    }
                }

                if (!array_key_exists("referral", $_POST)) {
                    $messages[] = "<tr><td>Referral cannot be empty!</td></tr>";
                }

                for ($i = 0; $i < sizeof($messages); $i++) {
                    echo $messages[$i];
                }

                if (sizeof($messages) == 0) {
                    $user = new User($_POST['customerName'], $_POST['phoneNumber'], $_POST['emailAddress'], '', $_POST['referral']);
                    $res = $userDAO->addUser($user);
                    echo '<tr><td>' . $res . '</td></tr>';
                }
                ?>
                <tr>
                    <td colspan='2'><input type='submit' name='btnSubmit'
                                           id='btnSubmit' value='Go back'>&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<!-- End Main -->
<?php include("css/footer.php") ?>
<!-- End Wrapper -->
</body>
</html>
