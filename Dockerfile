FROM tomcat:11.0.0-jdk21-openjdk-slim

# Copy the WAR file
COPY target/aston_homework_spring-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Start Tomcat
CMD ["catalina.sh", "run"]
