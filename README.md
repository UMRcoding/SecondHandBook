# 二手商品交易系统的设计与实现

这是一个实习练手项目，任何问题请与本人联系

前端：Vue，Vue-Router，Vuex，Axios，ElementUI

后台：SpringBoot，Mybatis-Plus



## 需求分析

在资源紧缺的今天，节约纸张是非常重要的课题，尤其是书籍的再利用。对于学生而言，购买新的书籍不仅浪费金钱，而且浪费纸张。书籍的循环利用恰好解决这个问题。但是二手书的交易一直存在不同程度的局限性，很多二手书持有者往往将其按照废纸的价钱卖到了二手书市场。为了解决这个问题,针对二手书的归属问题，我们决定建立属于个人对个人的二手书交易品台，一方面解决了纸张浪费的问题，另一方面解决了二手书持有者在廉价处理二手书方面的问题，让二手书持有者和二手书购买者更好地沟通与交易，在很大程度上解决了二手书持有者和二手书购买者在很多方面的问题,并且更好地进行文化的交流。

本系统立足于服务学生，节约资源的想法，建立适合学生日常交易的简易二手书交易平台力求帮助在校学生以最小的代价、最快捷的方式实现旧书买卖。平台以学校交易平台为中心，扩大平台交易规模，力求长远发展，将二手书这个平台发展为跨学校，跨区域的开放性网上市场。


**书籍发布流程数据流图**

![](MyPhoto/30.png)



**购买流程数据流图**

![](MyPhoto/31.png)



### 功能性需求分析

![](MyPhoto/29.png)





**用户注册与登录**

未注册的用户不能浏览二手书信息，也不能进行购买等一系列操作，所以为了给予用户最基本的操作权限，进行注册并登陆后才可以买书或卖书。为用户提供注册账号，登录账号的需求，进一步实现用户的购买或出售需求，并且每一个用户拥有自己的独立ID，更加方便管理员区别用户，管理用户。

![](MyPhoto/27.png)



**书籍发布与管理**

当卖家有新的二手书上架或书籍下架时，卖家产生更新自己的书籍信息的需求，将新书籍信息添加、下架的书籍信息删除.后台实现商品管理，前台商品展示。商家的商品属性可以自定义，能够满足产品行业特点。



**聊天室（买卖方之间的）**

当买家想要提前了解书籍信息时，为了解决用户和商家沟通的需求提供用户购买前想要了解商品的详情的、商品最终价格的商议、退款、以及商品售后服务的需求

![](MyPhoto/28.png)



**已发布书籍的查找与购买**

买家可以根据自己的需要浏览查找自己需要的书籍.用户可以查看所有的书籍信息，也可以只查看自己查找的书籍信息.

为了解决用户在大量商品中寻找自己想要的商品并且提供购买服务

使用户更加快捷的查找想要的商品并且购买



**留言功能**

当有买家不想提前和卖家沟通，但是还想了解商品详情时，同时也是为了解决用户购买前了解商家和商家商品的需求，提供已购买用户写下对购买商品的意见和对商家态度的评价，方便新用户了解。同时也提供类贴吧功能，买家之间，卖家之间，买卖双方可以互相对话.



### 非功能性需求分析

**交易平台提供瞬时客流量的可靠性（通过负载均衡）**

简易图

![](MyPhoto/14.png)





**用户头像的自定义，书籍实体图片的发布**

![](MyPhoto/32.png)

![](MyPhoto/33.png)

头像展示

![](MyPhoto/34.png)



**更换头像**

![](MyPhoto/35.png)

头像更换成功

![](MyPhoto/36.png)

![](MyPhoto/37.png)





**权限功能：提供管理员和普通用户的不同权限**

![](MyPhoto/38.png)









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

![](MyPhoto/21.png)



方案二

![](MyPhoto/22.png)

采用JavaScript+SpringBoot+MySQL技术实现。项目架设在Tomcat和java环境的服务器上，开发工具采用IDEA，前端框架采用Vue.js框架，后端框架采用SpringBoot+Maven，系统选用的数据库是MySQL数据库。Alipay沙箱， docker容器， k8s集群，nginx负载均衡， k8s检测窗





