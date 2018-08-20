# AirBoard 微服务平台

## 平台简介

AirBoard是基于SpringBoot 2.x和Spring Cloud 2.x搭建的一个Java微服务框架，以Spring MVC为模型视图控制器，MyBatis和Spring Data Jpa为数据访问层，
Apache Shiro为权限授权层，Redis对常用数据进行缓存，RabbitMQ实现异步消息收发

## 内置功能

1.	用户管理：用户CRUD

## 技术选型

1、后端

* 核心框架：SpringBoot 2.0.4.RELEASE、Spring Cloud 2.0.4.RELEASE
* 安全框架：Apache Shiro 1.4.0
* 服务端验证：Hibernate Validator 5.3.4
* 布局框架：FreeMarker 2.0.4
* 持久层框架：MyBatis 3.0-Beta, Spring Data Jpa 2.0.4.RELEASE
* 数据处理框架：MyBatis-Plus 3.0-Beta
* 数据库连接池：Alibaba Druid 1.1.10
* 缓存框架：Redis 2.0.4.RELEASE
* 消息框架：RabbitMQ
* 注册中心：Eureka
* 前端API展示：Swagger-UI
* 日志管理：LogBack
* TOKEN模式： JWT 3.4.0
* 工具类：Apache Commons、Jackson 2.9.6

2、前端

2.1 HPlus
* Js框架：jQuery 2.2.4
* CSS框架：Twitter Bootstrap 3.3.7 + HPlus 2.3.7
* 数据表格：BootStrap-Table
* 工具类框架：Layer 3.0

2.2 Vue
* Js框架：vue
* 全局框架：暂无

3、平台

* 数据库支持：支持MySql多数据源
* 开发环境：Java1.8以上、IDEA、Maven 3.1以上、Git

## 演示地址

暂无

## 快速体验

1. 具备运行环境：JDK1.7+、Maven3.0+、MySql5+。
2. 修改airbd-api\src\main\resources\application.yml文件中的数据库设置。
3. 运行mvn package脚本，即可创建项目jar文件，同时也可以通过java -jar *.jar 即可本地预览
4. 将airbd-api\src\main\resources\airboard.sql导入本地数据库即可
5. 测试账号，用户名：admin 密码：111111

## 如何交流、反馈、参与贡献？

* E-mail：10511026@qq.com
* GitHub：<https://github.com/w10511026/AirBoard.git>