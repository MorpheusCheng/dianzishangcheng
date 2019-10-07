## session8
熟练掌握 SpringBoot 使用，以及实战中的逻辑分层，以及多环境配置项的使用

额外掌握一个，在复杂业务需求中，多业务扩展性问题。
### 设计思路
业务简单时，分controller，service，dao 三层就能满足需求，但是如果业务复杂，就需要分controller，service，manager，repository，dao 五层。 
controller层管登陆态，和页面对象的转换； service层管 rpc 层的事情，就是服务； manager层管业务流程； repository层管数据存储，包括tair和db或者其他，以及数据存储的策略； dao层就是sql，增删改查。

举个例子：根据当前登陆用户获取订单列表， controller层要做的是拿到userId，以及校验当前用户是否有获取订单的权限； 
service层要做的是出入参，异常处理，以及接口暴露逻辑（rpc、http等）,
本例中，传入的参数就是userId，service开放一个根据userId获取订单列表的接口，
该接口除了controller层使用，也可能作为rpc接口提供给二方合作者使用； manager层拿到userId，直接返回列表，
有异常就抛出。这里是整个逻辑的编排聚集点。可能会调用外部服务，以及repository层； 
repository层的逻辑可能是根据userId，先在redis上查询缓存，如果查到了就返回。查询不到就查db，
然后回写到redis上。这一层的核心目标就是拿数据，任何数据的操作都在这里， 
dao层很简单，就是根据目标数据库的调用方式，抽象出某个表或者域所对应的方法。比如增删改查，比如redis就是一堆 get remove，
数据库就是一堆sql，但对外暴露都是增删改查方法。

比较难理解五层中的service，我们要清除定义定位service。service有很多种，从注解的角度来说，
类加@Service注解，就变成了内部服务，加@RestController注解，就成了一个http服务，
加dubbo的@Service注解，就变成了一个dubbo服务，以此类推。

### 主要关注内容
完成一个标准的电商流程。商品详情、加购、下单、支付、发货、收货确认完成。

核心模型： 商品
