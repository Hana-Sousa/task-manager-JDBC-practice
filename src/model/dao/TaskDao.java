package model.dao;

import entities.Task;
import java.util.*;

public interface TaskDao {
    Task findById(Integer id);
    void insert(Task task);
    void deleteById(Integer id);
    List<Task> findAll();

    void updateById(Task task);

}