**重要功能交互图**：





**开发环境：**

- **Idea 2021.02**
- **Python-3.8.3**
- **node v14.18.0**
- **vue/cli 5.0.0-beta.7**



**运行环境：**

- **jdk-8u161-linux-x64**
- **apache-tomcat-9.0.54**
- **mysql-5.7.30-linux-glibc2.12-x86_64**
- **nginx-1.21.4**
- **prometheus-2.32.1**



**数据库设计：**

![](MyPhoto/39.png)





## 详细设计

分模块设计，每个模块要有模块的功能描述、流程图或时序图、类图、模块输入、模块输出、页面跳转设计）



## 用户信息

**entity**

```java
package com.example.demo.entity;

@TableName("user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String nickName;

    private Integer age;

    private String sex;

    private String address;

    private String avatar;

    @TableField(exist = false)
    private List<Integer> roles;

    @TableField(exist = false)
    private List<Book> bookList;

    @TableField(exist = false)
    private String token;

    private BigDecimal account;

    @TableField(exist = false)
    private Set<Permission> permissions;
}
```



**controller**

```java
package com.example.demo.controller;

public class UserController extends BaseController {

    @PostMapping
    public Result<?> save(@RequestBody User user) {
        if (user.getPassword() == null) {
            user.setPassword(bCryptPasswordEncoder.encode(PwdEnum.PASSWORD.getPassword()));
        }
        userMapper.insert(user);

        UserRole userRole = UserRole.builder()
                .userId(user.getId())
                .roleId(RoleEnum.USER.getRoleId())
                .build();
        userRoleMapper.insert(userRole);

        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody User user) {
        userMapper.updateById(user);
        return Result.success();
    }


    @PutMapping("/pass")
    public Result<?> pass(@RequestBody Map<String, Object> map) {
        User user = userMapper.selectById((Integer) map.get("userId"));
        if (user== null) {
            return Result.error("-1", "未找到用户");
        }
        if (!bCryptPasswordEncoder.matches(map.get("password").toString(), user.getPassword())) {
            return Result.error("-1", "密码错误");
        }
        map.put("newPass", (bCryptPasswordEncoder.encode(map.get("newPass").toString())));
        userMapper.updatePass(map);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        //生明不使用配置文件
        BasicConfigurator.configure();
        Logger logger = Logger.getLogger(UserController.class.toString());
        logger.info("\n\n后台接口 getById 被调用\n\n");

        return Result.success(userMapper.selectById(id));
    }

    @GetMapping("/all")
    public Result<?> findAll() {
        return Result.success(userMapper.selectList(null));
    }
}
```





## 书籍信息

**entity**

```java
package com.example.demo.entity;

@TableName("book")
@Data
public class Book {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private BigDecimal price;

    private String author;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    private String cover;

    private String userId;

    // TableField注解表示数据库不存在的字段，而Java中需要使用，加上这个注解就不会报错
    @TableField(exist = false)
    private String username;
}
```



**controller**

```java
package com.example.demo.controller;

@RestController
@RequestMapping("/book")
public class BookController extends BaseController{

    @Resource
    BookMapper bookMapper;

    @PostMapping
    public Result<?> save(@RequestBody Book Book) {
        bookMapper.insert(Book);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Book Book) {
        bookMapper.updateById(Book);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        bookMapper.deleteById(id);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        bookMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Integer id) {
        return Result.success(bookMapper.selectById(id));
    }

    @GetMapping("/{userId}")
    public Result<?> getByUserId(@PathVariable Integer userId) {
        return Result.success(bookMapper.findByUserId(userId));
    }
}
```



## 订单信息

**entity**

```java
package com.example.demo.entity;

@Data
@TableName("t_order")
public class Order {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private BigDecimal totalPrice;

    private BigDecimal payPrice;

    private BigDecimal discount;

    private BigDecimal transportPrice;

    private String orderNo;

    private Integer userId;

    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payTime;

    private Integer state;

}
```



