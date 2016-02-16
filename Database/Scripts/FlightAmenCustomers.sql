SELECT customerType, ContractFuelCompany FROM mydb.flight_info, mydb.customer WHERE mydb.flight_info.Customer_customerID = mydb.customer.customerID;
SELECT DateofArrival, DateofDeparture, CompanyName, ArrivalAirport, DestinationAirport, Customs, CrewCar FROM mydb.flight_info, mydb.customer, mydb.amenities WHERE mydb.flight_info.Customer_customerID = mydb.customer.customerID AND mydb.flight_info.Amenities_AmenitiesID = mydb.amenities.AmenitiesID;
SELECT Hotel, 'Rental Car', Catering, Misc FROM mydb.flight_info, mydb.amenities WHERE mydb.flight_info.Amenities_AmenitiesID = mydb.amenities.AmenitiesID;

/*
Things to add:
	If statements where it takes in the dropdown menu for the category (Tail Number, Customer ID etc) in which the user is searching for and actual input
	Search based off of that category and return all tables which have the result in a single table (UNION? JOIN?)
    iirc: John said he wanted something in a seperate window?
Things to consider:
    How do we optimize the script? (Do we need multiple if statements for every case (e.g. Customer Name, Tail Number?)
    Should we have have 7 different Select statements where each Select statement is for a table to print out all of their unique elements?
    Do we need to print out the Customer ID?
    Is there a operand that I dont know of to get rid of multiple fields which are the same? (I think JOIN does that?)
*/