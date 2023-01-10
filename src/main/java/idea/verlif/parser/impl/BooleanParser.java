package idea.verlif.parser.impl;

import idea.verlif.parser.NullValueParser;
import idea.verlif.parser.ParamParser;

/**
 * @author Verlif
 */
public class BooleanParser extends ParamParser<Boolean> {

    private static final char[] TRUE = new char[]{'t', 'T', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    @Override
    public Class<?>[] match() {
        return new Class[]{Boolean.class, boolean.class};
    }

    /**
     * 转换文本到boolean值
     *
     * @param param 文本内容。可能为null，请注意处理空值。<br/>
     *              为true的格式有
     *              <ul>
     *                  <li> true、TRUE及所有 't' 或 'T' 开头的文本 </li>
     *                  <li> 首位为数字且大于数字0的文本，例如 '12'、'0ac' 等 </li>
     *              </ul>
     * @return 转换得到的boolean值。若格式有误则返回false。
     */
    @Override
    public Boolean parser(String param) {
        if (param == null) {
            return nullValueParser.parserNull();
        }
        char c = param.charAt(0);
        for (char c1 : TRUE) {
            if (c == c1) {
                return true;
            }
        }
        return false;
    }

    public final static class TrueValueParser implements NullValueParser<Boolean> {

        @Override
        public Boolean parserNull() {
            return true;
        }
    }

    public final static class FalseValueParser implements NullValueParser<Boolean> {

        @Override
        public Boolean parserNull() {
            return false;
        }
    }
}
