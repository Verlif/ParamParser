package idea.verlif.parser.impl;

import idea.verlif.parser.ParamParser;

/**
 * @author Verlif
 */
public class IntegerParser implements ParamParser<Integer> {
    @Override
    public Integer parser(String param) {
        return Integer.parseInt(param);
    }
}
