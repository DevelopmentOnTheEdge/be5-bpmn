Это приложение, которое позволяет использовать диаграммы и задачи BMPN (Business Process Modeling Notation).

Для поддержки BMPN используется Camunda (https://camunda.com/).

#### Maven
для maven нужно настроить nexus https://sites.google.com/a/developmentontheedge.com/wiki/home/sistemnoe-administrirovanie/maven-repozitorij-nexus
нужна только первая часть - до "mvn deploy"

#### Database
состоит из 2 схем
- camunda - таблицы Camunda
- public - таблицы be5

Чтобы создать базу данных:
1) в PgAdmin создайте пользователя, с правами создавать базы данных
2) создайте базу данных
3) config connection profile in file: [src/connectionProfiles.local.yaml](https://github.com/QProgS/testBe5app/blob/master/src/connectionProfiles.local.yaml) 
4) create file src/profile.local with name of connection, for example: "test_local"
5) создайте таблицы Camunda
```sh
mvn be5:data -DBE5_SCRIPT=camunda_db
```
6) создайте таблицы be5 и приложения
```sh
mvn be5:create-db
```

#### Frontend
для фронтенда на машине должен быть node >= 6, устанавливается nodejs

Фронтенд собирается в src/main/webapp/

```sh
// установить зависимости
npm install

// Режим разработки - запускается node сервер на порту 8888
npm start
// Можно запустить так для того чтобы слушать на произвольном IP адресе а не на localhost
node node_modules/.bin/webpack-dev-server --host 192.168.220.191 --progress --profile --colors

//На своей машине быстрее собрать незжатый 
npm run build

//На сервере собирается
npm run build-min
```

#### Backend
```sh
-- mvn install -U -Dmaven.artifact.threads=1
```

Полезные таски из плагина be5 для maven
 
```sh
mvn be5:sync
если нужно только обновить/синхронизировать структуру базы данных с ее декларацией в be5

mvn be5:data -DBE5_SCRIPT=ftl_script_name

```

Подробнее - https://github.com/DevelopmentOnTheEdge/be5/wiki/Maven-plugin

#### Run Test Be5 application!
Просто запустите main метод

```java
public class ..Main
{
    public static void main(String... args)
    {
        new EmbeddedJetty().run();
    }
}
```

Или
 
```sh
mvn clean install jetty:run -Djetty.http.port=8200
```

Если вы воспользовались вторым вариантом запуска тогда нужно настроить debug.

#### Debug, Hot Swapping With Maven, Jetty and IntelliJ
[Hot Swapping With Maven, Jetty and IntelliJ](https://gist.github.com/naaman/1053217)
```text
1)In VM Parameters, enter:
  -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=4000
2)add Add New Configuration -> Remote 
```

#### Dev
Для обновления yaml проекта без перезапуска настройте dev.yaml    
[Быстрое обновление dev.yaml](https://github.com/DevelopmentOnTheEdge/be5/wiki/%D0%91%D1%8B%D1%81%D1%82%D1%80%D0%BE%D0%B5-%D0%BE%D0%B1%D0%BD%D0%BE%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5-dev.yaml)


[![Build Status](https://travis-ci.org/Biosoft-ru/muscle.svg?branch=master)](https://travis-ci.org/Biosoft-ru/muscle)
[![Coverage Status](https://coveralls.io/repos/github/Biosoft-ru/muscle/badge.svg?branch=master)](https://coveralls.io/github/Biosoft-ru/muscle?branch=master)
