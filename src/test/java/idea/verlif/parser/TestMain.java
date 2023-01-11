package idea.verlif.parser;

import idea.verlif.parser.impl.DateParser;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class TestMain {

    public static void main(String[] args) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(calendar.getTime());
        ParamParserService pps = new ParamParserService();
        ParamParser<Date> dateParser = pps.getParser(Date.class);
        dateParser.setNullValueParser(new DateParser.NowDateValueParser());
        System.out.println(pps.getParser(boolean.class));
        System.out.println(pps.getParser(Boolean.class));
        System.out.println(dateParser.convert("2022-1-3 16:50:00"));
        System.out.println(dateParser.convert("2022-01-31 16:50:00"));
        System.out.println(dateParser.convert("2022-1-3 16:50"));
        System.out.println(dateParser.convert("2022/1-3 16:50:00.1"));
        System.out.println(dateParser.convert("16:50:00"));
        System.out.println(dateParser.convert("2022-1-3"));
    }
}
