package us.encom.sampleapp.units.temperature;

import us.encom.sampleapp.units.AUnit;
import us.encom.sampleapp.units.IBijection;

public class Rankine extends AUnit {
  public Rankine() {
    super("R");
  }

  /**
   * Conversion function from Rankine to base.
   */
  private final IBijection<Double, Double> _func = new IBijection<Double, Double>() {
    /**
     * Returns the inverse of this lambda, which is also a bijection.
     *
     * @return inverse
     */
    public IBijection<Double, Double> getInverse() {
      return _inverse;
    }

    /**
     * Apply the lambda.
     *
     * @return the value in the "base" scale
     */
    public Double apply(Double valueRankine) {
      return (valueRankine - 491.67) * 5 / 9;
    }
  };

  /**
   * Conversion function from base to Rankine.
   */
  private final IBijection<Double, Double> _inverse = new IBijection<Double, Double>() {
    /**
     * Returns the inverse of this lambda, which is also a bijection.
     *
     * @return inverse
     */
    public IBijection<Double, Double> getInverse() {
      return _func;
    }

    /**
     * Apply the lambda.
     * 
     * @return the value in Rankine
     */
    public Double apply(Double valueBase) {
      return (valueBase + 273.15) * 9 / 5;
    }
  };

  /**
   * Returns a function that converts a value in this temperature scale to a value in the "base" temperature scale.
   * 
   * @return conversion function to "base" scale
   */
  protected IBijection<Double, Double> getConversionFunction() {
    // Celsius is the "base" scale, so the conversion function is the function from Rankine to Celsius, and the inverse of the conversion function is the function from Celsius to
    // Rankine.
    return _func;
  }
} // class
