
Разработка и реализация REST API с использованием Hibernate / Spring/Spring MVC (или Spring-Boot) без переднего плана.

Задача такова:

Постройте систему голосования для принятия решения о том, где пообедать.

2 типа пользователей: администратор и обычные пользователи

Администратор может ввести ресторан и его обеденное меню дня (обычно 2-5 пунктов, просто название блюда и цена)

Меню меняется каждый день (администраторы делают обновления)

Пользователи могут проголосовать за то, в каком ресторане они хотят пообедать

На каждого пользователя засчитывается только один голос

Если пользователь проголосует снова в тот же день:

Если это произойдет до 11: 00, мы предполагаем, что он передумал.
Если это после 11: 00, то уже слишком поздно, голосование не может быть изменено

Каждый ресторан предлагает новое меню каждый день.

В результате предоставьте ссылку на репозиторий github.

Он должен содержать код и README.md с документацией API и командами curl для получения данных для голосования и голосования.



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