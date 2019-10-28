package us.flexion.convertunits.units.temperature;

import us.flexion.convertunits.units.AUnit;
import us.flexion.convertunits.units.IBijection;

public class Kelvin extends AUnit {
  public Kelvin() {
    super("K");
  }

  /**
   * Conversion function from Kelvin to base.
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
    public Double apply(Double valueKelvin) {
      return valueKelvin - 273.15;
    }
  };

  /**
   * Conversion function from base to Kelvin.
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
     * @return the value in Kelvins
     */
    public Double apply(Double valueBase) {
      return valueBase + 273.15;
    }
  };

  /**
   * Returns a function that converts a value in this temperature scale to a value in the "base" temperature scale.
   * 
   * @return conversion function to "base" scale
   */
  protected IBijection<Double, Double> getConversionFunction() {
    // Celsius is the "base" scale, so the conversion function is the function from Kelvin to Celsius, and the inverse of the conversion function is the function from Celsius to
    // Kelvin.
    return _func;
  }
} // class
