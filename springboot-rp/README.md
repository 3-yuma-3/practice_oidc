# README

- **あくまでサンプル実装。アプリを公開する場合は、JWKs, id_tokenの検証を行い、PKCEなどを用いること**
  - TODO: spring securityがどこまでやってくれてるのか(nonce, PKCEなど)、ドキュメント読み込む
    - [Spring Security / サーブレットアプリケーション / OAuth2](https://spring.pleiades.io/spring-security/reference/servlet/oauth2/)
      - `OAhtu2 ログイン`
        - `コア構成`, `高度な構成`
      - `OAuth2 クライアント`
        - `コアインターフェースとクラス`, `OAuth2 認証付与`, `OAuth2 クライアント認証`, `OAuth2 認定クライアント`

- spring initializr
  - `https://start.spring.io/#!type=gradle-project&language=java&platformVersion=3.1.4&packaging=jar&jvmVersion=17&groupId=com.example&artifactId=springboot-rp&name=springboot-rp&description=Demo%20project%20for%20Spring%20Boot&packageName=com.example.springboot-rp&dependencies=devtools,lombok,web,thymeleaf,oauth2-client`

- 参考
  - [Keycloak×Spring SecurityでOIDC入門](https://qiita.com/wtomioka-gxp/items/54c7c8e37b90a73cb52e)
  - [A Quick Guide to Using Keycloak With Spring Boot](https://www.baeldung.com/spring-boot-keycloak)
