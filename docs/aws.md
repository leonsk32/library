## CloudFormationでつくれるリソース


## 手作業で実施すること
* RDSを作成
* RDSのセキュリティグループ作成
* EC2にJDKインストール
* EC2にPostgreインストール
* EC2に環境変数を設定
* PostgreにDBを作成
* Jarをコピー
* Jarを起動
* Ec2のセキュリティグループにHttpを追加

* 非同期実行
```aidl
nohup java -jar hogehoge.jar &
```

* postgresのインストール
```aidl
sudo yum install -y  postgresql
```

* jdk11のインストール
```aidl
sudo yum install java-11-amazon-corretto

```

```aidl
export DATABASE_PASS=librarypass
export DATABASE_URL_SPRINGBOOT=jdbc:postgresql://xxxx:5432/library}
export DATABASE_USER=libraryuser

```