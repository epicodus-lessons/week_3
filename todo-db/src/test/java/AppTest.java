import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DataBaseRule database = new DataBaseRule();

  @Test
  public void categoryRoute_displaysCategoryName() {
    Category myCategory = new Category("Household chores");
    String categoryPath = String.format("http://localhost:4567/%d", myCategory.getId());
    goTo(categoryPath);
    assertThat(pageSource()).contains("Household chores");
  }
}
