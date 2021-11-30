package us.encom.sampleapp.units.volume;

import us.encom.sampleapp.units.AUnit;
import us.encom.sampleapp.units.IBijection;

public class Cup extends AUnit {
  public Cup() {
    super("C.");
  }

  /**
   * Returns a function that converts a value in this volume scale to a value in the "base" volume scale.
   * 
   * @return conversion function to "base" scale
   */
  protected IBijection<Double, Double> getConversionFunction() {
    // Cup has been chosen to be the "base" scale for volume, so methods within this Cup class simply return its own data.
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
