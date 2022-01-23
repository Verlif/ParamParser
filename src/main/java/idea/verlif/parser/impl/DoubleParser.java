package idea.verlif.parser.impl;

import idea.verlif.parser.ParamParser;

/**
 * @author Verlif
 */
public class DoubleParser implements ParamParser<Double> {
    @Override
    public Double parser(String param) {
        return Double.parseDouble(param);
    }
}
