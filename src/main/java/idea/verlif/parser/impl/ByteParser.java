package idea.verlif.parser.impl;

import idea.verlif.parser.NullValueParser;
import idea.verlif.parser.ParamParser;

public class ByteParser extends ParamParser<Byte> {

    @Override
    public Class<?>[] match() {
        return new Class[]{Byte.class, byte.class};
    }

    @Override
    protected Byte convert(String param) {
        return Byte.parseByte(param);
    }

    public final static class AdvanceValueParser implements NullValueParser<Byte> {

        private final byte advance;

        public AdvanceValueParser(byte advance) {
            this.advance = advance;
        }

        @Override
        public Byte parserNull() {
            return advance;
        }
    }
}
