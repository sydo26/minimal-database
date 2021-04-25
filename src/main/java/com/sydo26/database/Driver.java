package com.sydo26.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver {
    public static final Logger logger = LoggerFactory.getLogger(Driver.class);

    public static final String POSTGRESQL_DRIVER = "jdbc:postgresql";

    private Connection connection;

    private String driverName;
    private String host;
    private String port;
    private String databaseName;

    public Driver(String host, String port, String databaseName, String driverName) {
        this.connection = null;
        this.driverName = driverName;
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
    }

    public boolean close() {
        if (connection != null) {
            try {
                this.connection.close();
                this.connection = null;
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("Algum de errado aconteceu! {}", e.getMessage());
                return false;
            }
        } else {
            logger.warn("Você tentou fechar uma conexão já fechada!");
        }

        return false;
    }

    public boolean connect(String user, String password) {

        if (connection != null)
            close();

        String url = driverName + "://" + host + ":" + port + "/" + databaseName;
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        props.setProperty("ssl", "false");

        try {
            this.connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Algum de errado aconteceu! {}", e.getMessage());
            return false;
        }

        return true;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public static String getPostgresqlDriver() {
        return POSTGRESQL_DRIVER;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
