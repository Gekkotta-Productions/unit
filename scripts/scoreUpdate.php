<?php

$con = mysql_connect("localhost","root","");
$db = mysql_select_db("unit");

$IGname = $_GET["IGname"];
$score = $_GET["score"];

$query = "UPDATE Players SET `score` = '$score' WHERE `IGname` = '$IGname';";
echo $query;
mysql_query($query);
?>
