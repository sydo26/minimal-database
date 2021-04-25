package com.sydo26.database;

public abstract class Schema {

    protected String queryString;
    protected String schemaName;

    Schema(String name) {
        this.schemaName = name;
    }

    protected void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    protected void define(String queryString, Object... args) {
        setQueryString(String.format(queryString, args));
    }

    public String getSchemaName() {
        return schemaName;
    }

    public abstract void run(Query query);
}
