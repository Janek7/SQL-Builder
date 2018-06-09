# SQL-Builder
A Java MySQL api to easy built and run MySQL statements based on JDBC

## Usage
1. create a DataBaseConnection object based on connection infos from
   + simple string ⇨ see DataBaseConnection#DataBaseConnection(String, String, String, String)
   + config file ⇨ see the [belonging section](#config-file) and DataBaseConnection#DataBaseConnection(String or Properties)
   + example: ``DataBaseConnection dataBaseConnection = new DataBaseConnection("config.properties");``
2. build [sql statements](#sql-builder)
3. execute the statements
4. work with the return of the execution

## SQL Builder Examples
1. Select
    ````
    SelectBuilder selectBuilder = new SelectBuilder(dataBaseConnection);
    selectBuilder.select("attr").from("table").where("id", 1);
    ResultSet result = selectBuilder.execute();
    ````
2. Cotains
    ````
    ContainsBuilder cotainsBuilder = new ContainsBuilder(dataBaseConnection);
    cotainsBuilder.table("table").filter("id", 1);
    boolean result = cotainsBuilder.execute();
    ````
3. Insert
    ````
    InsertBuilder insertBuilder = new InsertBuilder(dataBaseConnection);
    insertBuilder.into("table").value("attr1", "Test").value("attr2", 5);
    insertBuilder.execute();
    ````
4. Update
    ````
    UpdateBuilder updateBuilder = new UpdateBuilder(dataBaseConnection);
    updateBuilder.update("table").set("attr1", "value").set("attr2", false).where("id", 3);
    updateBuilder.execute();
    ````
5. Delete
    ````
    DeleteBuilder deleteBuilder = new DeleteBuilder(dataBaseConnection);
    deleteBuilder.from("table").where("id", 1);
    deleteBuilder.execute();
    ````

## Config file
A config file which is used to create a new database connection must include the following properties:
````
server = 127.0.0.1
user = root
password = 123456
scheme = Example
````