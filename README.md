# AsteroidTracker

A Java application which can track asteroids in the Solar System using NASA’s Open APIs

## What does it do?

The application serves as a tracker for nearby asteroids. For a given day, the application returns the closest asteroid there is for that day which is closest to the Earth. The user has the option to input a date and get the asteroid name, its speed, estimated diameter, and if it is potentially hazardous or not.

## Features

- Allow user to input any date and get the closest asteroid for that day
- Get a wide range of data for an asteroid, including parameters like its official name, speed, estimated diameter, closest approach date and time, and also if the asteroid is hazardous or not.

## Working

### Data Retrieval 

The application will retrieve the asteroid data using NASA’s open API.  In order to get data , the application will send GET requests to the specified endpoint using DEMO_KEY which allows about 50 req/hour without authentication.

## Creators

- Suraj K Suresh
- Akhil K Gangadharan

