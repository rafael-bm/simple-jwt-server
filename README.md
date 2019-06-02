# Simple JWT Server
Is a friendly spring boot framework that enables simple oauth2 with JWT tokens

## Basic Usage

Given an empty spring boot project. Create a configuration class and annotate as follow:

```java
@Configuration
@EnableSimpleJwtServer
public class SimpleJwtServerConfiguration {
    
}
```

On application.yml file add:

```yaml

spring:
  jwtserver:
    clients:
      - clientName: acme
        clientSecret: $2a$10$BGDFk.RbwCTto/VCrHZ5I.I34RlZlFmuHMWn2kK/9iUwBulvuIh1y
        resourceIds: acme_inc
        accessTokenValiditySeconds: 60
        refreshTokenValiditySeconds: 120
        secretRequired: true
        refreshTokenEnabled: true
        tokenPrivateClaimVisible: true
        tokenPublicClaimVisible: true
        invalidateOnReauthorization: true
        tokenSignerMethod: rsa
        tokenSignerPublicKey: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl+t/98EZ66IEfQbWAv+9Je2lG/RcxssGnHD6eXSjRcbGk4gxXbrUMNy9rUH1r/3W/QvDwq6E8+VZmDPYmUGdL6zlieWCUK/kxe0iJycwdjC3YLzHWPtRmSE24rCSm9JimBF+K0Ie5clRcpWJviQsbdBabRO2Q9fa9fm4BqHVb1gM+P//SvFTW0Y0fV2a+TxheF7ipdApY8qBJd1rNYJdQke8yu0GQv2cVI89xrbhQE8RqMhgy/Uh1xM9wfyTMf17F9+6SUH4XXTpa66/1NmpMaFrCgypocRU6HBgGWIX/4Yt2T6J2y01xduAtSuB66y4Da/+1564dEo6IcJvGvkVSwIDAQAB
        tokenSignerPrivateKey: MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCX63/3wRnrogR9BtYC/70l7aUb9FzGywaccPp5dKNFxsaTiDFdutQw3L2tQfWv/db9C8PCroTz5VmYM9iZQZ0vrOWJ5YJQr+TF7SInJzB2MLdgvMdY+1GZITbisJKb0mKYEX4rQh7lyVFylYm+JCxt0FptE7ZD19r1+bgGodVvWAz4//9K8VNbRjR9XZr5PGF4XuKl0CljyoEl3Ws1gl1CR7zK7QZC/ZxUjz3GtuFATxGoyGDL9SHXEz3B/JMx/XsX37pJQfhddOlrrr/U2akxoWsKDKmhxFTocGAZYhf/hi3ZPonbLTXF24C1K4HrrLgNr/7Xnrh0Sjohwm8a+RVLAgMBAAECggEAK0AupHhjSsRLhtTFZQEt9nhbw0OkjiwK68r2ZFGikiDvM91YQJkKBAc0MPlQWxRneo844loRG+a1xTEtfyRA/eu5RbMDkLki5n6GdKMhluRzsKTOFDUom/Hq0Er40kfpLeX9B4/BgCX6fCa6mlluFK12sQslntb7TYAUtj6opxP3Ulw5cMEFqFGFmc+FKZEnZMgcMnh05ZI1jss7rsgXdF+Ezd3uS+1z/U6wP2AcCuoZlJbwyKBLc/eWBt6MuExEwNtPMSX/BnvzCLlENMAfE4dm2gGoDWP3VZCtuQtITKHzjYWECMDMJZiPyUDpHVjRc4gPptA0Iw8uljie3HJd8QKBgQDcVGB0h+vvJ18xEtvNHcG2O4SD3oWmG+qhJoKp/+BvCYvtvTqiF2pZ1l9wG8Nbz6aLtsKSENFBgzIHbGyFXl3ILXEX+sL3PmOUJfjhAa0ALe3psqdUHrQaT46bBuhY9YMBOLqCyzOj4Pf9tbBEUCPMORcU0tAh5eYMMQMVV+cZJQKBgQCwg91QrrxPPyMcZZRlX5PKvcBsvPbTU6G0djgsENY6/vvXNkW/t2RkICLUat7ywJDAk/linkHQTF++hOsjKhPtBWDwrDgmBfkU4sDAeX3jjTP68SR9SZndVKKcFCK9JZZk+KR/K1mTH6F/E48TXzE8Ooxpf8YDbQ852670DMLBrwKBgQCD4Y2KswEqOWrlOOCQOkuFBMMhG8Tr1LHwm9iCYUK5tAEp4KaD93gVJhgiRrv87KJH5rE4NRDMIfeEMgYTHpUMCQc0z0h8Lb/1IXWDWTlwbg2GYQV/eJFdXlmBufTBqtMrnAUn3PGgGjzWhDZp7X4/87w5HqauY6n7iirKT79ocQKBgHz0vGVzbUdEUNy3ZDfnUz6A2Ymgs7KiT6Tq05UOTjnxa+LMPtAbSWw/+6gYB8AdZduOZUrU8Y49osHCYprAHN77ocZ+hDkWnV+VrhITb3T47sVElCpFQjEWyw5mirkPtMnB4uFLlNu71p2ZAP4bs0UJxYChvVGLkO3KgK2voudtAoGABi3rTrkp50D9BmAQIR1zqH01IwXUdtpDoWq58f6qdNn789MuW+jNP/Q+YlPrLVT7qUJpNDsyRUZbh6G8QGo1qyOCV0cwcpl7Bm4y+pFJz5bPURjmkY3GKm1ggJPYepfuNp0ku7e4tWPxdfYg/ahMPxiQytIthMHmKjsMp9er6Lw=
        additionalInformation:
          ? key1
          : value1
          ? key2
          : value2

```
or with mac password:
```yaml

spring:
  jwtserver:
    clients:
      - clientName: acme
        clientSecret: $2a$10$BGDFk.RbwCTto/VCrHZ5I.I34RlZlFmuHMWn2kK/9iUwBulvuIh1y
        resourceIds: acme_inc
        accessTokenValiditySeconds: 60
        refreshTokenValiditySeconds: 120
        secretRequired: true
        refreshTokenEnabled: true
        tokenPrivateClaimVisible: true
        tokenPublicClaimVisible: true
        invalidateOnReauthorization: true
        tokenSignerMethod: mac
        tokenSignerSecret: NHA1HFM5FD2LLAE31AGKKHGDD81FN405
        additionalInformation:
          ? key1
          : value1
          ? key2
          : value2

```

