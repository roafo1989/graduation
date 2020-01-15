Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we asume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides new menu each day.

As a result, provide a link to github repository.

It should contain the code and README.md with API documentation and curl commands to get data for voting and vote.

P.S.: Make sure everything works with latest version that is on github :)

P.P.S.: Asume that your API will be used by a frontend developer to build frontend on top of that.


## Rest API 

### Голосование
USER_ROLE:
- POST /rest/voices - голосовать за ресторан по id
- GET /rest/voices/my-voice-today - получить свой голос на сегодня
- GET /rest/voices/my-history - получить историю своих голосов
- GET /rest/voices/by-restaurant-today - получить голоса за ресторан на сегодня
- GET /rest/voices/by-restaurant-history - получить историю голосов за ресторан
- GET /rest/voices/by-restaurant-history-from-date - получить историю голосов за ресторан, начиная с указанной даты
- GET /rest/voices/by-restaurant-history-between-dates - получить историю голосов за ресторан за указанный период
- GET /rest/voices/all-for-today - получить все голоса на сегодня
- GET /rest/voices/summary-today - получить все рестораны с голосами на сегодня (ресторан : [все голоса на сегодня])
- GET /rest/voices/rating-today - получить рейтинг ресторанов на сегодня (ресторан: количество голосов)


### Ресторан
ADMIN_ROLE:
- POST /rest/admin/restaurants - создать профиль ресторана
- PUT /rest/admin/restaurants/{restaurantId} - обновить профиль ресторана по id
- DELETE /rest/admin/restaurants/{restaurantId} - удалить профиль ресторана по id

USER_ROLE:
- GET /rest/restaurants/today - получить список всех ресторанов с меню на сегодня
- GET /rest/restaurants/by-date?date={date} - получить список всех ресторанов с меню на указанную дату
- GET /rest/restaurants/{restaurantId} - получить ресторан по id с меню на сегодня
- GET /rest/admin/restaurants - получить список всех ресторанов

### Меню
ADMIN_ROLE:
- GET /rest/admin/restaurants/{restaurantId}/lunches - получить список всех бдюд ресторана по id ресторана
- GET /rest/admin/restaurants/lunches/{lunchId} - получить бдюдо по id
- POST /rest/admin/restaurants/{restaurantId}lunch - создать бдюдо в меню ресторана
- PUT /rest/admin/restaurants/{restaurantId}/lunches/{lunchId} - обновить бдюдо в меню ресторана
- DELETE /rest/admin/restaurants/lunches/{lunchIdId} - удалить бдюдо по id

### Пользователь
ADMIN_ROLE:
- GET /rest/admin/users - получить список всех пользователей
- GET /rest/admin/users/{userId} - получить профиль пользователя по id
- GET /rest/admin/users/by?email={email} - получить профиль пользователя по email
- POST /rest/admin/users - создать профиль пользователя
- PUT /rest/admin/{userId} - обновить профиль пользователя по id
- DELETE /rest/admin/{userId} - удалить профиль пользователя по id

USER_ROLE:
- GET /rest/profile - получить свой профиль
- UPDATE /rest/profile - обновить свой профиль
- DELETE /rest/profile - удалить свой профиль

ANONYMOUS:
- POST /rest/profile - зарегистрироваться как новый пользователь


## Curl samples for Voices
package com.example.grad1.util.ValidationUtil.DEADLINE_TIME - настройка времени, до которого можно голосовать

#### Voice for Restaurant 100003 
`curl -s -X POST -d '{"restaurantId":"100003"}' -H "Content-Type:application/json;charset=UTF-8"
http://localhost:8080/rest/voices?restaurantId=100003 --user user@mail.ru:password`
#after deadline
{
    "time": "15-01-2020 11:59:56",
    "url": "http://localhost:8080/rest/voices",
    "cause": "VoteTimeViolationException",
    "detail": "Voting is over for today"
}
#during
{
    "id": 100033,
    "dateTime": "2020-01-15T12:05:06.073",
    "restaurantId": 100003,
    "restaurantName": "Kharbin",
    "userName": "user"
}

#### Change Voice for Restaurant 100004
`curl -s -X POST -d '{"restaurantId":"100004"}' -H "Content-Type:application/json;charset=UTF-8" 
http://localhost:8080/rest/voices?restaurantId=100004 --user user@mail.ru:password`
{
    "id": 100034,
    "dateTime": "2020-01-15T12:14:59.494",
    "restaurantId": 100004,
    "restaurantName": "Ogon da myaso",
    "userName": "user"
}
#### get my Voice for today
`curl -s 
http://localhost:8080/rest/voices/my-voice-today --user user@mail.ru:password`
{
    "id": 100034,
    "dateTime": "2020-01-15T12:14:59.494",
    "restaurantId": 100004,
    "restaurantName": "Ogon da myaso",
    "userName": "user"
}

