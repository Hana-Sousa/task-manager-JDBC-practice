package model.dao.impl;

import db.DB;
import db.DbException;
import entities.Task;
import model.dao.TaskDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    @Override
    public void insert(Task obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO tasks " +
                            "(Id, Title, Description, Due_date, Priority, Status ) " +
                            "VALUES (?, ?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getId());
            st.setString(2, obj.getTitle());
            st.setString(3, obj.getDescription());
            st.setDate(4, obj.getDue_date());
            st.setString(5, obj.getPriority());
            st.setString(6, obj.getStatus());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    obj.setId(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            }
            else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }


    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM tasks WHERE id = ?");
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("No task found with the given id: " + id);
            }

        } catch (SQLException e) {
            throw new DbException("Error deleting task: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void updateById(Task task) {

    }

    @Override
    public List<Task> findAll() {
        return List.of();
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