## Customisation Beans

**ClientDetailsService**

if not implemented will use a DefaultClientDetailsService which will make use of
application properties to register the clients.

using a custom implementation:

```
@Autowired
ClientDetailsService customClientDetailsService;

@Bean
ClientDetailsService clientDetailsService(){
    return customClientDetailsService;
}
```

**UserDetailsService**

this bean have a default hard coded implementation for a root user, 
meant to be used only for demonstration purposes.

Create a custom implementation to load the user by username, e.g.:

```
@Autowired
UserDetailsService customUserDetailsService;

@Bean
UserDetailsService myCustomUserDetailsService(){
    return customUserDetailsService;
}
```


```
Important!  both ClientDetailsService and UserDetailsService use BCryptPasswordEncoder as a default
encriptor. Each of them utilizes separate beans for password encoding.
```

**StoreService**

The store service is responsible for storing the token and refresh token. 
by default it uses inMemory implementation, which can be easy changed by a custom
implementations, e.g.: RedisTokenStore

## Adaptations Beans 

**UserPasswordEncoder**

Used internally in UserDetailsChecker for decoding user password

**ClientPasswordEncoder**

Used internally in ClientDetailsChecker for decoding client secret

**ClientDetailsChecker**

Executes checks on loaded client, such as, client secret

**UserDetailsChecker**

Executes checks on loaded user, such as, user password, user enabled, disabled, etc.

**TokenEnhancer**

Creates a token enhancer (JWT public clams) using the information received from
loaded client and user. Currently it enhances with 
_user_id_, _user_name_, _authorities_ and all userDetail's _additional information_

**TokenService**
Uses a default implementation of JWT token to create the access token and refresh token

