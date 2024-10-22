# AI
## 説明します。
- Javaは基本的なフレームワークとして使用します。
- AIの部分にはPython（FLASK）を使用します（Rest API）
- 現在、AIの部分はまだ決定していません。

## application files
```properties
# application-prod.properties
# application-dev.properties 
#　データベース関連の構成
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.datasource.url=jdbc:mysql://rds.example.com:3306/dbname
spring.datasource.url=jdbc:mysql://rds.example.com:3306/dbname
spring.datasource.username=username
#spring.datasource.url=jdbc:mysql://localhost/project
#spring.datasource.username=project
spring.datasource.password=password
spring.datasource.max-idle=10
spring.datasource.max-wait=10000
spring.datasource.min-idle=5
spring.datasource.initial-size=5

#　session　レイフサイクル　( live cycle)
server.servlet.session.timeout=30m
# Redis　データベースインデックス (database index)（デフォルト[default]は：0）
spring.redis.database=0
# Redis サーバーアドレス　(server address)
spring.redis.host=127.0.0.1
# Redis サーバーポート　(server port)
spring.redis.port=6379
# Redis サーバー接続パスワード： (デフォルトはなし)
spring.redis.password=
# 接続プール内の接続数の最大数は200です。（制限がないことを示すには負の値を使用します）
spring.redis.pool.max-active=200
# 接続プールの最大ブロック(block)待機時間。（制限がないことを示すには負の値を使用します）
spring.redis.pool.max-wait=-1
# 接続プール内の最大アイドル接続数
spring.redis.pool.max-idle=10
# 接続プール内の最小アイドル接続数
spring.redis.pool.min-idle=0
# 接続タイムアウト時間（ミリ秒）
spring.redis.timeout=1000

# システム構成パラメータ (parameter)
SYSTEM_KEY=ABC1234567890
SYSTEM_VERSION=1.0
SYSTEM_SECRET=TEST_CODE

# 構築されたシステムサービス
GLOBAL_SQL_ACTION="global.sql.action"

# データベースプルのプレフィックス（prefix）を生成する
# アドレス，ファイルアドレス
doctrine.prefix.dir=entity
doctrine.prefix.alias=product_api

fsm.server=v1
debug=true
product.api.alias=dev_online

```

## project struct 
```tree
.
├── out
│   └── artifacts
│       └── JavaApplication_war_exploded
│           ├── META-INF
│           └── WEB-INF
│               ├── classes
│               │   └── com
│               │       └── application
│               │           └── javaapplication
│               │               ├── conttroller
│               │               └── entity
│               └── lib
├── sqlSaver
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   └── application
│   │   │   │       └── javaapplication
│   │   │   │           ├── Commands
│   │   │   │           ├── Utils
│   │   │   │           ├── annotationCustomer
│   │   │   │           ├── conttroller
│   │   │   │           │   ├── WmsReceiveTaskController
│   │   │   │           │   └── operations
│   │   │   │           ├── entity
│   │   │   │           │   ├── doctrine
│   │   │   │           │   ├── enums
│   │   │   │           │   └── tools
│   │   │   │           ├── enums
│   │   │   │           ├── exec
│   │   │   │           │   ├── catalog
│   │   │   │           │   └── codes
│   │   │   │           ├── genernal
│   │   │   │           │   ├── controller
│   │   │   │           │   └── services
│   │   │   │           ├── login
│   │   │   │           │   ├── controller
│   │   │   │           │   ├── enums
│   │   │   │           │   └── utils
│   │   │   │           ├── operation
│   │   │   │           │   ├── Warehouse
│   │   │   │           │   ├── Wms
│   │   │   │           │   └── verify
│   │   │   │           ├── redis
│   │   │   │           ├── request
│   │   │   │           └── tools
│   │   │   │               ├── Service
│   │   │   │               ├── beans
│   │   │   │               ├── checkauth
│   │   │   │               ├── curlrequest
│   │   │   │               ├── dosql
│   │   │   │               ├── identity
│   │   │   │               ├── model
│   │   │   │               ├── pages
│   │   │   │               ├── result
│   │   │   │               └── verify
│   │   │   └── target
│   │   │       └── generated-sources
│   │   │           └── annotations
│   │   └── resources
│   │       ├── static
│   │       ├── templates
│   │       └── views
│   └── test
│       └── java
│           └── com
│               └── application
│                   └── javaapplication
└── target
    ├── classes
    │   ├── com
    │   │   └── application
    │   │       └── javaapplication
    │   │           ├── Utils
    │   │           ├── annotationCustomer
    │   │           ├── conttroller
    │   │           │   ├── WmsReceiveTaskController
    │   │           │   └── operations
    │   │           ├── entity
    │   │           │   ├── doctrine
    │   │           │   ├── enums
    │   │           │   └── tools
    │   │           ├── enums
    │   │           ├── exec
    │   │           │   └── catalog
    │   │           ├── genernal
    │   │           │   └── controller
    │   │           ├── login
    │   │           │   ├── controller
    │   │           │   ├── enums
    │   │           │   └── utils
    │   │           ├── operation
    │   │           │   ├── Warehouse
    │   │           │   ├── Wms
    │   │           │   └── verify
    │   │           ├── redis
    │   │           ├── request
    │   │           └── tools
    │   │               ├── beans
    │   │               ├── checkauth
    │   │               ├── curlrequest
    │   │               ├── dosql
    │   │               ├── identity
    │   │               ├── model
    │   │               ├── pages
    │   │               ├── result
    │   │               └── verify
    │   └── views
    ├── generated-sources
    │   └── annotations
    ├── generated-test-sources
    │   └── test-annotations
    ├── maven-archiver
    ├── maven-status
    │   └── maven-compiler-plugin
    │       ├── compile
    │       │   └── default-compile
    │       └── testCompile
    │           └── default-testCompile
    ├── surefire-reports
    └── test-classes
        └── com
            └── application
                └── javaapplication

```