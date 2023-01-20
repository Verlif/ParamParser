package idea.verlif.parser;

import idea.verlif.parser.impl.*;

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
        addOrReplace(new DateParser());
    }

    /**
     * 添加或替换已有的参数解析器
     *
     * @param parser 解析器
     * @return 是否添加或替换成功
     */
    public boolean addOrReplace(ParamParser<?> parser) {
        Class<?>[] cls = parser.match();
        if (cls.length == 0) {
            return false;
        }
        for (Class<?> match : cls) {
            parserMap.put(match, parser);
        }
        return true;
    }

    /**
     * 获取指定类型的参数解析器。优先子类解析器。
     *
     * @param cl  参数类型
     * @param <T> 参数泛型
     * @return 参数解析器，可能为null
     */
    public <T> ParamParser<T> getParser(Class<?> cl) {
        ParamParser<?> pp;
        do {
            pp = parserMap.get(cl);
            if (pp != null) {
                break;
            }
            cl = cl.getSuperclass();
        } while (cl != null);
        return (ParamParser<T>) pp;
    }

    /**
     * 解析参数
     *
     * @param cl    返回值类
     * @param param 参数字符串
     * @param <T>   返回值类
     * @return 参数解析值
     */
    public <T> T parse(Class<T> cl, String param) {
        return parse(cl, param, null);
    }

    /**
     * 解析参数
     *
     * @param cl         返回值类
     * @param param      参数字符串
     * @param defaultVal 当没有类解析器或是返回值为空时，返回默认值
     * @param <T>        返回值类
     * @return 参数解析值
     */
    public <T> T parse(Class<T> cl, String param, T defaultVal) {
        ParamParser<T> parser = getParser(cl);
        if (parser != null) {
            T t = parser.parse(param);
            if (t != null) {
                return t;
            }
        }
        return defaultVal;
    }
}
