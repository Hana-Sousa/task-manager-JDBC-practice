package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    // function to get Connection from a given properties file
    public static Connection getConnection() {
        if (conn == null){
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    // auxiliary function to load the properties of database on the "properties" file
    private static Properties loadProperties(){
        try (FileInputStream fs = new FileInputStream("/Users/hanags/curso/task-management/src/db/properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch (IOException e ){
            throw new DbException(e.getMessage());
        }
    }

    // function to close connection after the process finish
    public static void closeConnection(){
        if (conn != null){
            try{
                conn.close();
            }
            catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    // function to close statement handling the exceptions
    public static void closeStatement(Statement st){
        if (st != null){
            try {
                st.close();
            }
            catch (SQLException e){
                throw new DbException(e.getMessage());
            }

        }
    }

    // function to close the result set handling the exceptions
    public static void closeResultSet(ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            }
            catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

}
