import org.junit.*;
import static org.junit.Assert.*;

public class ActorTest {

  @Rule
  public DataBaseRule database = new DataBaseRule();

  @Test
  public void all_savesIntoDatabase_true() {
    Actor myActor = new Actor("Angelina Jolie");
    assertEquals(Actor.all().get(0).getName(), "Angelina Jolie");
  }

  @Test
  public void find_returnsAActor() {
    Actor myActor = new Actor("Angelina Jolie");
    Actor otherActor = new Actor("Jesse Eisenberg");
    assertEquals(Actor.find(otherActor.getId()).getName(), "Jesse Eisenberg");
  }

  @Test
  public void update_updatesActorsInDatabase() {
    Actor myActor = new Actor("Angelina Jolie");
    myActor.update("Jesse Eisenberg");
    assertEquals(Actor.find(myActor.getId()).getName(), "Jesse Eisenberg");
  }

  @Test
  public void delete_removesAActorFromDatabase() {
    Actor myActor = new Actor("Angelina Jolie");
    Actor otherActor = new Actor("Jesse Eisenberg");
    myActor.delete();
    assertEquals(Actor.all().size(), 1);
  }

  @Test
  public void update_allowsAssociationToAMovie() {
    Movie myMovie = new Movie("Hackers");
    Actor myActor = new Actor("Angelina Jolie");
    myActor.update(myMovie.getId());
    assertEquals(myActor.movies().get(0).getName(), myMovie.getName());
  }
}
