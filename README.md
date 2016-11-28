# Android 架构实践

## 模块结构
### Model层
负责数据的管理，包含如下特性：
* 获取数据
  使用二级或者三级缓存机制
* 缓存数据
  采用文件、SharedPreferences、数据库存储数据
* 数据更新通知
  使用Loader或者其他机制在数据更新的时候回调通知

### View层
负责Android UI的显示，提供视图接口

### Presenter层
负责Android页面的逻辑控制，对接Model和View层

## 使用到第三方库
| 名称       | 说明    | 版本    | 地址                                       |
| -------- | ----- | ----- | ---------------------------------------- |
| RxJava   |       |       |                                          |
| Retrofit |       |       |                                          |
| OkHttp   |       |       |                                          |
| Dbflow   |       |       | https://github.com/Raizlabs/DBFlow       |
| RxCache  |       |       | https://github.com/VictorAlbertos/RxCache |
| EventBus | 事件总线  | 3.0.0 | https://github.com/greenrobot/EventBus   |
| Glide    | 图片加载库 | 3.7.0 | https://github.com/bumptech/glide        |
|          |       |       |                                          |
|          |       |       |                                          |

## 参考文献
RxCache使用方法
http://srtianxia.github.io/2016/08/22/RxCache%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90/