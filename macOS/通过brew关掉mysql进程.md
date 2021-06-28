通过brew安装了mysql5.7，想关掉mysql进程，尝试了以下几种方法：

- `mysql.server stop`
- `brew services stop mysql`
- Activity Monitor里结束进程

都失败了，用ps命令查看进程，每次mysql都会自动重启，google后找到解决办法，输入以下命令：
 `launchctl unload -w ~/Library/LaunchAgents/homebrew.mxcl.mysql@5.7.plist`  
 命令最后的plist文件以实际文件名为准。最好是到这个**~/Library/LaunchAgents/**目录中确认你的plist文件名是什么。执行了这个命令后再执行上面的第一条命令就可以关闭mysql了，若不想mysql开机自动运行就执行上面的第二条命令（前提是通过brew安装）。
