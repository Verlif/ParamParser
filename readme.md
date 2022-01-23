# ParamParser
参数解析器

将String转换为各类对象的小组件。  
主要用于框架或是组件使用，通过向`ParamParserService`中添加自己的`ParamParser`来实现自己的String转换。

目前的参数解析器中内置了：
* `String`
* `Integer`与`int`
* `Double`与`double`
* `Boolean`与`boolean`

## 使用方法

1. 添加Jitpack仓库源

> maven
> ```xml
> <repositories>
>    <repository>
>        <id>jitpack.io</id>
>        <url>https://jitpack.io</url>
>    </repository>
> </repositories>
> ```

> Gradle
> ```text
> allprojects {
>   repositories {
>       maven { url 'https://jitpack.io' }
>   }
> }
> ```

2. 添加依赖

> maven
> ```xml
>    <dependencies>
>        <dependency>
>            <groupId>com.github.Verlif</groupId>
>            <artifactId>ParamParser</artifactId>
>            <version>alpha-0.1</version>
>        </dependency>
>    </dependencies>
> ```

> Gradle
> ```text
> dependencies {
>   implementation 'com.github.Verlif:ParamParser:alpha-0.1'
> }
> ```

3. 使用
```java
    // 获取解析服务对象
    ParamParserService pps = new ParamParserService();
    // 获取对应类型的解析器
    ParamParser<Double> parser = pps.getParser(Double.class);
    // 使用解析器
    double d = parser.parser("123");
    // 添加自定义的解析器
    pps.addOrReplace(new TestParser());
```