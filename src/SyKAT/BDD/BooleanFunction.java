package SyKAT.BDD;

/**
 *
 * @author Eric 'Siggy' Scott
 */
public abstract class BooleanFunction extends Executable<Boolean>
{
    protected String name;
    private int numInputs;
    private int numOutputs;
    
    public BooleanFunction(String name, int numInputs, int numOutputs)
    {
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    @Override
    public abstract Boolean execute(boolean[] input);
    
    @Override
    public int getNumInputs()
    {
        return this.numInputs;
    }

}
