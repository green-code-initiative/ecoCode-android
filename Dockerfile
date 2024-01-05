FROM maven:3.8-openjdk-11 AS builder

COPY . /usr/src/ecocode

WORKDIR /usr/src/ecocode
RUN ./tool_prepare-codenarc.sh
RUN ./tool_build.sh

FROM sonarqube:10.3.0-community
USER root
COPY --from=builder /usr/src/ecocode/lib/* /opt/sonarqube/extensions/plugins/

USER sonarqube
