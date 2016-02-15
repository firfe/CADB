INSERT INTO `mydb`.`Customer` (`customerID`, `customerType`, `ContractFuelCompany`) VALUES ('1', 'type1', 'Contract_Fuel_Company1');
INSERT INTO `mydb`.`Customer` (`customerID`, `customerType`, `ContractFuelCompany`) VALUES ('2', 'type2', 'Contract_Fuel_Company2');
INSERT INTO `mydb`.`Customer` (`customerID`, `customerType`, `ContractFuelCompany`) VALUES ('3', 'type3', 'Contract_Fuel_Company3');
INSERT INTO `mydb`.`Customer` (`customerID`, `customerType`, `ContractFuelCompany`) VALUES ('4', 'type4', 'Contract_Fuel_Company4');
INSERT INTO `mydb`.`Customer` (`customerID`, `customerType`, `ContractFuelCompany`) VALUES ('5', 'type5', 'Contract_Fuel_Company5');

INSERT INTO `mydb`.`Amenities` (`AmenitiesID`, `Hotel`, `Rental Car`, `Catering`, `Misc`) VALUES ('1', 'true', 'false', 'false', 'none');
INSERT INTO `mydb`.`Amenities` (`AmenitiesID`, `Hotel`, `Rental Car`, `Catering`, `Misc`) VALUES ('2', 'false', 'false', 'true', 'none');
INSERT INTO `mydb`.`Amenities` (`AmenitiesID`, `Hotel`, `Rental Car`, `Catering`, `Misc`) VALUES ('3', 'true', 'false', 'false', 'none');
INSERT INTO `mydb`.`Amenities` (`AmenitiesID`, `Hotel`, `Rental Car`, `Catering`, `Misc`) VALUES ('4', 'false', 'true', 'true', 'false');
INSERT INTO `mydb`.`Amenities` (`AmenitiesID`, `Hotel`, `Rental Car`, `Catering`, `Misc`) VALUES ('5', 'true', 'true', 'true', 'false');

INSERT INTO `mydb`.`Flight_Info` (`reservationID`, `DateofArrival`, `DateofDeparture`, `CompanyName`, `ArrivalAirport`, `DestinationAirport`, `Customs`, `CrewCar`, `Customer_customerID`, `Amenities_AmenitiesID`) VALUES ('1', '1-2-15', '1-3-15', 'Company1', 'ArrivalAirport1', 'DestinationAirport1', 'true', 'false', '1', '1');
INSERT INTO `mydb`.`Flight_Info` (`reservationID`, `DateofArrival`, `DateofDeparture`, `CompanyName`, `ArrivalAirport`, `DestinationAirport`, `Customs`, `CrewCar`, `Customer_customerID`, `Amenities_AmenitiesID`) VALUES ('2', '2-25-15', '2-27-15', 'Company2', 'ArrivalAirport2', 'DestinationAirport1', 'false', 'false', '2', '2');
INSERT INTO `mydb`.`Flight_Info` (`reservationID`, `DateofArrival`, `DateofDeparture`, `CompanyName`, `ArrivalAirport`, `DestinationAirport`, `Customs`, `CrewCar`, `Customer_customerID`, `Amenities_AmenitiesID`) VALUES ('3', '2-12-14', '2-15-14', 'company3', 'ArrivalAirport2', 'DestinationAirport3', 'true', 'true', '3', '3');
INSERT INTO `mydb`.`Flight_Info` (`reservationID`, `DateofArrival`, `DateofDeparture`, `CompanyName`, `ArrivalAirport`, `DestinationAirport`, `Customs`, `CrewCar`, `Customer_customerID`, `Amenities_AmenitiesID`) VALUES ('4', '11-15-14', '11-17-14', 'company1', 'ArrivalAirport4', 'DestionationAirport5', 'false', 'false', '4', '4');
INSERT INTO `mydb`.`Flight_Info` (`reservationID`, `DateofArrival`, `DateofDeparture`, `CompanyName`, `ArrivalAirport`, `DestinationAirport`, `Customs`, `CrewCar`, `Customer_customerID`, `Amenities_AmenitiesID`) VALUES ('5', '9-1-14', '9-5-14', 'company7', 'ArrivalAirport3', 'DestinationAirport9', 'true', 'true', '5', '5');

