import java.util.List;
import org.sql2o.*;

public class Task {
  private int id;
  private int categoryId;
  private String description;

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public Task(String description, int categoryId) {
    this.description = description;
    this.categoryId = categoryId;
    id = save(description, categoryId);
  }

  public static List<Task> all() {
    String sql = "SELECT id, description FROM Tasks";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Task.class);
    }
  }

  public int save(String description, int categoryId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO Tasks(description, categoryId) VALUES (:description, :categoryId)";
      int id = (int) con.createQuery(sql, true)
        .addParameter("description", description)
        .addParameter("categoryId", categoryId)
        .executeUpdate()
        .getKey();
        return id;
    }
  }

  public static Task find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Tasks where id=:id";
      Task task = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Task.class);
      return task;
    }
  }

  public void update(String description, int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tasks SET description = :description) WHERE id = :id";
      con.createQuery(sql)
        .addParameter("description", description)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM tasks WHERE id = :id;";
      con.createQuery(sql)
  }
}
