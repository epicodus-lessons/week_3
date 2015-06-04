import org.junit.*;
import static org.junit.Assert.*;

public class CategoryTest {

  @Rule
  public DataBaseRule database = new DataBaseRule();

  @Test
  public void all_savesIntoDatabase_true() {
    Category myCategory = new Category("Household chores");
    assertEquals(Category.all().get(0).getName(), "Household chores");
  }

  @Test
  public void find_findsCategoryInDatabase_true() {
    Category myCategory = new Category("Household chores");
    Category savedCategory = Category.find(myCategory.getId());
    assertEquals(savedCategory.getName(), "Household chores");
  }
}
