# SQL-Builder
A Java MySQL api to easy built and run MySQL statements based on JDBC

## Usage
1. create a DataBaseConnection object based on connection infos from
   + simple string ⇨ see DataBaseConnection#DataBaseConnection(String, String, String, String)
   + config file → see the [belonging section](#config-file) and DataBaseConnection#DataBaseConnection(String or Properties)
2. build [sql statements](#sql-builder)
3. execute the statements

## SQL Builder
1. Select 
2. Insert
3. Update
4. Delete

## Config file
A config file which is used to create a new database connection must include the following properties:
````
server = 127.0.0.1
user = root
password = 123456
scheme = Example
````