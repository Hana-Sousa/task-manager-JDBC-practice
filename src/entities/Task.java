package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Task implements Serializable {
    // Attributes and Constructors
    Integer id;
    String title;
    String description;
    Date due_date;
    String priority;
    String status;

    public Task(Integer id, String title, String description, Date due_date, String priority, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.due_date = due_date;
        this.priority = priority;
        this.status = status;
    }

    public Task(){}

    // Getters and Setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // HashCode and Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // ToString method
    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                "id='" + id + '\'' +
                '}';
    }
}
