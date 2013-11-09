<?php
$objConnect = new MongoClient("mongodb://localhost");
$db = "Server";
$objDB = $objConnect->$db;
$collection = $db->"Players";

$val = $_GET["id"];

$query = "";

if ($val == "a"){
	$query = $collection->find();
}
else {
	$query = $collection->find({"playerID": $val});
}
print $query;

?>
