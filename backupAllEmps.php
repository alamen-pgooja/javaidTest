<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "company";
 //this script well run every 5 h

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// calling methods: 
// firest chick the Integrity of two taboles
chickIntegrity($conn);
// chick the Integrity of two taboles****************************************************************************
function chickIntegrity($conn){
    if(getAllEmps($conn)==getAllEmpCopys($conn)){
        print("no new data to backup..");
    }else{
        backUp($conn);
    }
}
//****get All employees and return the number of rows so we can chick it leater for any update in the datebase */
function getAllEmps($conn){
    $sql = "SELECT * FROM employee";
$result = $conn->query($sql);

$emp =array ();
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        array_push($emp,$row);
        
    }
} else {
    echo "0 results";
}
// if we wont to print the data
// print_r(json_encode($emp));
return $result->num_rows;
}
//*get All employees_copy and return the number of rows so we can chick it leater for any update in the datebase */

function getAllEmpCopys($conn){
    $sql = "SELECT * FROM employee_copy";
$result = $conn->query($sql);

$emp =array ();
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        array_push($emp,$row); 
    }
} else {
    echo "0 results";
}
// if we wont to print the data
// print_r(json_encode($emp));
return $result->num_rows;
}
// if we need to print out the number of rows
// print( getAllEmps($conn) );
// print( getAllEmpCopys($conn) );

function backUp($serverConnection){
    $lastCopyedID=getAllEmpCopys($serverConnection) + 1;
    $curranteLastID=getAllEmps($serverConnection) + 1;
    // print($lastCopyedID);
    // print($curranteLastID);
    for($i=$lastCopyedID; $i<=$curranteLastID; $i++){

        $qurey="INSERT INTO employee_copy
        SELECT * FROM employee WHERE employee_id = $i";
        $result = $serverConnection->query($qurey) ;
        print("<br/>employe data with id: ".$i." bcakuped...");
    }
}

$conn->close();
?>