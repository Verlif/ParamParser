package idea.verlif.parser;

/**
 * @author Verlif
 */
public interface ParamParser<T> {

    /**
     * 文本解析为对象
     *
     * @param param 文本内容。可能为null，请注意处理空值。
     * @return 解析出的对象
     */
    T parser(String param);
}
