
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<head>
<style>
.hide {
  display: none;
  width: 300px;
  background-color: #cfc ;
  padding: 10px ;
  border: 1px solid green ;
}
    
.myDIV:hover + .hide {
  position: absolute;
  display: block;
  color: white;
  background-color: white;
}
</style>
</head>
<body>
<h1>Purkiss Property Rentals</h1>
<h2>Common Queries</h2>
<h2>Select a query of our data:</h2>
<table class="table">
<tr>
        <td>
        <div class="myDIV"><h3>Query 1</h3></div>
        <div class="hide">Find the social security numbers and full names of tenants who paid their first month rent in a specified month. (ex: August)</div>
        <form method="post">
        <input type="submit" name="Q1" value="Q1"></form>
        </td>


        <td>
        <div class="myDIV"><h3>Query 2</h3></div>
        <div class="hide">Find all of the tenants that have a specific type of damage charge.</div>
        <form method="post">
        <input type="submit" name="Q2" value="Q2"></form>
        </td>


        <td>
        <div class="myDIV"><h3>Query 3</h3></div>
        <div class="hide">Find the cost of a specific legal motion filed by a specific property owner.</div>
        <form method="post">
        <input type="submit" name="Q3" value="Q3"></form>
        </td>


        <td>
        <div class="myDIV"><h3>Query 4</h3></div>
        <div class="hide">Find all of the tenants that have a lease under a specific property owner.</div>
        <form method="post">
        <input type="submit" name="Q4" value="Q4"></form>
        </td>


        <td>
        <div class="myDIV"><h3>Query 5</h3></div>
        <div class="hide">FInd all employees who have worked on a property in a specific city.</div>
        <form method="post">
        <input type="submit" name="Q5" value="Q5"></form>
        </td>

