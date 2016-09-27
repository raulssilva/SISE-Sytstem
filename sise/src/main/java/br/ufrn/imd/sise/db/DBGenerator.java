package br.ufrn.imd.sise.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Fonte https://www.tutorialspoint.com/sqlite/sqlite_java.htm
 * @Editado_por Felipe
 */
public class DBGenerator {

	
	private Connection connection = null;
 
    public DBGenerator() {
    	
        try {
        	JdbcSQLiteConnection jdbcSQLiteConnection = JdbcSQLiteConnection.getInstance(); 
            this.connection = jdbcSQLiteConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
	public void generateDataBase(){
		
		Statement stmt = null;
		try {
			System.out.println("Opened database successfully");

			stmt = connection.createStatement();
			
			//ADD A criação de tabelas aqui pra não precisar ler de arquiv
			String sql = "CREATE TABLE IF NOT EXISTS USER("
								+ "ID_USER INT NOT NULL PRIMARY KEY,"
								+ "NAME VARCHAR(60) NOT NULL,"
								+ "ID_STUDENT INT NOT NULL"
								+ "ID_MATRICULATION INT NOT NULL"
						+ ");";
			
			stmt.executeUpdate(sql);
			stmt.close();
			connection.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

}
