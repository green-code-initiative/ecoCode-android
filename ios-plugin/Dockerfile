FROM sonarqube:9.9-community
USER root
ADD https://github.com/insideapp-oss/sonar-apple/releases/download/0.3.0/sonar-apple-plugin-0.3.0.jar /opt/sonarqube/extensions/plugins/sonar-apple-plugin-0.3.0.jar
RUN chmod 777 /opt/sonarqube/extensions/plugins/sonar-apple-plugin-0.3.0.jar
USER sonarqube