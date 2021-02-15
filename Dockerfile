FROM krjura/dev-tools-java-exec-env:v2

LABEL maintainer="Krešimir Jurasović <krjura@outlook.com>"

ENV HOME=/opt/dev-tools \
    USER=dt \
    GROUP=dt

RUN adduser --disabled-password --uid 1200 $USER \
    && mkdir -p $HOME \
    && chown -R $USER:$GROUP $HOME \
    && chmod -R a-w,o-rwx $HOME

COPY --chown=$USER:$GROUP build/libs/devtools-root-boot.jar $HOME/devtools-root-boot.jar
COPY --chown=$USER:$GROUP frontend/angular-web/dist/angular-web $HOME/web-resources

WORKDIR $HOME
USER $USER

EXPOSE 25000

ENTRYPOINT "java" "-jar" "devtools-root-boot.jar"