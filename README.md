# Makkara-api

## Usage

### Running

the original compojure version:

`lein simple`

compojure-api sweetened api:

`lein simple-api`

the swagger-api:

`lein swagger-api`

### Packaging and running as standalone jar

```
lein with-profile [simple|simple-api|swagger-api] do clean, ring uberjar
java -jar target/server.jar
```

### Packaging as war

`lein ring uberwar`

## License

Copyright © 2014 FIXME
