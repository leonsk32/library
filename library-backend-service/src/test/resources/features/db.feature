Feature: テストデータ

  Scenario:
    Given DBの設定をする
    And 初期化
    When id "111426" mail "aa@bb" 氏　"摂津の" 名 "きり丸" のユーザを作る
    When isbn "9784567890978" amount 10 の本を作る
    When "111426" が "9784567890978"　の本を借りる
    Then コネクションを閉じる