

# springmvc(第三天)

## 1、什么是MVC

> MVC（Model View Controller）是软件工程中的一种软件架构模式，它把软件系统分为模型、视图和控制器三个基本部分。用一种业务逻辑、数据、界面显示分离的方法组织代码，将业务逻辑聚集到一个部件里面，在改进和个性化定制界面及用户交互的同时，不需要重新编写业务逻辑。此模式透过对复杂度的简化，使程序结构更加直观。

## 2、springmvc工作流程

![](https://images2015.cnblogs.com/blog/249993/201612/249993-20161212142542042-2117679195.jpg)

1、 用户发送请求至前端控制器DispatcherServlet。

2、 DispatcherServlet收到请求调用HandlerMapping处理器映射器。

3、 处理器映射器找到具体的处理器(可以根据xml配置、注解进行查找)，生成处理器对象及处理器拦截器(如果有则生成)一并返回给DispatcherServlet。

4、 DispatcherServlet调用HandlerAdapter处理器适配器。

5、 HandlerAdapter经过适配调用具体的处理器(Controller，也叫后端控制器)。

6、 Controller执行完成返回ModelAndView。

7、 HandlerAdapter将controller执行结果ModelAndView返回给DispatcherServlet。

8、 DispatcherServlet将ModelAndView传给ViewReslover视图解析器。

9、 ViewReslover解析后返回具体View。

10、DispatcherServlet根据View进行渲染视图（即将模型数据填充至视图中）。

11、 DispatcherServlet响应用户

## 3、组件说明：

以下组件通常使用框架提供实现：

> DispatcherServlet：作为前端控制器，整个流程控制的中心，控制其它组件执行，统一调度，降低组件之间的耦合性，提高每个组件的扩展性。
>
> HandlerMapping：通过扩展处理器映射器实现不同的映射方式，例如：配置文件方式，实现接口方式，注解方式等。 
>
> HandlAdapter：通过扩展处理器适配器，支持更多类型的处理器。
>
> ViewResolver：通过扩展视图解析器，支持更多类型的视图解析，例如：jsp、freemarker、pdf、excel等。

## 4、常用注解

> `@Controller` 声明Action组件
>
> `@Service `声明Service组件 @Service("myMovieLister")
>
> `@Repository` 声明Dao组件
>
> `@Component` 泛指组件, 当不好归类时.
>
> `@RequestMapping("/menu")` 请求映射
>
> `@Resource` 用于注入，( j2ee提供的 ) 默认按名称装配，`@Resource(name="beanName")`
>
>  `@Autowired `用于注入，(srping提供的) 默认按类型装配
>
> `@Transactional( rollbackFor={Exception.class})` 事务管理`@ResponseBody`
>
>  `@Scope("prototype") `设定bean的作用域

