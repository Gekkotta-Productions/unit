<?php
$objConnect = mysql_connect("localhost","root","");
$objDB = mysql_select_db("unit");

$teamID = $_GET["teamID"];
$highScores = "SELECT `score` FROM `TeamScores` WHERE `teamID` = '1' ORDER BY `score` DESC  LIMIT 5;";

$topScores=mysql_query($highScores);

$rows = array();

while ($r = mysql_fetch_assoc($topScores)){
	$rows[] = $r;
}

print '{"unit":';
print json_encode($rows);
print '}';

?>
