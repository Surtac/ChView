package jo.d2k.data.logic.schema;

import jo.util.utils.obj.StringUtils;

public class ChoiceSchemaComparator extends SimpleSchemaComparator
{
    private static final String[] OPTIONS = {
        "Equals",
        "Not Equals",
        "Empty",
        "Not Empty",
    };
    private static final boolean[] ARGS = {
        true,
        true,
        false,
        false,
    };
    
    @Override
    public String[] getOptions()
    {
        return OPTIONS;
    }

    @Override
    public boolean isArgFor(int option)
    {
        return ARGS[option];
    }

    @Override
    public boolean isMatch(Object v, int option, Object arg)
    {
        String val = (String)v;
        switch (option)
        {
            case 0: // equals
                return val.equalsIgnoreCase(arg.toString());
            case 1: // not equals
                return !val.equalsIgnoreCase(arg.toString());
            case 2: // empty
                return StringUtils.isTrivial(val);
            case 3: // not empty
                return !StringUtils.isTrivial(val);
        }
        return false;
    }
}