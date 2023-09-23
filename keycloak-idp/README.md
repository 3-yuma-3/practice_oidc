# keycloak-idp

- [KEYCLOAK](https://www.keycloak.org/)
- [keycloak / keycloak (github)](https://github.com/keycloak/keycloak)
- [RED HAT Quay.io / keycloak / keycloak / Repository Tags](https://quay.io/repository/keycloak/keycloak?tab=tags&tag=latest)
- [Guides / Server / Running Keycloak in a container / Running Keycloak in a container](https://www.keycloak.org/server/containers)
- [Guides / Server / Configuring the database / Configuring the database](https://www.keycloak.org/server/db#_relevant_options)
- [Guides / Server Administration Guide / SSO protocols / OpenID Connect](https://www.keycloak.org/docs/latest/server_admin/index.html#con-oidc_server_administration_guide)
- [Guides / Authorization Services Guide / Managing resource servers](https://www.keycloak.org/docs/latest/authorization_services/index.html#_resource_server_overview)
- [Guides / Securing Apps Guide / Using OpenId Connect to secure applications and services](https://www.keycloak.org/docs/latest/securing_apps/#_oidc)
- [【入門】Keycloak + Docker で OIDC の SSO を設定](https://hogetech.info/oss/keycloak)

1. `$ docker compose build`
2. `$ docker compose up`
3. `http://localhost:8880` or `https://localhost:8443`
    - `Administration Console`
    - `Username or email`, `Password`: [compose.yml](./compose.yml) を参照

## memo

- realmとuserの作成
    1. realm: rails-rp, springboot-rp
    2. user: rails-rp_user1, password: password
    3. user: springboot-rp_user1, password: password
    4. `http://localhost:8880/realms/rails-rp/account/#/`
    5. `http://localhost:8880/realms/springboot-rp/account/#/`
- rails-rpのrealmでoidc clientを作成
  - `curl http://localhost:8880/realms/rails-rp/.well-known/openid-configuration | jq .`
- springboot-rpのrealmでoidc clientを作成
  - redirectURI: `http://localhost:8080/login/oauth2/code/keycloak`
