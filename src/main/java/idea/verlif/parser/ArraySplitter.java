package idea.verlif.parser;

/**
 * 数组分割类
 */
public interface ArraySplitter {

    /**
     * 分割字符串到字符串数组，以便解析一维数组
     *
     * @param param 字符串参数
     * @return 字符串参数拆解的数组
     */
    String[] split(String param);
}
