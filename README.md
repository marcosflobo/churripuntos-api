[![Build Status](https://travis-ci.org/marcosflobo/churripuntos-api.svg?branch=master)](https://travis-ci.org/marcosflobo/churripuntos-api)

# churripuntos-api
API to handle Churripuntos for your team

The frontend will be developed in a separated GIT repository

## Data model
### Member
- name: String
- points: Integer

## Docker

### Create docker image
```bash
./gradlew clean assemble
docker build . -t churripuntos-api:latest
```

### Run as docker container
```bash
docker run --rm -p 8080:8080 --name churripuntos-api-backend churripuntos-api:latest
```

## Kubernetes
TODO
