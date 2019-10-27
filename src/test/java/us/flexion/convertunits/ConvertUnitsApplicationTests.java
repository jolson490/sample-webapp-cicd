package us.flexion.convertunits;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConvertUnitsApplicationTests {
  private static final Logger logger = LogManager.getLogger(ConvertUnitsApplicationTests.class);

  @Test
  void contextLoads() {
    logger.trace("in contextLoads()");
  }

}
