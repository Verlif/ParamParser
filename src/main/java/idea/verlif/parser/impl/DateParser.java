package idea.verlif.parser.impl;

import idea.verlif.parser.ParamParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换器
 *
 * @author Verlif
 * @version 1.0
 * @date 2022/1/26 9:31
 */
public class DateParser implements ParamParser<Date> {

    private static final String[] DATE_SPLIT = new String[]{"-", "/", "\\"};
    private static final String TIME_SPLIT = ":";
    private static final String[] MS_SPLIT = new String[]{"\\."};

    /**
     * 时间进制，转换为毫秒的比例。按顺序分别为 时、分、秒，共计3个。
     */
    private static final int[] TIME_BASE = new int[]{3600000, 60000, 1000};

    @Override
    public Date parser(String param) {
        String trim;
        if (param == null || (trim = param.trim()).length() == 0) {
            return new Date();
        }
        String[] split = trim.split(" ");
        long time = getDateDay(split[0]);
        if (split.length > 1) {
            time += getTime(split[1]);
        } else if (param.contains(TIME_SPLIT)) {
            return new Date(getToday() + getTime(split[0]));
        }
        return new Date(time);
    }

    private long getDateDay(String day) {
        for (String split : DATE_SPLIT) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + split + "MM" + split + "dd");
            try {
                Date date = sdf.parse(day);
                return date.getTime();
            } catch (ParseException ignored) {
            }
        }
        return System.currentTimeMillis();
    }

    private long getTime(String time) {
        long count = 0;
        String[] times = new String[]{"0"};
        // 毫秒判定
        for (String split : MS_SPLIT) {
            times = time.split(split);
            if (times.length > 1) {
                break;
            }
        }
        String sec = times[0];
        String[] secs = sec.split(TIME_SPLIT);
        if (secs.length > 1) {
            for (int i = 0; i < secs.length && i < TIME_BASE.length; i++) {
                count += (long) Integer.parseInt(secs[i]) * TIME_BASE[i];
            }
        }
        // 若存在毫秒
        if (times.length > 1) {
            count += Integer.parseInt(times[1]);
        }
        return count;
    }

    private long getToday() {
        // TODO: 需要优化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return getDateDay(sdf.format(new Date()));
    }
}
