package us.flexion.convertunits;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppModifiedTime implements ApplicationListener<ApplicationReadyEvent> {
  private static final Logger logger = LogManager.getLogger(AppModifiedTime.class);
  private static String dateTimeUpdated = "(date/time TBD)";

  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
    logger.trace("in onApplicationEvent()");
    setTime();
  }

  private static void setTime() {
    logger.trace("in setTime() - default time zone: {}", java.util.TimeZone.getDefault());
    // The following line of code is equivalent to if the following text had been passed (on the command line) as a System Property to the java command that runs the app:
    // -Duser.timezone="America/Chicago"
    TimeZone.setDefault(TimeZone.getTimeZone("America/Chicago")); // CST
    logger.debug("setTime() - time zone changed to: {}", java.util.TimeZone.getDefault());

    try {
      final Date date = new Date(
          new File(AppModifiedTime.class.getClassLoader().getResource(AppModifiedTime.class.getCanonicalName().replace('.', '/') + ".class").toURI()).lastModified());
      final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a z");
      dateTimeUpdated = formatter.format(date);
      logger.debug("setTime() - dateTimeUpdated={}", dateTimeUpdated);
    } catch (URISyntaxException exception) {
      StringWriter errors = new StringWriter();
      exception.printStackTrace(new PrintWriter(errors));
      logger.error("getTime(): caught exception: {}", errors.toString());
    }
  }

  public static String getTime() {
    logger.trace("in getTime()");
    return dateTimeUpdated;
  }
}
