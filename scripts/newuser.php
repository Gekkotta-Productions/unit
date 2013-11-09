<?php
$objConnect = mysql_connect("localhost","root","");
$objDB = mysql_select_db("unit");

$IGname = $_GET["IGname"];
$name = $_GET["name"];
$email = $_GET["email"];

$userQuery = "SELECT IGname, email FROM Players";
$result = mysqul_query($userQuery);
while($r=mysql_fetch_assoc($result)){
	$uniqueName= bool in_array($IGname, $r);
	$uniqueEmail= bool in_array($email, $r);
	if($uniqueName == FALSE || $uniqueEmail ==FALSE){
	print FALSE;
	exit
	}
}

$userInput = "INSERT INTO Players(name, IGname, email) VALUES('$name','$IGname','$email')";

mysql_query($userQuery);
?>
