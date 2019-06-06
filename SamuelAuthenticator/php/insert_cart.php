<?php
error_reporting(0);
include_once("dbconnect.php");
$authenticatorcode = $_POST['AuthenticatorCode'];
$userid = $_POST['UserId'];
$authenticatorapp = $_POST['AuthenticatorAPP'];

    

if ($authenticatorcode>0){
    $sqlgetid = "SELECT * FROM USER WHERE USERID = '$userid'";
    $result = $conn->query($sqlgetid);
    $sqlupdate = "UPDATE AUTHENTICATOR SET AUTHENTICATORCODE = '$Authenticatorcode' WHERE AUTHENTICATORAPP = '$authenticatorapp'";
        $conn->query($sqlupdate);
        
if ($result->num_rows > 0) {
    while ($row = $result ->fetch_assoc()){
        $orderid = $row["ORDERID"];
    }
     $sqlinsert = "INSERT INTO CART(UserID,Username,Email,Password) VALUES ('$UserID','$Username','$Email','$Password','$foodname','$status','$restid','$orderid')";
     
    if ($conn->query($sqlinsert) === TRUE){
       echo "success";
    }
}
}



function generateRandomString($length = 7) {
    $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    $charactersLength = strlen($characters);
    $randomString = '';
    for ($i = 0; $i < $length; $i++) {
        $randomString .= $characters[rand(0, $charactersLength - 1)];
    }
    return date('dmY')."-".$randomString;
}

?>