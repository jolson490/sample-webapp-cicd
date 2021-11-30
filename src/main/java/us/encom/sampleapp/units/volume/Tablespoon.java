package us.flexion.sampleapp.units.volume;

import us.flexion.sampleapp.units.AUnit;
import us.flexion.sampleapp.units.IBijection;

public class Tablespoon extends AUnit {
  public Tablespoon() {
    super("Tbsp.");
  }

  /**
   * Conversion function from Tablespoon to base.
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
    public Double apply(Double valueTablespoon) {
      return valueTablespoon / 16;
    }
  };

  /**
   * Conversion function from base to Tablespoon.
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
     * @return the value in Tablespoon
     */
    public Double apply(Double valueBase) {
      return valueBase * 16; // https://www.inchcalculator.com/convert/cup-to-tablespoon/
    }
  };

  /**
   * Returns a function that converts a value in this volume scale to a value in the "base" volume scale.
   * 
   * @return conversion function to "base" scale
   */
  protected IBijection<Double, Double> getConversionFunction() {
    // Cup is the "base" scale, so the conversion function is the function from Tablespoon to Cup, and the inverse of the conversion function is the function from Cup to
    // Tablespoon.
    return _func;
  }
} // class
