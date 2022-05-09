/* 1. Find the social security numbers and full names of tenants who paid their 
first month rent in a specified month. (ex: August).*/
SELECT Tenant.ssn, CONCAT(Tenant.fName,' ',Tenant.lName) AS Name, SUM(r.cost) AS 
CumulativePayment
FROM lease
JOIN Tenant ON lease.tID = Tenant.tenantID
JOIN RentReceipt AS r ON r.tenantID=Tenant.tenantID
WHERE monthname(lease.startDate) = 'September'
GROUP BY Tenant.ssn;
/*
2. Find all of the tenants that have a specific type of damage charge.
*/
SELECT CONCAT(t.fName, " ",t.lName) AS NAME, t.ssn
FROM Tenant AS t
JOIN lease AS l ON l.tID=t.tenantID
JOIN Property AS p ON p.propID=l.pID
WHERE p.propID IN (SELECT r.propID
FROM RentReceipt AS r
JOIN DamageCharge AS d ON d.chargeID=r.chargeID
WHERE damageType='plumbing');
/*3. Find the cost of a specific legal motion filed by a specific property owner.*/
SELECT CONCAT(po.fName, " ", po.lName) AS NAME, po.ssn, lm.cost
FROM PropertyOwner AS po
JOIN LegalMotion AS lm ON lm.propertyOwnerID=po.propertyOwnerID
WHERE motionType='Emergency';
/*4. Find all of the tenants that have a lease under a specific property owner.*/
SELECT CONCAT(PropertyOwner.fName," ",PropertyOwner.lName) AS Owner, 
GROUP_CONCAT(CONCAT(Tenant.fName,' ',Tenant.lName)) AS Tenants
FROM lease
JOIN Tenant ON Tenant.tenantID = lease.tID
JOIN Property ON lease.pID = Property.propID
JOIN OwnerPayment ON Property.propID = OwnerPayment.propertyID
JOIN PropertyOwner ON PropertyOwner.PropertyOwnerID = OwnerPayment.PropertyOwnerID
WHERE PropertyOwner.fName = 'Doug';
/*5. FInd all employees who have worked on a property in a specific city.*/
SELECT Property.city AS city, GROUP_CONCAT(CONCAT(employee.fName,' 
',employee.lName)) AS name
FROM MaintenanceRecord
JOIN employee ON employee.employeeID = MaintenanceRecord.employeeID
JOIN Property ON Property.propID = MaintenanceRecord.propID
WHERE Property.city LIKE 'Miami';