**controller**

```java
package com.example.demo.controller;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Resource
    OrderMapper OrderMapper;

    @Resource
    BookMapper bookMapper;

    @PostMapping
    public Result<?> save(@RequestBody Order Order) {
        OrderMapper.insert(Order);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Order Order) {
        OrderMapper.updateById(Order);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        OrderMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(OrderMapper.selectById(id));
    }

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
        return Result.success(payUrl);
    }
}
```



## 登录与登出功能

**controller**

```java
package com.example.demo.controller;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @PostMapping("/login")
    public Result<?> login(@RequestBody User userParam) {
        User userPwd = userMapper.selectByName(userParam.getUsername());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userParam.getUsername());
        queryWrapper.eq("password", userPwd.getPassword());
        User res = userMapper.selectOne(queryWrapper);

        // 判断密码是否正确
        if (!bCryptPasswordEncoder.matches(userParam.getPassword(), userPwd.getPassword())) {
            return Result.error("-1", "密码错误");
        }
        if (res == null) {
            return Result.error("-1", "用户名或密码错误");
        }
        HashSet<Permission> permissionSet = new HashSet<>();
        // 1. 从user_role表通过用户id查询所有的角色信息
        Integer userId = res.getId();
        List<UserRole> userRoles = roleMapper.getUserRoleByUserId(userId);
        // 设置角色id
        res.setRoles(userRoles.stream().map(UserRole::getRoleId).distinct().collect(Collectors.toList()));
        for (UserRole userRole : userRoles) {
            // 2.根据roleId从role_permission表查询出所有的permissionId
            List<RolePermission> rolePermissions = permissionMapper.getRolePermissionByRoleId(userRole.getRoleId());
            for (RolePermission rolePermission : rolePermissions) {
                Integer permissionId = rolePermission.getPermissionId();
                // 3. 根据permissionId查询permission信息
                Permission permission = permissionMapper.selectById(permissionId);
                permissionSet.add(permission);
            }
        }
        // 对资源根据id进行排序
        LinkedHashSet<Permission> sortedSet = permissionSet.stream().sorted(Comparator.comparing(Permission::getId)).collect(Collectors.toCollection(LinkedHashSet::new));
        //设置当前用户的资源信息
        res.setPermissions(sortedSet);

        // 生成token
        String token = TokenUtils.genToken(res);
        res.setToken(token);
        return Result.success(res);
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (res != null) {
            return Result.error("-1", "用户名重复");
        }
//        if (user.getPassword() == null) {
//            user.setPassword("123456");
//        }
        User userInfo = User.builder()
                .username(user.getUsername())
                .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .nickName("用户" + IdWorker.getId())
                .build();

        userMapper.insert(userInfo);

        UserRole userRole = UserRole.builder()
                .userId(userInfo.getId())
                .roleId(RoleEnum.USER.getRoleId())
                .build();
        userRoleMapper.insert(userRole);
        return Result.success();
    }

}
```

