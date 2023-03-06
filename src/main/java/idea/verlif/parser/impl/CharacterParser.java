package idea.verlif.parser.impl;

import idea.verlif.parser.NullValueParser;
import idea.verlif.parser.ParamParser;

public class CharacterParser extends ParamParser<Character> {

    @Override
    public Class<?>[] match() {
        return new Class[]{Character.class, char.class};
    }

    @Override
    protected Character convert(String param) {
        return param.length() == 0 ? null : param.charAt(0);
    }

    public final static class AdvanceValueParser implements NullValueParser<Character> {

        private final char advance;

        public AdvanceValueParser(char advance) {
            this.advance = advance;
        }

        @Override
        public Character parserNull() {
            return advance;
        }
    }
}
