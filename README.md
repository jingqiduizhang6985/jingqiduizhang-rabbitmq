# jingqiduizhang-rabbitmq
消息中间件rabbitmq

rabbitMQ
开源中国 消息中间件
https://www.oschina.net/project/tag/103/message-server

rabbitmq-base
参考文档
https://www.oschina.net/p/rabbitmq

https://my.oschina.net/u/4168855/blog/4916220

simple 一对一发送
work1 work模式 1个发送 多个接收 相互抢占资源  消息平均分配
work2 work模式 1个发送 多个接收 相互抢占资源  能者多得 自动模式
work2 work模式 1个发送 多个接收 相互抢占资源  能者多得 手动模式
fanout  订阅模式 生产者发送到交换机   消费者 绑定交换机和队列 就可以使用数据
routing 路由模式 在发送的时候设置一个key   在接收端绑定key 就会获取到带有这个key的消息
topic   通配符 设置通配符 进行发送和接收
