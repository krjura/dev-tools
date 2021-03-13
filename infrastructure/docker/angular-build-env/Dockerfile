FROM node:14.15.5-alpine3.13

RUN mkdir -p /opt/node/npm-global \
    && chown -R node:node /opt/node/npm-global \
    && chmod a+rw /opt/node/npm-global \
    && mkdir -p /opt/node/npm-cache \
    && chown -R node:node /opt/node/npm-cache \
    && chmod a+rw /opt/node/npm-cache \
    && apk add bash

ENV PATH=/opt/node/npm-global/bin:$PATH
ENV NPM_CONFIG_PREFIX=/opt/node/npm-global

RUN npm config set cache /opt/node/npm-cache --global

CMD []