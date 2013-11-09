<?php
$objConnect = mysql_connect("localhost","root","");
$objDB = mysql_select_db("unit");

$IGname = $_GET["IGname"];
$name = $_GET["name"];
$email = $_GET["email"];

$userQuery = "SELECT IGname, email FROM Players";
$result = mysql_query($userQuery);
while($r=mysql_fetch_assoc($result)){
	$uniqueName = in_array($IGname, $r);
	$uniqueEmail = in_array($email, $r);
	if($uniqueName || $uniqueEmail){
		print false;
		exit;
	}
}

$userInput = "INSERT INTO Players(name, IGname, email) VALUES('$name','$IGname','$email')";

mysql_query($userInput);
print true;
?>
