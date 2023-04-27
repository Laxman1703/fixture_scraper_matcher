                             Welcome to the README file for this Web Scraping!

Project Overview:
----------------
    This project is a Spring Boot application that aims to Fetch Data From Different Websites
    and Compare there data which helps to track daily activities of different websites .


Installation
-------------
Prerequisites:
    Before you can run this project, you need to have the following tools installed on your machine:

    *Java
    *Maven
    *Git

Running the Project:
--------------------
To run this project on your local machine, follow these steps:

1)Clone the project repository to your local machine using the following command:
        git clone [insert project git URL here]

2)Navigate to the project directory:
        cd [insert project directory name here]

3)Run the application using Maven:
        mvn spring-boot:run

4)The application should now be running and accessible at http://localhost:8080.


USAGE :
-------

In this Project We have two Controllers

1) Sports-controller CBTF VS STARBET
    This is For Comparing CBTF VS STARBET (SOCCER , TENNIS & BASKETBALL)

The First method is called "ping" and is accessed through the "/ping" endpoint.
When this endpoint is called, it returns the string "pong".
This is to Check Connection .

The Second method is accessed through the "/{sportName}" endpoint.
It takes in a variable called "sportName" and returns information about that sport.
If the sportName is valid we will get four categories: Top Leagues, Upcoming, All League, and Live.
If the sportName is not valid, the response entity will include an error message stating that the sport name is invalid.

The Third method is accessed through the "/{sportName}/{category}" endpoint.
It takes in two variables, "sportName" and "category",
This method checks if the sportName is valid and if the category is one of the four previously mentioned categories.
If both the sportName and category are valid, it returns detailed information about that category of the sport.
If the sportName or category is not valid, it returns an error message.


/api/ping Shows “pong” for connection testing .

/api/{sportsname} - If the sportName is valid we will get four categories: Top Leagues, Upcoming, All League, and Live.

/api/{sportsname}/{category} - If the sportName is valid and if the category is one of the four previously mentioned categories.
                               If both the sportName and category are valid,
                               It returns detailed information about that category of the sport.

Example :: Get information about Tennis (Top Leagues, Upcoming, All League, and Live).

For LOCAL
    http://localhost:8080/api/Tennis/TopLeagues
    http://localhost:8080/api/Tennis/Upcoming
    http://localhost:8080/api/Tennis/AllLeague
    http://localhost:8080/api/Tennis/Live

Curl :: -X GET --header 'Accept: application/json' 'http://localhost:8080/api/tennis/live'

Request URL :: http://localhost:8080/api/tennis/live

Request Headers
{
  "Accept": "*/*"
}

Response Body
{
  "sportName": "tennis",
  "catagory": "live",
  "response": {
    "compared_with": "Starbet",
    "compared_site": "CBTF",
    "cbtf_fixture_ids": [
      "40821087",
      "40828133"
    ],
    "starbet_fixture_ids": [
      "40875973",
      "40875987",
      "40889779",
      "40889783",
      "40875815"
    ],
    "cbtf_missing_fixture_ids": [
      "40889783",
      "40875815",
      "40875987",
      "40889779",
      "40875973"
    ],
    "starbet_missing_fixture_ids": [
      "40821087",
      "40828133"
    ],
    "starbet_fixture_count": 5,
    "cbtf_fixture_count": 2,
    "cric10_fixture_count": 0
  }
}

Response Code :: 200



2) Sports-controller 10Cric - CBTF VS 10CRIC
    This is For Comparing CBTF VS 10CRIC  (ONLY CRICKET)

The First method is accessed through the "/{sportName}" endpoint.
It takes in a variable called "sportName" and returns information about that sport.
If the sportName is valid we will get four categories: Top Leagues, Upcoming, All League, and Live.
If the sportName is not valid, the response entity will include an error message stating that the sport name is invalid.

The Second method is accessed through the "/{sportName}/{category}" endpoint.
It takes in two variables, "sportName" and "category",
This method checks if the sportName is valid and if the category is one of the four previously mentioned categories.
If both the sportName and category are valid, it returns detailed information about that category of the sport.
If the sportName or category is not valid, it returns an error message.


/api2/{sportsname} - If the sportName is valid we will get four categories: Top Leagues, Upcoming, All League, and Live.

/api2/{sportsname}/{category} - If the sportName is valid and if the category is one of the four previously mentioned categories.
                                If both the sportName and category are valid,
                                It returns detailed information about that category of the sport.

Example ::
Get information about Cricket (Top Leagues, Upcoming, All League, and Live).

For LOCAL
    http://localhost:8080/api2/Cricket/TopLeagues
    http://localhost:8080/api2/Cricket/Upcoming
    http://localhost:8080/api2/Cricket/AllLeague
    http://localhost:8080/api2/Cricket/Live

Curl :: -X GET --header 'Accept: application/json' 'http://localhost:8080/api2/cricket/live'


Request URL :: http://localhost:8080/api2/cricket/live

Request Headers
{
  "Accept": "*/*"
}

Response Body
{
  "sportName": "cricket",
  "catagory": "live",
  "response": {
    "compared_with": "Cric10",
    "compared_site": "CBTF",
    "cbtf_fixture_ids": [
      "40294197",
      "40294195",
      "40373979",
      "40043429"
    ],
    "cric10_fixture_ids": [
        "40294197",
        "40043429",
        "40373979"
    ],
    "cbtf_missing_fixture_ids": ["40294195",],
    "cric10_missing_fixture_ids": [],
    "starbet_fixture_count": 0,
    "cbtf_fixture_count": 4,
    "cric10_fixture_count": 3
  }
}

Response Code :: 200


Overall, this code creates an API for accessing sports information about Tennis, Basketball, and Soccer in First API,
Cricket in Second API with details about Top Leagues, Upcoming, All League, and Live categories.


