package us.flexion.convertunits.units.volume;

import us.flexion.convertunits.units.AUnit;
import us.flexion.convertunits.units.IBijection;

public class Gallon extends AUnit {
  public Gallon() {
    super("G.");
  }

  /**
   * Conversion function from Gallon to base.
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
    public Double apply(Double valueGallon) {
      return valueGallon * 16;
    }
  };

  /**
   * Conversion function from base to Gallon.
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
     * @return the value in Gallon
     */
    public Double apply(Double valueBase) {
      return valueBase / 16;
    }
  };

  /**
   * Returns a function that converts a value in this volume scale to a value in the "base" volume scale.
   * 
   * @return conversion function to "base" scale.
   */
  protected IBijection<Double, Double> getConversionFunction() {
    // Cup is the "base" scale, so the conversion function is the function from Gallon to Cup, and the inverse of the conversion function is the function from Cup to Gallon.
    return _func;
  }
} // class
