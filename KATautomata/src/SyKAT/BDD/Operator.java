package SyKAT.BDD;

/**
 * Interface for the anonymous boolean functions you might want to pass into
 * SyKAT.BDD.apply(), since lovely Java doesn't support lambda expressions :-/.
 * 
 * @author Eric 'Siggy' Scott
 */
public interface Operator<T>
{
    public T operate(T x, T y);
}
