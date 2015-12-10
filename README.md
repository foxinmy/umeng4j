umeng4j
========

[友盟](http://dev.umeng.com/push/android/api-doc)消息推送
-------------

功能列表
-------

单播 `UniCast`

列播 `ListCast`

广播 `BroadCast`

组播 `GroupCast`

文件播 `FileCast`

自定义播 `CustomizedCast`

任务查询

任务取消

文件上传

项目说明
-------
友盟消息推送REST接口实现

如何使用
--------
1.接口的正确调用需要将`umeng4j.properties`文件复制到项目的`classpath`中

umeng4j.properties说明

| 属性名       |       说明      |
| :---------- | :-------------- |
| app_key     | 应用唯一标识 |
| master_secret  | 服务器秘钥,用于服务器端调用API请求时对发送内容做签名验证 |

2.实例化一个`UmengProxy`对象,调用API.

    UmengProxy umengProxy = new UmengProxy();
    umengProxy.pushMg(umengcast);
    
如何获取
-------
1.maven

	<dependency>
	    <groupId>com.foxinmy</groupId>
	    <artifactId>umeng4j</artifactId>
	    <version>1.1.0</version>
	</dependency>
	
2.git clone & mvn package.