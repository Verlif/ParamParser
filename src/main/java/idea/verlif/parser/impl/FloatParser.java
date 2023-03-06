package idea.verlif.parser.impl;

import idea.verlif.parser.NullValueParser;
import idea.verlif.parser.ParamParser;

public class FloatParser extends ParamParser<Float> {

    @Override
    public Class<?>[] match() {
        return new Class[]{Float.class, float.class};
    }

    @Override
    protected Float convert(String param) {
        return Float.parseFloat(param);
    }

    public final static class AdvanceValueParser implements NullValueParser<Float> {

        private final float advance;

        public AdvanceValueParser(float advance) {
            this.advance = advance;
        }

        @Override
        public Float parserNull() {
            return advance;
        }
    }
}
