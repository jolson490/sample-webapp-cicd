package us.flexion.convertunits.units.temperature;

import us.flexion.convertunits.units.AUnit;
import us.flexion.convertunits.units.IBijection;

public class Fahrenheit extends AUnit {
  public Fahrenheit() {
    super("F");
  }

  /**
   * Conversion function from Fahrenheit to base.
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
     * @param param lambda-specific parameter
     *
     * @return lambda-specific return value
     */
    public Double apply(Double param) {
      return (param - 32.0) / 9.0 * 5.0;
    }
  };

  /**
   * Conversion function from base to Fahrenheit.
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
     * @param param lambda-specific parameter
     *
     * @return lambda-specific return value
     */
    public Double apply(Double param) {
      return param / 5.0 * 9.0 + 32.0;
    }
  };

  /**
   * Returns a function that converts a value in this temperature scale to a value in the "base" temperature scale.
   * 
   * @return conversion function to "base" scale.
   */
  protected IBijection<Double, Double> getConversionFunction() {
    // Celsius is the "base" scale, so the conversion function is the function from Fahrenheit to Celsius, the inverse of the conversion function is the function from Celsius to
    // Fahrenheit.
    return _func;
  }
} // class
