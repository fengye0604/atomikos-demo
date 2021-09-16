## 项目演示atomikos的三种简单使用
### 直接使用TransactionEssentials的API ###
测试代码在com.xy.test包下的TransactionEssentialsTest

### 与spring和mybatis整合使用 ###
配置文件路径：resource/context/spring-atomikos.xml
测试代码在com.xy.test包下的TransactionEssentialsSpringTest

### 与spring和mybatis和springmvc整合成web项目 ###
spring配置文件路径：resource/context/aplicationContext.xml
springMvc配置文件路径：resource/context/spring-mvc.xml
测试URL：http://localhost:8082/testTransaction/add/llalla


### dbsql目录下有mysql和oracle的建表语句 ###
这是修改的文件----
