package idea.verlif.parser.impl;

import idea.verlif.parser.ParamParser;

/**
 * @author Verlif
 */
public class StringParser implements ParamParser<String> {

    @Override
    public Class<?>[] match() {
        return new Class[]{String.class};
    }

    @Override
    public String parser(String param) {
        return param;
    }
}