INSERT INTO `mydb`.`Operator` (`OperatorNumber`, `OperatorName`) VALUES ('1', 'Larry');
INSERT INTO `mydb`.`Operator` (`OperatorNumber`, `OperatorName`) VALUES ('2', 'Bob');
INSERT INTO `mydb`.`Operator` (`OperatorNumber`, `OperatorName`) VALUES ('3', 'Adam');
INSERT INTO `mydb`.`Operator` (`OperatorNumber`, `OperatorName`) VALUES ('4', 'Tim');
INSERT INTO `mydb`.`Operator` (`OperatorNumber`, `OperatorName`) VALUES ('5', 'Evan');

INSERT INTO `mydb`.`Owner` (`OwnerID`, `OwnerName`, `OwnerInfo`) VALUES ('OwnerID(Int)', 'OwnerName(VarChar)', 'OwnerInfo(VarChar)');
INSERT INTO `mydb`.`Owner` (`OwnerID`, `OwnerName`, `OwnerInfo`) VALUES ('1', 'Andrew', 'Loser');
INSERT INTO `mydb`.`Owner` (`OwnerID`, `OwnerName`, `OwnerInfo`) VALUES ('2', 'Bob', 'Superloser');
INSERT INTO `mydb`.`Owner` (`OwnerID`, `OwnerName`, `OwnerInfo`) VALUES ('3', 'Charry', 'Omegaloser');
INSERT INTO `mydb`.`Owner` (`OwnerID`, `OwnerName`, `OwnerInfo`) VALUES ('4', 'Derek', 'Notaloser');
INSERT INTO `mydb`.`Owner` (`OwnerID`, `OwnerName`, `OwnerInfo`) VALUES ('5', 'Eric', 'Soreloser');
INSERT INTO `mydb`.`Owner` (`OwnerID`, `OwnerName`, `OwnerInfo`) VALUES ('6', 'Frank', 'Winner');

INSERT INTO `mydb`.`Pilot_has_Customer` (`Operator_OperatorNumber`, `Customer_customerID`) VALUES ('1', '1');
INSERT INTO `mydb`.`Pilot_has_Customer` (`Operator_OperatorNumber`, `Customer_customerID`) VALUES ('2', '2');
INSERT INTO `mydb`.`Pilot_has_Customer` (`Operator_OperatorNumber`, `Customer_customerID`) VALUES ('3', '3');
INSERT INTO `mydb`.`Pilot_has_Customer` (`Operator_OperatorNumber`, `Customer_customerID`) VALUES ('4', '4');
INSERT INTO `mydb`.`Pilot_has_Customer` (`Operator_OperatorNumber`, `Customer_customerID`) VALUES ('5', '5');

INSERT INTO `mydb`.`Planes` (`TailNumber`, `PlaneType`, `FuelType`, `Planescol`, `Customer_customerID`, `Operator_OperatorNumber`, `Owner_OwnerID`) VALUES ('12345', 'RX1', '1', '1', '1', '1', '1');
INSERT INTO `mydb`.`Planes` (`TailNumber`, `PlaneType`, `FuelType`, `Planescol`, `Customer_customerID`, `Operator_OperatorNumber`, `Owner_OwnerID`) VALUES ('23456', 'RX2', '2', '2', '2', '2', '2');
INSERT INTO `mydb`.`Planes` (`TailNumber`, `PlaneType`, `FuelType`, `Planescol`, `Customer_customerID`, `Operator_OperatorNumber`, `Owner_OwnerID`) VALUES ('34567', 'RX3', '3', '3', '3', '3', '3');
INSERT INTO `mydb`.`Planes` (`TailNumber`, `PlaneType`, `FuelType`, `Planescol`, `Customer_customerID`, `Operator_OperatorNumber`, `Owner_OwnerID`) VALUES ('45678', 'RX4', '4', '4', '4', '4', '4');
INSERT INTO `mydb`.`Planes` (`TailNumber`, `PlaneType`, `FuelType`, `Planescol`, `Customer_customerID`, `Operator_OperatorNumber`, `Owner_OwnerID`) VALUES ('56789', 'RX5', '5', '5', '5', '5', '5');
