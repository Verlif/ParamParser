package idea.verlif.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * 空格分割器
 */
public class SpaceArraySplitter implements ArraySplitter {

    @Override
    public String[] split(String param) {
        char[] chars = param.trim().toCharArray();
        StringBuilder stb = new StringBuilder();
        List<String> lines = new ArrayList<>();
        boolean ignoredNext = false; // 是否忽略下一个字符，用于转移符号
        boolean noStr = true; // 是否不在字符串内
        for (char c : chars) {
            if (c == '\\') {
                stb.append(c);
                ignoredNext = true;
            } else if (ignoredNext) {
                stb.append(c);
                ignoredNext = false;
            } else if (c == '\"') {
                if (stb.length() > 0 && noStr) {
                    stb.append(c);
                } else {
                    noStr = !noStr;
                }
            } else if (noStr && c == ' ') { // 断开
                // 忽略重复空格
                if (stb.length() > 0) {
                    lines.add(stb.toString());
                    stb.setLength(0);
                }
            } else {
                stb.append(c);
            }
        }
        if (stb.length() > 0) {
            lines.add(stb.toString());
        }
        return lines.toArray(new String[0]);
    }
}
