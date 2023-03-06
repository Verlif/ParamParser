package idea.verlif.parser.impl;

import idea.verlif.parser.NullValueParser;
import idea.verlif.parser.ParamParser;

import java.math.BigDecimal;

public class BigDecimalParser extends ParamParser<BigDecimal> {

    @Override
    public Class<?>[] match() {
        return new Class[]{BigDecimal.class};
    }

    @Override
    protected BigDecimal convert(String param) {
        return new BigDecimal(param);
    }

    public final static class AdvanceValueParser implements NullValueParser<BigDecimal> {

        private final String advance;

        public AdvanceValueParser(String advance) {
            this.advance = advance;
        }

        @Override
        public BigDecimal parserNull() {
            return new BigDecimal(advance);
        }
    }
}