```vue
<template>
  <div class="homepage-hero-module">
    <div class="video-container">
      <div :style="fixStyle" class="filter">

        <div style="width: 400px; margin: 100px auto">
          <div style="font-size: 30px; text-align: center; padding: 30px 0; color: #fff">欢迎登录</div>

          <el-form ref="form" :model="form" size="normal" :rules="rules">
            <el-form-item prop="username">
              <el-input prefix-icon="el-icon-user-solid" v-model="form.username" placeholder="请输入账号"></el-input>
            </el-form-item>

            <el-form-item prop="password">
              <el-input prefix-icon="el-icon-lock" v-model="form.password" show-password placeholder="请输入密码"></el-input>
            </el-form-item>

            <el-form-item>
              <div style="display: flex">
                <el-input prefix-icon="el-icon-key" v-model="form.validCode" style="width: 65%; margin-right:20px" placeholder="请输入验证码" @keyup.enter="login"></el-input>
                <ValidCode @input="createValidCode" />
              </div>
            </el-form-item>

            <el-form-item>
              <el-button style="width: 100%" type="primary" @click="login">登 录</el-button>
            </el-form-item>

            <el-form-item><el-button type="text" @click="$router.push('/register')">前往注册 >> </el-button></el-form-item>
          
          </el-form>
        </div>
        
      </div>
      <video :style="fixStyle" autoplay loop muted class="fillWidth" v-on:canplay="canplay">
        <source src="../assets/sea.mp4" type="video/mp4"/>
      </video>
    </div>
  </div>

</template>

<script>
import request from "@/utils/request";
import ValidCode from "@/components/ValidCode";
import {activeRouter} from "@/utils/permission";

export default {
  name: "Login",
  components: {
    ValidCode,
  },
  data() {
    return {
      vedioCanPlay: false,
      fixStyle: '',
      form: {role: 1},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
      },
      validCode: ''
    }
  },
  mounted() {
    sessionStorage.removeItem("user")

    window.onresize = () => {
      const windowWidth = document.body.clientWidth
      const windowHeight = document.body.clientHeight
      const windowAspectRatio = windowHeight / windowWidth
      let videoWidth
      let videoHeight

      if (windowAspectRatio < 0.5625) {
        videoWidth = windowWidth
        videoHeight = videoWidth * 0.5625
        this.fixStyle = {
          height: windowWidth * 0.5625 + 'px',
          width: windowWidth + 'px',
          'margin-bottom': (windowHeight - videoHeight) / 2 + 'px',
          'margin-left': 'initial'
        }
      } else {
        videoHeight = windowHeight
        videoWidth = videoHeight / 0.5625
        this.fixStyle = {
          height: windowHeight + 'px',
          width: windowHeight / 0.5625 + 'px',
          'margin-left': (windowWidth - videoWidth) / 2 + 'px',
          'margin-bottom': 'initial'
        }
      }
    }
    window.onresize()
  },
  methods: {
    canplay() {
      this.vedioCanPlay = true
    },
    // 接收验证码组件提交的 4位验证码
    createValidCode(data) {
      this.validCode = data
    },
    login () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (!this.form.validCode) {
            this.$message.error("请填写验证码")
            return
          }
          if(this.form.validCode.toLowerCase() !== this.validCode.toLowerCase()) {
            this.$message.error("验证码错误")
            return
          }
          request.post("/user/login", this.form).then(res => {
            if (res.code === '0') {
              this.$message({
                type: "success",
                message: "登录成功"
              })
              sessionStorage.setItem("user", JSON.stringify(res.data))  // 缓存用户信息

              // 登录成功的时候更新当前路由
              activeRouter()
              this.$router.push("/")  //登录成功之后进行页面的跳转，跳转到主页

            } else {
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.homepage-hero-module,
.video-container {
  position: relative;
  height: 100vh;
  overflow: hidden;
}

.video-container .poster img{
  z-index: 0;
  position: absolute;
}

.video-container .filter {
  z-index: 1;
  position: absolute;
  /*background: rgba(0, 0, 0, 0.4);*/
  width: 100%;
}

.fillWidth {
  width: 100%;
}
</style>

```



## 登录验证的实现

新建 ValidCode 组件

![](MyPhoto/6.png)

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

![](MyPhoto/7.png)

导入组件，新建变量 validCode

![](MyPhoto/8.png)

新增方法：createValidCode，用来接收组件传过来的验证码并赋值给 data 中的变量 valideCode

![](MyPhoto/9.png)

在 login 方法里校验

![](MyPhoto/10.png)



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

![](MyPhoto/11.png)

![](MyPhoto/12.png)





## Alipay沙箱的实现

### 1.  密钥工具

地址：https://miniu.alipay.com/keytool/create

![](MyPhoto/1.png)

在线生成应用私钥`appPrivateKey`，然后点击保存并下载。



### 2.   沙箱环境

注册支付宝开发者账户，进入开发者控制台

https://openhome.alipay.com/dev/workspace



进入支付宝沙箱环境

https://open.alipay.com/platform/appDaily.htm



需要配置RSA2(SHA256)密钥

![](MyPhoto/2.png)



