#代码生成步骤

## 1.修改codeGenerator类中配置后.运行main方法生成代码

```
1.数据库相关配置
2.BASE_PACKAGE 基础包名
```

## 2.更改项目包名

1.application.property 相关配置
```
1. mybatis.type-aliases-package 更改为项目Model包路径
2. mapper.mappers 更改为正确包路径
```
2.application.java @mapper注解包名
```
 @MapperScan("com.aoxjia.merchat.sale.report.dao")
```

3.web.aop中切面包名

```
1.requestPointCut @Around("execution(* com.company.project.web.controller..*(..))")
2.exceptionPointCut @Around("execution(* com.company.project.web.exception..*(..)) ")

```

## 3.启动application.java
