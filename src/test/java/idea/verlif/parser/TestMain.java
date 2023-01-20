package idea.verlif.parser;

import idea.verlif.parser.impl.DateParser;
import idea.verlif.parser.impl.DoubleParser;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class TestMain {

    @Test
    public void parserTest() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(calendar.getTime());
        ParamParserService pps = new ParamParserService();
        ParamParser<Date> dateParser = pps.getParser(Date.class);
//        dateParser.setNullValueParser(new DateParser.NowDateValueParser());
        System.out.println(pps.getParser(boolean.class));
        System.out.println(pps.getParser(Boolean.class));
        System.out.println(pps.parse(Date.class, "2022-1-3 16:50:00"));
        System.out.println(pps.parse(String.class, "2022-01-31 16:50:00"));
        System.out.println(pps.parse(Date.class, "2022-1-3 16:50"));
        System.out.println(pps.parse(Date.class, "2022/1-3 16:50:00.1", new Date()));
        System.out.println(pps.parse(Date.class, "16:50:00"));
        System.out.println(pps.parse(Date.class, "2022-1-3"));
    }

    @Test
    public void demo() {
        ParamParserService pps = new ParamParserService();
        // 获取对应类型的解析器
        ParamParser<Double> doubleParser = pps.getParser(Double.class);
        // 使用解析器
        double d = doubleParser.parse("123");
        System.out.println(d);                              // 结果输出 123.0
        System.out.println(doubleParser.parse(null));       // 结果输出 null
        // 设定自定义空值处理
        doubleParser.setNullValueParser(new DoubleParser.AdvanceValueParser(2));
        System.out.println(doubleParser.parse(null));       // 结果输出 2.0
        System.out.println(pps.parse(Double.class, null));                // 结果输出 2.0
        // 添加自定义的解析器
//        pps.addOrReplace(new TestParser());
    }
}
