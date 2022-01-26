package idea.verlif.parser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Verlif
 */
public interface ParamParser<T> {

    /**
     * 解析器匹配的参数类型
     *
     * @return 参数类型列表
     */
    default Class<?>[] match() {
        Type[] types = getClass().getGenericInterfaces();
        List<Class<?>> list = new ArrayList<>();
        for (Type type : types) {
            try {
                for (Type argument : ((ParameterizedType) type).getActualTypeArguments()) {
                    Class<?> cl = Class.forName(argument.getTypeName());
                    list.add(cl);
                }
            } catch (Exception ignored) {
            }
        }
        return list.toArray(new Class[]{});
    }

    /**
     * 文本解析为对象
     *
     * @param param 文本内容。可能为null，请注意处理空值。
     * @return 解析出的对象
     * @throws RuntimeException 请注意可能会抛出解析异常。这些异常都表示输入的字符串格式与预期不符。
     */
    T parser(String param);
}
