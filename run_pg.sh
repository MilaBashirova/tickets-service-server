
docker pull postgres:9.6.16
mkdir -p $HOME/docker/volumes/postgres
docker run --rm   --name postgres -e POSTGRES_PASSWORD=docker -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data  postgres:9.6.16