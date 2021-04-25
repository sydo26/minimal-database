package com.sydo26.database;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Table extends Schema {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    public String tableName;

    public Table(String name) {
        super("table_" + name);
        this.tableName = name;
    }

    public void run(Query query) {
        try {
            query.st().executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();

            logger.error("Não foi possível criar a tabela {}! {}", tableName, e.getMessage());
            logger.error("Não foi possível executar a sequinte query: {}", queryString);
        }
    }

}
