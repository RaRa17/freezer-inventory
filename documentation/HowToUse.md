# How to use the application

## Prerequisites

To ease the use of the application a docker image is available on docker hub.

see also [on my docker hub - rara69](https://hub.docker.com/repository/docker/rara69/freezer-inventory-be/general)

### Using a local H2 Database

The tag `local_h2` persists the data in a local file-base H2 database.

File `.env` must contain the following:

```dotenv
H2_USER=admin
H2_PASSWORD=mypassword
```

Docker-compose file:

```yaml
services:
  freezer-be-h2:
    image: rara69/freezer-inventory-be:local_h2.0.1.0
    container_name: ct_freezer_be_h2
    environment:
      - TEST_SECRET=local_h2
    env_file:
      - .env
    #ports:
    #  - 8093:8090
    networks:
      - reverse-proxy
    restart: unless-stopped
networks:
  reverse-proxy:
    external: true
```

```bash
docker-compose up -d
```

### Using an existing MySQL Database

In case you want to use an existing MySql database, setup the connection properties via environment variables when creating the docker container from the image. (e.g., in a .env.net_mysql file)

```dotenv
MYSQL_HOST=<ip-or-dns-name-of-mysql-server>
MYSQL_PORT=<port-of-mysql-server>
MYSQL_DB=<database-name>
MYSQL_USER=<database-user>
MYSQL_PASSWORD=<database-password>
```

```yaml
services:
  freezer-be-mysql:
    image: rara69/freezer-inventory-be:net_mysql.0.1.0
    container_name: ct_freezer_be_mysql
    environment:
      - TEST_SECRET=proxmox_docker
    env_file:
      - .env
    # No ports published to use reverse proxy
    #ports:
    #  - 8092:8090
    networks:
      - reverse-proxy
    restart: unless-stopped
networks:
  reverse-proxy:
    external: true
```

```bash
docker-compose up -d
```

## Usage

Since currently no "front-end" is available, the application can only be used via the [`swagger UI`](http://localhost:8080/swagger-ui.html).

### Version

Endpoint `/api/version` returns a JSON object with current version and active spring profile of the application.

```json
{
  "version": "0.1.0",
  "profile": "local_h2"
}
```

Response code will always be 200 (ok).

### Frozen Items

This represents a package that has been frozen.

All endpoints are prefixed with `/api/frozen-items"`.

- GET /api/frozen-items: retrieves the total freezer content (all frozen items)
  - No parameters
  - response code always 200 (even with a list in the return body if no items are stored)
- POST /api/frozen-items: adds a new frozen item
  - No (path) parameters
  - request body: a JSON object with the following properties:
    - `id`: the id of the frozen item (should be null for new items)
    - `name`: the name of the frozen item
    - `quantity`: how many item got frozen
    - `frozenAt`: will be overwritten with the current date
  - if the id is given with a value already existing in the database, the request will fail
  - response code: 
    - 201 (created)
    - 406 (not acceptable): request body is not valid
    - 409 (conflict): Id given in request body but already existing in database
  - response body: a JSON object with the frozen item as written into the database
- GET /api/frozen-items/{id}: retrieves a specific frozen item by its id
  - Parameter: id of the frozen item
  - response code: 
    - 200 (ok)
      - response body: a JSON object with the properties of the frozen item {id}
    - 404 (not found): id not found
- GET /api/frozen-items/count: retrieves the total number of frozen items
  - No parameters
  - response code: 200 (ok)
  - response body: total number of frozen items in the database. Might be 0

***