### 3.  内网穿透

内网穿透就是把本机的ip和端口暴露到外网，通过指定的url可以访问你本地的服务。

natapp地址：https://natapp.cn

启动命令：natapp.exe -authtoken=你的authtoken

![](MyPhoto/15.png)

此时访问 http://bwd7wq.natappfree.cc 即后端页面

![](MyPhoto/16.png)

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

![](MyPhoto/17.png)



### 7.  前端Vue调用

在书籍的表格里，我加了个 购买的按钮用来测试支付功能。

点击购买按钮，会发生一次网络请求。请求后台的OrderController生成一个订单，并返回调用AliPayController的调用地址：

![](MyPhoto/18.png)

前端拿到这个地址，直接在新窗口打开即可出现支付宝的沙箱支付页面：

![](MyPhoto/20.png)

这里的账户和密码都是模拟的，可以在自己的沙箱账户里找到

地址：https://open.alipay.com/platform/appDaily.htm?tab=info

账户是虚拟的，可以随意充值。





### 8. 遇到的问题

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



#### 405 问题

请求的 Http 类型和你实际返回的 Http 类型不一致。我后台写了个 POST 接口，但是前端使用 GET 请求访问，服务器就会返回 405 的错误。所以要解决这个问题，只需要检查一下请求的方法类型是否和自己定义的方法返回类型一致即可。 

http 接口分成 3 部分

- 接口类型：例如 GET
- 接口路径：例如 /user
- 接口参数：例如 ?name=xxx 或者 /xxx



#### 接口代理问题

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



## JWT权限

加入工具类 TokenUtils 生成 Token，genToken 这个方法主要是根据用户的信息生成 token 的，这个 token 里面存储了 userId，使用用户密码进行加密，默认的过期时间是 1 天。

```java
public static String genToken(User user) {
    return JWT.create().withExpiresAt(DateUtil.offsetDay(new Date(), 1)).withAudience(user.getId().toString())
            .sign(Algorithm.HMAC256(user.getPassword()));
}
```

在 UserController 的 Login 方法中使用

![](MyPhoto/4.png)

加入拦截器，拦截除了注册登录外所有的请求，然后进入自己定义的 AuthInterceptor 类中验证 token 的合法性

![](MyPhoto/5.png)

可以自定义一个 BaseController 来获取请求中的 token，得到用户信息，任何继承 BaseController 的 Controller 都可以通过这个 getUser 的方法获取用户信息。





## 项目部署环境搭建

### Jdk

如果系统⾃带有 OpenJDK ，⾸先查找已经安装的 OpenJDK 包，再将 java 开头的安装包均卸载即可

```shell
rpm -qa | grep java
```

**卸载**

```shell
yum -y remove +openjdk包名
```



**安装**

```shell
cd /usr/local/
mkdir java
cd java
tar -zxvf /usr/local/application/jdk-8u161-linux-x64.tar.gz -C /usr/local/java/
```



**配置JDK环境变量**

编辑 `/etc/profile` ⽂件，在⽂件尾部加⼊配置

```
JAVA_HOME=/usr/local/java
CLASSPATH=$JAVA_HOME/lib/
PATH=$PATH:$JAVA_HOME/bin
export PATH JAVA_HOME CLASSPATH
```

让环境变量⽣效

```
source /etc/profile
```



**检验**

```
java -version
javac
```



方法二：不推荐但是能一步搞定

```
yum search jdk | grep openjdk
```



### MYSQL

```
tar -zxvf /usr/local/application/mysql-5.7.30-linux-glibc2.12-x86_64.tar.gz -C /usr/local/
mv mysql-5.7.30-linux-glibc2.12-x86_64/ mysql/
```

**创建MYSQL⽤户和⽤户组**

```
groupadd mysql
useradd -g mysql mysql
```

修改MYSQL目录的归属用户

```
chown -R mysql:mysql ./
```



在 `/etc` ⽬录下新建 `my.cnf`⽂件，写入以下配置

