Feature: サンプル機能
  Scenario: サンプルコントローラーにアクセスできる
    When "sample"にアクセスする
    Then 本を"top"する

  @developing
  Scenario: ログインする
    Given "sample"にアクセスする
    When ログインボタンを押す






