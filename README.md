# SQL-Builder
A Java MySQL api for an easy creation and execution of MySQL statements

## Usage
1. create a DataBaseConnection object based on connection infos from
   + simple string ⇨ see DataBaseConnection#DataBaseConnection(String, String, String, String)
   + config file ⇨ see the [belonging section](#config-file) and DataBaseConnection#DataBaseConnection(String)
   + example: ``DataBaseConnection dataBaseConnection = new DataBaseConnection("config.properties");``
2. build a [sql statement](#sql-builder)
3. execute the statement
4. work with the return of the execution

## SQL Builder Examples
1. Select
   Possible configurations:
   + select
   + from
   + where
   + join 
   + order by
   
   Example:
    ````
    SelectBuilder selectBuilder = new SelectBuilder(dataBaseConnection);
    selectBuilder.select("attr").from("table").where("id", 1);
    ResultSet result = selectBuilder.execute();
    ````
2. Contains
   Possible configurations:
   + table
   + filter / where
   
   Example:
    ````
    ContainsBuilder cotainsBuilder = new ContainsBuilder(dataBaseConnection);
    cotainsBuilder.table("table").filter("id", 1);
    boolean result = cotainsBuilder.execute();
    ````
3. Insert
   Possible configurations:
   + into
   + values
   
   Example:
    ````
    InsertBuilder insertBuilder = new InsertBuilder(dataBaseConnection);
    insertBuilder.into("table").value("attr1", "Test").value("attr2", 5);
    insertBuilder.execute();
    ````
4. Update
   Possible configurations:
   + table
   + values
   + where
   
   Example:
    ````
    UpdateBuilder updateBuilder = new UpdateBuilder(dataBaseConnection);
    updateBuilder.update("table").set("attr1", "value").set("attr2", false).where("id", 3);
    updateBuilder.execute();
    ````
5. Delete
   Possible configurations:
   + table
   + where
   
   Example:
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
