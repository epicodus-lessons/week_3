import java.time.LocalDateTime;

import org.junit.*;
import static org.junit.Assert.*;

public class TaskTest {

  @Rule
  public DataBaseRule database = new DataBaseRule();

  @Test
  public void taskall_savesIntoDatabase_true() {
    Task myTask = new Task("Mow the lawn");
    assertEquals(Task.all().get(0).getDescription(), "Mow the lawn");
  }

  @Test
  public void find_findsTaskInDatabase_true() {
    Task myTask = new Task("Mow the lawn");
    Task savedTask = Task.find(myTask.getId());
    assertEquals(savedTask.getDescription(), "Mow the lawn");
  }

}
