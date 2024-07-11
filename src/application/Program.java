package application;

import db.DB;
import db.DbException;
import entities.Task;
import model.dao.DaoFactory;
import model.dao.TaskDao;

import java.sql.*;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        TaskDao taskDao = DaoFactory.createTaskDao();
        Scanner sc = new Scanner(System.in);


        System.out.println("===== Test 1: Task findById ====");
        System.out.println("Insert an id: ");
        int id = sc.nextInt();

        Task task = taskDao.findById(id);
        System.out.println(task);

        System.out.println("===== Test 2: Task deleteById ====");
        System.out.println("Insert an id: ");
        id = sc.nextInt();

        taskDao.deleteById(id);
        System.out.println("Deleted!");

        System.out.println("===== Test 3: Task insert ====");
        Task newTask = new Task(8, "Create Map", "Learn Java and PostGis to create a web application", new Date(12/8/2024),"High", "In Progress" );
        taskDao.insert(newTask);
        System.out.println("New Task inserted!");


        sc.close();
    }
}
