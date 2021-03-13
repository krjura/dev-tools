FROM adoptopenjdk/openjdk15:x86_64-alpine-jre-15.0.2_7

RUN apk add git bash

RUN mkdir -p /opt/cache/gradle \
    && chmod -R a+rwx /opt/cache