```
[mysql]
# 设置mysql客户端默认字符集
default-character-set=utf8
socket=/var/lib/mysql/mysql.sock

[mysqld]
skip-name-resolve
#设置3306端⼝
port = 3306
socket=/var/lib/mysql/mysql.sock
# 设置mysql的安装⽬录
basedir=/usr/local/mysql
# 设置mysql数据库的数据的存放⽬录
datadir=/usr/local/mysql/data
# 允许最⼤连接数
max_connections=200
# 服务端使⽤的字符集默认为8⽐特编码的latin1字符集
character-set-server=utf8
# 创建新表时将使⽤的默认存储引擎
default-storage-engine=INNODB
lower_case_table_names=1
max_allowed_packet=16M
```

使⽤如下命令创建 /var/lib/mysql目录，并修改权限

```
mkdir /var/lib/mysql
chmod 777 /var/lib/mysql
```

7 = 4 + 2 + 1    读 写 运行

5 = 4 + 1       读 运行

4 = 4          只读

**正式开始安装**

```
cd /usr/local/mysql
mkdir data
./bin/mysqld --initialize --user=mysql --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data
```

![](../repository/source/environment/img/3.png)

**复制启动脚本到资源目录**

```
cp ./support-files/mysql.server /etc/init.d/mysqld
```



**修改 `/etc/init.d/mysqld` ，修改其 `basedir 和 datadir` 为实际对应⽬录：**

```
basedir=/usr/local/mysql
datadir=/usr/local/mysql/data
```



**设置MYSQL系统服务并开启自启**

```
chmod +x /etc/init.d/mysqld
```

同时将 mysqld 服务加⼊到系统服务

```
chkconfig --add mysqld
```

最后检查 mysqld 服务是否已经⽣效即可

```
chkconfig --list mysqld
```

在2、3、4、5运⾏级别随系统启动而自动启动，以后可以直接使 ⽤ service 命令控制 mysql 的启停。



**启动MYSQLD**

```
service mysqld start
```



**加环境变量**

编辑`~/.bash_profile`文件，在⽂件末尾处追加如下信息

```
export PATH=$PATH:/usr/local/mysql/bin
```

**重载配置文件**

```
source ~/.bash_profile
```



**登录**

```
mysql -u root -p
```



**解决：mysql: error while loading shared libraries: libncurses.so.5: cannot open shared object file: No such file or directory**

```
service mysqld stop
yum install libncurses*
service mysqld start
```



**修改ROOT账户密码**

```
alter user user() identified by "123456";
flush privileges;
```



**设置远程主机登录**

```
use mysql;
update user set user.Host='%' where user.User='root';
flush privileges;
```

连接测试通不过就开3306端口或者关虚拟机防火墙



### Nginx

**预先安装额外的依赖**

```
yum -y install pcre-devel
yum -y install openssl openssl-devel
yum -y install gcc-c++
yum -y install gcc automake autoconf libtool make
```

**编译安装NGINX**

```
tar -zxvf /usr/local/application/nginx-1.21.4.tar.gz -C /usr/local/
mv nginx-1.21.4/ nginx/
cd /usr/local/nginx/
./configure
make && make install
mkdir /usr/local/nginx/logs/
```

**启动Nginx**

```
/usr/local/nginx/sbin/nginx
```

**停止Nginx服务**

```
/usr/local/nginx/sbin/nginx -s stop
```

**重新加载Nginx**

```
/usr/local/nginx/sbin/nginx -s reload
```

**注意其配置⽂件位于：**

```
/usr/local/nginx/conf/nginx.conf
```



### Tomcat

```
tar -zxvf /usr/local/application/apache-tomcat-8.5.55.tar.gz -C /usr/local/
cd /usr/local/tomcat/bin/
./startup.sh
这时候浏览器访问： 主机IP:8080 ，得到画面说明成功启动了
```



**配置快捷操作和开机启动**

```
cd /etc/rc.d/init.d
touch tomcat
chmod +x tomcat
```

编辑 tomcat ⽂件，并在其中加⼊如下内容：

