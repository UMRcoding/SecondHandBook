## Alipay沙箱的实现

### 1.  密钥工具

地址：https://miniu.alipay.com/keytool/create

![](img/1.png)

在线生成应用私钥`appPrivateKey`

点击生成密钥即可生成应用的公钥和私钥，点击保存并下载。



### 2.   沙箱环境

注册支付宝开发者账户，进入开发者控制台

https://openhome.alipay.com/dev/workspace

这里有专属的appid，网关的地址是测试环境地址，是固定的



进入支付宝沙箱环境

https://open.alipay.com/platform/appDaily.htm

![](img/2.png)

需要配置下RSA2(SHA256)密钥

 







### 3.  内网穿透

内网穿透就是把本机的ip和端口暴露到外网，通过指定的url可以访问你本地的服务。

natapp地址：https://natapp.cn/

启动命令：natapp.exe -authtoken=你的authtoken

隧道的端口要配置成你的后台端口

直接在上面的命令后面加上就可以启动你的natapp，设置内网穿透了。



### 4.  Java SDK

打开支付宝官方的文档：https://opendocs.alipay.com/open/54/00y8k9

使用Easy版本的Java SDK集成方案

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

新建一个AliPayController，写一个Get接口，这个是支付的接口，前端需要把 订单的标题、订单编号、订单的总金额传到后台来，后台去调用支付宝的APi生成支付订单，在网页上实现支付。

![img](file:///C:/Users/Oakley/AppData/Local/Temp/msohtmlclip1/01/clip_image026.jpg)

@GetMapping("/pay")

public String pay(AliPay aliPay) {

  AlipayTradePagePayResponse response;

  try {

​    // 发起API调用（以创建当面付收款二维码为例）

​    response = Factory.Payment.Page()

​        .pay(aliPay.getSubject(), aliPay.getTraceNo(), aliPay.getTotalAmount(), "");

  } catch (Exception e) {

​    System.err.println("调用遭遇异常，原因：" + e.getMessage());

​    throw new RuntimeException(e.getMessage(), e);

  }

  return response.getBody();

}

第二个接口是支付成功回调的接口，我们在这个接口可以获取到支付订单的订单编号和支付时间，然后我们可以修改本地订单的支付状态。注意：这是一个 POST 接口。

@PostMapping("/notify") // 注意这里必须是POST接口

public String payNotify(HttpServletRequest request) throws Exception {

  if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {

​    System.out.println("=========支付宝异步回调========");

 

​    Map<String, String> params = new HashMap<>();

​    Map<String, String[]> requestParams = request.getParameterMap();

​    for (String name : requestParams.keySet()) {

​      params.put(name, request.getParameter(name));

​      // System.out.println(name + " = " + request.getParameter(name));

​    }

 

​    String tradeNo = params.get("out_trade_no");

​    String gmtPayment = params.get("gmt_payment");

 

​    // 支付宝验签

​    if (Factory.Payment.Common().verifyNotify(params)) {

​      // 验签通过

​      System.out.println("交易名称: " + params.get("subject"));

​      System.out.println("交易状态: " + params.get("trade_status"));

​      System.out.println("支付宝交易凭证号: " + params.get("trade_no"));

​      System.out.println("商户订单号: " + params.get("out_trade_no"));

​      System.out.println("交易金额: " + params.get("total_amount"));

​      System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));

​      System.out.println("买家付款时间: " + params.get("gmt_payment"));

​      System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

 

​      // 更新订单未已支付

​      orderMapper.updateState(tradeNo, 1, gmtPayment);

​    }

  }

  return "success";

}

### 4.  前端Vue调用

在书籍的表格里，我加了个 购买的按钮用来测试支付功能。

