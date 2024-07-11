package model.dao;

import entities.Task;

public interface TaskDao {
    Task findById(Integer id);
}
