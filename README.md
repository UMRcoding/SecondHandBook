# 开源项目说明

**目的：学术研究**

如果您遇到任何安装或使用问题，可以通过QQ或issue的方式告诉我。同时使用本项目写论文或做其它学术研究的朋友，如果想把自己的研究成果展示在下面，也可以通过QQ或issue的方式告诉我。看到的小伙伴记得点点右上角的Star~

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022236.png)



# 二手商品交易系统的设计与实现

前端：Vue，Vue-Router，Vuex，Axios，ElementUI

后台：SpringBoot，Mybatis-Plus



## 需求分析

在资源紧缺的今天，节约纸张是非常重要的课题，尤其是书籍的再利用。对于学生而言，购买新的书籍不仅浪费金钱，而且浪费纸张。书籍的循环利用恰好解决这个问题。但是二手书的交易一直存在不同程度的局限性，很多二手书持有者往往将其按照废纸的价钱卖到了二手书市场。为了解决这个问题,针对二手书的归属问题，我们决定建立属于个人对个人的二手书交易品台，一方面解决了纸张浪费的问题，另一方面解决了二手书持有者在廉价处理二手书方面的问题，让二手书持有者和二手书购买者更好地沟通与交易，在很大程度上解决了二手书持有者和二手书购买者在很多方面的问题,并且更好地进行文化的交流。

本系统立足于服务学生，节约资源的想法，建立适合学生日常交易的简易二手书交易平台力求帮助在校学生以最小的代价、最快捷的方式实现旧书买卖。平台以学校交易平台为中心，扩大平台交易规模，力求长远发展，将二手书这个平台发展为跨学校，跨区域的开放性网上市场。



**书籍发布流程数据流图**

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022828.png)



**购买流程数据流图**

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022834.png)



### 功能性需求分析

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022829.png)





**用户注册与登录**

未注册的用户不能浏览二手书信息，也不能进行购买等一系列操作，所以为了给予用户最基本的操作权限，进行注册并登陆后才可以买书或卖书。为用户提供注册账号，登录账号的需求，进一步实现用户的购买或出售需求，并且每一个用户拥有自己的独立ID，更加方便管理员区别用户，管理用户。

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022831.png)



**书籍发布与管理**

当卖家有新的二手书上架或书籍下架时，卖家产生更新自己的书籍信息的需求，将新书籍信息添加、下架的书籍信息删除.后台实现商品管理，前台商品展示。商家的商品属性可以自定义，能够满足产品行业特点。



**聊天室（买卖方之间的）**

当买家想要提前了解书籍信息时，为了解决用户和商家沟通的需求提供用户购买前想要了解商品的详情的、商品最终价格的商议、退款、以及商品售后服务的需求

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022835.png)



**已发布书籍的查找与购买**

买家可以根据自己的需要浏览查找自己需要的书籍.用户可以查看所有的书籍信息，也可以只查看自己查找的书籍信息.

为了解决用户在大量商品中寻找自己想要的商品并且提供购买服务

使用户更加快捷的查找想要的商品并且购买



**留言功能**

当有买家不想提前和卖家沟通，但是还想了解商品详情时，同时也是为了解决用户购买前了解商家和商家商品的需求，提供已购买用户写下对购买商品的意见和对商家态度的评价，方便新用户了解。同时也提供类贴吧功能，买家之间，卖家之间，买卖双方可以互相对话.



### 非功能性需求分析

**交易平台提供瞬时客流量的可靠性（通过负载均衡）**

简易图

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022845.png)





**用户头像的自定义，书籍实体图片的发布**

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022721.png)

头像展示

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022739.png)



**更换头像**

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022764.png)

头像更换成功

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022797.png)



**权限功能：提供管理员和普通用户的不同权限**





## 概要设计

**总体设计**

(1)登录注册模块

对于一个二手商品系统而言，除了运行流畅和性能稳定之外，还必须要对数据进行保密，不允许他人进行非法访问，所以本系统需要设有登录与注册的功能。

注册的普通用户仅能使用部分功能（包括展示模块，书籍管理，我的订单，通知管理，聊天室，在线留言），管理员能够正常使用全部的用户功能，包括展示模块，书籍管理，我的订单，通知管理，聊天室，在线留言，用户管理，功能权限，角色管理。注册用户需要填写登陆名称、以及密码。





(7)个人信息查询和修改
系统能管理登录名称、密码，头像，个人历史评论，可以对个人信息进行修改，删除。





**技术架构**

