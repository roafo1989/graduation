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

## Rest API 

### Голосование
USER_ROLE:
- POST /rest/voices - голосовать за ресторан по id
- GET /rest/voices?date={date} - получить свой голос на дату
- GET /rest/voices - получить историю своих голосов

- GET /rest/voices/rating-by - получить рейтинг ресторанов на дату (ресторан: количество голосов)
- GET /rest/voices/rating -  получить рейтинг ресторанов (ресторан: количество голосов)

ADMIN_ROLE
- GET /rest/admin/voices/restaurant?start={start}&end={end}&restaurantId={restaurantId} - получить голоса за ресторан между датами
- GET /rest/admin/voices/restaurant?date={date}&restaurantId={restaurantId} - получить историю голосов за ресторан на указанную дату
- GET /rest/admin/voices/restaurant?restaurantId={restaurantId} - получить полную историю голосов за ресторан

- GET /rest/admin/voices/all-by - получить все голоса на дату
- GET /rest/admin/voices/all - получить все голоса

- GET /rest/admin/voices/summary-by - получить все рестораны с голосами на дату (ресторан : [все голоса на дату])
- GET /rest/admin/voices/summary - получить все рестораны с голосами


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
