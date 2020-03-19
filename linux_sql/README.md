# Linux Cluster Monitoring Agent

##Introduction
This cluster monitoring agent is used to record the hardware specifications of each node and
 monitor node resource usage in real-time. The collected data can be used to generate report
s by the infrastructure team which helps them to make better resource planning in the future
.

## Architecture & Design

![diagram](./assets/architecture.png)

1. Tables:
  * `host_info`: a table contains hardware specifications data.
  * `host_usage`: a table contains server CPU and memory usage data.
2. Scripts:
  * `psql_docker.sh`: a bash script is used to setup PostgreSQL with Docker and provision a
psql instance.
  * `host_info.sh`: a bash script is used to collect hardware specifications data and store
them in table host_info.
  * `host_usage.sh`: a bash script is used to collect server usage data and store them in ta
ble host_usage.
  * `ddl.sql`: a set of SQL queries to create database and tables that store the collected d
ata.
  * `queries.sql': a set of queries to find a group of hosts by hardware info and also calcu
late the average memory usage percentage for each host.

## Usage
1. Install PostgreSQL and create the database host_agent:
```
bash ./psql_docker.sh [start|stop] docker
psql -h localhost -U postgres -W -f ./ddl.sql
```
2. host_info.sh usage:
``
`bash ./host_info.sh localhost 5432 host_agent postgres docker
```
3. host_usage.sh usage:
```
bash ./host_usage.sh localhost 5432 host_agent postgres docker
```
4. Crontab setup:
```
* * * * * bash /home/centos/dev/jarvis_data_eng_Hongting/linux_sql/scripts/host_usage.sh localhost 5432 host_agent postgres docker > /tmp/host_usage.log
```

## Improvements
1. Create a script which includes all commands for all processes, like docker initialization
,database&tables creation, and data insertion.
2. Allows more servers to add data into database instead of localhost.
3. Add queries to obtain more information and source usage about localhost.
