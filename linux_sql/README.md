# Linux Cluster Monitoring Agent

## Introduction
This cluster monitoring agent is used to record the hardware specifications of each node and monitor node resource usage in real-time. The collected data can be used to generate reports by the infrastructure team which helps them to make better resource planning in the future.

## Architecture & Design

![diagram](./assets/architecture.png)

As shown above, two bash scripts are running in the Linux Cluster to collect the host's information, hardware specifications and real-time resource usage, of each node. Then the collected data will be inserted to the PostgreSQL database which is set up on a Docker container in the first server.  

1. There are two tables in the database `host_agent`:
  * `host_info`: a table contains hardware specifications data of each node and there is only one host information for now.

##### Schema of host_info
| Attributes       | Data Type    | Description                            |
| ---------------- | ------------ | -------------------------------------- |
| id               | SERIAL       | Primary key                            |
| hostname         | VARCHAR      | Unique                                 |
| cpu_number       | INT          | Number of cpu cores                    |
| cpu_architecture | VARCHAR      | x86_64                                 |
| cpu_model        | VARCHAR      | Intel(R) Xeon(R) CPU @ 2.30GHz         |
| cpu_mhz          | FLOAT8       | 2300                                   |
| l2_cache         | INT          | L2 cache in KB                         |
| total_mem        | INT          | Total memory in KB                     |
| "timestamp"      | TIMESTAMP    | Time when inserting data(UTC time zone)|

  * `host_usage`: a table contains server CPU and memory usage data.

##### Schema of host_usage
| Attributes       | Data Type    | Description                            |
| ---------------- | ------------ | -------------------------------------- |
| "timestamp"      | TIMESTAMP    | Time when inserting data(UTC time zone)|
| host_id          | SERIAL       | References public.host_info(id)        |
| memory_free      | INT          | Free memory in KB                      |
| cpu_idel         | INT          | Percentage of idle cpu                 |
| cpu_kernel       | INT          | Percentage of kernel in tasks          |
| disk_io          | INT          | Number of inprogress IO                |
| disk_available   | INT          | Root directory available disk in KB    |

2. Scripts:
  * `psql_docker.sh`: a bash script is used to setup PostgreSQL with Docker and provision a psql instance.
  * `host_info.sh`: a bash script is used to collect hardware specifications data and stor them in table host_info.
  * `host_usage.sh`: a bash script is used to collect server usage data and store them in table host_usage.
  * `ddl.sql`: a set of SQL queries to create database and tables that store the collected data.
  * `queries.sql': a set of queries to find a group of hosts by hardware info and also calculate the average memory usage percentage for each host.

## Usage
1. Setup docker and start a container, stop a container:
```
bash ./psql_docker.sh start docker
bash ./psql_docker.sh stop
```

2. Create database and tables:
```
psql -h localhost -U postgres -W -f ./ddl.sql
```

3. Collect hardware specification by running host_info.sh:
```
bash ./host_info.sh localhost 5432 host_agent postgres docker
```

4. Collect server usage information by running host_usage.sh usage:
```
bash ./host_usage.sh localhost 5432 host_agent postgres docker
```

5. Crontab setup and execute `host_usage.sh` periodically:
```sh
# Edit crontab jobs
crontab -e
# Add following command and host_usage.sh will be running periodically
* * * * * bash /home/centos/dev/jarvis_data_eng_Hongting/linux_sql/scripts/host_usage.sh localhost 5432 host_agent postgres docker > /tmp/host_usage.log
# List crontab jobs
crontab -l
```

## Improvements
1. Create a script which includes all commands for all processes, like docker initialization, database&tables creation, and data insertion.
2. Allows more servers to add data into database instead of localhost.
3. Add queries to obtain more information and source usage about localhost.
