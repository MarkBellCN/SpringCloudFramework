# 异常状态码规则
异常类型主要分两种：系统内部异常和 API 接口（业务请求）异常，
对应的异常类为： `ApiException` 和 `InternalException`
本系统的异常码，统一由 `ErrorType` 子类进行区分，code 定义规则如下示例说明。

示例状态码为 ： `00000001`
    
    前四位业务类型  |   状态错误码
    
    0000          |      0001

对应的类为： 

    SystemErrorType         ：   系统错误状态码
    AuthErrorType           ：   授权错误状态码
    SmServerErrorType       ：   系统设计错误错误状态码  

业务类型定义为：

    0000    |   系统通用
    0001    |   网关服务
    0002    |   授权中心
    0003    |   系统设置服务 

```
├─app-server                        |   应用服务子系统程序
│  └─sm-server                      |   系统设置服务
├─auth                              |   授权
│  ├─auth-api                       |   授权服务暴露接口
│  └─auth-server                    |   授权服务
├─common                            |   通用类库
│  ├─core                           |   核心类库，定义错误异常、返回结果
│  └─web                            |   WEB相关的类库，定义基本控制器、通用全局异常处理、查询参数
├─data                              |   数据层服务
│  ├─rdb-api                        |   关系库服务暴露接口
│  └─rdb-server                     |   关系库服务实现
├─doc                               |   数据库设计、SQL脚本等文档
├─gateway                           |   网关服务
```