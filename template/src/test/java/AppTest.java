import org.junit.*;
import static org.junit.Assert.*;

public class AppTest {
  Sql2o sql2o = new Sql2o("jdbc:mysql://localhost:5432/todo-development", "jake", "password");
}
