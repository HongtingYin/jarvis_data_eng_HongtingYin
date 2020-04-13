package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCExecutor {

    private static Logger logger = LoggerFactory.getLogger(JDBCExecutor.class);

    public static void main(String... args){
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "postgres", "hplussport", "docker");
        try {

            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);

            logger.info("Update a record:");
            Customer customer = customerDAO.findById(10000);
            customer.setEmail("gwashington@wh.gov");
            customer = customerDAO.update(customer);
            logger.info(customer.getFirstName() + " "
                    + customer.getLastName() + " "
                    + customer.getEmail()
            );

        }catch(SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
