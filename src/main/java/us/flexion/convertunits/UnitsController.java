package us.flexion.convertunits;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import us.flexion.convertunits.model.Problem;
import us.flexion.convertunits.model.Problem.UnitTypes;
import us.flexion.convertunits.units.AUnit;
import us.flexion.convertunits.units.Measurement;
import us.flexion.convertunits.units.temperature.Celsius;
import us.flexion.convertunits.units.temperature.Fahrenheit;
import us.flexion.convertunits.units.temperature.Kelvin;
import us.flexion.convertunits.units.temperature.Rankine;
import us.flexion.convertunits.units.volume.CubicFoot;
import us.flexion.convertunits.units.volume.CubicInch;
import us.flexion.convertunits.units.volume.Cup;
import us.flexion.convertunits.units.volume.Gallon;
import us.flexion.convertunits.units.volume.Liter;
import us.flexion.convertunits.units.volume.Tablespoon;

@Controller
public class UnitsController {
  private static final Logger logger = LogManager.getLogger(UnitsController.class);

  // curl -X GET http://localhost:8080/convertunits/
  @RequestMapping("/")
  public String home(Model model) {
    logger.trace("in home()");
    return "index";
  }

  private void addUnitAttributes(Model model, UnitTypes unitType) {
    Map<String, String> units = new LinkedHashMap<String, String>();
    if (unitType == null) {
      // do nothing
    } else if (unitType == UnitTypes.TEMPERATURE) {
      // Ideally each temperature (and ditto for volume) would somehow be grouped together - e.g. either:
      // * a "Temperature" class that each Celsius/Fahrenheit/etc. class extends.
      // * or a class named "TemperatureTypes", which would be similar to the UnitTypes class.
      // i.e. so instead of having a line of code to 'put' each temperature into 'units', there'd be a single line of code here to add all the temperatures to 'units' (similar to
      // what's done below by 'UnitTypes.getEnumAsMap()') - so that if/when a new temperature class is created then we don't have to remember to add a corresponding line for it
      // here.
      units.put(Celsius.class.getCanonicalName(), Celsius.class.getSimpleName());
      units.put(Fahrenheit.class.getCanonicalName(), Fahrenheit.class.getSimpleName());
      units.put(Kelvin.class.getCanonicalName(), Kelvin.class.getSimpleName());
      units.put(Rankine.class.getCanonicalName(), Rankine.class.getSimpleName());
    } else if (unitType == UnitTypes.VOLUME) {
      units.put(CubicFoot.class.getCanonicalName(), CubicFoot.class.getSimpleName());
      units.put(CubicInch.class.getCanonicalName(), CubicInch.class.getSimpleName());
      units.put(Cup.class.getCanonicalName(), Cup.class.getSimpleName());
      units.put(Gallon.class.getCanonicalName(), Gallon.class.getSimpleName());
      units.put(Liter.class.getCanonicalName(), Liter.class.getSimpleName());
      units.put(Tablespoon.class.getCanonicalName(), Tablespoon.class.getSimpleName());
    } else {
      // It's not possible to get in here, but still write a line of code to log an error anyway.
      logger.error("addUnitAttributes(): encountered unexpected unitType: {}", unitType);
    }
    model.addAttribute("listUnits", units);

    final Map<String, String> listUnitTypes = UnitTypes.getEnumAsMap();
    model.addAttribute("listUnitTypes", listUnitTypes);
  }

  // curl -X GET http://localhost:8080/convertunits/checkAnswer
  @RequestMapping(value = "/checkAnswer", method = RequestMethod.GET)
  public String checkResponse(Model model) {
    logger.trace("in checkResponse()");
    model.addAttribute("problemAttribute", new Problem());
    addUnitAttributes(model, null);
    return "checkAnswer";
  }

  @RequestMapping(value = "/checkAnswer", method = RequestMethod.POST)
  public String checkResponsePost(@ModelAttribute("problemAttribute") Problem theBoundProblem, Model model) {
    logger.trace("in checkResponsePost(): theBoundProblem={}", theBoundProblem);
    addUnitAttributes(model, theBoundProblem.getUnitType());
    determineOutput(theBoundProblem);
    return "checkAnswer";
  }

  // Determines whether the student's response is correct.
  // Note that the web UI doesn't allow the possibility of invalid data being entered, so no need to have code that displays "invalid" as the output.
  private void determineOutput(Problem theBoundProblem) {
    // The following if check will not be entered when the user changes the value of the "Type of Unit" dropdown menu without having specified the rest of the fields.
    if (theBoundProblem.allDataProvided()) {
      final AUnit inputUnitObject = getUnitObjectFromName(theBoundProblem.getInputUnit());
      final AUnit targetUnitObject = getUnitObjectFromName(theBoundProblem.getTargetUnit());
      if (inputUnitObject == null || targetUnitObject == null) {
        // It shouldn't be possible for the user to cause this code to get executed, but this code is still here just in case so the situation would be gracefully handled.
        theBoundProblem.setProblemOutput("<system error occurred>");
      } else {
        final Measurement inputMeasurement = new Measurement(theBoundProblem.getInputValue(), inputUnitObject);

        final double convertedValue = targetUnitObject.convertTo(inputMeasurement).getValue(); // The correct/actual answer

        final double convertedValueRounded = Math.round(convertedValue * 10.0) / 10.0; // round to the nearest tenth
        final double studentResponseRounded = Math.round(theBoundProblem.getStudentResponse() * 10.0) / 10.0;

        logger.trace("determineOutput(): convertedValue={} convertedValueRounded={} studentResponseRounded={}", convertedValue, convertedValueRounded, studentResponseRounded);
        theBoundProblem.setProblemOutput(convertedValueRounded == studentResponseRounded ? "correct" : "incorrect");
      }
    }
  } // determineOutput

  private AUnit getUnitObjectFromName(String canonicalName) {
    logger.trace("in getUnitObjectFromName(): canonicalName={}", canonicalName);
    AUnit unit = null;
    try {
      final Class<?> clasz = Class.forName(canonicalName);
      final Constructor<?>[] allConstructors = clasz.getConstructors();
      final Class<?>[] parametersOfConstructor1 = allConstructors[0].getParameterTypes();
      final Constructor<?> constructor = clasz.getConstructor(parametersOfConstructor1);
      unit = (AUnit) constructor.newInstance();
      logger.debug("getUnitObjectFromName(): unit={}", unit);
    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException exception) {
      StringWriter errors = new StringWriter();
      exception.printStackTrace(new PrintWriter(errors));
      logger.error("getUnitObjectFromName(): caught exception: {}", errors.toString());
    }
    return unit;
  }
} // class
