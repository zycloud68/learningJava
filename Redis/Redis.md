## Redis安装与使用

### 1. 在Linux下安装Redis

首先使用root用户登录Linux系统,然后执行一下命令

```bash
cd /usr/
# 创建redis文件夹
mkdir  redis
cd redis
wget http://download.redis.io/releases/redis-3.2.10.tar.gz
# 解压安装
# 解压文件到当前文件夹
tar -xzvf  redis-3.2.10.tar.gz
cd redis-3.2.10
make
# 启动
src/redis-server
```

最后看到redis的启动信息,这样Redis就启动好了,为了执行Redis命令,可以在另外一个窗口打开命令行窗口,用来启动Redis提供的命令行操作,依次执行一下命令:

```bash
cd /usr/redis/redis-3.2.10
src/redis-cli
```


