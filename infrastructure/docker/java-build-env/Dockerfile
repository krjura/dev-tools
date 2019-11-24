FROM ubuntu:19.04

RUN apt-get -y update \
    && apt-get -y install git openjdk-11-jdk-headless \
    && apt-get clean \
    && rm -rf /var/cache/* /tmp/* /var/tmp/*

RUN apt-get -y update \
    && apt-get install -y locales \
    && locale-gen en_GB.UTF-8 \
    && apt-get clean \
    && rm -rf /var/cache/* /tmp/* /var/tmp/*

RUN mkdir -p /opt/cache/gradle \
    && chmod -R a+rwx /opt/cache

ENV LANG en_GB.UTF-8
ENV LANGUAGE en_GB:en