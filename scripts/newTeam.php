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
		print "FALSE";
	exit;
	}
}

$teamInput = "INSERT INTO Teams(name) VALUES('$teamname');";
$teamObjQuery = mysql_query($teamInput);

$teamNumQuery = "SELECT `teamID` FROM Teams ORDER BY `teamID` DESC LIMIT 1;";
$teamNum = mysql_query($teamNumQuery);
$res = mysql_fetch_assoc($teamNum);

$query = "UPDATE Players SET `teamID` = $res[teamID] WHERE `IGname` = '$IGname';";
mysql_query($query);
print "true";
?>
