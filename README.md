# Android 大作业

* **项目描述** 
> 此次的大作业是个人完成的，所以想做一些和自己身边相关的东西，正好暑假的时候想写科协的官网却鸽了，这次用Android来实现一个物联网科协的内部app   

项目service主要包括以下几个部分：
1. 社团活动展示（即文章展示）
2. 旧书借用系统
3. 外联记账系统
4. 用户登录系统


* **Tips**
1. 在测试webview内嵌html页面时，一定要注意的是，url的格式是固定的 file:///android_asset/index.html ,html页面也是放在固定的文件夹即名为assets的package下的，assets的位置也是固定的，必须与java，res平行存在。
2. 在测试Android使用jdbc连接mysql时，一定要注意的是，在AndroidManiFest.xml中加上 `<uses-permission android:name="android.permission.INTERNET" />` ,同时，还要注意，不能直接将sql连接之类的操作写在mainactivity中，而要创建一个新的进程，直接操作似乎是不被允许的，另外，在新的进程中不能对页面进行任何操作，即在非UI线程不要试着去操作界面。
