1) Освободить порты:
     server.port(приложение) = 8888
     database.port(база данных) = 3333
2) В основной папке скачать файл - docker-compose.yml
3) Запустить докер
4) В CMD или Terminal перейти в папку где сохранили  docker-compose.yml (cd C:\camunda\resources (это пример))
5) ввести команду docker-compose up -d - сразу запустится и приложение, и база данных

    Swagger = http://localhost:8888/swagger-ui/index.html

    Postman:
       Authorization:
           Auth Type: Basic Auth
           Username: admin
           Password: password
   Этим самым пройдете авторизацию

   Регистрация нового сенсора:
       POST:
           http://localhost:8888/sensors/registration
       Body-raw-JSON:
           {
                "name": "sensor1"
           }

   Единичное добавление измерения:
       POST:
           http://localhost:8888/measurements/add
       Body-raw-JSON:
           {
              "value": 22.3,
              "raining": false,
              "sensor": {
                          "name": "sensor1"
                        }
            }

   Добавление случайных изменений:
       POST:
           http://localhost:8888/measurements/addRandom?sensorName=sensor1&count=1000 (это добавит 1000 изменений к 1 сенсору, можете поменять сенсор и количество при необходимости)

   Получение всех измерений:
       GET:
           http://localhost:8888/measurements

   Получение дождливых дней:
       GET:
           http://localhost:8888/measurements/rainyDaysCount
