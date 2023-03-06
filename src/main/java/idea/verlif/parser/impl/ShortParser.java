package idea.verlif.parser.impl;

import idea.verlif.parser.NullValueParser;
import idea.verlif.parser.ParamParser;

/**
 * @author Verlif
 */
public class ShortParser extends ParamParser<Short> {

    @Override
    public Class<?>[] match() {
        return new Class[]{Short.class, short.class};
    }

    @Override
    public Short convert(String param) {
        return Short.parseShort(param);
    }

    public final static class ZeroValueParser implements NullValueParser<Short> {

        @Override
        public Short parserNull() {
            return 0;
        }
    }

    public final static class AdvanceValueParser implements NullValueParser<Short> {

        private final short advance;

        public AdvanceValueParser(short advance) {
            this.advance = advance;
        }

        @Override
        public Short parserNull() {
            return advance;
        }
    }
}