![img](file:///C:/Users/Oakley/AppData/Local/Temp/msohtmlclip1/01/clip_image028.jpg)

点击购买按钮，会发生一次网络请求。请求后台的OrderController生成一个订单，并返回调用AliPayController的调用地址：

![img](file:///C:/Users/Oakley/AppData/Local/Temp/msohtmlclip1/01/clip_image030.jpg)

@GetMapping("/buy/{bookId}")

public Result<?> buy(@PathVariable Long bookId) {

  Book book = bookMapper.selectById(bookId);

  String orderNo = IdUtil.getSnowflake().nextIdStr();

  String payUrl = "http://localhost:9090/alipay/pay?subject=" + book.getName() + "&traceNo=" + orderNo + "&totalAmount=" + book.getPrice();

 

  User user = getUser();

  Order order = new Order();

  order.setOrderNo(orderNo);

  order.setTotalPrice(book.getPrice());

  order.setPayPrice(book.getPrice());

  order.setTransportPrice(BigDecimal.ZERO);

  order.setUserId(user.getId());

  order.setUsername(user.getUsername());

  order.setName(book.getName());

  save(order);

  // 新建订单，扣减库存

  return Result.success(payUrl);

}

前端拿到这个地址，直接在新窗口打开即可出现支付宝的沙箱支付页面：

![img](file:///C:/Users/Oakley/AppData/Local/Temp/msohtmlclip1/01/clip_image032.jpg)

buy(bookId) { // 购买按钮调用的方法

 request.get("/order/buy/" + bookId).then(res => {

  // 请求成功跳转沙箱支付的页面

  window.open(res.data)

 })

},

支付宝沙箱页面是这样的：

![img](file:///C:/Users/Oakley/AppData/Local/Temp/msohtmlclip1/01/clip_image034.jpg)

这里的账户和密码都是模拟的，可以在自己的沙箱账户里找到，地址：https://openhome.alipay.com/platform/appDaily.htm?tab=account

![img](file:///C:/Users/Oakley/AppData/Local/Temp/msohtmlclip1/01/clip_image036.jpg)

账户是虚拟的，可以随意充值。

如果一不小心出现了下面的这个页面

![img](file:///C:/Users/Oakley/AppData/Local/Temp/msohtmlclip1/01/clip_image038.jpg)别慌！你有2个选择：

\1.   打开一个新的浏览器，进入系统再次购买即可！

\2.   关闭所有网页，清除缓存，重新进入购买页面，点击购买。

![img](file:///C:/Users/Oakley/AppData/Local/Temp/msohtmlclip1/01/clip_image040.jpg)

 

然后你输入上面看到的账户密码继续就行了：

![img](file:///C:/Users/Oakley/AppData/Local/Temp/msohtmlclip1/01/clip_image042.jpg)

支付密码也是 111111 ，点击确认付款

![img](file:///C:/Users/Oakley/AppData/Local/Temp/msohtmlclip1/01/clip_image044.jpg)

跳转到这个页面，表示支付成功

![img](file:///C:/Users/Oakley/AppData/Local/Temp/msohtmlclip1/01/clip_image046.jpg)

如果你上面回调接口配置的没问题，此时你的SpringBoot控制台会打印这些信息：

![img](file:///C:/Users/Oakley/AppData/Local/Temp/msohtmlclip1/01/clip_image048.jpg)

非常详细的记录了这次交易的一些重要的参数。

一定要注意：每次重启 natapp 都会重新生成新的外网地址，你需要在你的配置文件里面及时更换，否则，无法回调！

在这个回调接口里面我们可以拿到自己的订单编号 traceNo，然后我们就能更新自己的订单支付状态了。

![img](file:///C:/Users/Oakley/AppData/Local/Temp/msohtmlclip1/01/clip_image050.jpg)

// 更新订单未已支付

orderMapper.updateState(tradeNo, 1, gmtPayment);

状态1表示已支付，同时设置支付的时间，在我的订单中可以看到这些信息。

![img](file:///C:/Users/Oakley/AppData/Local/Temp/msohtmlclip1/01/clip_image052.jpg)

 

至此，支付宝沙箱模拟支付集成完毕！

 