package us.flexion.convertunits.units;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import us.flexion.convertunits.UnitsController;
import us.flexion.convertunits.model.Problem;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitsControllerTest {
  @Autowired
  private UnitsController unitsController;

  @Test
  public void testDetermineOutput_FahrenheitRankine() { // 84.2 F = 543.87 R (tests rounding)
    Problem problem = Problem.builder().inputValue(84.2).inputUnit(Fahrenheit.class.getCanonicalName()).targetUnit(Rankine.class.getCanonicalName()).studentResponse(543.94)
        .build();
    unitsController.determineOutput(problem);
    assertEquals(problem.getProblemOutput(), "correct");
  }

  @Test
  public void testDetermineOutput_KelvinFahrenheit() { // 317.33 K = 111.524 F (tests rounding)
    Problem problem = Problem.builder().inputValue(317.33).inputUnit(Kelvin.class.getCanonicalName()).targetUnit(Fahrenheit.class.getCanonicalName()).studentResponse(111.554)
        .build();
    unitsController.determineOutput(problem);
    assertEquals(problem.getProblemOutput(), "incorrect");
  }

  @Test
  public void testDetermineOutput_CupLiter() { // 25.6 C. = 6.056659 L. (tests rounding)
    Problem problem = Problem.builder().inputValue(25.6).inputUnit(Cup.class.getCanonicalName()).targetUnit(Liter.class.getCanonicalName()).studentResponse(6.1).build();
    unitsController.determineOutput(problem);
    assertEquals(problem.getProblemOutput(), "correct");
  }

  @Ignore("Currently this test yields a ProblemOutput of incorrect, but ideally it should yield invalid.")
  @Test
  public void testDetermineOutput_GallonKelvin() { // converting volume to temperature is invalid
    Problem problem = Problem.builder().inputValue(73.12).inputUnit(Gallon.class.getCanonicalName()).targetUnit(Kelvin.class.getCanonicalName()).studentResponse(19.4).build();
    unitsController.determineOutput(problem);
    assertEquals(problem.getProblemOutput(), "invalid");
  }

  @Test
  public void testDetermineOutput_dogCelsius() { // invalid inputUnit
    Problem problem = Problem.builder().inputValue(136.1).inputUnit("dog").targetUnit(Celsius.class.getCanonicalName()).studentResponse(45.32).build();
    unitsController.determineOutput(problem);
    assertEquals(problem.getProblemOutput(), "invalid");
  }

  @Test
  public void testDetermineOutput_CelsiusFahrenheit() { // 36 C = 96.8 F
    Problem problem = Problem.builder().inputValue(36.0).inputUnit(Celsius.class.getCanonicalName()).targetUnit(Fahrenheit.class.getCanonicalName()).studentResponse(96.8).build();
    unitsController.determineOutput(problem);
    assertEquals(problem.getProblemOutput(), "correct");
  }

  @Test
  public void testDetermineOutput_CubicFootGallon() { // 3.7 CUFT = 27.6779 G.
    Problem problem = Problem.builder().inputValue(3.7).inputUnit(CubicFoot.class.getCanonicalName()).targetUnit(Gallon.class.getCanonicalName()).studentResponse(27.6779).build();
    unitsController.determineOutput(problem);
    assertEquals(problem.getProblemOutput(), "correct");
  }

  @Test
  public void testDetermineOutput_CubicInchTablespoon() { // 490 CI = 543.03 Tbsp.
    Problem problem = Problem.builder().inputValue(490.0).inputUnit(CubicInch.class.getCanonicalName()).targetUnit(Tablespoon.class.getCanonicalName()).studentResponse(543.03)
        .build();
    unitsController.determineOutput(problem);
    assertEquals(problem.getProblemOutput(), "correct");
  }

  // Ideally there would also be tests for the rest of the application besides the 'determineOutput' method in UnitsController.
}
