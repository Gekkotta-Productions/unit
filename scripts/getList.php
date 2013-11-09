<?php
$objConnect = new MongoClient("mongodb://$root:$pass@172.26.13.13");
$objDB = $objConnect -> selectDB('Server');

$val = $_GET["q"];

$query = "";

if ($val == "a"){
	$query = "SELECT * FROM projects;";
}
else{
	$query = "SELECT * FROM projects WHERE projID = '$val';";
}
$objQuery = mysql_query($query);

$rows = array();

while ($r = mysql_fetch_assoc($objQuery)){
	$rows[] = $r;
}
print '{"knittens":';
print json_encode($rows);
print '}';

?>
