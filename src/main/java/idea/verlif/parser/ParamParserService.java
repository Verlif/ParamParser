package idea.verlif.parser;

import idea.verlif.parser.impl.BooleanParser;
import idea.verlif.parser.impl.DoubleParser;
import idea.verlif.parser.impl.IntegerParser;
import idea.verlif.parser.impl.StringParser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Verlif
 */
public class ParamParserService {

    private final Map<Class<?>, ParamParser<?>> parserMap;

    public ParamParserService() {
        parserMap = new HashMap<>();

        addOrReplace(new StringParser());
        addOrReplace(new IntegerParser());
        addOrReplace(new DoubleParser());
        addOrReplace(new BooleanParser());
    }

    /**
     * 添加或替换已有的参数解析器
     *
     * @param parser 解析器
     * @return 是否添加或替换成功
     */
    public boolean addOrReplace(ParamParser<?> parser) {
        for (Class<?> match : parser.match()) {
            parserMap.put(match, parser);
        }
        return false;
    }

    /**
     * 获取参数解析器
     *
     * @param cl  参数类型
     * @param <T> 参数泛型
     * @return 参数解析器，可能为null
     */
    public <T> ParamParser<T> getParser(Class<T> cl) {
        return (ParamParser<T>) parserMap.get(cl);
    }
}
