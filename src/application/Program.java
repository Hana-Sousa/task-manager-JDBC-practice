package application;

import db.DB;
import db.DbException;
import entities.Task;
import model.dao.DaoFactory;
import model.dao.TaskDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Program {
    public static void main(String[] args) {
        TaskDao taskDao = DaoFactory.createTaskDao();

        System.out.println("===== Test 1: Task findById ====");
        Task task = taskDao.findById(3);
        System.out.println(task);

    }
}
