<?php
$objConnect = mysql_connect("localhost","root","");
$objDB = mysql_select_db("knits");

$val = $_GET["q"];

$query = "";

if ($val == "a"){
	$query = "SELECT * FROM projects;";
}
else{
	$query = "SELECT * FROM projects WHERE projID = '$val';";
}
$objQuery = mysql_query($query);

$rows = array();

while ($r = mysql_fetch_assoc($objQuery)){
	$rows[] = $r;
}
print '{"knittens":';
print json_encode($rows);
print '}';

?>
