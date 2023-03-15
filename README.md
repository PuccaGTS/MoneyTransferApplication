# Сервис перевода денег

## Описание проекта
Разработан REST-приложение: "Сервис перевода денег". В нем развернут интерфейс для перевода денег с одной карты на другую по заранее описанной спецификации.
Заранее подготовленное веб-приложение (FRONT) подключается к разработанному сервису без доработок и использует его функционал для перевода денег.

## Окружение
JDK: openjdk:19-jdk-alpine

Система сборки: Maven

## Структура проекта

```
src
  ├───main
  │   ├───java
  │   │   └───ru
  │   │       └───netology
  │   │           └───moneytransferapplication
  │   │               ├───controller
  │   │               ├───exception
  │   │               ├───handler
  │   │               ├───model
  │   │               ├───repository
  │   │               ├───service
  │   │               └───validation
  │   └───resources
  │       ├───static
  │       └───templates
  └───test
      └───java
          └───ru
              └───netology
                  └───moneytransferapplication
```


### controller
`/controller/TransferController.java` - Rest-контроллер для обработки эндпоинтов;
### exceptions
`/exceptions/TransferException.java` - Ошибка сервера, соответствует коду ответа 500 - Internal Server Error;

`/exceptions/WrongInputException.java` -Ошибка данных запроса, соответствует коду ответа 400 - Bad Request;
### handler
`/handler/ApplicationExceptionHandler.java` - Обработчики ошибок;
### model
`/model/Amount.java` - сущность денежных средств, состоит из количества и вида валюты;

`/model/Operation.java` - сущность операции, данный вид сущности приходит запросом на сервер методом POST по адресу localhost:5500/tranfer;

`/model/OperationСonfirmation.java` - сущность подтверждения операции, данная модель приходит запросом на сервер методом POST по адресу localhost:5500/confirmOperation;

`/model/Success200OK.java` - объект ответа при успешной обработке запроса;

`/model/UnSucccessResp.java` - объект ответа при ошибочной обработке запроса;

### repository
`/repository/TransferRepository.java` - интерфейс репозитория;

`/repository/TransferRepositoryImp.java` - Репозиторий для хранения запросов, использует ConcurrentHashMap для хранения Operation;

### service
`/service/TransferService.java` - интерфейс сервиса;

`/service/TransferServiceImp.java` - Сервис для обработки запросов;
### validation
`/validation/ServiceValidation.java` - Класс для валидации данных Сервиса;
### resources
`application.properties` - файл для настроки сервера;

`log4j.properties` - файл для настроки логгера;

### logger
В качестве логгера используется библиотека: log4j. Перед началом запуска в файле `\src\main\resources\log4j.properties` необходимо изменить директорию сохранения файла логов в строке: `log4j.appender.file.File="Ваш путь"\\log_file.log`.

## Описание интеграции с FRONT
FRONT доступен [по адресу](https://github.com/serp-ya/card-transfer). 
1. Можно скачать репозиторий и запустить Node.js приложение локально (в описании репозитория FRONT добавлена информация, как запустить)
2. Использовать уже развёрнутое демо-приложение [по адресу](https://serp-ya.github.io/card-transfer/), тогда в файле `application.properties` необходимо указать порт 5500.

В проекте используется 2ой вариант работы с FRONT.

## Запуск приложения
1. Склонировать репозиторий

2. Сначала вам надо собрать jar-архив. Для этого в терминале, в корне вашего проекта выполните команду:

Для gradle: `./gradlew clean build` (если пишет Permission denied, тогда сначала выполните `chmod +x ./gradlew`).

Для maven: `./mvnw clean package` (если пишет Permission denied, тогда сначала выполните `chmod +x ./mvnw`).

3. В терминале запустите приложение командой: `docker-compose up` (должен быть установлен Docker);

### Виды запросов
Данный с формы отправляют `POST` запрос по адресу `http://localhost:5500/transfer` в формате `application/json` вида (Или используйте, например, POSTMAN для отправки запроса):
```
 {
    "cardFromNumber": "1234123412341234",
    "cardFromValidTill": "1225",
    "cardFromCVV": "324",
    "cardToNumber": "1234123412341234",
    "amount": {
        "value": 12,
        "currency":"rub"
    }
}
```
Далее происходит отправка `POST` запроса по адресу `http://localhost:5500/confirmOperation` в формате `application/json` вида (Или используйте, например, POSTMAN для отправки запроса):
```
{
    "operationId": "2",
    "code": "0000"
}
```