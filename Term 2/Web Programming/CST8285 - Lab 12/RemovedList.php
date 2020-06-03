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
        <h1>Unsubscribed users list</h1>
        <form name="frmNewsletter" id="frmNewsletter" method="post"
              action="SubscribeUser.php">
            <table>
                <?php
                $userDao = new UserDAO();
                $users = $userDao->getUnsubscribedUsers();

                if ($users) {
                    echo "<p>User who decided to unsubscribe from our mailing list</p>";
                    echo('<tr>
                    <th>Name:</th>
                    <th>Phone Number:</th>
                    <th>Email:</th>
                    <th>Heard from:</th>
                    <th>Subscribe?</th>
                    </tr>
                ');
                    foreach ($users as $user) {
                        echo('<tr><form name="frmNewsletter" id="frmNewsletter" method="post" action="SubscribeUser.php">
                            <td>' . $user->getUsername() . '</td>
                            <td>' . $user->getPhoneNumber() . '</td>
                            <td><input type="text" name="id" id="id" hidden=true value="' . $user->getId() . '">' . $user->getEmailAddress() . '</td>
                            <td>' . $user->getReferrer() . '</td>
                            <td><input type="submit" name="btnSubmit" id="btnSubmit" value="Subscribe"></td>
                            </form></tr>
                ');
                    }
                } else {
                    echo "<p>All existing users are subscribed. Congratulations!</p>";
                }
                ?>
            </table>
        </form>
    </div>
</div>
<!-- End Main -->
<?php include("css/footer.php") ?>
<!-- End Wrapper -->

</html>
