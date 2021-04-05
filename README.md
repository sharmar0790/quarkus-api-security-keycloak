# quarkus-api-security-keycloak-authorization project

In tis project we will be integrating the quarkus with the key-cloak.

## Create the project
```
$ mvn io.quarkus:quarkus-maven-plugin:1.12.2.Final:create \
    -DprojectGroupId=org.acme \
    -DprojectArtifactId=security-keycloak-authorization-quickstart \
    -Dextensions="oidc,keycloak-authorization,resteasy-jackson"
```


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```


## Start the key-cloak
```
$ docker run --name keycloak -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -p 8180:8080 -p 8543:8443 jboss/keycloak
```

## Run the key-cloak with pre-loaded settings
```
$ docker run --name keycloak -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -p 8180:8080 -p 8543:8443 -v ${pwd}:/tmp jboss/keycloak
      //-e KEYCLOAK_IMPORT=/tmp/example-realm.json -v /tmp/example-realm.json:/tmp/example-realm.json jboss/keycloak
```

You will be able to access the key-cloak either on http://localhost:8180/auth or http://localhost:8543/auth  
Once login  
* Create the new realm
* Create the client
* Create the new roles as user_role and admin_role
* Create the two new users
  * user
  * admin
  

## Get the access token
* Access the key-cloak API using **http://localhost:8180/auth/realms/quarkus-api-security/protocol/openid-connect/token**. In this _quarkus-api-security_ is the realm name.
* Command to get the access token
```
$ export access_token=$(\
     curl --insecure -X POST https://localhost:8543/auth/realms/<realm-name>/protocol/openid-connect/token \
     --user <client-name>:secret \
     -H 'content-type: application/x-www-form-urlencoded' \
     -d 'username=<username>&password=<password>&grant_type=password' | jq --raw-output '.access_token' \
  )
  
  EX: 
  
  export access_token=$(\
      curl --insecure -X POST https://localhost:8543/auth/realms/quarkus/protocol/openid-connect/token \
      --user quarkus-service:secret \
      -H 'content-type: application/x-www-form-urlencoded' \
      -d 'username=admin&password=admin&grant_type=password' | jq --raw-output '.access_token' \
   )
```

## Access the API
```
$ curl -v -X GET \
     http://localhost:8080/api/admin \
     -H "Authorization: Bearer "$access_token
```


## Key-cloak client settings
```
## OIDC Configuration
quarkus.oidc.auth-server-url=http://localhost:8180/auth/realms/<realm-name>
quarkus.oidc.client-id=<client-name>
quarkus.oidc.credentials.secret=secret
quarkus.oidc.tls.verification=none


## Enable Policy Enforcement
quarkus.keycloak.policy-enforcer.enable=false
```
   
