#!/bin/bash
#script usage: ./host_info.sh psql_host psql_port db_name psql_user psql_password

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
password=$5

#check the number of arguments
if [ "$#" != 5 ]; then
	echo "Invalid arguments!"
fi

#collect hardware specifications
lscpu_out='lscpu'

hostname=$(hostname -f)

cpu_number=$("$lscpu_out" | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$("$lscpu_out" | egrep "^Architecture" | awk '{ print $2 }' | xargs)
cpu_model=$("$lscpu_out" | egrep 'Model name:' | sed -n 's/^Model name: //p' | xargs)
cpu_mhz=$("$lscpu_out" | egrep 'CPU MHz:' | sed -n 's/^CPU MHz: //p' | xargs)
l2_cache=$("$lscpu_out" | egrep "^L2\scache:" | awk '{print $3}' | grep -o '[0-9]\+')
total_mem=$(grep MemTotal /proc/meminfo | awk '{print $2}')
timestamp=$(date -u '+%Y-%m-%d %H:%M:%S')

export PGPASSWORD=$password

#insert data to the psql
psql -h $psql_host -p $psql_port -U $psql_user -d $db_name -c \
"INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, timestamp)
VALUES('$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', '$cpu_mhz', $l2_cache, $total_mem, '$timestamp');"

exit 0
