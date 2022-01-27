# Api Service - Cashier-api

The REST API backed server for the [CashierApp](https://github.com/Grigoriym/Cashier) application.

## Technology / Tools used
- Kotlin
- Ktor
- PostgreSQL
- Kodein

### Project components description

- bdconfig.properties is used to create connection to postgres db
- jwks.json is used for JWT RSA256

### Project setup (on Windows)

- Install [WSL](https://docs.microsoft.com/en-us/windows/wsl/install-manual)
- Install [Docker Desktop](https://www.docker.com/products/docker-desktop)
- Install [pgAdmin4](https://www.pgadmin.org/)
- Use docker-compose.yml to start postgres in docker
- Windows Defender Firewall can block local ip addresses!

### Roadmap
- Swagger
- Architecture (I do not know what the architectures I can use in ktor projects)
- Learn kodein