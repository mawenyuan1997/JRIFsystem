package KATautomata.SyKAT.BDD;

import java.util.ArrayList;

/**
 * A unified interface for BDDs and MultiBDDs -- useful mostly for extension
 * by the client (ex. to define objective functions in an evolutionary algorithm).
 * 
 * @author Eric 'Siggy' Scott
 */
public abstract class Executable<T>
{
    
    public abstract T execute(boolean[] input);
    
//    /** Run execute(boolean[] input) on an array of inputs
//     *
//     */
//    public T[] execute(ArrayList<boolean[]> inputs)
//    {
//        T[] outputs = new boolean[inputs.size()][inputs.get(0).length];
//        for (int i = 0; i < inputs.size(); i++)
//        {
//            boolean[] in = inputs.get(i);
//            assert(in.length == inputs.get(0).length); // Ragged input is invalid
//            outputs[i] = this.execute(in);
//        }
//        return outputs;
//    }
   
    public abstract int getNumInputs();
    

//    public String printTruthTable()
//    {
//        String output = "";
//        ArrayList<boolean[]> inputs = Util.generateInputs(this.getNumInputs());
//        for (boolean[] in : inputs)
//        {
//            for (boolean v : in)
//            {
//                output += (v ? "T " : "F ");
//            }
//            output += ":";
//            boolean[] outs = this.execute(in);
//            for (boolean out : outs)
//                output += "  " + (out ? "T  " : "F  ");
//            output += "\n";
//        }
//        return output;
//    }
}
