package idea.verlif.parser.impl;

import idea.verlif.parser.ParamParser;

/**
 * @author Verlif
 */
public class IntegerParser implements ParamParser<Integer> {

    @Override
    public Class<?>[] match() {
        return new Class[]{Integer.class, int.class};
    }

    @Override
    public Integer parser(String param) {
        return Integer.parseInt(param);
    }
}
