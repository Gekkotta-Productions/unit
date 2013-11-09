<?php
$objConnect = mysql_connect("localhost","root","");
$objDB = mysql_select_db("unit");

$IGname = $_GET["IGname"];
$name = $_GET["name"];
$email = $_GET["email"];
$teamname = $_GET["teamname"];

$teamQuery = "INSERT INTO Teams(name) VALUES('$teamname')";
$userQuery = "INSERT INTO Players(name, IGname, email) VALUES('$name','$IGname','$email')";

$teamObjQuery = mysql_query($teamQuery);
$userObjQuery = mysql_query($userQuery);

print json_encode($teamObjQuery);
print json_encode($userObjQuery);

?>
