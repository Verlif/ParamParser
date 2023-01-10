package idea.verlif.parser.impl;

import idea.verlif.parser.NullValueParser;
import idea.verlif.parser.ParamParser;

/**
 * @author Verlif
 */
public class DoubleParser extends ParamParser<Double> {

    @Override
    public Class<?>[] match() {
        return new Class[]{Double.class, double.class};
    }

    @Override
    public Double parser(String param) {
        return Double.parseDouble(param);
    }

    public final static class ZeroValueParser implements NullValueParser<Double> {

        @Override
        public Double parserNull() {
            return 0D;
        }
    }

    public final static class AdvanceValueParser implements NullValueParser<Double> {

        private final double advance;

        public AdvanceValueParser(double advance) {
            this.advance = advance;
        }

        @Override
        public Double parserNull() {
            return advance;
        }
    }
}
