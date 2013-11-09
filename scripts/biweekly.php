<?php

$date = time();
$objConnect = mysql_connect("localhost","root","");
$objDB = mysql_select_db("unit");

$query = "SELECT * FROM Teams";
$result = mysql_query($query);

while ($r = mysql_fetch_assoc($result)){
	$query ="SELECT SUM(score) AS sum FROM Players WHERE `teamID`= $r[teamID];";
	$return = mysql_query($query);
	$val = mysql_fetch_assoc($return);
	$query = "INSERT INTO TeamScores (`teamID`,`timeStamp`,`score`) VALUES ('$r[teamID]', '$date', '$val[sum]');";
	mysql_query($query);
}

$query = "SELECT * FROM Players";
$result = mysql_query($query);

while ($r = mysql_fetch_assoc($result)){
	$query = "INSERT INTO Scores (playerID,timeStamp,score) VALUES ('$r[playerID]','$date','$r[score]');";
	mysql_query($query);
	$query = "UPDATE Players SET score=0 WHERE `playerID` = $r[playerID];";
	mysql_query($query);
}
?>
