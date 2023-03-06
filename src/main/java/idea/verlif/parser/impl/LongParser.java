package idea.verlif.parser.impl;

import idea.verlif.parser.NullValueParser;
import idea.verlif.parser.ParamParser;

public class LongParser extends ParamParser<Long> {

    @Override
    public Class<?>[] match() {
        return new Class[]{Long.class, long.class};
    }

    @Override
    protected Long convert(String param) {
        return Long.parseLong(param);
    }

    public final static class AdvanceValueParser implements NullValueParser<Long> {

        private final long advance;

        public AdvanceValueParser(long advance) {
            this.advance = advance;
        }

        @Override
        public Long parserNull() {
            return advance;
        }
    }
}
