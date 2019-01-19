FROM ubuntu:19.04

RUN apt-get -y update \
    && apt-get -y upgrade \
	&& apt-get -y install less vim wget curl openjdk-11-jre-headless \
	&& apt-get clean \
	&& rm -rf /var/cache/* /tmp/* /var/tmp/*

RUN apt-get -y update \
	&& apt-get install -y locales \
	&& locale-gen en_GB.UTF-8 \
	&& apt-get clean \
        && rm -rf /var/cache/* /tmp/* /var/tmp/*

ENV LANG en_GB.UTF-8
ENV LANGUAGE en_GB:en