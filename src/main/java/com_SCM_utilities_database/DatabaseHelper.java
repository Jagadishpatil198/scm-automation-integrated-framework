package com_SCM_utilities_database;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseHelper {

    // Logger for logging errors or information
	private static final Logger logger = LogManager.getLogger(DatabaseHelper.class);


    // Connection and ResultSet objects should not be static unless really needed
    private Connection con = null;
    private ResultSet result = null;
   // private FileUtility fileUtility = new FileUtility();

    /**
     * Establishes a connection to the database using provided credentials.
     * @param url Database URL
     * @param username Database username
     * @param password Database password
     */
    public void getDbConnection(String url, String username, String password) {
        try {
            Driver driverRef = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driverRef);
            con = DriverManager.getConnection(url, username, password);
            logger.info("Database connection established successfully.");
        } catch (SQLException e) {
            logger.error("Failed to establish database connection: " + e.getMessage(), e);
        }
    }

    /**
     * Connects to the database using credentials from the properties file.
     * @throws SQLException If a database access error occurs
     */
    public void connectToDB() throws SQLException {
        try {
            Driver driverRef = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driverRef);
         //   String url = fileUtility.getDataFromPropetyFile("DBUrl");
         //   String username = fileUtility.getDataFromPropetyFile("DB_Username");
         //   String password = fileUtility.getDataFromPropetyFile("DB_Password");
           // con = DriverManager.getConnection(url, username, password);
            logger.info("Database connection established using credentials from properties file.");
        } catch (SQLException e) {
            logger.error("Error while connecting to the database: " + e.getMessage(), e);
            throw e;  // Rethrow to allow calling methods to handle it
        }
    }

    /**
     * Executes a query and returns the result set.
     * @param query The SQL query to be executed
     * @return The result set obtained from executing the query
     */
    public ResultSet executeQuery(String query) {
        try {
            Statement statement = con.createStatement();
            result = statement.executeQuery(query);
            logger.info("Query executed successfully: " + query);
        } catch (SQLException e) {
            logger.error("Query execution failed: " + e.getMessage(), e);
        }
        return result;
    }

    /**
     * Executes a non-select query (INSERT, UPDATE, DELETE).
     * @param query The SQL query to be executed
     * @return The number of rows affected by the query
     */
    public int executeNonSelectQuery(String query) {
        int rowsAffected = 0;
        try (Statement statement = con.createStatement()) {
            rowsAffected = statement.executeUpdate(query);
            logger.info("Non-select query executed successfully: " + query);
        } catch (SQLException e) {
            logger.error("Non-select query execution failed: " + e.getMessage(), e);
        }
        return rowsAffected;
    }

    /**
     * Executes a SELECT query and verifies the data in the specified column.
     * @param query The SQL query to execute
     * @param columnIndex The column index to verify the data
     * @param expectedData The expected data to compare
     * @return true if the expected data is found, false otherwise
     */
    public boolean executeSelectQueryVerifyAndGetData(String query, int columnIndex, String expectedData) {
        boolean isVerified = false;
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                if (resultSet.getString(columnIndex).equals(expectedData)) {
                    isVerified = true;
                    break;
                }
            }
            if (isVerified) {
                logger.info("Data verified successfully: " + expectedData);
            } else {
                logger.warn("Data not verified: " + expectedData);
            }
        } catch (SQLException e) {
            logger.error("Error during data verification: " + e.getMessage(), e);
        }
        return isVerified;
    }

    /**
     * Closes the database connection.
     * @throws SQLException If an error occurs while closing the connection
     */
    public void closeDbConnection() throws SQLException {
        if (con != null && !con.isClosed()) {
            try {
                con.close();
                logger.info("Database connection closed successfully.");
            } catch (SQLException e) {
                logger.error("Error while closing database connection: " + e.getMessage(), e);
                throw e;
            }
        }
    }

    /**
     * Helper method to check if the connection is established.
     * @return true if the connection is valid, false otherwise
     * @throws SQLException If a database access error occurs
     */
    public boolean isConnectionValid() throws SQLException {
        if (con != null && con.isValid(2)) {
            logger.info("Database connection is valid.");
            return true;
        } else {
            logger.warn("Database connection is invalid.");
            return false;
        }
    }
}

