import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Movie {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Movie(String name) {
    this.name = name;
    id = save(name);
  }

  public static List<Movie> all() {
    String sql = "SELECT id, name FROM movies";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Movie.class);
    }
  }

  public int save(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO Movies(name) VALUES (:name)";
      int id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .executeUpdate()
        .getKey();
        return id;
    }
  }

  public static Movie find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM movies where id=:id";
      Movie movie = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Movie.class);
      return movie;
    }
  }

  public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE movies SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void update(int actorId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO actors_movies (actorId, movieId) VALUES (:actorId, :movieId)";
      con.createQuery(sql)
        .addParameter("actorId", actorId)
        .addParameter("movieId", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM Movies WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public List<Actor> actors() {
    try(Connection con = DB.sql2o.open()) {
      List<Actor> actors = new ArrayList<Actor>();
      String sql = "SELECT actorId FROM actors_movies WHERE movieId = :movieId";
      List<Integer> actorIds = con.createQuery(sql)
        .addParameter("movieId", id)
        .executeAndFetch(Integer.class);
      for ( Integer id : actorIds) {
        Actor actor = Actor.find(id.intValue());
        actors.add(actor);
      }
      return actors;
    }
  }
}
