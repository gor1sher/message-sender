# Сервис-отправитель
## Кратко о микросервисе:
### Сервис получает данные через внешнее API (кол-во людей в космосе и список космонавтов), потом через брокер сообщений RabbitMQ отправляет второму микросервису эти данные для дальнейшей работы с ним. 
### RabbitMQ был взят так как его легче развертывать, чем Kafka, и легче работать с очередью 