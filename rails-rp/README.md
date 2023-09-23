# README

- **あくまでサンプル実装。アプリを公開する場合は、JWKs, id_tokenの検証を行い、PKCEなどを用いること**

1. devcontainerで `rails-rp` ディレクトリを開く
    - rails用のmysqlは勝手に立ち上がる
2. devcontainer内で `# bin/rails s -b 0.0.0.0`
3. `http://localhost:3000`

## CSRF

- keycloakへのSSO時、networkタブの下記部分を確認する
  - `http://localhost:8880/realms/rails-rp/login-actions/authenticate?`
    - `Location`
- 上記部分にrails-rpへのcallback時に渡される認可コード(code)が含まれる
- burp suiteなどでinterceptし、上記redirectURLをそのままメールなどで送信し、他者がログインしてくれれば、他者の情報が取得できてしまう
- `CallbackControllerverify_id_token!` でコメントアウトしている `nonce` の検証をすれば防げる
