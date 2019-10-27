package us.flexion.convertunits;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UnitsController {
  private static final Logger logger = LogManager.getLogger(UnitsController.class);

  // curl -X GET http://localhost:8080/convertunits/
  @RequestMapping("/")
  public String home(Model model) {
    logger.debug("in home()");

    // model.addAttribute("lastUpdated", AppModifiedTime.getTime());

    return "index";
  }

} // class
