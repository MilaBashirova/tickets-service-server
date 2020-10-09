FROM java:8
WORKDIR /
ADD tickets-service-server-1.0-SNAPSHOT.jar tickets-service-server-1.0-SNAPSHOT.jar
CMD java -jar tickets-service-server-1.0-SNAPSHOT.jar