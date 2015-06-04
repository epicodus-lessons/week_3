import org.junit.*;
import static org.junit.Assert.*;

public class TaskTest {

  @Rule
  public DataBaseRule database = new DataBaseRule();

  @Test
  public void all_savesIntoDatabase_true() {
    Task myTask = new Task("Mow the lawn", 1);
    assertEquals(Task.all().get(0).getDescription(), "Mow the lawn");
  }

  @Test
  public void find_findsTaskInDatabase_true() {
    Task myTask = new Task("Mow the lawn", 1);
    Task savedTask = Task.find(myTask.getId());
    assertEquals(savedTask.getDescription(), "Mow the lawn");
  }

  @Test
  public void save_savesCategoryIdIntoDB_true() {
    Category myCategory = new Category("Household chores");
    Task myTask = new Task("Mow the lawn", myCategory.getId());
    Task savedTask = Task.find(myTask.getId());
    assertEquals(savedTask.getCategoryId(), myCategory.getId());
  }
}
