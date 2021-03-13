FROM krjura/dev-tools-java-exec-env:v3

LABEL maintainer="Krešimir Jurasović <krjura@outlook.com>"

ENV HOME=/opt/dev-tools \
    USER=dt \
    GROUP=dt

RUN adduser --disabled-password --uid 1200 $USER \
    && mkdir -p $HOME \
    && mkdir -p $HOME\lib \
    && chown -R $USER:$GROUP $HOME \
    && chmod -R a-w,o-rwx $HOME

COPY --chown=$USER:$GROUP build/install/devtools-root/lib $HOME/lib
COPY --chown=$USER:$GROUP frontend/angular-web/dist/angular-web $HOME/web-resources

WORKDIR $HOME
USER $USER

EXPOSE 25000

ENTRYPOINT "java" "-cp" "lib/*" "-Djava.security.egd=file:/dev/urandom" "org.krjura.devtools.DevToolsRootKt"