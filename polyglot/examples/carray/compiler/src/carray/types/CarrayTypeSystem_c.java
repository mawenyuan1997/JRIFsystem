package carray.types;

import polyglot.types.ArrayType;
import polyglot.types.Type;
import polyglot.types.TypeSystem_c;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;

public class CarrayTypeSystem_c extends TypeSystem_c implements
        CarrayTypeSystem {
    // ************************************************
    // Methods to give back ConstArrayType objects
    // ************************************************
    @Override
    public ConstArrayType constArrayOf(Type type) {
        return constArrayOf(type.position(), type);
    }

    @Override
    public ConstArrayType constArrayOf(Position pos, Type type) {
        return new ConstArrayType_c(this, pos, type, true);
    }

    @Override
    public ConstArrayType constArrayOf(Type type, int dims) {
        return constArrayOf(null, type, dims);
    }

    @Override
    public ConstArrayType constArrayOf(Position pos, Type type, int dims) {
        if (dims > 1) {
            return constArrayOf(pos, constArrayOf(pos, type, dims - 1));
        }
        else if (dims == 1) {
            return constArrayOf(pos, type);
        }
        else {
            throw new InternalCompilerError("Must call constArrayOf(type, dims) with dims > 0");
        }
    }

    @Override
    public ArrayType arrayOf(Position pos, Type type) {
        return new ConstArrayType_c(this, pos, type, false);
    }
}
