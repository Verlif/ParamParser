package idea.verlif.parser.impl;

import idea.verlif.parser.NullValueParser;
import idea.verlif.parser.ParamParser;

/**
 * @author Verlif
 */
public class IntegerParser extends ParamParser<Integer> {

    @Override
    public Class<?>[] match() {
        return new Class[]{Integer.class, int.class};
    }

    @Override
    public Integer parser(String param) {
        return Integer.parseInt(param);
    }

    public final static class ZeroValueParser implements NullValueParser<Integer> {

        @Override
        public Integer parserNull() {
            return 0;
        }
    }

    public final static class AdvanceValueParser implements NullValueParser<Integer> {

        private final int advance;

        public AdvanceValueParser(int advance) {
            this.advance = advance;
        }

        @Override
        public Integer parserNull() {
            return advance;
        }
    }
}
