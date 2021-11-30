package us.encom.sampleapp.units;

/**
 * An interface for a lambda. (A lambda expresses an instance of a functional interface, which has a single abstract method.)
 */
public interface ILambda<R, P> {
  /**
   * Apply the lambda.
   * 
   * @param param lambda-specific parameter
   * @return lambda-specific return value
   */
  public R apply(P param);
}