#### get my history of Voices
`curl -s 
http://localhost:8080/rest/voices/my-history --user user@mail.ru:password`
[
    {
        "id": 100034,
        "dateTime": "2020-01-15T12:14:59.494",
        "restaurantId": 100004,
        "restaurantName": "Ogon da myaso",
        "userName": "user"
    },
    {
        "id": 100011,
        "dateTime": "2020-01-14T00:19:00.473197",
        "restaurantId": 100004,
        "restaurantName": "Ogon da myaso",
        "userName": "user"
    },
    {
        "id": 100008,
        "dateTime": "2020-01-12T00:19:00.473",
        "restaurantId": 100003,
        "restaurantName": "Kharbin",
        "userName": "user"
    }
]
#### get Voice for restaurant 100003
`curl -s 
http://localhost:8080/rest/voices/by-restaurant-today?restaurantId=100003 --user user@mail.ru:password`
[
    {
        "id": 100006,
        "dateTime": "2020-01-15T00:19:00.473",
        "restaurantId": 100003,
        "restaurantName": "Kharbin",
        "userName": "Вася Пупкин"
    }
]
#### get history of Voices for restaurant 100003
`curl -s 
http://localhost:8080/rest/voices/by-restaurant-history?restaurantId=100003 --user user@mail.ru:password`
[
    {
        "id": 100006,
        "dateTime": "2020-01-15T00:19:00.473",
        "restaurantId": 100003,
        "restaurantName": "Kharbin",
        "userName": "Вася Пупкин"
    },
    {
        "id": 100008,
        "dateTime": "2020-01-12T00:19:00.473",
        "restaurantId": 100003,
        "restaurantName": "Kharbin",
        "userName": "admin"
    }
]

`curl -s 
http://localhost:8080/rest/voices/by-restaurant-history-from-date?restaurantId=100003&start=2020-01-01 --user user@mail.ru:password`
[
    {
        "id": 100006,
        "dateTime": "2020-01-15T00:19:00.473",
        "restaurantId": 100003,
        "restaurantName": "Kharbin",
        "userName": "Вася Пупкин"
    },
    {
        "id": 100008,
        "dateTime": "2020-01-12T00:19:00.473",
        "restaurantId": 100003,
        "restaurantName": "Kharbin",
        "userName": "admin"
    }
]
`curl -s 
http://localhost:8080/rest/voices/by-restaurant-history-between-dates?restaurantId=100003&start=2020-01-01&end=2020-01-14 --user user@mail.ru:password`
[
    {
        "id": 100008,
        "dateTime": "2020-01-12T00:19:00.473",
        "restaurantId": 100003,
        "restaurantName": "Kharbin",
        "userName": "admin"
    }
]

#### get all Voices for today
`curl -s 
http://localhost:8080/rest/voices/all-for-today --user user@mail.ru:password`
{
        "id": 100007,
        "dateTime": "2020-01-15T00:19:00.473",
        "restaurantId": 100005,
        "restaurantName": "Panda",
        "userName": "Carl Cimmer"
    },
    {
        "id": 100006,
        "dateTime": "2020-01-15T00:19:00.473",
        "restaurantId": 100003,
        "restaurantName": "Kharbin",
        "userName": "Вася Пупкин"
    },
    {
        "id": 100009,
        "dateTime": "2020-01-15T00:19:00.473",
        "restaurantId": 100004,
        "restaurantName": "Ogon da myaso",
        "userName": "S. King"
    }

#### get rating for today
`curl -s 
http://localhost:8080/rest/voices/rating-today --user user@mail.ru:password`
{
    "Ogon da myaso": 2,
    "Kharbin": 1,
    "Panda": 2
}
#### get summary for today
`curl -s 
http://localhost:8080/rest/voices/summary-today --user user@mail.ru:password`

{
    "Restaurant{name='Panda', id=100005}": [
        {
            "id": 100007,
            "dateTime": "2020-01-15T00:19:00.473",
            "restaurantId": 100005,
            "restaurantName": "Panda",
            "userName": "Carl Cimmer"
        },
        {
            "id": 100010,
            "dateTime": "2020-01-15T00:19:00.473",
            "restaurantId": 100005,
            "restaurantName": "Panda",
            "userName": "Вася Пупкин"
        }
    ],
    "Restaurant{name='Kharbin', id=100003}": [
        {
            "id": 100006,
            "dateTime": "2020-01-15T00:19:00.473",
            "restaurantId": 100003,
            "restaurantName": "Kharbin",
            "userName": "Вася Пупкин"
        }
    ],
    "Restaurant{name='Ogon da myaso', id=100004}": [
        {
            "id": 100009,
            "dateTime": "2020-01-15T00:19:00.473",
            "restaurantId": 100004,
            "restaurantName": "Ogon da myaso",
            "userName": "S. King"
        },
        {
            "id": 100034,
            "dateTime": "2020-01-15T12:14:59.494",
            "restaurantId": 100004,
            "restaurantName": "Ogon da myaso",
            "userName": "admin"
        }
    ]
}