</tr>
</table>
<?php
$con = mysqli_connect("db.luddy.indiana.edu","i308s22_team51","my+sql=i308s22_team51","i308s22_team51");
if(!$con){
	die("Connection failed: ".mysqli_connect_error());
}
if(array_key_exists("Q1",$_POST)) {
	echo "<table class='table'>
                <tr>
                        <form method='post'>
                                <td>
                                        <label for='selmonth'>Select Month:</label>
                                        <select name='selmonth' id='selmonth'>
                                                <option value='none' selected disabled hidden>Select an Option</option>
                                                <option value='January'>January</option>
                                                <option value='February'>February</option>
                                                <option value='March'>March</option>
                                                <option value='April'>April</option>
                                                <option value='May'>May</option>
                                                <option value='June'>June</option>
                                                <option value='July'>July</option>
                                                <option value='August'>August</option>
                                                <option value='September'>September</option>
                                                <option value='October'>October</option>
                                                <option value='November'>November</option>
                                                <option value='December'>December</option>
                                        </select>
                                </td>
                                <td>
                                        <input type='submit' name='Q1R' value='Q1R'>
                                </td>
                                <td>
                                </td>
                        </form>
                </tr>
        </table>";
}
if(array_key_exists("Q1R",$_POST)) {
        $sanselmonth = mysqli_real_escape_string($con,$_POST['selmonth']);
	echo "<table class='table'>
                <tr>
                        <form method='post'>
                                <td>
                                        <label for='selmonth'>Select Month:</label>
                                        <select name='selmonth' id='selmonth'>
                                                <option value='none' selected disabled hidden>Select an Option</option>
                                                <option value='January'>January</option>
                                                <option value='February'>February</option>
                                                <option value='March'>March</option>
                                                <option value='April'>April</option>
                                                <option value='May'>May</option>
                                                <option value='June'>June</option>
                                                <option value='July'>July</option>
                                                <option value='August'>August</option>
                                                <option value='September'>September</option>
                                                <option value='October'>October</option>
                                                <option value='November'>November</option>
                                                <option value='December'>December</option>
                                        </select>
                                </td>
                                <td>
                                        <input type='submit' name='Q1R' value='Q1R'>
                                </td>
                                <td>
                                </td>
                        </form>
                </tr>
        </table>";
        $sql = "SELECT Tenant.ssn, CONCAT(Tenant.fName,' ',Tenant.lName) AS Name, SUM(r.cost) AS CumulativePayment
                FROM lease
                JOIN Tenant ON lease.tID = Tenant.tenantID
                JOIN RentReceipt AS r ON r.tenantID=Tenant.tenantID
                WHERE monthname(lease.startDate) = '".$sanselmonth."'
                GROUP BY Tenant.ssn;";
        if (mysqli_query($con,$sql)){
                $result = mysqli_query($con,$sql);
                echo "<table class='table'>";
                echo "<tr><th>ssn</th><th>Name</th><th>CumulativePayment</th></tr>";
                while($row = $result->fetch_assoc()){
                        echo "<tr><td>".$row["ssn"] . "</td>" . "<td>".$row["Name"] . "<td>".$row["CumulativePayment"]."</td>"."</td></tr>";
                }
                echo "</table>";
        }
}
if(array_key_exists("Q2",$_POST)) {
        echo "<table class='table'>
                <tr>
                        <form method='post'>
                                <td>
                                        <label for='seldamage'>Select Damage Type:</label>
                                        <select name='seldamage' id='seldamage'>
                                        <option value='none' selected disabled hidden>Select an Option</option>";
                                        $result = mysqli_query($con, "SELECT distinct damageType FROM DamageCharge;");
                                        while ($row = mysqli_fetch_assoc($result)){
                                            $id = $row['damageType'];
                                            $name =$row['damageType'];
                                            echo "<option value=" . $id." "  ."name=". $name. ">".$name."</option>";
                                        }
        echo
                                        "</select>
                                </td>
                                <td>
                                        <input type='submit' name='Q2R' value='Q2R'>
                                </td>
                                <td>
                                </td>
                        </form>
                </tr>
        </table>";
}
if(array_key_exists("Q2R",$_POST)) {
        $sanseldamage = mysqli_real_escape_string($con,$_POST['seldamage']);
        echo "<table class='table'>
                <tr>
                        <form method='post'>
                                <td>
                                        <label for='seldamage'>Select Damage Type:</label>
                                        <select name='seldamage' id='seldamage'>
                                        <option value='none' selected disabled hidden>Select an Option</option>";
                                        $result = mysqli_query($con, "SELECT distinct damageType FROM DamageCharge;");
                                        while ($row = mysqli_fetch_assoc($result)){
                                        $id = $row['damageType'];
                                        $name =$row['damageType'];
                                        echo "<option value=" . $id." "  ."name=". $name. ">".$name."</option>";
                                        }
        echo
                                        "</select>
                                </td>
                                <td>
                                        <input type='submit' name='Q2R' value='Q2R'>
                                </td>
                                <td>
                                </td>
                        </form>
                </tr>
        </table>";
        $sql = "SELECT CONCAT(t.fName, ' ',t.lName) AS NAME, t.ssn, COUNT(p.propID) AS Count
                FROM Tenant AS t
                JOIN lease AS l ON l.tID=t.tenantID
                JOIN Property AS p ON p.propID=l.pID
                WHERE p.propID IN (SELECT r.propID
                FROM RentReceipt AS r
                JOIN DamageCharge AS d ON d.chargeID=r.chargeID
                WHERE damageType='".$sanseldamage."')
                GROUP BY p.propID;";
        if (mysqli_query($con,$sql)){
                $result = mysqli_query($con,$sql);
                echo "<table class='table'>";
                echo "<tr><th>NAME</th><th>ssn</th></tr>";
                while($row = $result->fetch_assoc()){
                        echo "<tr><td>".$row["NAME"] . "</td>" . "<td>".$row["ssn"]."</td>"."<td>"."</td></tr>";
                }
                echo "</table>";
        }
}


