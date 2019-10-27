package us.flexion.convertunits;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConvertUnitsApplication {
  private static final Logger logger = LogManager.getLogger(ConvertUnitsApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(ConvertUnitsApplication.class, args);
  }

  // Get a warm fuzzy relatively early on during initialization/startup of the application.
  @PostConstruct
  public void logSomething() {
    logger.trace("in logSomething()");
  }
}
