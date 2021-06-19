# AndroidRoomStudy01

Android Roomと Live Dataの連携の勉強

## 仕様

* アイテム一覧画面
  * メニューからアイテムの並びを変更できる (作成日順、更新日順、それぞれ昇順降順)
  * お気に入りアイテムだけを表示するよう切り替えが可能
  * アイテムを長押しすると削除できる
  * 右のハートマークをタップするとお気に入り状態をトグルできる
  * リストをタップするとアイテムの編集
  * FABを押すとアイテムの新規作成
* アイテム新規作成画面
  * テキスト入力して作成ボタンで新規作成を実行。完了したら前の画面に戻る
* アイテム編集画面
  * テキスト入力して更新ボタンで更新を実行。完了したら前の画面に戻る



## コードを読む順 (実装する順)

* Topic (このクラスから Topicテーブルが自動生成される)
* TopicDAO (SQLは基本的に自動生成、クエリを自分で書いて細かな操作も可能)
* DateConverter (Dateクラスを文字列に変換する)
* AppDatabase (DAOを提供する)
* TopicRepository (なくてもよいが、 Roomと Realmを切り替えたいといったときのために daoは直接使わないほうがいい)
* MyApplication (AppDatabaseオブジェクトはアプリケーションで1個だけ持つ)
* Fragmentと対応する ViewModel

### データベース

Topicオブジェクトによってこのようなテーブルが作られる。

| id | topic | fav | createdAt | updatedAt |
|----|-------|-----|-----------|-----------|
| 1 | SAMPLE | false | "2021-06-19 20:00" | "2021-06-19 20:00" |
