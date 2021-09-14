FROM tomcat:9.0-alpine
LABEL maintainer="elbaglikov@gmail.com"
ADD /target/server /usr/local/tomcat/webapps/server
EXPOSE 8080
CMD ["catalina.sh", "run"]