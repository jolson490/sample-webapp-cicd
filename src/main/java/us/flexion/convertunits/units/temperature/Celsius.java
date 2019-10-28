package us.flexion.convertunits.units.temperature;

import us.flexion.convertunits.units.AUnit;
import us.flexion.convertunits.units.IBijection;

public class Celsius extends AUnit {
  public Celsius() {
    super("C");
  }

  /**
   * Returns a function that converts a value in this temperature scale to a value in the "base" temperature scale.
   * 
   * @return conversion function to "base" scale
   */
  protected IBijection<Double, Double> getConversionFunction() {
    // Celsius has been chosen to be the "base" scale, so methods within this Celsius class simply return its own data.
    return new IBijection<Double, Double>() {
      public IBijection<Double, Double> getInverse() {
        return this;
      }

      public Double apply(Double param) {
        return param;
      }
    };
  }
} // class
