[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f64332307bc3454c9c582cfb67522bb8)](https://www.codacy.com/manual/marcosflobo/churripuntos-api?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=marcosflobo/churripuntos-api&amp;utm_campaign=Badge_Grade) [![Build Status](https://travis-ci.org/marcosflobo/churripuntos-api.svg?branch=master)](https://travis-ci.org/marcosflobo/churripuntos-api)

# churripuntos-api
API to handle Churripuntos for your team

The frontend is implemented at [https://github.com/marcosflobo/churripuntos-frontend](churripuntos-frontend)

## What is this about?
The LAW OF THE CHURRIPUNTOS means that, inside of a team work, each time that a team member screws it up, based on
some rules previously defined within the team, a "churripunto" (point unit) is applied to that team member.

When a team member reached the churripuntos limit allowed by the team, such member should bring to the whole team what
is called "Churripunto".

## What is Churripuntos?
"Churripuntos" can be whatever good thing for the team. Some examples (mostly food-related):
- Box of churros for the whole team
- Box of special croissants or pain au chocolat for the whole team
- ... I think you get it

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
