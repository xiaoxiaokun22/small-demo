### redis的持久化方式rdb和aof什么区别？
rdb fork创建一个子进程去持久化数据,默认保存在dump.rdb文件。
触发机制 1.shutdown的时候 2.配置文件的三个save策略的时候 3.主动执行save或者bgsave的时候  
aof 将redis的操作命令直接保存到append.aof文件。启动的时候恢复
触发机制 三种：no，always，everysec（默认，每秒同步一次）
### redis主从复制，如果主的持久化文件被删了，从的还在而且里面有数据；然后先启动主，再启动从，数据会把从服务器的数据复制到主服务器吗？
不会，经过测试不管谁先启动，数据总是把主服务器的数据复制到从服务器
