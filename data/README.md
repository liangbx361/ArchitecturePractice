# Model层
Model层负责统一管理应用数据，包括网络数据、配置数据都通过Model层进行获取和存储。

## 特性
* 网络数据
* 应用配置数据
* Http缓存处理

## 可测试性研究方向
* API验证 
直接通过发起真实的网络请求验证API是否正确，验证Model是否正确
* 单元测试
使用Mock数据验证Model层的处理逻辑是否正确、边界值处理是否正常

## 数据库选型
* GreenDao 效率高
* Dbflow 支持多数据库，操作方便

## 示例
* [知乎API](https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90)

## 参考文献
* [Retrofit2.0 更新说明](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0915/3460.html)
* [Android应用架构](http://www.jianshu.com/p/8ca27934c6e6)
* [浅析MVP中model层设计](http://www.jianshu.com/p/d299153ff853)
* [Android Retrofit 2.0 使用](http://wuxiaolong.me/2016/06/18/retrofits/)
* [Retrofit 使用教程大全](https://futurestud.io/tutorials/tag/retrofit)
* [OkHttp3缓存策略](http://www.jianshu.com/p/9cebbbd0eeab)
* [HTTP 状态码](http://blog.csdn.net/zll01/article/details/5018413)