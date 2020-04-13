# SpringBoot返回参数封装

## 一、新建一个统一返回封装类

```java
public class Result implements Serializable {

    @SuppressWarnings("unused")
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Result.class);

    private static final long serialVersionUID = -1802122468331526708L;
    private int status = -1;
    private String message = "待处理";
    private Map<String, Object> data = new HashMap<String, Object>();

    public Result(){}

    public Result(int status, String message){
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void putData(String key, Object value) {
        data.put(key, value);
    }

    public void removeData(String key) {
        data.remove(key);
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
```

## 二、controller

```java
@RestController
public class HelloController {

    @RequestMapping(value="/hello", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<Result> hello(@RequestParam(value="bad", required=false, defaultValue="false") boolean bad) {

        // 结果封装类对象
        Result res = new Result(200, "ok");

        if(bad) {
            res.setStatus(400);
            res.setMessage("Bad request");

            // ResponseEntity是响应实体泛型，通过它可以设置http响应的状态值，此处返回400
            return new ResponseEntity<Result>(res, HttpStatus.BAD_REQUEST);
        }

        // 把结果数据放进封装类
        res.putData("words", "Hello world!");

        // ResponseEntity是响应实体泛型，通过它可以设置http响应的状态值，此处返回200
        return ResponseEntity.ok(res);
    }
}
```