```
#!/bin/bash
#chkconfig:- 20 90
#description:tomcat
#processname:tomcat
TOMCAT_HOME=/usr/local/tomcat/
case $1 in
 start) su root $TOMCAT_HOME/bin/startup.sh;;
 stop) su root $TOMCAT_HOME/bin/shutdown.sh;;
 *) echo "require start|stop" ;;
esac
```

这样后续对于Tomcat的开启和关闭只需要执⾏如下命令即可：

```
service tomcat start
service tomcat stop
```

**加入开机启动：**

```
chkconfig --add tomcat
chkconfig tomcat on
```



Linux上启动Tomcat，结果弹出：-bash: ./startup.sh: Permission denied 的提示。

这是因为用户没有权限，而导致无法执行。用命令chmod 修改一下bin目录下的.sh权限就可以了。

```
chmod u+x *.sh
```

这里的u 这里指文件所有者，+x 添加可执行权限，*.sh表示所有的sh文件。



```shell
#!/bin/bash
#chkconfig:- 20 90
#description:tomcat
#processname:tomcat
TOMCAT_HOME=/usr/local/tomcat/apache-tomcat-9.0.54
case $1 in
 start) su root $TOMCAT_HOME/bin/startup.sh;;
 stop) su root $TOMCAT_HOME/bin/shutdown.sh;;
 *) echo "require start|stop" ;;
esac
```



conf/setting.xml?

```xml
<Context path="/" docBase="/usr/local/tomcat/apache-tomcat-9.0.54/webapps/SSM-Question" reloadable="false">
	</Context>
```



### docker

CentOS 8 中安装 docker 和 Podman 冲突，**这里是CentOS 8的安装过程**

```
1) 查看是否安装 Podman
rpm -q podman
2) 删除Podman
dnf remove podman
```

分别执行如下命令:

```bash
sudo yum install -y yum-utils  device-mapper-persistent-data  lvm2
sudo yum-config-manager  --add-repo   https://download.docker.com/linux/centos/docker-ce.repo
sudo yum install docker-ce docker-ce-cli containerd.io
sudo yum install docker-ce docker-ce-cli
```

启动docker

```sql
sudo systemctl start docker
```



**非CentOS8系统的安装过程**

```
# 下载
yum install -y docker
# 开启服务
systemctl start docker.service
```



**查看安装结果**

```
docker version
```

**设置开机自启**

```
systemctl enable docker.service
```

配置DOCKER镜像下载加速

```
# 在其中加⼊加速镜像源地址即可：
vim /etc/docker/daemon.json

{
 "registry-mirrors": ["http://hub-mirror.c.163.com"]
}
```

**重启docker**

```
systemctl daemon-reload
systemctl restart docker.service
```

查看镜像

```
docker images
```



安装可视化面板

```
docker pull portainer/portainer
docker volume create portainer_data
docker run -d --name portainer -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer
```





## 项目正常部署

### jar

后端打`jar`包，包在target里

```java
执行 mvn package
```



### war

后端打`war`包

`pom`里声明war

```java
<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.5.2</version>
		<relativePath/>
</parent>
	<groupId>com.example</groupId>
	<artifactId>springboot</artifactId>
	<version>0.0.1-SNAPSHOT</version>
    <name>springboot</name>

	<!--声明war-->
	<packaging>war</packaging>
```

`pom`里加如下依赖

```java
<!--      发布时剔除内置tomcat-->
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-tomcat</artifactId>
<scope>provided</scope>
</dependency>
```

启动类同级目录下加新的引导类，为了主类的改造：继承初始类，重写configure方法，指向原来的启动类

```java
package com.example.demo;

public class SpringBootStarterApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }
}
```

```java
执行 mvn package
```





前端打`dist`包，需要有node环境

打生产环境的包

```
yarn build
```

打包生成 dist文件夹，包含网页各种css样式，各种静态文件。将 `dist文件夹`压缩，上传到服务器

![](MyPhoto/3-16561474822651.png)



使用`xftp`上传包到服务器



### nginx启动dist

更改`/usr/local/nginx/conf/nginx.conf`文件

默认配置

