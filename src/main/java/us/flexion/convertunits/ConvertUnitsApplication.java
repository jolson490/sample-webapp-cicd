package us.flexion.convertunits;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConvertUnitsApplication extends SpringBootServletInitializer {
  private static final Logger logger = LogManager.getLogger(ConvertUnitsApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(ConvertUnitsApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(ConvertUnitsApplication.class);
  }

  // Get a warm fuzzy relatively early on during initialization/startup of the application.
  @PostConstruct
  public void logSomething() {
    logger.trace("in logSomething()");
  }
}

// Just to test out a REST controller
@RestController
class GreetingController {
  // e.g. http://localhost:8080/convertunits/hello/JoshO
  @RequestMapping("/hello/{name}")
  public String hello(@PathVariable String name) {
    return "Hello, " + name + "!";
  }
}
