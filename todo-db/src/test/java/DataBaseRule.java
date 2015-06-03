import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DataBaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do_test", "jake", "password");
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM tasks *;";
      con.createQuery(sql).executeUpdate();
    }
  }
}
