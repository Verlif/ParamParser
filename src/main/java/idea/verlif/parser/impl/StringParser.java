package idea.verlif.parser.impl;

import idea.verlif.parser.NullValueParser;
import idea.verlif.parser.ParamParser;

/**
 * @author Verlif
 */
public class StringParser extends ParamParser<String> {

    @Override
    public Class<?>[] match() {
        return new Class[]{String.class};
    }

    @Override
    public String convert(String param) {
        return param;
    }

    public final static class EmptyStringValueParser implements NullValueParser<String> {

        @Override
        public String parserNull() {
            return "";
        }
    }
}
