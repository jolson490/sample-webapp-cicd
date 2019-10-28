package us.flexion.convertunits.units;

/**
 * A bijection is a function that has an inverse. R = return, P = parameter.
 */
public interface IBijection<R, P> extends ILambda<R, P> {
  /**
   * Returns the inverse of this lambda.
   *
   * @return inverse
   */
  public IBijection<P, R> getInverse();
}
