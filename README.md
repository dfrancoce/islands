# Islands on a map

Given an API endpoint that provides a tilemap, this solution counts the number of islands in the map. An island is 
formed by tiles of type "land" which are surrounded by "water" tiles. Two tiles belong to the same island if they touch. 
Tiles only count as touching if they are directly vertically or horizontally next to each other. If they touch with 
their corners they do not count as touching.

### Prerequisites

Docker is required in order to start the app. Also a docker image needs to be created with the API of the following repo 
(https://github.com/dfrancoce/islands-api) to set up the endpoint we need to consume to get the tiles of the map.

### Starting the app

To run the app just execute the following command in the root directory 
```
docker-compose up
```
Once everything is up and running, an UI with the endpoints provided by the API can be seen at 
```
localhost:8080/swagger-ui.html
```
