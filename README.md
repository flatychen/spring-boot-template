## 代码生成步骤

1. 修改codeGenerator类中配置后.运行main方法

```
1.数据库相关配置
2.BASE_PACKAGE 基础包名
```

2. 更改application.property中包名

```
1. mybatis.type-aliases-package 更改为项目Model包路径
2. mapper.mappers 更改为正确包路径
```

