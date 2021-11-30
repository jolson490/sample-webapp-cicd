package us.encom.sampleapp.units;

public class Measurement {
  /**
   * Unit of this measurement.
   */
  private AUnit _unit;

  /**
   * Value of this measurement.
   */
  private double _value;

  public Measurement(double value, AUnit unit) {
    _unit = unit;
    _value = value;
  }

  public AUnit getUnit() {
    return _unit;
  }

  public double getValue() {
    return _value;
  }

  public String toString() {
    return String.valueOf(_value) + " " + _unit.toString();
  }

  public boolean equals(Object other) {
    return (other instanceof Measurement) && (_value == ((Measurement) other).getValue()) && (_unit.equals(((Measurement) other).getUnit()));
  }
} // class
