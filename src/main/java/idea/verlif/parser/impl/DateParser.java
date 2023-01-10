package idea.verlif.parser.impl;

import idea.verlif.parser.NullValueParser;
import idea.verlif.parser.ParamParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间转换器
 *
 * @author Verlif
 * @version 1.0
 */
public class DateParser extends ParamParser<Date> {

    private static final char[] DATE_SPLIT = new char[]{'-', '/', '\\'};
    private static final String TIME_SPLIT = ":";

    /**
     * 时间进制，转换为毫秒的比例。按顺序分别为 时、分、秒，共计3个。
     */
    private static final int[] TIME_BASE = new int[]{3600000, 60000, 1000};

    /**
     * 解析文本到时间格式
     *
     * @param param 文本内容。可能为null，请注意处理空值。<br/>
     *              <ul>
     *                  <li> yyyy/MM/dd - 当天的0点0分0秒0毫秒 </li>
     *                  <li> HH:mm:ss.sss - 今天的HH点mm分ss秒sss毫秒（毫秒可忽略） </li>
     *                  <li> yyyy/MM/dd HH:mm:ss.sss - 如文本所示时间 </li>
     *              </ul>
     * @return 解析的时间，若条件都不符合，则返回当前时间。
     */
    @Override
    public Date parser(String param) {
        String trim;
        if (param == null || (trim = param.trim()).length() == 0) {
            return new Date();
        }
        // 通过空格隔开日期与时间
        String[] split = trim.split(" ");
        /*
            先判断类型：
            长度 1 - 可能只有日期或是只有时间
            长度 2 - 同时有日期与时间
         */
        char[] chars = param.toCharArray();
        if (split.length == 1) {
            // 仅时间
            if (contains(chars, TIME_SPLIT.charAt(0))) {
                return new Date(getToday() + getTime(param));
            }
            // 仅日期
            Long date = getDateDay(param);
            // 无法解析日期
            if (date == null) {
                return nullValueParser.parserNull();
            } else {
                return new Date(date);
            }
        } else {
            Long date = getDateDay(split[0]);
            if (date == null) {
                return nullValueParser.parserNull();
            } else {
                return new Date(date + getTime(split[1]));
            }
        }
    }

    private Long getDateDay(String day) {
        char[] chars = day.toCharArray();
        Date date = null;
        for (char split : DATE_SPLIT) {
            if (contains(chars, split)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + split + "MM" + split + "dd");
                try {
                    date = sdf.parse(day);
                } catch (ParseException ignored) {
                    date = nullValueParser.parserNull();
                    break;
                }
            }
        }
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }

    private boolean contains(char[] chars, char c) {
        for (char aChar : chars) {
            if (aChar == c) {
                return true;
            }
        }
        return false;
    }

    private long getTime(String time) {
        long count = 0;
        // 存在毫秒
        if (time.length() > 9) {
            count += Integer.parseInt(time.substring(9));
        }
        if (time.length() < 8) {
            return count;
        }
        String sec = time.substring(0, 8);
        String[] secs = sec.split(TIME_SPLIT);
        if (secs.length > 1) {
            for (int i = 0; i < secs.length && i < TIME_BASE.length; i++) {
                count += (long) Integer.parseInt(secs[i]) * TIME_BASE[i];
            }
        }
        return count;
    }

    private Long getToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    public final static class NowDateValueParser implements NullValueParser<Date> {

        @Override
        public Date parserNull() {
            return new Date();
        }
    }
}
