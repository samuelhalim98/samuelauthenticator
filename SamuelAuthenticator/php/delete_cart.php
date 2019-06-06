<?php
error_reporting(0);
include_once("dbconnect.php");
$UserId = $_POST['UserId'];
$AuthenticatorCode = $_POST['AuthenticatorCode'];
    $sqldelete = "DELETE FROM CART WHERE USERID = '$userid' AND AuthenticatorCode='$AuthenticatorCode";
    if ($conn->query($sqldelete) === TRUE){
       echo "success";
    }else {
        echo "failed";
    }
?>