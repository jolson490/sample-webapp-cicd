package us.flexion.sampleapp.units.volume;

import us.flexion.sampleapp.units.AUnit;
import us.flexion.sampleapp.units.IBijection;

public class CubicInch extends AUnit {
  public CubicInch() {
    super("CI");
  }

  /**
   * Conversion function from CubicInch to base.
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
    public Double apply(Double valueCubicInch) {
      return valueCubicInch * 0.069264;
    }
  };

  /**
   * Conversion function from base to CubicInch.
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
     * @return the value in CubicInch
     */
    public Double apply(Double valueBase) {
      return valueBase * 14.4375;
    }
  };

  /**
   * Returns a function that converts a value in this volume scale to a value in the "base" volume scale.
   * 
   * @return conversion function to "base" scale.
   */
  protected IBijection<Double, Double> getConversionFunction() {
    // Cup is the "base" scale, so the conversion function is the function from CubicInch to Cup, and the inverse of the conversion function is the function from Cup to CubicInch.
    return _func;
  }
} // class