采用JavaScript+SpringBoot+MySQL技术实现。开发工具采用IDEA，前端框架采用Vue.js框架，后端框架采用SpringBoot+Maven，系统选用的数据库是MySQL数据库。



**部署方案一**

项目架设在Tomcat和java环境的服务器上，前端通过nginx代理到80端口，实现静态页面访问。将负载均衡到K8S集群，管理不同的docker。每个docker有自己的Pod，运行着jar或者war包，对外暴露不同地址。通过PromeTheus进行数据采集，采集到的数据发送给Grafana。Grafana可设置报警值，一旦K8S出现宕机或压力超过上限，将发送报警数据给睿象云平台。睿象云将通过电子邮件，电话，短信等方式通知管理员

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022546.png)



方案二

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022575.png)

采用JavaScript+SpringBoot+MySQL技术实现。项目架设在Tomcat和java环境的服务器上，开发工具采用IDEA，前端框架采用Vue.js框架，后端框架采用SpringBoot+Maven，系统选用的数据库是MySQL数据库。Alipay沙箱， docker容器， k8s集群，nginx负载均衡， k8s检测窗



**重要功能交互图**：





**开发环境：**

- Idea 2021.02
- Python-3.8.3
- node v14.18.0
- vue/cli 5.0.0-beta.7



**运行环境：**

- jdk-8u161-linux-x64
- apache-tomcat-9.0.54
- mysql-5.7.30-linux-glibc2.12-x86_64
- nginx-1.21.4
- prometheus-2.32.1



**数据库设计：**

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022596.png)





## 详细设计

分模块设计，每个模块要有模块的功能描述、流程图或时序图、类图、模块输入、模块输出、页面跳转设计）





## 登录验证的实现

新建 ValidCode 组件

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022618.png)

```vue
  methods: {
    refreshCode () {
      this.createdCode()
    }，
    createdCode () {
      const len = this.length
      const codeList = []
      const chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz0123456789'
      const charsLen = chars.length
      // 生成
      for (let i = 0; i < len; i++) {
        const rgb = [Math.round(Math.random() * 220), Math.round(Math.random() * 240), Math.round(Math.random() * 200)]
        codeList.push({
          code: chars.charAt(Math.floor(Math.random() * charsLen)),
          color: `rgb(${rgb})`,
          fontSize: `${10 + (+[Math.floor(Math.random() * 10)] + 6)}px`,
          padding: `${[Math.floor(Math.random() * 10)]}px`,
          transform: `rotate(${Math.floor(Math.random() * 90) - Math.floor(Math.random() * 90)}deg)`
        })
      }

      // 指向
      this.codeList = codeList

      // 将当前数据发出去
      // console.log(codeList.map(item => item.code).join(''))
      this.$emit('input', codeList.map(item => item.code).join(''))
    },

    getStyle (data) {
      return `color: ${data.color}; font-size: ${data.fontSize}; padding: ${data.padding}; transform: ${data.transform}`
    }
  }
}
</script>
```

在 Login.vue 里面新增表单

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022644.png)

导入组件，新建变量 validCode

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022946.png)

新增方法：createValidCode，用来接收组件传过来的验证码并赋值给 data 中的变量 valideCode

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022356.png)

在 login 方法里校验

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022382.png)



## 导入导出

在 pom.xml 里面添加 POI 依赖

```pom
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>4.1.2</version>
</dependency>
```

写导出接口：

```java
/**
* 功能描述: Excel导出
* @Param: [javax.servlet.http.HttpServletResponse]
* @Author: Liu Heng
* @return: void
*/
@GetMapping("/export")
public void export(HttpServletResponse response) throws IOException {

    List<Map<String, Object>> list = CollUtil.newArrayList();

    List<User> all = userMapper.selectList(null);
    for (User user : all) {
        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("用户名", user.getUsername());
        row1.put("昵称", user.getNickName());
        row1.put("年龄", user.getAge());
        row1.put("性别", user.getSex());
        row1.put("地址", user.getAddress());
        list.add(row1);
    }

    // 2. 写excel
    ExcelWriter writer = ExcelUtil.getWriter(true);
    writer.write(list, true);

    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
    String fileName = URLEncoder.encode("用户信息", "UTF-8");
    response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

    ServletOutputStream out = response.getOutputStream();
    writer.flush(out, true);
    writer.close();
    IoUtil.close(System.out);
}
```

Excel导入

