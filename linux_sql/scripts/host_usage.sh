#!/bin/bash

#script usage
#bash scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
password=$5

export PGPASSWORD=$password

#check the number of arguments
if [ "$#" -ne 5 ];
then
	echo "Invalid number of arguments!"
	echo "usage:host_usage.sh psql_host psql_port db_name psql_user psql_password"
	exit 1
fi

#collect server information
timestamp=$(date -u '+%Y-%m-%d %H:%M:%S') #current timestamp in `2019-11-26 14:40:19` format
hostname=$(hostname -f)
memory_free=$(vmstat --unit M | tail -1 | awk '{print $4}' | xargs)
cpu_idel=$(vmstat -t | tail -1 | awk '{print $15}' | xargs)
cpu_kernel=$(vmstat --unit M | tail -1 | awk '{print $14}' | xargs)
disk_io=$(vmstat -d | tail -1 | awk '{print $10}' | xargs)
disk_available=$(df -BM / | awk '{print $4}' | sed 's/[A-Za-z]*//g' | xargs)

#insert data to psql
psql -h $psql_host -U $psql_user -w $db_name -p $psql_port -c "INSERT INTO host_usage(timestamp, host_id, memory_free, cpu_idel, cpu_kernel, disk_io, disk_available) VALUES ('$timestamp', (SELECT id FROM host_info WHERE hostname='$hostname'), '$memory_free', '$cpu_idel', $cpu_kernel, $disk_io, $disk_available);"


exit 0


