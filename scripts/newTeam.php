<?php
$objConnect = mysql_connect("localhost","root","");
$objDB = mysql_select_db("unit");

$teamname = $_GET["teamname"];
$IGname = $_GET["IGname"];
$teamQuery = "SELECT name FROM Teams";
$result = mysql_query($teamQuery);
while($r=mysql_fetch_assoc($result)){
	$uniqueTeam=  in_array($teamname, $r);
		if($uniqueTeam == true){
		print FALSE;
	exit;
	}
}

$teamInput = "INSERT INTO Teams(name) VALUES('$teamname')";

$teamObjQuery = mysql_query($teamInput);
$teamNumQuery = "SELECT LAST(teamID) FROM Teams";
$teamNum = mysql_query($teamNumQuery);
mysql_query("UPDATE Players SET teamID = $teamNum WHERE IGname = $IGname");
print true;
?>