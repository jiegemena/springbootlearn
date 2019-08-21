

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

## SpringBoot web开发之登录注册

1. 新建 LoginController.java 文件
2. 使用 session 控制登录
```
@Autowired
HttpSession session;
//添加sessionKey
session.setAttribute("name", "admin");
//获取session
session.getAttribute("name")
//删除
session.removeAttribute("name");
```
----------
示例：(简单控制登录)
- 主页控制器
```
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController{

    @Autowired
    HttpSession session;

    @RequestMapping("/")
    public String Home(){
        if(!"admin".equals(session.getAttribute("name"))){
            //重定向 到 登录页面
            return "redirect:/login/";
        }
        //登录成功
        return "home/index";
    }
}
```
--------
- 登录控制器
```
package com.learn.first.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/login")
public class LoginController{

    @Autowired
    HttpSession session;

    @RequestMapping("")
    public String index(){
        return "login/index";
    }

    @RequestMapping("/in")
    public RedirectView in(@RequestParam String lname,@RequestParam String pwd){
        if("admin".equals(lname) && "admin".equals(pwd)){
            RedirectView redirectTarget = new RedirectView();
            redirectTarget.setContextRelative(true);
            redirectTarget.setUrl("/");
            session.setAttribute("name", "admin");
            
            return redirectTarget;
        }

        RedirectView redirectTarget = new RedirectView();
        redirectTarget.setContextRelative(true);
        redirectTarget.setUrl("/login/");
        return redirectTarget;
    }

    @RequestMapping("/out")
    public String out(){
        // session.setAttribute("name", null);
        session.removeAttribute("name");
        return "redirect:/login/";
    }
}
```
-----
pom.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.7.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.learn</groupId>
	<artifactId>first</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>first</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>nz.net.ultraq.thymeleaf</groupId>
			<artifactId>thymeleaf-layout-dialect</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

```

3. 使用 thymeleaf模板 返回页面
4. 打开浏览器，http：//127.0.0.1:8080 测试
5. demo 路径

## 使用 mysql

1. 构造 数据库实体类型
- 添加maven依赖
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
</dependency>
```
- 新建 entity/BaseEntity.java   

(1) 每个数据表应该建立一个 ID 作为主键，独立一个 BaseEntity 出来，不创建表
(2) 创建 用户表,新建 entity/User.java （注意，属性小写开头）
```
package com.learn.first.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.learn.first.commons.StringTools;

@Entity
public class User{
	@Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true, length = 32)
    private String userName;
    @Column(nullable = false,length = 32)
    private String passWord;
    @Column(nullable = false, unique = true,length = 32)
    private String phoneNum;

	//...get--set
}
```

(3) 添加 数据库配置 src/main/resources/application.properties ,(mysql要自己先到数据库新建数据库 test)
```
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.datasource.url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8
spring.datasource.username=root
spring.datasource.password=asdf@#123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

2. 新建 Login2Controller.java

```
// 添加一天数据测试
@RequestMapping("")
public String index(){
	//TODO TEST
	if(userService.getUserByName("admin") == null){
		User user = new User();
		user.setUserName("admin");
		user.setPassWord("admin");
		user.setPhoneNum("110900");
		userService.Add(user);
	}
	return "login/index";
}

//登录验证
@RequestMapping("/in")
public String in(@RequestParam String lname,@RequestParam String pwd){
	User user = userService.getUserByName(lname);
	if(user != null && user.checkPassWorl(pwd)){
		session.setAttribute("name", "admin");
		return "redirect:/";
	}
	return "redirect:/login/";
}
```