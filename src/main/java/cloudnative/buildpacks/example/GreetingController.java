package cloudnative.buildpacks.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  @GetMapping
  public String hello() {
    return "Hello Cloud Native World!";
  }
}
