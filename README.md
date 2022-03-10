# Магазин игрушек (ToyMarket)

Есть 2 окружения dev и основное 

Окружение dev используюется для разработки, база данных h2 в памяти, чтобы войти в бд, надо перейти по пути /h2-conslole и ввести логи, которые находятся в application-dev

Основное окружение - используется база данных mysql, чтобы поднять бд можно использовать docker, а именно docker-compose для этого нужно установить docker/docker-compose,
перейти в корневую папку проекта, открыть консоль и написать команду для запуска docker-compose up (поднимает базу данных), а также команда
для удаления docker-compose down (удаление образа docker и прекращение работы бд).

# Использованные технологии
FrontEnd:
- HTML 5
- CSS
- JavaScript
- Фреймворк UIKIT

BackEnd:
- Java 17
- Spring Boot
- Spring Web
- Spring Data
- Spring Security
- OAuth2 (Google)
