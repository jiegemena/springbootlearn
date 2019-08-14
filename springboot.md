

# spring boot

## 1、构建项目

1. 去 https://start.spring.io/ 下载基础项目
2. 添加 web 依赖

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
3. Application.java 同级 新建 controller/HomeController.java
```
//换自己的包名
package com.learn.first.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController{

    @RequestMapping("/")
    public String Home(){
        return "First Spring boot app";
    }

}
```
4. 运行 Application.java java文件
5. 打开浏览器，http：//127.0.0.1:8080,输出 First Spring boot app