if(array_key_exists("Q3",$_POST)) {
        echo "<table class='table'>
                <tr>
                        <form method='post'>
                                <td>
                                        <label for='selmotion'>Select Motion Type:</label>
                                        <select name='selmotion' id='selmotion'>
                                        <option value='none' selected disabled hidden>Select an Option</option>";
                                        $result = mysqli_query($con, "SELECT distinct motionType FROM LegalMotion;");
                                        while ($row = mysqli_fetch_assoc($result)){
                                            $id = $row['motionType'];
                                            $name =$row['motionType'];
                                            echo "<option value=" . $id." "  ."name=". $name. ">".$name."</option>";
                                        }
        echo
                                        "</select>
                                </td>
                                <td>
                                        <input type='submit' name='Q3R' value='Q3R'>
                                </td>
                                <td>
                                </td>
                        </form>
                </tr>
        </table>";
}
if(array_key_exists("Q3R",$_POST)) {
        $sanselmotion = mysqli_real_escape_string($con,$_POST['selmotion']);
        echo "<table class='table'>
                <tr>
                        <form method='post'>
                                <td>
                                        <label for='selmotion'>Select Motion Type:</label>
                                        <select name='selmotion' id='selmotion'>
                                        <option value='none' selected disabled hidden>Select an Option</option>";
                                        $result = mysqli_query($con, "SELECT distinct motionType FROM LegalMotion;");
                                        while ($row = mysqli_fetch_assoc($result)){
                                        $id = $row['motionType'];
                                        $name =$row['motionType'];
                                        echo "<option value=" . $id." "  ."name=". $name. ">".$name."</option>";
                                        }
        echo
                                        "</select>
                                </td>
                                <td>
                                        <input type='submit' name='Q3R' value='Q3R'>
                                </td>
                                <td>
                                </td>
                        </form>
                </tr>
        </table>";
        $sql = "SELECT CONCAT(po.fName, ' ', po.lName) AS NAME, po.ssn, lm.cost
                FROM PropertyOwner AS po
                JOIN LegalMotion AS lm ON lm.propertyOwnerID=po.propertyOwnerID
                WHERE motionType='".$sanselmotion."';";
        if (mysqli_query($con,$sql)){
                $result = mysqli_query($con,$sql);
                echo "<table class='table'>";
                echo "<tr><th>NAME</th><th>ssn</th><th>cost</th></tr>";
                while($row = $result->fetch_assoc()){
                        echo "<tr><td>".$row["NAME"] . "</td>" . "<td>".$row["ssn"]."</td>"."<td>".$row["cost"] . "</td></tr>";
                }
                echo "</table>";
        }
}








if(array_key_exists("Q4",$_POST)) {
        echo "<table class='table'>
                <tr>
                        <form method='post'>
                                <td>
                                        <label for='selowner'>Select Property Owner:</label>
                                        <select name='selowner' id='selowner'>
                                        <option value='none' selected disabled hidden>Select an Option</option>";
                                        $result = mysqli_query($con, "SELECT distinct CONCAT(fName,' ',mName,' ',lName) AS NAME,propertyOwnerID AS ID FROM PropertyOwner;");
                                        while ($row = mysqli_fetch_assoc($result)){
                                            $id = $row['ID'];
                                            $name =$row['NAME'];
                                            echo "<option value=" . $id." "  ."name=". $name. ">".$name."</option>";
                                        }
        echo
                                        "</select>
                                </td>
                                <td>
                                        <input type='submit' name='Q4R' value='Q4R'>
                                </td>
                                <td>
                                </td>
                        </form>
                </tr>
        </table>";
}
if(array_key_exists("Q4R",$_POST)) {
        $sanselowner = mysqli_real_escape_string($con,$_POST['selowner']);
        echo "<table class='table'>
                <tr>
                        <form method='post'>
                                <td>
                                        <label for='selowner'>Select Property Owner:</label>
                                        <select name='selowner' id='selowner'>
                                        <option value='none' selected disabled hidden>Select an Option</option>";
                                        $result = mysqli_query($con, "SELECT distinct CONCAT(fName,' ',mName,' ',lName) AS NAME,propertyOwnerID AS ID FROM PropertyOwner;");
                                        while ($row = mysqli_fetch_assoc($result)){
                                            $id = $row['ID'];
                                            $name =$row['NAME'];
                                            echo "<option value=" . $id." "  ."name=". $name. ">".$name."</option>";
                                        }
        echo
                                        "</select>
                                </td>
                                <td>
                                        <input type='submit' name='Q4R' value='Q4R'>
                                </td>
                                <td>
                                </td>
                        </form>
                </tr>
        </table>";
        $sql = "SELECT CONCAT(PropertyOwner.fName,' ',PropertyOwner.lName) AS Owner, GROUP_CONCAT(CONCAT(Tenant.fName,' ',Tenant.lName)) AS Tenants
                FROM lease
                JOIN Tenant ON Tenant.tenantID = lease.tID
                JOIN Property ON lease.pID = Property.propID
                JOIN OwnerPayment ON Property.propID = OwnerPayment.propertyID
                JOIN PropertyOwner ON PropertyOwner.PropertyOwnerID = OwnerPayment.PropertyOwnerID
                WHERE PropertyOwner.propertyOwnerID = '".$sanselowner."';";
        if (mysqli_query($con,$sql)){
                $result = mysqli_query($con,$sql);
                echo "<table class='table'>";
                echo "<tr><th>Owner</th><th>Tenants</th></tr>";
                while($row = $result->fetch_assoc()){
                        echo "<tr><td>".$row["Owner"] . "</td>"."<td>".$row["Tenants"] . "</td></tr>";
                }
                echo "</table>";
        }
}





