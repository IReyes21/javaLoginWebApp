/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import org.flywaydb.core.Flyway;

import java.sql.*;

/**
 *
 * @author Gokhan
 */
public class DB {
    private static final String DB_URL = "jdbc:derby:shoestoredb;create=true;user=shoestoredb;password=shoestoredb";
    private static final String MIGRATION_DIR = "classpath:db.migrations";
    private static DB instance = null;
    private Connection mConnection;

    private DB() throws SQLException {
        migrateDb();
        mConnection = DriverManager.getConnection(DB_URL);
    }
    
    /**
     * This is the Singleton method that returns only one database instance
     * @return 
     */
    public static DB getInstance() {
        if (instance == null) {
            try {
                instance = new DB();
            } catch (SQLException ex) {
                System.err.println("Database Error: " + ex.toString());
                ex.printStackTrace();
                return null;
            }
        }
        return instance;
    }
    
    /**
     * Use this method for INSERT, UPDATE, DELETE
     * @param sql
     * @return
     * @throws SQLException 
     */
    public int executeUpdate(String sql) throws SQLException {
        return mConnection.createStatement().executeUpdate(sql);
    }

    /**
     * Use this method to return ResultSet for SELECT SQL queries with no parameters
     * @param sql
     * @return
     * @throws SQLException 
     */
    public ResultSet executeQuery(String sql) throws SQLException {
        return mConnection.createStatement().executeQuery(sql);
    }

    /**
     * Use this method to return PreparedStatement for SELECT SQL queries with parameters 
     * @param sql
     * @return
     * @throws SQLException 
     */
    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return mConnection.prepareStatement(sql);
    }
    
    /**
     * This migrateDB method is used to create tables under db.migrations package
     * if they are not created before
     * We use FlyWay class from flyway library to perform the migration in our code
     */
    private void migrateDb() {
        System.out.println("Starting database migration...");

        try {
            Flyway flyway = Flyway.configure()
                    .dataSource(DB_URL, null, null)
                    .locations(MIGRATION_DIR)
                    .baselineOnMigrate(true)
                    .validateOnMigrate(false)  // ← SKIP VALIDATION
                    .load();

            flyway.migrate();
            System.out.println("Database migration completed successfully!");

        } catch (Exception e) {
            System.err.println("Migration error (continuing anyway): " + e.getMessage());
            // Don't rethrow - let application continue
        }

    }
}
