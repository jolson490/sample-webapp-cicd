package us.flexion.convertunits;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import us.flexion.convertunits.model.Problem;
import us.flexion.convertunits.units.temperature.Celsius;
import us.flexion.convertunits.units.temperature.Fahrenheit;

@Controller
public class UnitsController {
  private static final Logger logger = LogManager.getLogger(UnitsController.class);

  // curl -X GET http://localhost:8080/convertunits/
  @RequestMapping("/")
  public String home(Model model) {
    logger.trace("in home()");
    return "index";
  }

  private void addTemperaturesToNamedAttribute(Model model, String attributeName) {
    Map<String, String> units = new LinkedHashMap<String, String>();
    units.put(Celsius.class.getCanonicalName(), Celsius.class.getSimpleName());
    units.put(Fahrenheit.class.getCanonicalName(), Fahrenheit.class.getSimpleName());
    model.addAttribute(attributeName, units);
  }

  private void addTemperatureAttributes(Model model) {
    addTemperaturesToNamedAttribute(model, "inputUnits");
    addTemperaturesToNamedAttribute(model, "targetUnits");
  }

  // curl -X GET http://localhost:8080/convertunits/checkAnswer
  @RequestMapping(value = "/checkAnswer", method = RequestMethod.GET)
  public String checkResponse(Model model) {
    logger.trace("in checkResponse()");
    model.addAttribute("problemAttribute", new Problem());
    addTemperatureAttributes(model);
    return "checkAnswer";
  }

  @RequestMapping(value = "/checkAnswer", method = RequestMethod.POST)
  public String checkResponsePost(@ModelAttribute("problemAttribute") Problem theBoundProblem, BindingResult result, Model model) {
    logger.trace("in checkResponsePost(): theBoundProblem={} result.hasErrors()={}", theBoundProblem, result.hasErrors());
    addTemperatureAttributes(model);
    theBoundProblem.setProblemOutput("correct"); // TODO actually check the student's answer
    return "checkAnswer";
  }
} // class
