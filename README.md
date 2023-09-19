# Reggie瑞吉外卖
> 如果对你有帮助请帮我点个Star⭐
### 实现功能

- 用户端
  - 短信登陆：后台随机生成验证码，通过腾讯云服务发送至手机
  - 商品加购：购物车增删商品，一键清空
  - 商品下单：提交下单，历史订单查询
  - 地址绑定：记录多个用户地址，支持默认地址选择
  - 再来一单：订单完成后支持再来一单选项，一键下单
- 后台管理
  - 密码登录
  - 员工管理：管理员工信息（性别，姓名，身份证等）
  - 菜品管理：菜品CRUD，菜品拥有口味选择等属性
  - 套餐管理：菜品组合套餐，管理套餐售卖状态
  - 订单管理：派送订单
- 其他
  - 登录过滤器：检测登录状态，用户未登录需先登录
  - 公共字段填充：创建时间等信息
  - 文件上传下载：随机生成文件名，上传至服务端
  - Mysql主从复制：主库增删改 从库查询
  - Redis：使用Redis缓存用户端查询菜品与套餐缓存，缓存短信验证码

### 运行说明

- 修改`utils/SMSUtils.java`工具类中关于腾讯云服务的相关配置
> 使用前请先注册腾讯云并创建短信 签名与模板
- 修改`application.yml`中关于Mysql与Redis配置
- 前后端分离使用docker部署在云服务器
- 使用nginx放置静态资源与实现反向代理
  - 后台系统：~~<http:///backend/index.html> (账户：admin 密码：123456)~~
  - 用户端：~~<http:///front/index.html>~~
  
### 后续待改进

- 再来一单不符合现实逻辑，应改为重新加回购物车
- 用户登录增添QQ/Vx登录功能
- 支付功能调用支付宝
---
服务器被攻击重装系统了  懒得重新部署了  
2023.9.19
