## Alipay沙箱的实现

### 1.  密钥工具

地址：https://miniu.alipay.com/keytool/create

![](img/1.png)

在线生成应用私钥`appPrivateKey`，然后点击保存并下载。



### 2.   沙箱环境

注册支付宝开发者账户，进入开发者控制台

https://openhome.alipay.com/dev/workspace



进入支付宝沙箱环境

https://open.alipay.com/platform/appDaily.htm



需要配置RSA2(SHA256)密钥

![](img/2.png)



### 3.  内网穿透

内网穿透就是把本机的ip和端口暴露到外网，通过指定的url可以访问你本地的服务。

natapp地址：https://natapp.cn

启动命令：natapp.exe -authtoken=你的authtoken



### 4.  Java SDK

打开支付宝官方的文档：https://opendocs.alipay.com/open/54/00y8k9

我使用Easy版本的Java SDK集成方案

maven依赖：

```xml
<dependency>
  <groupId>com.alipay.sdk</groupId>
  <artifactId>alipay-easysdk</artifactId>
  <version>2.2.0</version>
</dependency>
```



### 5.  Alipay配置

alipay.appId：应用ID

alipay.appPrivateKey：自动生成的私钥

alipay.alipayPublicKey：根据`alipay.appPrivateKey`生成的Alipay公钥

alipay.notifyUrl：回调地址



### 6.  支付和回调接口

参照阿里支付Demo

新建一个AliPayController，写一个Get接口，这个是支付的接口。需要前端把订单的标题、订单编号、订单的总金额传到后台来，后台去调用支付宝的APi生成支付订单，在网页上实现支付。支付成功后使用隧道回调本地端口。

第二个接口是支付成功回调的接口，我们在这个接口可以获取到支付订单的订单编号和支付时间，然后我们可以修改本地订单的支付状态。注意：这是一个 POST 接口。



### 4.  前端Vue调用

在书籍的表格里，我加了个 购买的按钮用来测试支付功能。

点击购买按钮，会发生一次网络请求。请求后台的OrderController生成一个订单，并返回调用AliPayController的调用地址：

前端拿到这个地址，直接在新窗口打开即可出现支付宝的沙箱支付页面：

这里的账户和密码都是模拟的，可以在自己的沙箱账户里找到

地址：https://open.alipay.com/platform/appDaily.htm?tab=info

账户是虚拟的，可以随意充值。



### 遇到的问题

#### 出现支付存在风险的警告

可能已经被系统捕捉为钓鱼网站

1. 打开一个新的浏览器，进入系统再次购买即可
2. 关闭所有网页，在浏览器更多工具里点击清除缓存，重新进入购买页面，点击购买。

然后你输入上面看到的账户密码继续就行了：

如果你上面回调接口配置的没问题，此时你的SpringBoot控制台会打印这些信息，非常详细的记录了这次交易的一些重要的参数。

```
=========支付宝异步回调========
交易名称: 碳中和革命
交易状态: TRADE_SUCCESS
支付宝交易凭证号: 2021122122001476780501972087
商户订单号: 1473215065908846592
交易金额: 60.00
买家在支付宝唯一id: 2088622957376782
买家付款时间: 2021-12-21 16:53:59
买家付款金额: 60.00
```



#### 重启后页面支付不回调

每次重启后 natapp 隧道都会重新生成新的外网地址，需要在配置文件里面及时更换，否则，无法回调

状态1表示已支付，同时设置支付的时间，在我的订单中可以看到支付信息。





















 