```java
/**
* 功能描述: Excel导入
* 导入的模板可以使用 Excel导出的文件
* @Param: [org.springframework.web.multipart.MultipartFile]
* @Author: Liu Heng
* @return: com.example.demo.common.Result<?>
*/
@PostMapping("/import")
public Result<?> upload(MultipartFile file) throws IOException {
    InputStream inputStream = file.getInputStream();
    List<List<Object>> lists = ExcelUtil.getReader(inputStream).read(1);
    List<User> saveList = new ArrayList<>();
    for (List<Object> row : lists) {
        User user = new User();
        user.setUsername(row.get(0).toString());
        user.setNickName(row.get(1).toString());
        user.setAge(Integer.valueOf(row.get(2).toString()));
        user.setSex(row.get(3).toString());
        user.setAddress(row.get(4).toString());
        saveList.add(user);
    }
    for (User user : saveList) {
        userMapper.insert(user);
    }
    return Result.success();
}
```

加完接口重启后台

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022409.png)

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022436.png)





## Alipay沙箱的实现

### 1.  密钥工具

地址：https://miniu.alipay.com/keytool/create

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022463.png)

在线生成应用私钥`appPrivateKey`，然后点击保存并下载。



### 2.   沙箱环境

注册支付宝开发者账户，进入开发者控制台

https://openhome.alipay.com/dev/workspace



进入支付宝沙箱环境

https://open.alipay.com/platform/appDaily.htm



需要配置RSA2(SHA256)密钥

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022810.png)



### 3.  内网穿透

内网穿透就是把本机的ip和端口暴露到外网，通过指定的url可以访问你本地的服务。

natapp地址：https://natapp.cn

启动命令：natapp.exe -authtoken=你的authtoken

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022429.png)

此时访问 http://bwd7wq.natappfree.cc 即后端页面

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022456.png)

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

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022486.png)



### 7.  前端Vue调用

在书籍的表格里，我加了个 购买的按钮用来测试支付功能。

点击购买按钮，会发生一次网络请求。请求后台的OrderController生成一个订单，并返回调用AliPayController的调用地址：

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022516.png)

前端拿到这个地址，直接在新窗口打开即可出现支付宝的沙箱支付页面：

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022543.png)

这里的账户和密码都是模拟的，可以在自己的沙箱账户里找到

地址：https://open.alipay.com/platform/appDaily.htm?tab=info

账户是虚拟的，可以随意充值。





## 遇到的问题

### 出现支付存在风险的警告

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



### 重启后页面支付不回调

每次重启后 natapp 隧道都会重新生成新的外网地址，需要在配置文件里面及时更换，否则，无法回调

状态1表示已支付，同时设置支付的时间，在我的订单中可以看到支付信息。



### 405 问题

请求的 Http 类型和你实际返回的 Http 类型不一致。我后台写了个 POST 接口，但是前端使用 GET 请求访问，服务器就会返回 405 的错误。所以要解决这个问题，只需要检查一下请求的方法类型是否和自己定义的方法返回类型一致即可。 

http 接口分成 3 部分

- 接口类型：例如 GET
- 接口路径：例如 /user
- 接口参数：例如 ?name=xxx 或者 /xxx



### 接口代理问题

```vue
// 跨域配置
module.exports = {
    devServer: {                //记住，别写错了devServer//设置本地默认端口  选填
        port: 9876,
        proxy: {                 //设置代理，必须填
            '/api': {              //设置拦截器  拦截器格式   斜杠+拦截器名字，名字可以自己定
                target: 'http://localhost:9090',     //代理的目标地址
                changeOrigin: true,              //是否设置同源，输入是的
                pathRewrite: {                   //路径重写
                    '/api': ''                     //选择忽略拦截器里面的单词
                }
            }
        }
    }
}
```



### JWT权限

加入工具类 TokenUtils 生成 Token，genToken 这个方法主要是根据用户的信息生成 token 的，这个 token 里面存储了 userId，使用用户密码进行加密，默认的过期时间是 1 天。

```java
public static String genToken(User user) {
    return JWT.create().withExpiresAt(DateUtil.offsetDay(new Date(), 1)).withAudience(user.getId().toString())
            .sign(Algorithm.HMAC256(user.getPassword()));
}
```

在 UserController 的 Login 方法中使用

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022775.png)

加入拦截器，拦截除了注册登录外所有的请求，然后进入自己定义的 AuthInterceptor 类中验证 token 的合法性

![](https://umrcoding.oss-cn-shanghai.aliyuncs.com/Obsidian/202212040022065.png)

可以自定义一个 BaseController 来获取请求中的 token，得到用户信息，任何继承 BaseController 的 Controller 都可以通过这个 getUser 的方法获取用户信息。











