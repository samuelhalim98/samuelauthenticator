<?php
error_reporting(0);
include_once("dbconnect.php");
$email = $_POST['email'];
$password = ($_POST['password']);

$sql = "SELECT * FROM USER WHERE EMAIL = '$email' AND PASSWORD = '$password'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    while ($row = $result ->fetch_assoc()){
        echo $data = $row["NAME"].",".$row["PHONE"];
    }
    }
else{
    echo "failed";
}

?>