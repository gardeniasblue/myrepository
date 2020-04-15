> 如果你已经配置好了Git

## 一、选择一个目录，进入git命令操作

## 二、绑定github仓库

git remote add origin git@github.com:wotuobang/myrepository.git

## 三、将指定文件上传到本地仓库

1、git add 文件名

2、git commit -m "提交信息"

3、git pull --rebase origin master

4、git push -u origin master

## 四、Git的常用操作

### 1、git diff 文件名

当文件被修改后，gitstatus查看文件，会看到如下

![](C:\Users\Administrator\Pictures\1.PNG)

这时输入git diff 文件名 命令，即可查看被修改的内容

![](C:\Users\Administrator\Pictures\2.PNG)

### 2、git log  查看所有提交的版本及日志

![](C:\Users\Administrator\Pictures\3.PNG)

### 3、git reset --hard HEAD 版本的回退

要想回退至上一个版本，只需输入命令git reset --hard HEAD^ 如果想回退至上上版本，则git reset --hard HEAD^^

git reset --hard 版本号  可回退至指定版本号



修改