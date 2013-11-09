<?php
$objConnect = mysql_connect("localhost","root","");
$objDB = mysql_select_db("unit");

$teamname = $_GET["teamname"];
$IGname = $_GET["IGname"];

if ($teamname == 0){
	mysql_query("UPDATE Players SET teamID = '0' WHERE IGname = $IGname");
}
else{
	$teamNum = mysql_query("SELECT teamID FROM Teams WHERE name = $teamname");
	mysql_query("UPDATE Players SET teamID = $teamNum WHERE IGname = $IGname");
}

?>