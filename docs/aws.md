## やり残し
* クラウドフォーメーションでの自動構築時にユーザデータで以下のインストールを自動でやる
    * jdk11　のインストール
    * postgresのインストール
    * DB用環境変数設定
    * コードデプロイのエージェントをインストール

## 手作業で実施すること
* EC2にJDKインストール
* EC2にPostgreクライアントをインストール
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

* jarの中身を見る 
```aidl
jar tf hogehoge.jar
```

* jarとその他をZIPにする 
```aidl
jar -cMf hoge.zip
```


* jdk11のインストール
```aidl
sudo yum install java-11-amazon-corretto
```

* 手動で作成したリソースからクラウドフォーメーションのtemplateを作成する方法
```aidl
https://docs.aws.amazon.com/ja_jp/AWSCloudFormation/latest/UserGuide/cfn-using-cloudformer.html
```

```aidl
export DATABASE_PASS=xxxx
export DATABASE_URL_SPRINGBOOT=jdbc:postgresql://xxxx:5432/library}
export DATABASE_USER=libraryuser

```