# codFrame-admin
 * 北京天蓝科技有限公司

 * 功能介绍
   
   | 功能列表 | 说明      |         |
   | :-----:  | :-------: | :-----: |
   | 菜单管理 | 管理菜单  |         |
   | 日志管理 | 日志管理  |         |
   | 权限管理 | 权限管理  |         |
   | 附件管理 | 上传附件  |         |
   | 用户管理 | 用户管理  |         |
   | 角色管理 |           |         |
   | 系统设置 |           |         |
   | API管理  |           |         |
   | 接口验证 |           |         |
   | 缓存管理 |           |         |
   | 字典管理 |           |         |
   | 部门管理 |           |         |
   | 公司管理 |           |         |
   
 * 支持列表
 
   | 支持列表        | 说明                                             |       |
   | :----:          | :----:                                           | :---: |
   | cors            | 支持跨域                                         |       |
   | apollo          | 支持apollo配置中心                               |       |
   | 阿里大鱼        | 阿里短信&邮件提醒                                |       |
   | 阿里云oss       | 支持上传oss                                      |       |
   | 阿里云rds       | 支持阿里云数据库                                 |       |
   | 七牛云          | 支持上传七牛云                                   |       |
   | 又拍云          | 支持上传又拍云                                   |       |
   | 阿里云日志服务  | 支持日志打到阿里云日志服务<br>（互联网项目推荐） |       |
   | Jenkins持续集成 | 支持Jenkins                                      |       |
     
 * 支持数据库连接池

    | 名称     | 介绍         |       |
    | :----:   | :----:       | :---: |
    | druid    | 阿里巴巴开源 |       |
    | dbcp     | apache       |       |
    | c3p0     | 开源         |       |
    | HikariCP | 开源（推荐） |       |
   
   
 * 框架架构
 
   | 模块        | 介绍           |        |
   | :----:      | :----:         | :----: |
   | cod-action  | 后台模块       |        |
   | cod-api     | 接口模块       |        |
   | cod-cache   | 缓存模块       |        |
   | cod-common  | 公共模块       |        |
   | cod-core    | 核心模块       |        |
   | cod-dao     | 数据库操作模块 |        |
   | cod-facade  | 外部访问模块   |        |
   | cod-filter  | 过滤器模块     |        |
   | cod-log     | 日志模块       |        |
   | cod-message | 消息模块       |        |
   | cod-model   | model模块      |        |
   | cod-service | 内部访问模块   |        |
   | cod-util    | 工具模块       |        |
   | cod-wechat  | 微信模块       |        |
   
 * 使用方式
   
   不设置端口,默认端口: 9999
   
 * 调试
 
 * 待续


* *TODO*

  1. [x] 彻底删除附件默认时间设置
  2. [DONE] 是否打印sql设置
  3. [x] ehcahed memcached redis缓存
  4. [x] 自定义jetty Filter/ jettyFilter集成数据库配置
  5. [DONE] 封装日志 (默认支持clog)
  6. [x] 集成阿里云日志服务
  7. [x] 增加跨域清单功能
  8. [] finder不支持union
  

* 框架缓存文件夹

  .cod-temp
  
  默认隐藏
  
    codCache（缓存）
    
      - codEhcache
      
      - codFile
      
      - codJson
      
    codLogs
    
      yyyy
        MM
          dd
          
            debug.yyyy-MM-dd.log
            debug.yyyy-MM-dd-001.log
            debug.yyyy-MM-dd-002.log
            info.yyyy-MM-dd.log
            warn.yyyy-MM-dd.log
            error.yyyy-MM-dd.log
    codData
    
    codFile
    
      codVideo
      codAudio
      codAttachment
      codOther
      ...
    codConfig
    
    codPackage
    
    codBackup
    
      codData(数据备份)
      codApplication(应用备份)
      
    ...

