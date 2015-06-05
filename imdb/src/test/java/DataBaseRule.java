import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DataBaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/i_m_d_b_test", "jake", "password");
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteMoviesQuery = "DELETE FROM movies *;";
      String deleteActorsQuery = "DELETE FROM actors *;";
      con.createQuery(deleteMoviesQuery).executeUpdate();
      con.createQuery(deleteActorsQuery).executeUpdate();
    }
  }
}
