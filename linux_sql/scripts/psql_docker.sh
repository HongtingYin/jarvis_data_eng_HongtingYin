#!/bin/bash

#usage: ./psql_docker.sh start|stop [password]

command=$1
password=$2

#check command is start or stop
if [[ $1 = 'start' && "$#" -eq 2 ]]; then
	#start docker if docker server is not running
	systemctl status docker || systemctl start docker
	echo "docker is running"

	#get latest postgres image
	docker pull postgres

	#create a new volume if not exist
	docker volume create pgdata

	export PGPASSWORD='password'

	#create a container using psql image with name=jrvs-psql
	docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres

	#check if the container `jrvs-psql` is created or not
	docker container ls -a -f name=jrvs-psql

	#check if `jrvs-psql` container is running
	docker ps -f name=jrvs-psql

	#start a container
	docker container start jrvs-psql
	echo "container is running!"

elif [[ $1 = 'stop' && "$#" -eq 1 ]]; then
	#stop the container & docker
	systemctl status docker && docker stop jrvs-psql
	echo "container stopped!"
	exit 0
else
	echo "Error!"
	echo "usage:./psql_docker.sh start|stop password"
	exit 1

fi

exit 0

