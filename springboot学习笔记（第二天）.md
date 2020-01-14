# springboot学习笔记（第二天）

## 一、Spring IoC容器

![](C:\Users\Administrator\Desktop\学习笔记\175724-911dc459ef9cec28.webp)



> DefaultListableBeanFactory`作为一个比较通用的BeanFactory实现，它同时也实现了BeanDefinitionRegistry接口，因此它就承担了Bean的注册管理工作。从图中也可以看出，BeanFactory接口中主要包含getBean、containBean、getType、getAliases等管理bean的方法，而BeanDefinitionRegistry接口则包含registerBeanDefinition、removeBeanDefinition、getBeanDefinition等注册管理BeanDefinition的方法。

## 二、常用注解

### 1.1、@ComponentScan

> `@ComponentScan`注解对应XML配置形式中的`< context:component-scan>`元素，表示启用组件扫描，Spring会自动扫描所有通过注解配置的bean，然后将其注册到IOC容器中。我们可以通过`basePackages`等属性来指定`@ComponentScan`自动扫描的范围，如果不指定，默认从声明`@ComponentScan`所在类的`package`进行扫描。正因为如此，SpringBoot的启动类都默认在`src/main/java`下。

### 1.2、@Import

> `@Import`注解用于导入配置类，举个简单的例子：

```java
@Configuration
public class MoonBookConfiguration {
    @Bean
    public BookService bookService() {
        return new BookServiceImpl();
    }
}
```

> 现在有另外一个配置类，比如：`MoonUserConfiguration`，这个配置类中有一个bean依赖于`MoonBookConfiguration`中的bookService，如何将这两个bean组合在一起？借助`@Import`即可

```java
@Configuration
// 可以同时导入多个配置类，比如：@Import({A.class,B.class})
@Import(MoonBookConfiguration.class)
public class MoonUserConfiguration {
    @Bean
    public UserService userService(BookService bookService) {
        return new BookServiceImpl(bookService);
    }
}
```

> 需要注意的是，在4.2之前，`@Import`注解只支持导入配置类，但是在4.2之后，它支持导入普通类，并将这个类作为一个bean的定义注册到IOC容器中。

### 1.3@Conditional

> `@Conditional`注解表示在满足某种条件后才初始化一个bean或者启用某些配置。它一般用在由`@Component`、`@Service`、`@Configuration`等注解标识的类上面，或者由`@Bean`标记的方法上。如果一个`@Configuration`类标记了`@Conditional`，则该类中所有标识了`@Bean`的方法和`@Import`注解导入的相关类将遵从这些条件。

> 在Spring里可以很方便的编写你自己的条件类，所要做的就是实现`Condition`接口，并覆盖它的`matches()`方法。举个例子，下面的简单条件类表示只有在`Classpath`里存在`JdbcTemplate`类时才生效：

```java
public class JdbcTemplateCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        try {
        conditionContext.getClassLoader().loadClass("org.springframework.jdbc.core.JdbcTemplate");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
```

>这个例子中只有当`JdbcTemplateCondition`类的条件成立时才会创建MyService这个bean。也就是说MyService这bean的创建条件是`classpath`里面包含`JdbcTemplate`，否则这个bean的声明就会被忽略掉。



### 1.4@ConfigurationProperties与@EnableConfigurationProperties

> 当某些属性的值需要配置的时候，我们一般会在`application.properties`文件中新建配置项，然后在bean中使用`@Value`注解来获取配置的值，比如下面配置数据源的代码。

``` java
// jdbc config
jdbc.mysql.url=jdbc:mysql://localhost:3306/sampledb
jdbc.mysql.username=root
jdbc.mysql.password=123456
......

// 配置数据源
@Configuration
public class HikariDataSourceConfiguration {

    @Value("jdbc.mysql.url")
    public String url;
    @Value("jdbc.mysql.username")
    public String user;
    @Value("jdbc.mysql.password")
    public String password;
    
    @Bean
    public HikariDataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);
        // 省略部分代码
        return new HikariDataSource(hikariConfig);
    }
}
```

### 1.5@SpringBootApplication

> 典型的Spring Boot应用的启动类一般均位于`src/main/java`根路径下，比如`MoonApplication`类：

```java
@SpringBootApplication
public class MoonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoonApplication.class, args);
    }
}
```

其中`@SpringBootApplication`开启组件扫描和自动配置，而`SpringApplication.run`则负责启动引导应用程序。`@SpringBootApplication`是一个复合`Annotation`，它将三个有用的注解组合在一起：

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
        @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
    // ......
}
```

>`@SpringBootConfiguration`就是`@Configuration`，它是Spring框架的注解，标明该类是一个`JavaConfig`配置类。而`@ComponentScan`启用组件扫描，前文已经详细讲解过，这里着重关注`@EnableAutoConfiguration`。
>
>`@EnableAutoConfiguration`注解表示开启Spring Boot自动配置功能，Spring Boot会根据应用的依赖、自定义的bean、classpath下有没有某个类 等等因素来猜测你需要的bean，然后注册到IOC容器中。



