package com.sydo26.database;

import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Query {

    public static final Logger logger = LoggerFactory.getLogger(Query.class);

    private Statement statement;

    public Query() {
        //
    }

    public boolean init(Driver driver) {
        try {
            this.statement = driver.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Algum de errado aconteceu! {}", e.getMessage());
            return false;
        }

        return true;
    }

    public Statement st() {
        return this.statement;
    }

    public boolean close() {
        try {
            this.statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Não foi possível fechar o statement da Query! {}", e.getMessage());
            return false;
        }

        return true;
    }

}
