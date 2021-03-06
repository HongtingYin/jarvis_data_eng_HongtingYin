--create a database, host_agent
DROP DATABASE IF EXISTS host_agent;
CREATE DATABASE host_agent;

--connect to host_agent
\c host_agent

DROP TABLE IF EXISTS PUBLIC.host_info CASCADE;
DROP TABLE IF EXISTS PUBLIC.host_usage CASCADE;

--create table
CREATE TABLE PUBLIC.host_info
	(
		id			SERIAL NOT NULL PRIMARY KEY,
		hostname		VARCHAR NOT NULL UNIQUE,
		cpu_number		INT NOT NULL,
		cpu_architecture	VARCHAR NOT NULL,
		cpu_model		VARCHAR NOT NULL,
		cpu_mhz			FLOAT8 NOT NULL,
		l2_cache		INT NOT NULL,
		total_mem		INT NOT NULL,
		"timestamp"		TIMESTAMP NOT NULL
	);

CREATE TABLE PUBLIC.host_usage
        (
		"timestamp"             TIMESTAMP NOT NULL, 
		host_id                 SERIAL REFERENCES public.host_info(id),
		memory_free             INT NOT NULL,
        	cpu_idel                INT NOT NULL,
        	cpu_kernel       	INT NOT NULL,
		disk_io               	INT NOT NULL,
		disk_available          INT NOT NULL
	 );
