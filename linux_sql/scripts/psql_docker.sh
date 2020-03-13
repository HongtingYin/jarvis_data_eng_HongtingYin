#!/bin/bash

#usage: bash psql_docker.sh start|stop [password]

command=$1
password=$2

export PGPASSWORD='password'

#check command is start or stop
if [[ $1 = 'start' && "$#" -eq 2 ]]; then
	#start docker if docker server is not running
	systemctl status docker || systemctl start docker
	echo "Docker is running"

	#get latest postgres image
	docker pull postgres

	#create a new volume if not exist
	if [ "$(docker volume ls | wc -l)" != 2 ]; then
		docker volume create pgdata
		echo "Docker volume is created!"
	else
		echo "Docker volume already exists!"
	fi

        #check if the container `jrvs-psql` is created or not
        if [ "$(docker container ls -a -f name=jrvs-psql | wc -l)" != 2 ]; then
		#create a container using psql image with name=jrvs-psql
		docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
		echo "Docker container is created!"
	else
		echo "Docker container already exists!"
	fi

	#check if `jrvs-psql` container is running
	if [ "$(docker container ls -a -f name=jrvs-psql | wc -l)" != 2 ]; then
		#start a container
		docker container start jrvs-psql
		echo "Docker container starts running!"
	else
		echo "Docker container is running!"
		exit 0
	fi

elif [[ $1 = 'stop' && "$#" -eq 1 ]]; then
	#stop the container & docker
	systemctl status docker && docker stop jrvs-psql
	echo "container stopped!"
	exit 0
else
	echo "Error!"
	echo "usage:bash psql_docker.sh start|stop password"
	exit 1

fi

exit 0

