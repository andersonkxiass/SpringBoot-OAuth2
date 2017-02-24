# Spring Boot OAuth Authorization & Resource server




## Requirements
 
  * Java Platform (JDK) 8
  * Eclipse + Buildship Gradle plugin ( Insall from Eclipse Marketplace)
  * Mysql
  
## How to run it

 * Clone the repository
 * Eclipse, import project as gradle project, sync gradle project for download dependencies.

### Running projects 
    
  * OAuth Authorization Server
      
      `AuthorizationServerApplication.java`
 
  * ResourceOwner Server
      
      `ResOwnerApp.java`
      
  * Resource Server 
  
      `Application.java`
      
      
      
# Usage

Test the `test` endpoint:

 `curl http://localhost:8888/api/v1/test/list`
 
 You receive the following JSON response, which indicates you are not authorized to access the resource:
 
 ```Json
 {
    "error": "unauthorized",
    "error_description": "Full authentication is required to access this resource"
 }
 
 ```

In order to access the protected resource, you must first request an access token via the OAuth handshake. Request OAuth authorization:

```curl
 curl -X POST -vu fooClientIdPassword:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=123456&username=anderson&grant_type=password&scope=read%20write&client_id=fooClientIdPassword
```
 
 A successful authorization results in the following JSON response:
 
 ```Json
 {
    "access_token": "e007e13c-8b44-4980-918e-7427b886f640",
    "token_type": "bearer",
    "refresh_token": "e124ceb5-e260-4420-95dc-095f725bf4f6",
    "expires_in": 35999,
    "scope": "foo read write"
}
```

Use the access_token returned in the previous request to make the authorized request to the protected endpoint:

```curl
 curl http://localhost:8888/api/v1/test/list -H "Authorization: Bearer e007e13c-8b44-4980-918e-7427b886f640"
```
 
 If the request is successful, you will see the following JSON response:
 
 ```Json
 [
  "Test",
  "Test",
  "Test",
  "Test"
 ]
```

After the specified time period, the access_token will expire. Use the refresh_token that was returned in the original OAuth authorization to retrieve a new access_token:

```curl
curl -X POST -vu fooClientIdPassword:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "grant_type=refresh_token&refresh_token=e124ceb5-e260-4420-95dc-095f725bf4f6&client_id=fooClientIdPassword"
```
