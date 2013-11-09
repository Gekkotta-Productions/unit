<?php
$objConnect = mysql_connect("localhost","root","");
$objDB = mysql_select_db("unit");

$teamname = $_GET["teamname"];

$teamQuery = "SELECT name FROM Teams";
$result = mysql_query($teamQuery);
while($r=mysql_fetch_assoc($result)){
	$uniqueTeam=  in_array($teamname, $r);
	if($uniqueTeam == true){
	print FALSE;
	exit
	}
}

$teamInput = "INSERT INTO Teams(name) VALUES('$teamname')";

$teamObjQuery = mysql_query($teamInput);

print json_encode($teamObjQuery);
?>