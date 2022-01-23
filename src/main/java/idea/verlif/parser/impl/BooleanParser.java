package idea.verlif.parser.impl;

import idea.verlif.parser.ParamParser;

/**
 * @author Verlif
 */
public class BooleanParser implements ParamParser<Boolean> {

    @Override
    public Class<?>[] match() {
        return new Class[]{Boolean.class, boolean.class};
    }

    @Override
    public Boolean parser(String param) {
        return Boolean.parseBoolean(param);
    }
}
