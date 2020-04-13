# SpringBoot全局捕获异常

>**通用异常处理**
>
>其实Spring系列的项目全局异常处理方式早已存在，只不过我们一直忙于搬砖，很少停下脚步去审视这个日夜与我们相伴的朋友。为了贴合主题，本次主要针对SpringBoot全局异常处理进行举例说明。SpringBoot中有一个`@ControllerAdvice`的注解，使用该注解即表示开启全局异常捕获，接下来我们只需在自定义的方法上使用`@ExceptionHandler`注解，并定义捕获异常的类型，对这种类型的异常进行统一的处理。

## 一、在config配置包下新建全局捕获异常处理类

## 二、根据具体要捕获的异常编写处理类

##  

> 假如我们需要针对NullException（空指针异常，是Java程序员最痛恨的异常，没有之一）进行全局处理（如下所示）。

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
        /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    public BaseResponseFacade exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是:",e);
        return ResponseUtil.error(ResponseCode.ERROR);
    }
}
```

自定义异常

```java
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class BizException extends RuntimeException {
    /**
     * 错误码
     */
    protected Integer errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;
}
```



```java
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public BaseResponseFacade bizExceptionHandler(HttpServletRequest req, BizException e){
        log.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return ResponseUtil.error(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    public BaseResponseFacade exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是:",e);
        return ResponseUtil.error(ResponseCode.ERROR);
    }


    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    public BaseResponseFacade exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！原因是:",e);
        return ResponseUtil.error(ResponseCode.INTERNAL_SERVER_ERROR);
    }
}
```

当然，具体的异常处理还需根据具体的项目进行改变