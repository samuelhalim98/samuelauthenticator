<?php
error_reporting(0);
include_once("dbconnect.php");
$location = $_POST['location'];
if (strcasecmp($location, "All") == 0){
    $sql = "SELECT * FROM AREA"; 
}else{
    $sql = "SELECT * FROM AREA WHERE LOCATION = '$location'";
}
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    $response["area"] = array();
    while ($row = $result ->fetch_assoc()){
        $arealist = array();
        $arealist[area] = $row["AREAID"];
        $arealist[name] = $row["NAME"];
        $arealist[phone] = $row["PHONE"];
        $arealist[address] = $row["ADDRESS"];
        $arealist[location] = $row["LOCATION"];
        array_push($response["area"], $arealist);
    }
    echo json_encode($response);
}else{
    echo "nodata";
}
?>