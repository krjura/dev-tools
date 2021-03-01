FROM krjura/dev-tools-java-exec-env:v2 AS builder

ENV HOME=/opt/dev-tools \
    USER=dt \
    GROUP=dt

RUN adduser --disabled-password --uid 1200 $USER \
    && mkdir -p $HOME \
    && mkdir -p $HOME\lib

COPY --chown=$USER:$GROUP build/install/devtools-root/lib $HOME/lib
COPY --chown=$USER:$GROUP frontend/angular-web/dist/angular-web $HOME/web-resources

RUN chown -R $USER:$GROUP $HOME \
    && chmod -R a-w,o-rwx $HOME

FROM krjura/dev-tools-java-exec-env:v2

LABEL maintainer="Krešimir Jurasović <krjura@outlook.com>"

ENV HOME=/opt/dev-tools \
    USER=dt \
    GROUP=dt

RUN adduser --disabled-password --uid 1200 $USER

COPY --from=builder $HOME $HOME

WORKDIR $HOME
USER $USER

EXPOSE 25000

ENTRYPOINT "java" "-cp" "lib/*" "-Djava.security.egd=file:/dev/urandom" "org.krjura.devtools.DevToolsRootKt"