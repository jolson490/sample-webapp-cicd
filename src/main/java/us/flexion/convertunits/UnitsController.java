package us.flexion.convertunits;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import us.flexion.convertunits.model.Problem;

@Controller
public class UnitsController {
  private static final Logger logger = LogManager.getLogger(UnitsController.class);

  // curl -X GET http://localhost:8080/convertunits/
  @RequestMapping("/")
  public String home(Model model) {
    logger.trace("in home()");
    return "index";
  }

  // curl -X GET http://localhost:8080/convertunits/checkAnswer
  @RequestMapping(value = "/checkAnswer", method = RequestMethod.GET)
  public String checkResponse(Model model) {
    logger.trace("in checkResponse()");
    model.addAttribute("problemAttribute", new Problem());
    return "checkAnswer";
  }

  @RequestMapping(value = "/checkAnswer", method = RequestMethod.POST)
  public String checkResponsePost(@ModelAttribute("problemAttribute") Problem theBoundProblem, Model model) {
    logger.trace("in checkResponsePost(): theBoundProblem={}", theBoundProblem);
    theBoundProblem.setProblemOutput("correct"); // TODO actually check the student's answer
    return "checkAnswer";
  }
} // class
