# app
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

### Screenshots
#### 一覧
![Screenshot_1624114234](https://user-images.githubusercontent.com/750091/122646311-8050b700-d159-11eb-8f93-4190c96775a2.png)

### ソートおよびフィルタ
![Screenshot_1624114263](https://user-images.githubusercontent.com/750091/122646357-aece9200-d159-11eb-9489-e19be036edb8.png)

#### 新規作成
![Screenshot_1624114292](https://user-images.githubusercontent.com/750091/122646334-96f70e00-d159-11eb-9451-327e9a21a3e5.png)

#### 編集
![Screenshot_1624114280](https://user-images.githubusercontent.com/750091/122646346-a1190c80-d159-11eb-8dfe-f0ea73e78469.png)

#### ロングタップで削除
![Screenshot_1624114254](https://user-images.githubusercontent.com/750091/122646366-b9892700-d159-11eb-9047-c3013a8a22fe.png)


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
