# Git的远程连接

1、打开一个目录，作为git本地仓库

2、使用git打开

![](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200114140630866.png)

3、输入命令ssh-keygen -t rsa -b 4096 -C "你的邮箱地址"生成SSH指纹

![](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200114140821786.png)

> 注意，要按三下回车

4、已经成功的生成了ssh key，再输入eval “ssh-agent -s”，如下图：

![](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200114164004770.png)

5、再输入ssh-add ~/.ssh/id_rsa，再输入步骤2中设定的ssh的密码，在输入ssh-add ~/.ssh/id_rsa可能会产生“could not open a connection to your authentication agent”错误，如下图：

![](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200114164103319.png)

6、将key添加到github账户中，用vi复制key的内容：vi ~/.ssh/id_rsa.pub，右键选中出现的内容，然后按键盘“y”,复制选中的内容。如下图： 

![](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200114164151936.png)

7、将复制的内容填入

![](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200114164249850.png)

8、输入ssh -T git@gitub.com，如图，说明已经完成

![](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200114164412316.png)

9、执行git remote add origin git@github.com:xxxx/jerryPlayer.git 
其中xxxx是github账户的名称，jerryPlayer.git是在github上新建的仓库 
（git remote rm origin 可以取消连接Github上的 jerryPlayer.git 仓库）
10、把本地文件上传到github上，执行命令 git push -u origin master，若出现如下报错 

![](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200114164543623.png)

执行命令git pull --rebase origin master

然后再push