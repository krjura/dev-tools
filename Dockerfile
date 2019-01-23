FROM docker.krjura.org/dev-tools/java-exec-env:v1

ENV APPLICATION_HOME=/opt/dev-tools \
    APPLICATION_USER=dt \
    APPLICATION_GROUP=dt

COPY build/libs/devtools-root-boot.jar $APPLICATION_HOME/devtools-root-boot.jar
COPY frontend/angular-web/dist/angular-web $APPLICATION_HOME/web-resources

RUN useradd --create-home --uid 1200 $APPLICATION_USER \
    && mkdir -p $APPLICATION_HOME/logs \
    && mkdir -p $APPLICATION_HOME/tmp \
    && mkdir -p $APPLICATION_HOME/running \
    && chown -R $APPLICATION_USER:$APPLICATION_GROUP $APPLICATION_HOME \
    && chmod -R a-w,o-rwx $APPLICATION_HOME \
    && chmod -R ug+w $APPLICATION_HOME/running $APPLICATION_HOME/logs $APPLICATION_HOME/tmp


FROM docker.krjura.org/dev-tools/java-exec-env:v1

LABEL maintainer="Krešimir Jurasović <krjura@outlook.com>"

ENV APPLICATION_HOME=/opt/dev-tools \
    APPLICATION_USER=dt \
    APPLICATION_GROUP=dt

WORKDIR /opt/dev-tools

RUN useradd --create-home --uid 1200 $APPLICATION_USER

USER $APPLICATION_USER

COPY --from=0 --chown=1200:1200 $APPLICATION_HOME .

EXPOSE 25100

ENTRYPOINT ["java", "-jar", "devtools-root-boot.jar"]