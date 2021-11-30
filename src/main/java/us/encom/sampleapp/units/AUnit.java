package us.encom.sampleapp.units;

/**
 * Abstract class for a unit of measure.
 */
public abstract class AUnit {
  protected String _unitSymbol;

  /**
   * Returns a function that converts a value in these units to a value in "base" units.
   *
   * @return conversion function to "base" scale
   */
  protected abstract IBijection<Double, Double> getConversionFunction();

  public String toString() {
    return _unitSymbol;
  }

  protected AUnit(String unitSymbol) {
    _unitSymbol = unitSymbol;
  }

  /**
   * Converts a measurement in some unit to a measurement in this unit.
   * 
   * @param m measurement in some unit
   * @return measurement in this unit
   */
  public Measurement convertTo(Measurement m) {
    final double amount = m.getValue();
    final double amountInBase = m.getUnit().getConversionFunction().apply(amount);
    final double amountInThis = getConversionFunction().getInverse().apply(amountInBase);
    return new Measurement(amountInThis, this);
  }

  public boolean equals(Object other) {
    if (other == null || other.getClass() != this.getClass()) {
      return false;
    } else {
      return other == this;
    }
  }
} // class
