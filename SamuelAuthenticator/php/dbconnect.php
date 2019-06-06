<?php
$servername = "localhost";
$username   = "id9170894_samuelauthenticator";
$password   = "Eskopi77";
$dbname     = "id9170894_authenticator";

$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>