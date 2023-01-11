package idea.verlif.parser;

import com.sun.istack.internal.NotNull;
import idea.verlif.parser.valueparser.SameNullValueParser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Verlif
 */
public abstract class ParamParser<T> {

    protected NullValueParser<? extends T> nullValueParser;

    public ParamParser() {
        this.nullValueParser = SameNullValueParser.getInstance();
    }

    /**
     * 解析器匹配的参数类型
     *
     * @return 参数类型列表
     */
    public Class<?>[] match() {
        Type type = getClass().getGenericSuperclass();
        List<Class<?>> list = new ArrayList<>();
        try {
            for (Type argument : ((ParameterizedType) type).getActualTypeArguments()) {
                Class<?> cl = Class.forName(argument.getTypeName());
                list.add(cl);
            }
        } catch (Exception ignored) {
        }
        return list.toArray(new Class[]{});
    }

    /**
     * 文本转换为对象
     *
     * @param param 文本内容。可能为null，请注意处理空值。
     * @return 解析出的对象
     */
    public T parse(String param) {
        if (param == null) {
            return nullValueParser.parserNull();
        }
        T t = convert(param);
        if (t == null) {
            return nullValueParser.parserNull();
        } else {
            return t;
        }
    }

    /**
     * 文本转义为对象
     *
     * @param param 文本内容，不为空。
     * @return 解析出的对象
     * @throws RuntimeException 请注意可能会抛出解析异常。这些异常都表示输入的字符串格式与预期不符。
     */
    protected abstract T convert(@NotNull String param);

    /**
     * 设置空值转换器
     * @param nullValueParser 空置转换器
     */
    public void setNullValueParser(NullValueParser<T> nullValueParser) {
        this.nullValueParser = nullValueParser;
    }
}
