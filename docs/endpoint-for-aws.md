## 貸し出し
https://0i4gd1lpy7.execute-api.ap-northeast-1.amazonaws.com/test
* 貸し出し状況の確認
```
  GET
  http://localhost:8080/api/v1/lendingRecords

  RESPONSE
  {
    "lendingRecords": [
        {
            "isbn": "9781111111111",
            "userId": "1234567",
            "namae": "ass",
            "simei": "aaa"
        }
    ]
}
  ```
* 借りる
```
POST
http://localhost:8080/api/v1/lendingRecords
```

* 返す
  
  ```
  DELETE
  http://localhost:8080/api/v1/lendingRecords/9781234567891/1234568
  ```

## ユーザ
* 全取得
```
GET
http://localhost:8080/api/v1/users
```
* 登録
    * フロントアプリケーションにのみ公開予定（ローカルからはアクセスできない）
```sh
# URL
http://localhost:8080/api/v1/users/1234567
# body idだけ必須
{
	"userId": "1234567",
	"email": "aa@bb",
	"simei": "aaa",
	"namae": "ass"
}
```
* 削除
  ```
  DELETE
  http://localhost:8080/api/v1/users/1234567
  ```

## 本
* 全取得
```
GET
http://localhost:8080/api/v1/books
```
* 登録
```
PUT
http://localhost:8080/api/v1/books/9781111111111
```
* 廃棄
```
DELTE
http://localhost:8080/api/v1/books/9781234567890
```

