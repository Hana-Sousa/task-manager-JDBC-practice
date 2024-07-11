package model.dao;

import db.DB;
import model.dao.impl.TaskDaoJDBC;

public class DaoFactory {
    public static TaskDao createTaskDao() {return new TaskDaoJDBC(DB.getConnection());}
}
