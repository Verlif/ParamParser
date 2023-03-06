package idea.verlif.parser.impl;

import idea.verlif.parser.NullValueParser;
import idea.verlif.parser.ParamParser;

import java.math.BigInteger;

public class BigIntegerParser extends ParamParser<BigInteger> {

    @Override
    public Class<?>[] match() {
        return new Class[]{BigInteger.class};
    }

    @Override
    protected BigInteger convert(String param) {
        return new BigInteger(param);
    }

    public final static class AdvanceValueParser implements NullValueParser<BigInteger> {

        private final String advance;

        public AdvanceValueParser(String advance) {
            this.advance = advance;
        }

        @Override
        public BigInteger parserNull() {
            return new BigInteger(advance);
        }
    }
}
