## １冊借りるときのURL
```
http://localhost:8080/api/v1/books/{isbn} <= 10桁だけ
（例）http://localhost:8080/api/v1/books/1111111111　
```

## Run App

```shell script
cd library-backend-service
./gradlew bootRun
```

## Scripts

### Run Unit Tests

``` shell script
./scripts/run-unit-tests.sh
```

### Run Tests & Push

```shell script
./scripts/ship-it.sh
```

## PostgreSQL Database Setup

``` shell script
cd tools/
docker-compose up -d
```

- Access to pgAdmin
    - http://localhost:8000
    - pgadmin / pgadmin
    - Connection
        - ![](./tools/connection-info.png)
        
