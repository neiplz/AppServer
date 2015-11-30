# AppServer
Pedometer Server based on Spring Boot

###Maven
* 使用 mvn eclipse:eclipse 命令生成eclipse项目

###MySQL
* 修改src\main\resources\application.properties中MySQL相关配置

###Spring Boot
* 项目导入后，直接运行src\main\java\com\github\neiplz\Application.java启动项目
* 默认启动嵌入式Tomcat，监听8080端口
* 项目启动后，可选择将测试数据导入数据库，测试数据位置:src\main\resources\db\datas.sql
