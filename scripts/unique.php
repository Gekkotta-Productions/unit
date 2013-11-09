<?php
$objConnect = mysql_connect("localhost","root","");
$objDB = mysql_select_db("unit");

$f = $_GET["f"];
$q = $_GET["q"];
$table = $_GET["table"];

$userQuery = "SELECT $f FROM $table";
$result = mysql_query($userQuery);
while($r = mysql_fetch_assoc($result)){
	if(in_array($q, $r)){
		echo "false";
		exit;
	}
}
echo "true";
?>
