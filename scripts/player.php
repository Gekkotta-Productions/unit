<?php
$objConnect = new MongoClient("mongodb://$root:$pass@172.26.13.13");
$objDB = $objConnect -> selectDB('Server');

$val = $_GET["id"];

$query = "";

if ($val == "a"){
	$query = db.Server.find();
}
else {
	$query = db.Server.find({PlayerID: $val});
}
print $query;

?>