if(array_key_exists("Q5",$_POST)) {
        echo "<table class='table'>
                <tr>
                        <form method='post'>
                                <td>
                                        <label for='selcity'>Select City:</label>
                                        <select name='selcity' id='selcity'>
                                        <option value='none' selected disabled hidden>Select an Option</option>";
                                        $result = mysqli_query($con, "SELECT distinct city FROM Property;");
                                        while ($row = mysqli_fetch_assoc($result)){
                                            $id = $row['city'];
                                            $name =$row['city'];
                                            echo "<option value=" . $id." "  ."name=". $name. ">".$name."</option>";
                                        }
        echo
                                        "</select>
                                </td>
                                <td>
                                        <input type='submit' name='Q5R' value='Q5R'>
                                </td>
                                <td>
                                </td>
                        </form>
                </tr>
        </table>";
}
if(array_key_exists("Q5R",$_POST)) {
        $sanselcity = mysqli_real_escape_string($con,$_POST['selcity']);
                echo "<table class='table'>
                <tr>
                        <form method='post'>
                                <td>
                                        <label for='selcity'>Select City:</label>
                                        <select name='selcity' id='selcity'>
                                        <option value='none' selected disabled hidden>Select an Option</option>";
                                        $result = mysqli_query($con, "SELECT distinct city FROM Property;");
                                        while ($row = mysqli_fetch_assoc($result)){
                                            $id = $row['city'];
                                            $name =$row['city'];
                                            echo "<option value=" . $id." "  ."name=". $name. ">".$name."</option>";
                                        }
        echo
                                        "</select>
                                </td>
                                <td>
                                        <input type='submit' name='Q5R' value='Q5R'>
                                </td>
                                <td>
                                </td>
                        </form>
                </tr>
        </table>";
        $sql = "SELECT Property.city AS city, GROUP_CONCAT(CONCAT(employee.fName,' ',employee.lName)) AS name
                FROM MaintenanceRecord
                JOIN employee ON employee.employeeID = MaintenanceRecord.employeeID
                JOIN Property ON Property.propID = MaintenanceRecord.propID
                WHERE Property.city = '".$sanselcity."';";
        if (mysqli_query($con,$sql)){
                $result = mysqli_query($con,$sql);
                echo "<table class='table'>";
                echo "<tr><th>city</th><th>name</th></tr>";
                while($row = $result->fetch_assoc()){
                        echo "<tr><td>".$row["city"] . "</td>"."<td>".$row["name"] . "</td></tr>";
                }
                echo "</table>";
        }
}
?>
</body>
</html>