# Introduction
In Java, JDBC(Java Database Connectivity) is one of the most common technology to access a relational database from RDBMs. 
This application allows deveopers to connect to a PostgreSQL database and implement CRUD(Create, Read, Updata, Delete) from a Java application 
operations by using DAO(Data Access Object) design pattern.

By developing this project, I understand the basic JDBC and Transactions concepts. Also, I learned how to use JDBC and design patterns, such as DAO and CRUD.
# ER Diagram
## Loading Data
1. Provision a new psql instance on a Docker container by running [psql_docker.sh](../../linux_sql/scripts/psql_docker.sh).
2. Log into database:

    `psql -h localhost -U postgres -d hplussport`
3. Create starter data(under the directory which contains the `.sql` files):
    ```sh
    psql -h localhost -U postgres -f database.sql
    psql -h localhost -U postgres -d hplussport -f customer.sql
    psql -h localhost -U postgres -d hplussport -f product.sql
    psql -h localhost -U postgres -d hplussport -f salesperson.sql
    psql -h localhost -U postgres -d hplussport -f orders.sql
    ```
## ER Diagram 

![ER diagram](./assets/jdbc-er.png)

There are five tables in the database `hplussport`:

`order_item`: contain the order id and the products id included in this order.  
`orders`: contain the order information such as status, date, customer_id and salesperson_id.  
`customer`: contain the customer information such as name email and address.  
`products`: contain the products information such as name, size and variety.  
`salesperson`: contain the salesperson information such as name email and address.


# Design Patterns
The Data Access Object(DAO) pattern and the Repository pattern are both used to abstract persistence access details. 
However, the Repository is a higher-level concept dealing directly with business/domain objects while DAO is lower level, 
closer to the database/storage dealing only with data. The repository could be implemented using DAO?s, but we could not do the opposite.
For example, a Repository should be simply a collection of objects, like how to find 'customer_id' from `orders` 
and then find the corresponding customer in the table of `customer`. But in DAO, we can `JOIN` those two tables, `orders` and `customer`, by foreign keys.