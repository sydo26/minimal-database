package com.sydo26.database;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Database {

    public static final Logger logger = LoggerFactory.getLogger(Database.class);

    private Driver driver;
    private Query query;
    private Map<String, Schema> schemas;

    private boolean success = true;

    public Database(String host, String port, String database, String user, String password, String driver) {
        this.driver = new Driver(host, port, database, driver);

        if (this.driver.connect(user, password)) {
            logger.info("Conectado ao banco de dados com sucesso!");
        } else {
            logger.error("A conexão com o banco de dados falhou!");
            this.success = false;
            return;
        }

        this.query = new Query();
        if (this.query.init(this.driver)) {
            logger.info("Statement criado com sucesso!");
        } else {
            logger.error("A criação do statement falhou!");
            this.success = false;
            return;
        }

        this.schemas = new HashMap<>();
    }

    public void callSchemas() {
        logger.info("Executando todos os schemas.");
        for (Schema schema : schemas.values()) {
            schema.run(this.query);
        }
    }

    public void addSchema(Schema schema) {
        if (success) {
            this.schemas.put(schema.getSchemaName(), schema);
        }
    }

    public Query q() {
        return success ? query : null;
    }

    public Connection c() {
        return driver.getConnection();
    }

    public void close() {
        if (this.driver.close()) {
            logger.info("Conexão com o banco de dados finalizada com sucesso!");
        } else {
            logger.error("Não foi possível finalizar a conexão com o banco de dados!");
        }
        if (this.query.close()) {
            logger.info("Statement encerrado com sucesso!");
        } else {
            logger.error("Não foi possível encerrar o Statement!");
        }

    }

}
