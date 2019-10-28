package us.flexion.convertunits;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
import us.flexion.convertunits.units.AUnit;
import us.flexion.convertunits.units.Measurement;
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
    determineOutput(theBoundProblem);
    return "checkAnswer";
  }

  private void determineOutput(Problem theBoundProblem) {
    final AUnit inputUnitObject = getUnitObjectFromName(theBoundProblem.getInputUnit());
    final Measurement inputMeasurement = new Measurement(theBoundProblem.getInputValue(), inputUnitObject);

    final AUnit targetUnitObject = getUnitObjectFromName(theBoundProblem.getTargetUnit());
    final double convertedValue = targetUnitObject.convertTo(inputMeasurement).getValue();

    final double convertedValueRounded = Math.round(convertedValue * 10.0) / 10.0; // round to the nearest tenth

    final double studentResponseRounded = Math.round(theBoundProblem.getStudentResponse() * 10.0) / 10.0;
    logger.trace("checkResponsePost(): convertedValue={} convertedValueRounded={} studentResponseRounded={}", convertedValue, convertedValueRounded, studentResponseRounded);

    final String output = convertedValueRounded == studentResponseRounded ? "correct" : "incorrect";

    theBoundProblem.setProblemOutput(output);
  }

  private AUnit getUnitObjectFromName(String canonicalName) {
    logger.trace("in getUnitObjectFromName(): canonicalName={}", canonicalName);
    AUnit unit = null;
    try {
      Class<?> clasz = Class.forName(canonicalName);
      Constructor<?>[] allConstructors = clasz.getConstructors();
      Class<?>[] parametersOfConstructor1 = allConstructors[0].getParameterTypes();
      Constructor<?> constructor = clasz.getConstructor(parametersOfConstructor1);
      unit = (AUnit) constructor.newInstance();
      logger.debug("getUnitObjectFromName(): unit={}", unit);
    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
      e.printStackTrace();
    }
    return unit;
  }
} // class
