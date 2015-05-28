
import java.util.HashMap;
import java.util.List;

import org.sql2o.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/todos.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/todos", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Todo> todos = Todo.all();
      model.put("todos", todos);
      model.put("template", "templates/todos.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/todos/:id", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Todo todo = Todo.find(id);
      model.put("todo", todo);
      model.put("template", "templates/todo.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/todos", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String description = request.queryParams("description");
      Todo newTodo = new Todo(description);
      response.redirect("/todos");
      return null;
    });
  }
}
