DROP TABLE IF EXISTS MaintenanceRecord CASCADE; DROP TABLE IF EXISTS LegalMotion 
CASCADE;
DROP TABLE IF EXISTS OwnerPayment CASCADE; DROP TABLE IF EXISTS employee_phone 
CASCADE; DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS RentReceipt CASCADE;
DROP TABLE IF EXISTS Lease_PetType CASCADE;
DROP TABLE IF EXISTS lease CASCADE;
DROP TABLE IF EXISTS PropertyOwner_Phone CASCADE; DROP TABLE IF EXISTS Tenant_Phone
CASCADE;
DROP TABLE IF EXISTS Tenant CASCADE;
DROP TABLE IF EXISTS Property CASCADE;
DROP TABLE IF EXISTS PropertyOwner CASCADE;
DROP TABLE IF EXISTS DamageCharge CASCADE;
CREATE TABLE PropertyOwner( propertyOwnerID INT AUTO_INCREMENT, ssn CHAR(11) NOT 
NULL,
fName VARCHAR(30) NOT NULL,
mName VARCHAR(30),
lName VARCHAR(30) NOT NULL, PRIMARY KEY (propertyOwnerID)
) ENGINE = innodb;
CREATE TABLE PropertyOwner_Phone( poid INT,
phone VARCHAR(15),
type VARCHAR(15),
FOREIGN KEY (poid) REFERENCES PropertyOwner(propertyOwnerID)
)ENGINE=innodb;
CREATE TABLE Property( propID INT auto_increment, street VARCHAR(50),
city VARCHAR(50),
state CHAR(2),
zip VARCHAR(10), residenceType VARCHAR(30), fullBathCount INT, halfBathCount INT, 
bedroomCount INT, amentityDesc VARCHAR(1000), PRIMARY KEY (propID) )ENGINE=innodb;
CREATE TABLE Tenant(
tenantID INT auto_increment,
ssn CHAR(11) NOT NULL,
fName VARCHAR(30) NOT NULL,
mName VARCHAR(30),
lName VARCHAR(30) NOT NULL, backgroundCheck VARCHAR(5) NOT NULL, 
backgroundCheckDesc VARCHAR(1000), PRIMARY KEY (tenantID)
) ENGINE = innodb;
CREATE TABLE Tenant_Phone( tenID INT,
phone VARCHAR(15),
type VARCHAR(15),
FOREIGN KEY (tenid) REFERENCES Tenant (tenantID)
) ENGINE = innodb;
CREATE TABLE lease (
leaseID INT AUTO_INCREMENT,
pID INT,
tID INT,
startDate DATE,
endDate DATE,
rentCost INT,
FOREIGN KEY (pID) REFERENCES Property(propID), FOREIGN KEY(tID) REFERENCES 
Tenant(tenantID), PRIMARY KEY (leaseID)
)ENGINE=INNODB;
CREATE TABLE employee (
employeeID INT AUTO_INCREMENT PRIMARY KEY, fName VARCHAR(30),
lName VARCHAR(30),
email VARCHAR(50),
company VARCHAR(100),
position VARCHAR(100)
) ENGINE = INNODB;
CREATE TABLE employee_phone ( employeeID INT,
number VARCHAR(15),
type VARCHAR(30),
FOREIGN KEY (employeeID) REFERENCES employee(employeeID)
)ENGINE = INNODB;
CREATE TABLE MaintenanceRecord (
maintenanceType VARCHAR(90),
cost DECIMAL(10,2),
date DATE,
employeeID INT,
propID INT,
FOREIGN KEY (employeeID) REFERENCES employee(employeeID),
FOREIGN KEY (propID) REFERENCES Property(propID)
) ENGINE = INNODB;
CREATE TABLE Lease_PetType(
leaseID INT,
petType VARCHAR(30),
FOREIGN KEY (leaseID) REFERENCES lease(leaseID) ) ENGINE = INNODB;
CREATE TABLE DamageCharge( chargeID INT auto_increment, damageType VARCHAR(150), 
cost DECIMAL(10,2), damageDate DATE,
PRIMARY KEY (chargeID)
) ENGINE = INNODB;
CREATE TABLE RentReceipt(
financialType VARCHAR(150) NOT NULL,
date DATE,
cost DECIMAL(10,2),
tenantID INT,
propID INT,
chargeID INT,
FOREIGN KEY (tenantID) REFERENCES Tenant(tenantID),
FOREIGN KEY (propID) REFERENCES Property(propID),
FOREIGN KEY (chargeID) REFERENCES DamageCharge(chargeID)
) ENGINE = INNODB;
CREATE TABLE LegalMotion(
motionType VARCHAR(150),
dateFiled DATE,
cost DECIMAL(10,2),
tenantID INT,
propertyOwnerID INT,
FOREIGN KEY (tenantID) REFERENCES Tenant(tenantID),
FOREIGN KEY (propertyOwnerID) REFERENCES PropertyOwner(propertyOwnerID)
) ENGINE = INNODB;
CREATE TABLE OwnerPayment(
receiptNumber VARCHAR(100),
date DATE,
amount INT,
propertyOwnerID INT,
propertyID INT,
FOREIGN KEY (propertyOwnerID) REFERENCES PropertyOwner(propertyOwnerID),
FOREIGN KEY (propertyID) REFERENCES Property(propID)
) ENGINE = INNODB;
INSERT INTO PropertyOwner (propertyOwnerID, ssn, fName, mName, lName) VALUES (1, 
"263-45-1633", "Raven", "Terrell", "Glancey"),
(2, "838-47-5085", "Grete", "Paco", "Sydney"),
(3, "879-32-1144", "Normy", "Jameson", "Iacopetti"),
(4, "648-76-8051", "Kaia", "Edvard", "Croxley"),
(5, "434-29-8410", "Matilda", "Selma", "Renachowski"), (6, "706-40-8154", "Bendix",
"Jaquenetta", "Bucknell"), (7, "893-21-4432", "Berkie", "Maurizia", "Colbron"),
(8, "889-14-6620", "Hendrik", "Trix", "Hardaway"),
(9, "693-23-6605", "Delphine", "Britta", "Deverille"), (10, "863-75-9342", "Doug", 
"Sheppard", "Bignold"),
(11, "708-43-4790", "Bond", "Barbaraanne", "O'Hoolahan"), (12, "623-05-4558", 
"Atlante", "Garland", "Satterfitt"), (13, "119-66-0952", "Farlay", "Laural", 
"Moquin"),
(14, "370-03-1439", "Chicky", "Godiva", "Gouldbourn"), (15, "545-66-4910", "Pace", 
"Charyl", "Priddie"),
(16, "361-99-3714", "Bart", "Gerri", "Baldazzi"),
(17, "544-60-4497", "Keelby", "Lorita", "Thrower"),
(18, "765-89-7213", "Meredith", "Rakel", "Newstead"), (19, "377-42-3353", 
"Isadora", "Scotti", "Whetson"), (20, "157-09-4490", "Sayers", "Gwenneth", 
"Gepson");
INSERT INTO Property (propID, street, city, state, zip, residenceType,
fullBathCount, halfBathCount, bedroomCount) VALUES
(1, "7355 Cordelia Drive", "Lansing", "MI", "48930", "Suburb", 3, 1, 5),
(2, "13 Spaight Crossing", "Charlotte", "NC", "28242", "Suburb", 5, 1, 9),
(3, "892 Becker Terrace", "Columbia", "SC", "29225", "Urban", 2, 1, 5),
(4, "327 Victoria Crossing", "Tacoma", "WA", "98464", "Urban", 1, 2, 4),
(5, "747 Declaration Junction", "Washington", "DC", "20244", "Urban", 2, 5, 2),
(6, "672 Upham Drive", "Southfield", "MI", "48076", "Rural", 5, 3, 3),
(7, "327 Sunbrook Crossing", "Richmond", "VA", "23260", "Suburb", 5, 4, 6),
(8, "48456 Maple Wood Way", "Stamford", "CT", "06905", "Urban", 4, 1, 7),
(9, "78 Longview Crossing", "Los Angeles", "CA", "90035", "Urban", 4, 4, 3),
(10, "229 Nelson Parkway", "Las Vegas", "NV", "89145", "Rural", 3, 5, 8),
(11, "3 Annamark Pass", "Oklahoma City", "OK", "73124", "Urban", 4, 4, 7),
(12, "72 Luster Drive", "Detroit", "MI", "48295", "Rural", 5, 2, 5),
(13, "6386 Bashford Point", "Santa Cruz", "CA", "95064", "Rural", 3, 3, 2),
(14, "74 Kim Point", "Miami", "FL", "33134", "Urban", 4, 5, 1),
(15, "347 Merrick Plaza", "Fredericksburg", "VA", "22405", "Rural", 2, 1, 1),
(16, "636 Nancy Court", "Baton Rouge", "LA", "70883", "Suburb", 5, 5, 2),
(17, "2 Mockingbird Center", "San Jose", "CA", "95173", "Suburb", 2, 1, 2),
(18, "220 Fair Oaks Avenue", "Naples", "FL", "33961", "Urban", 4, 5, 5),
(19, "58934 Elka Drive", "Washington", "DC", "20566", "Suburb", 3, 3, 2),
(20, "939 Susan Avenue", "Henderson", "NV", "89074", "Urban", 3, 1, 7);
INSERT INTO PropertyOwner_Phone (poID, phone, type) VALUES (1, "878-904-4670", 
"work"),
(2, "127-362-4051", "home"),
(3, "521-951-1750", "work"),
(4, "588-210-7943", "cell"), (5, "356-786-7341", "work"), (6, "243-722-9803", 
"work"), (7, "602-826-2892", "home"), (8, "452-809-6340", "work"), (9, "559-957-
6046", "work"), (10, "773-837-4551", "work"), (11, "412-137-1233", "work"), (12, 
"767-679-8388", "home"), (13, "161-322-5661", "cell"), (14, "291-348-7746", 
"work"), (15, "615-237-5874", "home"), (16, "707-360-5298", "home"), (17, "809-502-
6901", "cell"), (18, "597-115-5793", "home"), (19, "833-735-6532", "cell"), (20, 
"857-885-8392", "cell");
INSERT INTO Tenant (tenantID, ssn, fName, mName, lName, backgroundCheck, 
backgroundCheckDesc) VALUES
(1, "875-41-7379", "Gian", "Lissi", "O'Currane", "false", "N/a"),
(2, "490-53-4674", "Ruprecht", "Glyn", "Deveraux", "true", "N/a"),
(3, "530-86-6161", "Ingram", "Helaine", "Ben", "false", "N/a"),
(4, "183-31-7484", "Marrissa", "Tootsie", "Pirozzi", "false", "N/a"), (5, "387-28-
5276", "Dorri", "Anjela", "Clousley", "false", "N/a"),
(6, "695-98-6062", "Yancey", "Rikki", "Little", "false", "N/a"),
(7, "616-02-7088", "Berry", "Myrtie", "Samper", "false", "N/a"),
(8, "821-30-9801", "Pippy", "Ignatius", "Mitchinson", "false", "N/a"), (9, "184-78-
6857", "Elsey", "Sherwood", "Neissen", "false", "N/a"),
(10, "514-94-6707", "Bailie", "Armstrong", "Trippick", "true", "N/a"), (11, "890-
74-2320", "Shandie", "Judon", "Lowen", "true", "N/a"),
(12, "522-95-4748", "Finn", "Derrick", "Bartosik", "false", "N/a"),
(13, "469-68-2575", "Eric", "Anatollo", "Allday", "true", "N/a"),
(14, "894-56-1508", "Leigha", "Olia", "Caldero", "false", "N/a"),
(15, "458-89-4649", "Kelsey", "Stanfield", "O' Driscoll", "true", "N/a"), (16, 
"699-52-7726", "Staffard", "Berte", "Bruckstein", "true", "N/a"), (17, "596-61-
8288", "Allyn", "Cindi", "Sargerson", "true", "N/a"),
(18, "335-98-6442", "Yurik", "Cynthy", "Lapwood", "false", "N/a"),
(19, "787-29-2547", "Garfield", "Sisile", "Balazin", "true", "N/a"), (20, "113-62-
9493", "Neala", "Jone", "Christley", "false", "N/a");
INSERT INTO Tenant_Phone (tenID, phone, type) VALUES (1, "427-228-3331", "work"),
(2, "549-558-8890", "home"),
(3, "485-533-3867", "work"),
(4, "518-504-7319", "cell"), (5, "379-889-8941", "work"), (6, "495-728-3341", 
"work"), (7, "497-954-4415", "cell"), (8, "996-448-5968", "cell"), (9, "677-248-
0528", "cell"), (10, "427-719-4588", "home"), (11, "855-944-1885", "work"), (12, 
"796-318-8368", "home"), (13, "314-273-4915", "home"), (14, "314-765-4551", 
"cell"), (15, "766-905-3192", "cell"), (16, "945-521-0620", "home"), (17, "243-876-
6682", "cell"), (18, "876-486-9098", "home"), (19, "999-485-5126", "cell"), (20, 
"525-584-4589", "work");
INSERT INTO lease (leaseID, pID, tID, startDate, endDate, rentCost) VALUES (1, 1, 
1, "2018-04-02", "2020-09-21", 1381.76),
(2, 2, 2, "2019-11-21", "2021-02-26", 1204.92),
(3, 3, 3, "2019-09-16", "2020-04-26", 500.93),
(4, 4, 4, "2015-09-10", "2021-09-20", 1197.01), (5, 5, 5, "2019-11-23", "2020-02-
12", 1695.68), (6, 6, 6, "2019-11-03", "2022-04-08", 1145.44), (7, 7, 7, "2017-08-
28", "2021-11-14", 504.21), (8, 8, 8, "2015-02-04", "2022-03-11", 1975.70), (9, 9, 
9, "2019-11-21", "2022-03-14", 1790.91), (10, 10, 10, "2015-10-28", "2021-07-01", 
781.90), (11, 11, 11, "2019-04-04", "2021-10-29", 355.72), (12, 12, 12, "2016-12-
07", "2020-03-21", 544.00), (13, 13, 13, "2015-01-10", "2021-08-28", 1889.82), (14,
14, 14, "2015-03-11", "2020-06-16", 1759.00), (15, 15, 15, "2016-10-15", "2021-09-
17", 1251.31), (16, 16, 16, "2015-10-16", "2022-02-18", 1755.08), (17, 17, 17, 
"2018-01-21", "2020-01-29", 1558.98), (18, 18, 18, "2019-01-14", "2021-06-25", 
1592.17), (19, 19, 19, "2015-12-07", "2020-04-08", 1003.43), (20, 20, 20, "2016-06-
21", "2020-11-03", 1996.86);
INSERT INTO employee (employeeID, fName, lName, email, company, position) VALUES 
(1, "Maurita", "Alliott", "malliott0@diigo.com", "Hyre", "Operator"),
(2, "Luci", "Tite", "ltite1@si.edu", "Hyre", "Operator"),
(3, "Betti", "Wheadon", "bwheadon2@guardian.co.uk", "Parkiss", "Manager"),
(4, "Bridget", "Colchett", "bcolchett3@vimeo.com", "Dagon Inc", "Maintenance"), (5,
"Othella", "Petegrew", "opetegrew4@reference.com", "Dagon Inc", "Manager"), (6, 
"Sapphira", "Kingett", "skingett5@exblog.jp", "Dagon Inc", "Manager"),
(7, "Chet", "Morrish", "cmorrish6@hubpages.com", "Dagon Inc", "Operator"),
(8, "Laurette", "Stetlye", "lstetlye7@nationalgeographic.com", "Hyre", 
"Maintenance"),
(9, "Findlay", "Gentiry", "fgentiry8@people.com.cn", "Parkiss", "Manager"), (10, 
"Elisa", "Benadette", "ebenadette9@salon.com", "Hyre", "Maintenance"),
(11, "Dennis", "Primrose", "dprimrosea@multiply.com", "Hyre", "Operator"), (12, 
"Augusta", "Weems", "aweemsb@altervista.org", "Dagon Inc", "Manager"), (13, 
"Bruis", "Greenaway", "bgreenawayc@spotify.com", "Hyre", "Maintenance"), (14, 
"Yvor", "Spincke", "yspincked@theguardian.com", "Dagon Inc", "Manager"), (15, 
"Tabina", "Kitcatt", "tkitcatte@prlog.org", "Hyre", "Operator"),
(16, "Ulrikaumeko", "Ulyet", "uulyetf@macromedia.com", "Parkiss", "Maintenance"), 
(17, "Colly", "Noorwood", "cnoorwoodg@apache.org", "Hyre", "Operator"),
(18, "Brenda", "Humbee", "bhumbeeh@ustream.tv", "Hyre", "Operator"),
(19, "Katinka", "Orviss", "korvissi@smugmug.com", "Dagon Inc", "Operator"),(10, "514-94-6707", "Bailie", "Armstrong", "Trippick", "true", "N/a"), (11, "890-
74-2320", "Shandie", "Judon", "Lowen", "true", "N/a"),
(12, "522-95-4748", "Finn", "Derrick", "Bartosik", "false", "N/a"),
(13, "469-68-2575", "Eric", "Anatollo", "Allday", "true", "N/a"),
(14, "894-56-1508", "Leigha", "Olia", "Caldero", "false", "N/a"),
(15, "458-89-4649", "Kelsey", "Stanfield", "O' Driscoll", "true", "N/a"), (16, 
"699-52-7726", "Staffard", "Berte", "Bruckstein", "true", "N/a"), (17, "596-61-
8288", "Allyn", "Cindi", "Sargerson", "true", "N/a"),
(18, "335-98-6442", "Yurik", "Cynthy", "Lapwood", "false", "N/a"),
(19, "787-29-2547", "Garfield", "Sisile", "Balazin", "true", "N/a"), (20, "113-62-
9493", "Neala", "Jone", "Christley", "false", "N/a");
INSERT INTO Tenant_Phone (tenID, phone, type) VALUES (1, "427-228-3331", "work"),
(2, "549-558-8890", "home"),
(3, "485-533-3867", "work"),
(4, "518-504-7319", "cell"), (5, "379-889-8941", "work"), (6, "495-728-3341", 
"work"), (7, "497-954-4415", "cell"), (8, "996-448-5968", "cell"), (9, "677-248-
0528", "cell"), (10, "427-719-4588", "home"), (11, "855-944-1885", "work"), (12, 
"796-318-8368", "home"), (13, "314-273-4915", "home"), (14, "314-765-4551", 
"cell"), (15, "766-905-3192", "cell"), (16, "945-521-0620", "home"), (17, "243-876-
6682", "cell"), (18, "876-486-9098", "home"), (19, "999-485-5126", "cell"), (20, 
"525-584-4589", "work");
INSERT INTO lease (leaseID, pID, tID, startDate, endDate, rentCost) VALUES (1, 1, 
1, "2018-04-02", "2020-09-21", 1381.76),
(2, 2, 2, "2019-11-21", "2021-02-26", 1204.92),
(3, 3, 3, "2019-09-16", "2020-04-26", 500.93),
(4, 4, 4, "2015-09-10", "2021-09-20", 1197.01), (5, 5, 5, "2019-11-23", "2020-02-
12", 1695.68), (6, 6, 6, "2019-11-03", "2022-04-08", 1145.44), (7, 7, 7, "2017-08-
28", "2021-11-14", 504.21), (8, 8, 8, "2015-02-04", "2022-03-11", 1975.70), (9, 9, 
9, "2019-11-21", "2022-03-14", 1790.91), (10, 10, 10, "2015-10-28", "2021-07-01", 
781.90), (11, 11, 11, "2019-04-04", "2021-10-29", 355.72), (12, 12, 12, "2016-12-
07", "2020-03-21", 544.00), (13, 13, 13, "2015-01-10", "2021-08-28", 1889.82), (14,
14, 14, "2015-03-11", "2020-06-16", 1759.00), (15, 15, 15, "2016-10-15", "2021-09-
17", 1251.31), (16, 16, 16, "2015-10-16", "2022-02-18", 1755.08), (17, 17, 17, 
"2018-01-21", "2020-01-29", 1558.98), (18, 18, 18, "2019-01-14", "2021-06-25", 
1592.17), (19, 19, 19, "2015-12-07", "2020-04-08", 1003.43), (20, 20, 20, "2016-06-
21", "2020-11-03", 1996.86);
INSERT INTO employee (employeeID, fName, lName, email, company, position) VALUES 
(1, "Maurita", "Alliott", "malliott0@diigo.com", "Hyre", "Operator"),
(2, "Luci", "Tite", "ltite1@si.edu", "Hyre", "Operator"),
(3, "Betti", "Wheadon", "bwheadon2@guardian.co.uk", "Parkiss", "Manager"),
(4, "Bridget", "Colchett", "bcolchett3@vimeo.com", "Dagon Inc", "Maintenance"), (5,
"Othella", "Petegrew", "opetegrew4@reference.com", "Dagon Inc", "Manager"), (6, 
"Sapphira", "Kingett", "skingett5@exblog.jp", "Dagon Inc", "Manager"),
(7, "Chet", "Morrish", "cmorrish6@hubpages.com", "Dagon Inc", "Operator"),
(8, "Laurette", "Stetlye", "lstetlye7@nationalgeographic.com", "Hyre", 
"Maintenance"),
(9, "Findlay", "Gentiry", "fgentiry8@people.com.cn", "Parkiss", "Manager"), (10, 
"Elisa", "Benadette", "ebenadette9@salon.com", "Hyre", "Maintenance"),
(11, "Dennis", "Primrose", "dprimrosea@multiply.com", "Hyre", "Operator"), (12, 
"Augusta", "Weems", "aweemsb@altervista.org", "Dagon Inc", "Manager"), (13, 
"Bruis", "Greenaway", "bgreenawayc@spotify.com", "Hyre", "Maintenance"), (14, 
"Yvor", "Spincke", "yspincked@theguardian.com", "Dagon Inc", "Manager"), (15, 
"Tabina", "Kitcatt", "tkitcatte@prlog.org", "Hyre", "Operator"),
(16, "Ulrikaumeko", "Ulyet", "uulyetf@macromedia.com", "Parkiss", "Maintenance"), 
(17, "Colly", "Noorwood", "cnoorwoodg@apache.org", "Hyre", "Operator"),
(18, "Brenda", "Humbee", "bhumbeeh@ustream.tv", "Hyre", "Operator"),
(19, "Katinka", "Orviss", "korvissi@smugmug.com", "Dagon Inc", "Operator"),
(20, "Carlynne", "Simond", "csimondj@typepad.com", "Hyre", "Operator");
INSERT INTO employee_phone (employeeID, number, type) VALUES (1, "545-253-1547", 
"home"),
(2, "646-197-1469", "home"),
(3, "729-151-2746", "work"),
(4, "744-277-4040", "work"), (5, "683-559-3605", "cell"), (6, "859-251-4446", 
"cell"), (7, "164-150-2272", "cell"), (8, "370-323-9452", "work"), (9, "102-170-
3554", "cell"), (10, "860-282-5488", "home"), (11, "751-559-2854", "work"), (12, 
"273-942-7290", "cell"), (13, "679-379-3584", "home"), (14, "393-470-4015", 
"work"), (15, "305-934-6887", "cell"), (16, "291-648-0881", "home"), (17, "939-136-
3472", "work"), (18, "696-254-7648", "cell"), (19, "234-818-5259", "cell"), (20, 
"365-842-1185", "work");
INSERT INTO MaintenanceRecord (maintenanceType, cost, date, employeeID, propID) 
VALUES
("Furnace", 1206.49, "2018-03-22", 1, 1),
("dry-wall repair", 391.78, "2020-03-24", 2, 2),
("AC", 589.43, "2017-07-15", 3, 3),
("AC", 833.39, "2019-10-17", 4, 4),
("AC", 1631.02, "2022-01-27", 5, 5),
("plumbing", 1867.94, "2019-10-06", 6, 6),
("AC", 385.46, "2019-04-10", 7, 7),
("leak", 846.32, "2021-01-30", 8, 8),
("AC", 1100.61, "2019-08-18", 9, 9),
("dry-wall repair", 883.58, "2021-09-03", 10, 10), ("plumbing", 1338.00, "2018-07-
05", 11, 11), ("plumbing", 1909.01, "2018-05-12", 12, 12), ("short-circuit", 
753.05, "2021-10-02", 13, 13), ("Furnace", 357.42, "2017-12-16", 14, 14),
("AC", 1316.31, "2019-11-29", 15, 15), ("Furnace", 1422.19, "2020-05-25", 16, 16), 
("plumbing", 1601.26, "2019-11-03", 17, 17), ("short-circuit", 541.82, "2020-09-
26", 18, 18), ("plumbing", 313.21, "2020-03-15", 19, 19), ("short-circuit", 
1239.12, "2017-10-12", 20, 20);
INSERT INTO Lease_PetType (leaseID, petType) VALUES (1, "Cat"),
(2, "Cat"),
(3, "Exotic-species"), (4, "Cat"),
(5, "Dog"),
(6, "Dog"),
(7, "Bird"),
(8, "Dog"),
(9, "Exotic-species"), (10, "Bird"),
(11, "Bird"),
(12, "Bird"),
(13, "Bird"),
(14, "Exotic-species"), (15, "Cat"),
(16, "Exotic-species"), (17, "Dog"),
(18, "Cat"),
(19, "Bird"),
(20, "Dog");
INSERT INTO DamageCharge (chargeID, damageType, cost, damageDate) VALUES (1, 
"Carpet", 1553.10, "2017-04-22"),
(2, "plumbing", 1159.52, "2017-11-12"),
(3, "Paint", 6458.10, "2017-11-27"),
(4, "Appliance", 5537.48, "2018-06-03"), (5, "Carpet", 6298.44, "2021-04-21"), (6, 
"Paint", 8634.68, "2018-11-29"),
(7, "Carpet", 8526.02, "2017-07-07"), (8, "Carpet", 5920.81, "2021-04-05"), (9, 
"Carpet", 791.34, "2017-05-29"), (10, "Drywall", 7852.58, "2018-11-03"), (11, 
"Carpet", 312.41, "2018-06-02"), (12, "Drywall", 2299.77, "2019-11-05"), (13, 
"plumbing", 2489.48, "2017-06-14"), (14, "Appliance", 5471.96, "2021-05-18"), (15, 
"Appliance", 9481.36, "2020-10-21"), (16, "Appliance", 6898.51, "2019-12-09"), (17,
"plumbing", 886.83, "2017-06-09"), (18, "plumbing", 1428.27, "2017-05-08"), (19, 
"Paint", 3604.14, "2017-08-20"), (20, "plumbing", 9897.90, "2021-06-27");
Insert into OwnerPayment(receiptNumber, date, amount, propertyOwnerID, propertyID) 
values
('ACAjDAD2341', '2012-12-03', 43, 1, 1),
('ACAaDAD2342', '2022-01-08', 47, 2, 2),
('ACA7DAD2343', '2021-11-03', 141, 3, 3), ('ACA2DAD2344', '2015-06-03', 463, 4, 4),
('ACAuDAD2345', '2015-09-03', 553, 5, 5), ('BCAeDAD2341', '2016-02-03', 247, 6, 6),
('BCAaDAD2342', '2017-12-03', 341, 7, 7), ('BCAkDAD2343', '2018-01-03', 43, 8, 8), 
('BCAjDAD2344', '2019-11-03', 73, 9, 9), ('BCAhDAD2345', '2011-01-03', 53, 10, 10),
('CCAdDAD2341', '2010-02-03', 447, 11, 11), ('CCAsDAD2342', '2012-04-03', 31, 12, 
12), ('CCAaDAD2343', '2013-07-03', 137, 13, 13), ('CCAdDAD2344', '2014-01-03', 3, 
14, 14), ('CCAjDAD2345', '2015-08-03', 56, 15, 15), ('DCAhDAD2341', '2016-12-03', 
527, 16, 16),
('DCAgDAD2342', '2017-11-03', 311, 17, 17), ('DCAdDAD2343', '2018-03-03', 843, 18, 
18), ('DCAaDAD2344', '2019-05-03', 944, 19, 19), ('DCAzDAD2345', '2020-01-03', 54, 
20, 20);
Insert into LegalMotion(motionType, dateFiled, cost, tenantID, propertyOwnerID) 
values
('Damage', '2012-12-03', 1.43, 1, 1),
('Court', '2022-01-08', 21.47, 2, 2),
('Emergency', '2021-11-03', 31.41, 3, 3), ('Break', '2015-06-03', 41.63, 4, 4), 
('Jury', '2015-09-03', 51.53, 5, 5), ('Damage', '2016-02-03', 211.47, 6, 6), 
('Court', '2017-12-03', 311.41, 7, 7), ('Emergency', '2018-01-03', 641.63, 8, 8), 
('Jury', '2019-11-03', 71.43, 9, 9), ('Break', '2011-01-03', 851.53, 10, 10), 
('Damage', '2010-02-03', 821.47, 11, 11), ('Court', '2012-04-03', 31.41, 12, 12), 
('Emergency', '2013-07-03', 1.63, 13, 13), ('Jury', '2014-01-03', 133.43, 14, 14), 
('Damage', '2015-08-03', 351.53, 15, 15), ('Court', '2016-12-03', 521.47, 16, 16), 
('Emergency', '2017-11-03', 331.41, 17, 17), ('Jury', '2018-03-03', 841.63, 18, 
18), ('Break', '2019-05-03', 91.43, 19, 19), ('Emergency', '2020-01-03', 541.53, 
20, 20);
Insert into RentReceipt(financialType, date, cost, tenantID, propID, chargeID) 
values
('Credit', '2012-12-03', 1.43, 1, 1, 1),
('Debit', '2022-01-08', 21.47, 2, 2, 2),
('Cash', '2021-11-03', 31.41, 3, 3, 3), ('Credit', '2015-06-03', 41.63, 4, 4, 4), 
('Debit', '2015-09-03', 51.53, 5, 5, 5), ('Cash', '2016-02-03', 211.47, 6, 6, 6), 
('Credit', '2017-12-03', 311.41, 7, 7, 7), ('Debit', '2018-01-03', 641.63, 8, 8, 
8), ('Cash', '2019-11-03', 71.43, 9, 9, 9), ('Credit', '2011-01-03', 851.53, 10, 
10, 10), ('Debit', '2010-02-03', 821.47, 11, 11, 11), ('Cash', '2012-04-03', 31.41,
12, 12, 12), ('Credit', '2013-07-03', 1.63, 13, 13, 13), ('Debit', '2014-01-03', 
133.43, 14, 14, 14), ('Cash', '2015-08-03', 351.53, 15, 15, 15), ('Credit', '2016-
12-03', 521.47, 16, 16, 16), ('Debit', '2017-11-03', 331.41, 17, 17, 17), ('Cash', 
'2018-03-03', 841.63, 18, 18, 18), ('Credit', '2019-05-03', 91.43, 19, 19, 19), 
('Debit', '2020-01-03', 541.53, 20, 20, 20);
