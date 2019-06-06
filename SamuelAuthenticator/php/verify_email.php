<?php
    //ini_set( 'display_errors', 1 );
    error_reporting(0);
    include_once("dbconnect.php");
    $email = $_POST['email'];
    $sql = "SELECT * FROM USER WHERE EMAIL = '$email'";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
         while ($row = $result ->fetch_assoc()){
             $ran = $row["PASSWORD"];
         }
        $from = "noreply@samuelauthenticator.com";
        $to = $email;
        $subject = "From Samuel Authenticator. Reset your password";
        $message = "Use the following link to reset your password :"."\n http://uumresearch.com/foodninja/php/reset_password.php?email=".$email."&key=".$ran;
        $headers = "From:" . $from  . "\r\n" .
                    "CC: samuelhalim@uum.edu.my";;
        mail($email,$subject,$message, $headers);
        echo "success";
    }else{
        echo "failed";
    }
    
    
?>
