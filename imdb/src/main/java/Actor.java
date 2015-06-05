import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Actor {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Actor(String name) {
    this.name = name;
    id = save(name);
  }

  public static List<Actor> all() {
    String sql = "SELECT id, name FROM actors";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Actor.class);
    }
  }

  public int save(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO actors(name) VALUES (:name)";
      int id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .executeUpdate()
        .getKey();
        return id;
    }
  }

  public static Actor find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM actors where id=:id";
      Actor Actor = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Actor.class);
      return Actor;
    }
  }

  public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE actors SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void update(int movieId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO actors_movies (actorId, movieId) VALUES (:actorId, :movieId)";
      con.createQuery(sql)
        .addParameter("actorId", id)
        .addParameter("movieId", movieId)
        .executeUpdate();
    }
  }

  public List<Movie> movies() {
    try(Connection con = DB.sql2o.open()) {
      List<Movie> movies = new ArrayList<Movie>();
      String sql = "SELECT movieId FROM actors_movies WHERE actorId = :actorId";
      List<Integer> movieIds = con.createQuery(sql)
        .addParameter("actorId", id)
        .executeAndFetch(Integer.class);
      for ( Integer id : movieIds) {
        Movie movie = Movie.find(id.intValue());
        movies.add(movie);
      }
      return movies;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM Actors WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}