**TokenParser**
Creates the final response for the api, which can be seen on Response Sample below

## Core Beans

**TokenPasswordFlow**

Core implementation for grant type password

**TokenRefreshFlow**

Core implementation for grant type refresh_token

```
This framework accepts custom grant_types implementations
```


## Testing

Request token - password flow
```bash
curl -X POST \
  http://<server>:<port>/auth-server/oauth/token \
  -H 'Authorization: Basic YWNtZTphY21lc2VjcmV0' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"username" : "root",
	"password" : "root",
	"grant_type" : "password"
}'
```
Request token - refresh_token flow

```bash
curl -X POST \
  http://<server>:<port>/auth-server/oauth/token \
  -H 'Authorization: Basic YWNtZTphY21lc2VjcmV0' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"refresh_token" : "<REFRESH_TOKEN_HERE>",
	"grant_type" : "refresh_token"
}'
```

Response Sample:

```json
{
    "access_token": "eyJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJhY21lX2luYyIsInVzZXJfaWQiOiIxMjM0IiwidXNlcl9uYW1lIjoicm9vdCIsImV4cCI6MTU1OTQwNjI0MiwiaWF0IjoxNTU5NDAyNTgyLCJqdGkiOiIxMzYyMzNkOS0xMWE2LTQzZjEtOWVlZS0wZTgyNTZiMGY5YTIiLCJhdXRob3JpdGllcyI6WyJST0xFX1NBTVBMRUEiLCJST0xFX1NBTVBMRUIiXX0.dW6KRp54yLAaPcsEyHf6Nay0Cl9Q8Z_ujmshpTPPfO5JyB1q2NU3L0f780_rvyGkFC0jJEDidotN8qpy3MxHC9UWFK9ftHQFimlI-QqO4X-iyy_9_yCAyMfEDt5w419M5_qrrIDV21SykwPGitGAh4yP4tf9HLkMKuPITfDYy2Ujr59emf9TtVY30mWCd4zoMa8NjWLjVA7JRZZASn6iKN_ozay3tyaK8s_Q4kW2jgHJ-5wOwlAu7Z9HhfVBtgVvgb7ls_0TggZfzemzOEyX-HAXTtz-LxXh1umCZ6vOYFVHShTCtiVhqdiIWa6FdNhVE0usHHOT5Gl6pQaoG4HbQA",
    "refresh_token": "eyJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJhY21lX2luYyIsImF0aSI6IjEzNjIzM2Q5LTExYTYtNDNmMS05ZWVlLTBlODI1NmIwZjlhMiIsImV4cCI6MTU1OTQwNjMwMywiaWF0IjoxNTU5NDAyNTgzLCJqdGkiOiJlZGNhN2E1MC01NGY0LTRiNWYtYjU1ZC1jYzUwY2E1YjkxZmEifQ.eTfH0isb_Gd3OlBiNlv0nWNqxLjpV6rVg6UTwqMM4NTejeZvFxLOqnnXHNe2b_LshuGJMGnO2ZDKfwLRM4s4FGHpIN0Mm4wg4U3f3pG1kN1rr7eaqX8EzYS4zR9E3zwGCtW_QS_5dUfzaZmNbW1-KPXaWUY4np-xvy4L4Vf9yxEQyNWxuWqUuXdZAJAaIsBzgo3N3XdTW8_vdtT69aXV7DygPHP7OcmVrm9Jd2two6VJ51d1JJsAjWFrPA4POJax0BHZvrqOY7nb8WwoTVG3ZL-I-WYbJog6fZYmDoMe3J3LDvXbtH_oVU_xamBvocPgiylesLObf9oS1oSqWK2wRA",
    "aud": [
        "acme_inc"
    ],
    "user_id": "1234",
    "user_name": "root",
    "exp": 1559406242000,
    "expires_in": 60,
    "iat": 1559402582000,
    "authorities": [
        "ROLE_SAMPLEA",
        "ROLE_SAMPLEB"
    ],
    "jti": "136233d9-11a6-43f1-9eee-0e8256b0f9a2"
}
```
