[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f64332307bc3454c9c582cfb67522bb8)](https://www.codacy.com/manual/marcosflobo/churripuntos-api?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=marcosflobo/churripuntos-api&amp;utm_campaign=Badge_Grade) [![Build Status](https://travis-ci.org/marcosflobo/churripuntos-api.svg?branch=master)](https://travis-ci.org/marcosflobo/churripuntos-api)

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
