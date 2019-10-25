## [ユビキタス言語](./docs/UbiquitousLanguage.md)

## やり残し一覧
* swaggerでAPI生成してみたい
* ORMapperつかってない、JPAてきなのつかってみたい
* ISBN13桁を無視している
* 本をイミュータブルにして、イベントソーシング的なことをやってみたい
* バリデーションをしていない
* 本の購入管理とかやりたい

## sonarqube
https://sonarcloud.io/dashboard?id=AgileDevelopmentClub_library

## CI
github actionsを使用して、単体テストを行った後にsonarqubeをしています。

## エンドポイントについて
今回、参考にしたのはTrello API。  
借りる・返す、は名詞ではないので、RESTのエンドポイントとしては不適格。  
なので、actionsという"行動"という名詞に集約させて、パラメータで判断することが良いと判断しています。　　
https://developers.trello.com/reference/#enterprisesidadminsidmember-1


## HEROKUエンドポイント


```aidl
https://enigmatic-depths-14464.herokuapp.com/api/v1/books
```

### 本の一覧が欲しい時
```
/books 
Getメソッド
```

### 本を登録・削除するとき
```
/books/{isbn}
Put, Deleteメソッド
```

### 本に対しての行動(借りる・返す)
```
/books/{isbn}/{actions}
Postメソッド
jsonのtypeで判断するようにする
{
  "type":"borrow"
}
等々
```

### １冊借りるときのURL[旧エンドポイント]
```
http://localhost:8080/api/v1/books/{isbn} <= 10桁だけ
（例）http://localhost:8080/api/v1/books/1111111111　
```

## Run App

```shell script
cd library-backend-service
./gradlew bootRun
```

## Scripts

### Run Unit Tests

``` shell script
./scripts/run-unit-tests.sh
```

### Run Tests & Push

```shell script
./scripts/ship-it.sh
```

## PostgreSQL Database Setup

``` shell script
cd tools/
docker-compose up -d
```

- Access to pgAdmin
    - http://localhost:8000
    - pgadmin / pgadmin
    - Connection
        - ![](./tools/connection-info.png)
        
## herokuへのデプロイ
* github actionsとの連携はまだ、ローカルからherokuへのデプロイ手順を書く
```

heroku login

heroku create

heroku addon:みたいな感じでDBを作る
https://devcenter.heroku.com/articles/heroku-postgresql

作ったPostgresqlのクレデンシャル情報を環境変数に突っ込む
作ったPostgresqlのセッティングをapplication.ymlに書く
spring:
  datasource:
    url: ${XXX:jdbc:postgresql://haxitkllyitchb:96a9b73d86e8ae1397e353195c07e82089244fa6b1810ada85f5ab45fde5196a@ec2-54-163-255-1.compute-1.amazonaws.com:5432/d9ilikcb97gf6o}
    username: ${YYY:haxitkllyitchb}
    password:${ZZZ:xxxxx}
    driver-class-name: org.postgresql.Driver
    
herokuのセッティングに書いてある

Procfileに以下のように書く
web: java -jar -Dserver.port=$PORT demo/build/libs/demo-0.0.1-SNAPSHOT.jar --server.port=$PORT --spring.profiles.active=herok
Procfileはプロジェクトルートに置く

herokuへのデプロイをGradleで実施するにはbuild.gradleに以下を定義

```aidl
/**
 * heorkuへのデプロイ用
 */
task stage(dependsOn: ['build', 'clean'])
build.mustRunAfter clean

gradle.taskGraph.whenReady {
    taskGraph ->
        if (taskGraph.hasTask(stage)) {
            test.enabled = false
        }
}

```

herokuへのデプロイするときに、Java11をつかって上のタスクを実行するためにsystem.propertiesを作成

```aidl
java.runtime.version=11
```

git push heroku master

logは以下で見る
heroku logs --tails

heroku pg:psqlでログイン

```