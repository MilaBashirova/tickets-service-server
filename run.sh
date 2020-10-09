docker stop tickets-service-server
docker rm tickets-service-server
docker rmi tickets-service-server
docker build -t tickets-service-server .
docker run --name tickets-service-server -d -p 8080:8080  tickets-service-server