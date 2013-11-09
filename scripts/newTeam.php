<?php
$objConnect = mysql_connect("localhost","root","");
$objDB = mysql_select_db("unit");

$teamname = $_GET["teamname"];

$teamQuery = "SELECT name FROM Teams";

$teamInput = "INSERT INTO Teams(name) VALUES('$teamname')";

$teamObjQuery = mysql_query($teamQuery);

print json_encode($teamObjQuery);
?>