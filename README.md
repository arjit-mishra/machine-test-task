# machine-test-task
Machine Test task for eminence innovation.

--------------------------------------------------------------

> user and password is hardcoded.

> Secret and Resource URL are taken from application.yml

_______________________________________________________________

## user1: 
    username - admin
    password - admin
    roles - ROLE_ADMIN

## user2:
    username - tester
    password - tester
    roles - ROLE_TESTER

--------------------------------------------------------------


## Endpoints

```body
To get JWT Token

POST localhost:9000/auth/
    
{
    "username" : "admin",
    "password" : "admin"
}
```

```body
TASK 1: To get data from token (both admin and tester can access)

    GET  localhost:9000/api/admin/drawMatchesCount
```

```body
TASK 2: is divided in 2 APIs (only admin can access)


I - To get draw match count without multithreading

    GET  localhost:9000/api/tokenData

II - To get draw match count with multithreading

    GET - localhost:9000/api/admin/drawMatchesCountThread

```




    

