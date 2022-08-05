
# METROID :metro:
The idea is to replace metro tickets :ticket: by using NFC techonolgy 

## About

Metroid divieded into three parts METROID ADMIN APP , METROID Backend(spring boot), METROID APP

METROID APP :iphone: : android app using kotlin
(MVVM,DI,Repository Pattern,Retrofit,NFC)
replaced metro tickets by NFC techonology

** i'll provide a video in Demo section below ** 

METROID Backend :computer: : using java srping boot 
,provide an API to metroid app 

METROID ADMIN  :iphone: : android app , facilitate to admin to add trip , delete one and see feedbacks



## Tech Stack

**Client:** KOTLIN, JAVA, ANDROID STUDIO 

**Server:** SPRING BOOT, JAVA


## Installation

first you should open metroid backend and edit email and password which will be used to send email to clients

```
#put your email and password here
spring.mail.password=
spring.mail.username=
```

second you should open metroid app and change api address

    

  ```KOTLIN
    // you can put your ip here
    const val BASE_URL = ""
   ```
## Screenshots

- Postman
![image](https://user-images.githubusercontent.com/52335429/183098003-4a638eba-ef26-473b-84b4-5cd6b98881af.png)
- Database Scheme
![image](https://user-images.githubusercontent.com/52335429/183098132-22665344-3296-41b0-b5c8-fe6b3301c7aa.png)



## Demo

https://user-images.githubusercontent.com/52335429/183096620-d800b9e2-4441-473c-90da-78a14528645b.mp4




