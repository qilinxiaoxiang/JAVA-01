# 作业
## 1. 写代码实现Spring Bean的装配, 方式越多越好(XML、Annotation都可以), 提交到github.
1. XML方式已经被遗弃了, @Bean注解能实现XML的所有功能, 我理解在XML上花时间的价值并不高, 所以并没有写
2. 注解形式写了两种
    1. 模式注解, 即日常工作中增删改查用到的@Controller、@Service、@Repository、@Component, 对此小马哥的spring课程中有详细讲解
        - 其中基本类型字段的值也可以通过@Value注解从配置文件中取
    2. @Bean方式, 可以实现xml装配的所有功能, 包括scope、init-method、destroy-method、lazy、primary、depends-on等等

## 2. 给前面课程提供的Student/Klass/School实现自动配置和Starter


## 3. 研究一下JDBC接口和数据库连接池, 掌握它们的设计和用法
1. 用JDBC原生接口, 实现数据库的增删改查操作
2. 使用事务, PrepareStatement方式, 批处理方式, 改进上述操作
    1. https://www.cnblogs.com/zhaiqianfeng/p/4620187.html
    2. https://www.cnblogs.com/huangdabing/p/9189532.html
3. 配置Hikari连接池, 改进上述操作.
    - 原生使用, https://www.cnblogs.com/yszzu/p/10192206.html
    - Spring boot2(默认使用hikari), https://blog.csdn.net/woshilijiuyi/article/details/83010997