<?php
$objConnect = mysql_connect("localhost","root","");
$objDB = mysql_select_db("unit");

$teamname = $_GET["teamname"];
$query = "INSERT INTO Teams("name") VALUES($teamname)";
$objQuery = mysql_query($query);
print '{"unit":';
print json_encode($objQuery);
print '}';


/*
if ($id == "a"){
	$query = "SELECT * FROM Players";
}
else {
	$query = "SELECT * FROM Players WHERE `playerID` = $id";
}

$objQuery = mysql_query($query);

$rows = array();

while ($r = mysql_fetch_assoc($objQuery)){
	$rows[] = $r;
}

print '{"unit":';
print json_encode($rows);
print '}';
*/
?>
