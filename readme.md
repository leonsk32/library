# このリポジトリについて

## 1. DDD関連
* [ユビキタス言語](./docs/UbiquitousLanguage.md)

---
## 2. Infra Architecture

#### 2.1 共通
#### 2.2 Frontd
#### 2.3 [Back](./docs/aws.md)

---

## 3. APP Architecture

#### 3.1 共通
* [swaggerの定義公開URL](https://stoplight.io/p/docs-preview/gh/agiledevelopmentclub/library)
#### 3.2 Frontd
* [画面イメージ](https://adc-library.firebaseapp.com/#/)
#### 3.3 Back
* [エンドポイント](./docs/endpoint-for-aws.md)

---

## 4. CI/CD
#### 4.1 共通 
* [sonarqube](https://sonarcloud.io/dashboard?id=AgileDevelopmentClub_library)
#### 4.2 Frontd

#### 4.3Back
* インフラ側はawsの[cloudformation](./infra/createForCloudFormer.yaml)で自動デプロイ可能
* バックエンドアプリケーションは以下の手順で自動デプロイ構築予定
    1. githubにpush
    1. github actionsがテスト＋ビルド＋s3にアップロード
    1. aws piplineが発火
    1. aws code deploy　でec2にデプロイ
    
* DBバックアップ
    * RDAの自動バックアップ（７日）
* アーティファクトのバックアップ
    * S3をバージョンニングしている

---

## 5. ローカル環境 


## Run App

```shell script
cd library-backend-service
./gradlew bootRun
```


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
        
