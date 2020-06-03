<?php require_once('./dao/UserDAO.php'); ?>
<?php require_once('./model/User.php'); ?>
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
        <form name="frmNewsletter" id="frmNewsletter" method="post"
              action="RemovedList.php">
            <table>
                <h1>Subscription status</h1>
                <?php
                $userDAO = new UserDAO();
                $user = $userDAO->getUser($_POST["id"]);
                $res = $userDAO->addSubscription($user);

                echo '<tr><td>' . $res . '</td></tr>';
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
