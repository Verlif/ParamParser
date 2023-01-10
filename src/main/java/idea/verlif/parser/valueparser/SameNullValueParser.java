package idea.verlif.parser.valueparser;

import idea.verlif.parser.NullValueParser;

public final class SameNullValueParser<T> implements NullValueParser<T> {

    private static final SameNullValueParser<?> PARSER = new SameNullValueParser<>();

    public static <T> SameNullValueParser<T> getInstance() {
        return (SameNullValueParser<T>) PARSER;
    }

    @Override
    public T parserNull() {
        return null;
    }
}
