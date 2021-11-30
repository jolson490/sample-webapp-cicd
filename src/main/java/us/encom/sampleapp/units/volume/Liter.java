package us.encom.sampleapp.units.volume;

import us.encom.sampleapp.units.AUnit;
import us.encom.sampleapp.units.IBijection;

public class Liter extends AUnit {
  public Liter() {
    super("L.");
  }

  /**
   * Conversion function from Liter to base.
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
    public Double apply(Double valueLiter) {
      return valueLiter * 4.226753;
    }
  };

  /**
   * Conversion function from base to Liter.
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
     * @return the value in Liter
     */
    public Double apply(Double valueBase) {
      return valueBase * 0.236588;
    }
  };

  /**
   * Returns a function that converts a value in this volume scale to a value in the "base" volume scale.
   * 
   * @return conversion function to "base" scale
   */
  protected IBijection<Double, Double> getConversionFunction() {
    // Cup is the "base" scale, so the conversion function is the function from Liter to Cup, and the inverse of the conversion function is the function from Cup to Liter.
    return _func;
  }
} // class
