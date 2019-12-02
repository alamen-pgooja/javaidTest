<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "company";
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}


$values=array();
$values = $_POST['values'];

// values POSTed as json object string 
$decodedText = html_entity_decode($values);
$empArray = json_decode($values, true);


$size=sizeof($empArray);

// loop over json object
for($i=0;$i<$size;$i++){
    
    // insert query
    $sql = "INSERT INTO employee_copy (
        employee_id, 
        employee_name, 
        employee_national_id_no,
        employee_photograph)

    VALUES ($empArray[$i]['employee_id'],
            $empArray[$i]['employee_name'],
            $empArray[$i]['employee_national_id_no'],
            $empArray[$i]['employee_photograph'])";
        
        if ($conn->query($sql) === TRUE) {
            echo "New record created successfully";
        }  else {
            echo "Error: " . $sql . "<br>" . $conn->error; 
        }

}

?>