```conf
#user  nobody;
worker_processes  1;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;

    server {
        listen       80;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / {
            root   html;
            index  index.html index.htm;
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }


    # another virtual host using mix of IP-, name-, and port-based configuration
    #
    #server {
    #    listen       8000;
    #    listen       somename:8080;
    #    server_name  somename  alias  another.alias;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}


    # HTTPS server
    #
    #server {
    #    listen       443 ssl;
    #    server_name  localhost;

    #    ssl_certificate      cert.pem;
    #    ssl_certificate_key  cert.key;

    #    ssl_session_cache    shared:SSL:1m;
    #    ssl_session_timeout  5m;

    #    ssl_ciphers  HIGH:!aNULL:!MD5;
    #    ssl_prefer_server_ciphers  on;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}

}
```

更改为

```

```

重启nginx服务

```
/usr/local/nginx/sbin/nginx -s reload
```





```
location /192.168.13.10/ {
            proxy_set_header Host $http_host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header REMOTE-HOST $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_pass http://192.168.13.10:8080/;
        }
```





守护进程使后台运行

```java
nohup java -jar springboot-0.0.1-SNAPSHOT.jar &
```





端口查看

```
lsof -i:80
```



## 反向代理

```xml
location / {
            root   /usr/local/application/demo/dist/;
            index  index.html index.htm;
			try_files $uri $uri/ /index.html;
			error_page 405 =200 http://$host$request_uri;
        }
```





## 负载均衡

![](MyPhoto/13.png)



```xml
http {
    include       mime.types;
    default_type  application/octet-stream;

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;
	
	upstream pod{
		server 192.168.13.10:8080 weight=5
		server 192.168.13.11:8080 weight=5

    server {
        listen       80;
        server_name  localhost;
        #charset koi8-r;
        #access_log  logs/host.access.log  main;

        location / {
            root   /usr/local/application/demo/dist/;
            index  index.html index.htm;
			try_files $uri $uri/ /index.html;
			error_page 405 =200 http://$host$request_uri;
        }
		
		location /192.168.13.10/ {
            proxy_set_header Host $http_host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header REMOTE-HOST $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_pass http://pod/;
        }
```



## docker部署

**后端部署**

拉取java8

```
docker pull java:8
```

在jar包和dist同级文件夹中

```
vi Dockerfile
```

```
FROM java:8
VOLUME /tmp
ADD springboot-0.0.1-SNAPSHOT.jar springboot-0.0.1-SNAPSHOT.jar
EXPOSE 9999
ENTRYPOINT ["java","-jar","springboot-0.0.1-SNAPSHOT.jar"]
```

打包为images

```
docker build -t springboot .
docker images
```

运行容器( -d后台运行，-p映射端口 )

```
docker run -d -p 9999:9090 --name springboot-9090 springboot
```

查看

```
docker ps
```



**前端部署**

将dist解压

在jar包和dist同级文件夹中

```
vi Dockerfile
```

```
FROM nginx:latest
COPY ./dist /usr/share/nginx/html/
EXPOSE 80
```

打包为images

```
docker build -t vue .
```

运行容器( -d后台运行，-p映射端口 )

```
docker run -d -p 9998:9997 --name vue-9997 vue
```

查看

```
docker ps
```



## 普罗米修斯

**1.下载软件**

https://prometheus.io/download/

**2.安装**

第一步:上传到服务器

第二步:解压并安装

解压

`tar xf prometheus-2.32.1.linux-amd64.tar.gz -C /usr/local/`

安装

`mv /usr/local/prometheus-2.32.1.linux-amd64/ /usr/local/prometheus`

![](MyPhoto/25.png)



第三步:启动普罗米修斯软件

`cd /usr/local/proetheus`

`./prometheus --config.file="/usr/local/prometheus/prometheus.yml"&`

(&连接符代表后台运行,不占用终端窗口)

![](MyPhoto/24.png)

确认端口

`lsof -i:9090`

![](MyPhoto/23.png)



**3.普罗米修斯软件界面**

![](MyPhoto/26.png)













