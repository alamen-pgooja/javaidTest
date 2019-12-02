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
$myArray = json_decode($values, true);


$size=sizeof($myArray);

// loop over json object
for($i=0;$i<$size;$i++){
    print_r($myArray[$i]) ;
    $columns = implode(", ",array_keys($myArray[$i]));
    print_r($columns);
    // insert query
    $sql = "INSERT INTO employeeassignments_copy (
        employee_id, 
        assignment_name, 
        assignment_description,
        'start_date',
        end_date) VALUES ($myArray[$i]=>['employee_id'],
            $myArray[$i]['employee_name'],
            $myArray[$i]['employee_national_id_no'],
            $myArray[$i]['employee_photograph'])";
        
        if ($conn->query($sql) === TRUE) {
            echo "copied successfully";
        }  else {
            echo "Error: " . $sql . "<br>" . $conn->error; 
        }
}

?>