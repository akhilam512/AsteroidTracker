# Backend specification

### Get list of asteroids

```
cd asteroid
mvn spring-boot:run
curl "http://localhost:8080/asteroids?start_date=2019-06-06"
```

### Get asteroid by id

```
cd asteroid
mvn spring-boot:run
curl "http://localhost:8080/asteroids/{asteroidID}"
```