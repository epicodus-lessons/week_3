import org.junit.*;
import static org.junit.Assert.*;

public class MovieTest {

  @Rule
  public DataBaseRule database = new DataBaseRule();

  @Test
  public void all_savesIntoDatabase_true() {
    Movie myMovie = new Movie("Hackers");
    assertEquals(Movie.all().get(0).getName(), "Hackers");
  }

  @Test
  public void find_returnsAMovie() {
    Movie myMovie = new Movie("Hackers");
    Movie otherMovie = new Movie("The Social Network");
    assertEquals(Movie.find(otherMovie.getId()).getName(), "The Social Network");
  }

  @Test
  public void update_updatesMoviesInDatabase() {
    Movie myMovie = new Movie("Hackers");
    myMovie.update("The Social Network");
    assertEquals(Movie.find(myMovie.getId()).getName(), "The Social Network");
  }

  @Test
  public void update_allowsAssociationToAMovie() {
    Movie myMovie = new Movie("Hackers");
    Actor myActor = new Actor("Angelina Jolie");
    myMovie.update(myActor.getId());
    assertEquals(myMovie.actors().get(0).getName(), myActor.getName());
  }

  @Test
  public void delete_removesAMovieFromDatabase() {
    Movie myMovie = new Movie("Hackers");
    Movie otherMovie = new Movie("The Social Network");
    myMovie.delete();
    assertEquals(Movie.all().size(), 1);
  }
}
