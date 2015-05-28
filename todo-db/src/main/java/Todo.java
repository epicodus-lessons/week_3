import java.util.List;

import org.sql2o.*;

public class Todo {
  private int id;
  private String description;
  static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/todo-development", "jake", "password");

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public Todo(String description) {
    save(description);
  }

  public static List<Todo> all() {
    String sql = "SELECT id, description FROM todos";
    try(Connection con = sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Todo.class);
    }
  }

  public boolean save(String description) {
    try(Connection con = sql2o.open()) {
      String sql = "INSERT INTO todos(description) VALUES (:description)";
      con.createQuery(sql)
        .addParameter("description", description)
        .executeUpdate();
        return true;
    }
  }

  public static Todo find(int id) {
    try(Connection con = sql2o.open()) {
      String sql = "SELECT * FROM todos where id=:id";
      Todo todo = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Todo.class);
      return todo;
    }
  }
}
