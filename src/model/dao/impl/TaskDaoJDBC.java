package model.dao.impl;

import db.DB;
import db.DbException;
import entities.Task;
import model.dao.TaskDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskDaoJDBC implements TaskDao {

    private Connection conn;

    public TaskDaoJDBC(Connection conn){this.conn = conn;}

    @Override
    public Task findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement("SELECT * FROM tasks WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Task obj = instantiateTask(rs);
                return obj;

            }
            else{return null;}
        }
        catch (SQLException e ){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Task instantiateTask(ResultSet rs) throws SQLException {
        Task task = new Task();
        task.setId(rs.getInt("id"));
        task.setTitle(rs.getString("title"));
        task.setDescription(rs.getString("description"));
        task.setDue_date(rs.getDate("due_date"));
        task.setPriority(rs.getString("priority"));
        task.setStatus(rs.getString("status"));

        return task;

    }


}

