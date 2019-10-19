**BookStock** 
* 購入した本とその量を表す
* Properties
    * isbn...
    * num  
* type:Entity
    * id...isbn10+isbn13にしてみました
    
**Book**
* 本を表す
* Properties
    * isbn
    * title
    
**LendingRecord**
* 本の貸し出しを表す
* Properties
    * isbn
    * user
    * date
    
**User**
* ユーザを表す
* Properties
    * userId 
  