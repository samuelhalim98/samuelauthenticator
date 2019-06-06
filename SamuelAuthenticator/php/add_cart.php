<?php
error_reporting(0);
include_once("dbconnect.php");
$username = $_POST['username'];
$userid = $_POST['userid'];
$code = $_POST['code'];
$status = "not complete";

    $sqlinsert = "INSERT INTO CART(SONGID,USERID,SONGTITLE,STATUS) VALUES ('$songid','$userid','$songtitle','$status')";
    if ($conn->query($sqlinsert) === TRUE){
       echo "success";
    }else {
        echo "failed";
    }

?>