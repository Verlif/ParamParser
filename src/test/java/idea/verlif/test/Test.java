package idea.verlif.test;

import idea.verlif.parser.ParamParser;
import idea.verlif.parser.ParamParserService;
import idea.verlif.parser.impl.DateParser;
import idea.verlif.parser.impl.DoubleParser;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(calendar.getTime());
        ParamParserService pps = new ParamParserService();
        ParamParser<Date> dateParser = pps.getParser(Date.class);
        ParamParser<Double> doubleParser = pps.getParser(Double.class);
        System.out.println(doubleParser.parse(null));
        doubleParser.setNullValueParser(new DoubleParser.AdvanceValueParser(2));
        System.out.println(doubleParser.parse(null));
        dateParser.setNullValueParser(new DateParser.NowDateValueParser());
        System.out.println(doubleParser.parse("123"));
        System.out.println(pps.getParser(boolean.class));
        System.out.println(pps.getParser(Boolean.class));
        System.out.println(dateParser.parse("2022-1-3 16:50:21"));
        System.out.println(dateParser.parse("2022-01-31 16:50:21"));
        System.out.println(dateParser.parse("2022-1-3 16:50"));
        System.out.println(dateParser.parse("2022/1-3 16:50:21.1"));
        System.out.println(dateParser.parse("16:50:21"));
        System.out.println(dateParser.parse("2022-1-3"));
        System.out.println(pps.parse(Date.class, "2022-1-3 16:50:21.1"));
        System.out.println(pps.parse(BigDecimal.class, "2.4123213123"));
        System.out.println(Arrays.toString(pps.parse(BigDecimal[].class, "3122   3123 ", new BigDecimal[]{BigDecimal.valueOf(321)})));
    }
}
