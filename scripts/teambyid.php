<?php

$val = $_GET["id"];

$query = "";

if ($val == "a"){
	$query = db.Teams.find();
}
else {
	$query = db.Teams.find({PlayerID: $val});
}
$objQuery = mysql_query($query);

$rows = array();

?>