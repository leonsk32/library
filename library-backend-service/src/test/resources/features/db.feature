Feature: テストデータ
  @developing
  Scenario:
    Given DBの設定をする
    And 初期化
    And ユーザを作る
      | id     | mail  | familyName | firstname |
      | 111426 | aa@bb | 摂津の        | きり丸       |
    And 本を作る
      | isbn          | amount |
      | 9784567890978 | 10     |
    And userId が isbn の本を借りる
      | userId | isbn          |
      | 111426 | 9784567890978 |
    Then コネクションを閉じる
