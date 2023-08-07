# ParamParser

参数解析器

将String转换为各类对象的小组件。  
例如：`new ParamParserService().parse(Date.class, "2022-1-3 16:50:21.1")`会返回对应时间的`Date`对象。  
主要用于框架或是组件使用，通过向`ParamParserService`中添加自己的`ParamParser`来实现自定义的`String`到对象的转换。

## 用途

1. 用于对未知的参数进行解析。例如在反射时进行set：

    ```java
    MySqlConfig config = new MySqlConfig();
    Properties properties = new Properties();
    properties.load(new FileReader("mysql.properties"));
  
    ParamParserService service = new ParamParserService();
    for (Field field : MySqlConfig.class.getDeclaredFields()) {
        ParamParser<?> parser = service.getParser(field.getType());
        String value = properties.getProperty(field.getName());
        if (value != null) {
            field.setAccessible(true);
            field.set(config, parser.parse(value));
        }
    }
    ```

2. 统一参数解析接口

   目前的参数解析器中内置了：

    * 处理8种基础属性的基础属性解析器
    * 处理`BigDecimal`的`BigDecimalParser`
    * 处理`BigInteger`的`BigIntegerParser`
    * 处理`Date`（允许格式 - `hh:mm:ss`、`yyyy/MM/dd`、`yyyy/MM/dd hh:mm:ss`等）的`DateParser`

3. 支持的功能

    1. 字符串转对象
    2. `null`值自定义处理
    3. 一维数组转换

## 添加依赖

1. 添加Jitpack仓库源

   maven

    ```xml
    <repositories>
       <repository>
           <id>jitpack.io</id>
           <url>https://jitpack.io</url>
       </repository>
    </repositories>
    ```

   Gradle

    ```text
    allprojects {
      repositories {
          maven { url 'https://jitpack.io' }
      }
    }
    ```

2. 添加依赖

   __lastVersion__ [![](https://jitpack.io/v/Verlif/param-parser.svg)](https://jitpack.io/#Verlif/param-parser)

   maven

   ```xml
      <dependencies>
          <dependency>
              <groupId>com.github.Verlif</groupId>
              <artifactId>param-parser</artifactId>
              <version>lastVersion</version>
          </dependency>
      </dependencies>
   ```

   Gradle

   ```text
   dependencies {
     implementation 'com.github.Verlif:param-parser:lastVersion'
   }
   ```

3. 使用

   `ParamParserService`与`ParamParser`的使用

   ```java
   // 获取解析服务对象
   ParamParserService pps = new ParamParserService();
   // 获取对应类型的解析器
   ParamParser<Double> doubleParser = pps.getParser(Double.class);
   // 使用解析器
   double d = doubleParser.parse("123");
   System.out.println(d);                              // 结果输出 123.0
   System.out.println(doubleParser.parse(null));       // 结果输出 null
   / 设定自定义空值处理
   doubleParser.setNullValueParser(new DoubleParser.AdvanceValueParser(2));
   System.out.println(doubleParser.parse(null));       // 结果输出 2.0
   System.out.println(pps.parse(Double.class, null));  // 结果输出 2.0
   // 添加自定义的解析器
   pps.addOrReplace(new TestParser());
   ```

   自定义`ParamParser`和`NullValueParser`，这里以`BooleanParser`举例

   ```java
   public class BooleanParser extends ParamParser<Boolean> {

       private static final char[] TRUE = new char[]{'t', 'T', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

       @Override
       public Class<?>[] match() {
           return new Class[]{Boolean.class, boolean.class};
       }

       /**
        * 转换文本到boolean值
        *
        * @param param 文本内容，不为空。<br/>
        *              为true的格式有
        *              <ul>
        *                  <li> true、TRUE及所有 't' 或 'T' 开头的文本 </li>
        *                  <li> 首位为数字且大于数字0的文本，例如 '12'、'0ac' 等 </li>
        *              </ul>
        * @return 转换得到的boolean值。若格式不匹配则返回false。若是空字符串则返回null，交由ParamParser父类处理空值。
        */
       @Override
       public Boolean convert(String param) {
           if (param.length() == 0) {
               return null;
           }
           char c = param.charAt(0);
           for (char c1 : TRUE) {
               if (c == c1) {
                   return true;
               }
           }
           return false;
       }
       
       /**
        * 空值返回true
        */
       public final static class TrueValueParser implements NullValueParser<Boolean> {
           @Override
           public Boolean parserNull() {
               return true;
           }
       }
   
       /**
        * 空值返回false
        */
       public final static class FalseValueParser implements NullValueParser<Boolean> {
           @Override
           public Boolean parserNull() {
               return false;
           }
       }
   }
   ```
