# RAR Framework in Java [![Build Status](https://travis-ci.org/aleDsz/rarframework-java.svg?branch=master)](https://travis-ci.org/aleDsz/rarframework-java)

## 1. Introdução

Após ter criado o mesmo framework, originalmente em [PHP](https://github.com/aleDsz/rarframework), percebi que eu teria a mesma necessidade de um ORM em outras linguagens. Assim como eu precisei quando comecei a utilizar o Java em ambiente profissional e, com a praticidade que eu tinha em PHP, resolvi adaptar para Java.

## 2. Como Funciona

Através do pacote DBI, é possível realizar uma conexão com vários tipos de banco de dados. Além disso, por meio do `Generics`, é possível acessar o conteúdo de um objeto e obter todas as informações necessárias para criar uma instrução SQL.

Neste caso, uma classe deve seguir o seguinte modelo:

```java
import br.com.aledsz.rarframework.database.objects.DbColumnAttribute;
import br.com.aledsz.rarframework.database.objects.DbTableAttribute;

@DbTableAttribute(databaseName = "sqlite", tableName = "contacts")
public class Contact {

    @DbColumnAttribute(fieldName = "id", primaryKey = true, size = 11, type = "Integer")
    public Integer id;

    @DbColumnAttribute(fieldName = "name", primaryKey = false, size = 120, type = "String")
    public String name;

    @DbColumnAttribute(fieldName = "email", primaryKey = true, size = 120, type = "String")
    public String email;

    @DbColumnAttribute(fieldName = "phone", primaryKey = false, size = 30, type = "String")
    public String phoneNumber;
}

```

## 3. Como Utilizar

Para que você possa utilizar todos as funcionalidades do framework no seu ambiente, você pode criar 1 (ou mais, dependendo da sua forma de trabalho) classe para acessar ao banco de dados de forma genérica.

```java

import br.com.aledsz.rarframework.database.DatabaseFactory;
import br.com.aledsz.rarframework.database.command.CommandContext;
import br.com.aledsz.rarframework.database.data.DataContext;
import br.com.aledsz.rarframework.database.enums.TiposSelect;
import br.com.aledsz.rarframework.database.objects.ObjectContext;
import br.com.aledsz.rarframework.database.sql.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author aleDsz
 */
public class ModelDataAccess<T> {

    public void create(T obj) throws Exception {
        try {
            ObjectContext<T> objContext = new ObjectContext<>(obj);
            DataContext dataContext = DatabaseFactory.getInstanceOfDataAccess(objContext.getDatabase());
            SqlStatementInsert<T> sqlStatement = new SqlStatementInsert<>(obj);

            dataContext.begin();

            CommandContext commandContext = new CommandContext(objContext.getDatabase(), sqlStatement.getSql());
            commandContext.executeQuery();

            dataContext.commit();
        } catch (IOException | SQLException ex) {
            throw ex;
        }
    }

    public void save(T obj) throws Exception {
        try {
            ObjectContext<T> objContext = new ObjectContext<>(obj);
            DataContext dataContext = DatabaseFactory.getInstanceOfDataAccess(objContext.getDatabase());
            SqlStatementUpdate<T> sqlStatement = new SqlStatementUpdate<>(obj);

            dataContext.begin();

            CommandContext commandContext = new CommandContext(objContext.getDatabase(), sqlStatement.getSql());
            commandContext.executeQuery();

            dataContext.commit();
        } catch (IOException | SQLException ex) {
            throw ex;
        }
    }

    public void remove(T obj) throws Exception {
        try {
            ObjectContext<T> objContext = new ObjectContext<>(obj);
            DataContext dataContext = DatabaseFactory.getInstanceOfDataAccess(objContext.getDatabase());
            SqlStatementDelete<T> sqlStatement = new SqlStatementDelete<>(obj);

            dataContext.begin();

            CommandContext commandContext = new CommandContext(objContext.getDatabase(), sqlStatement.getSql());
            commandContext.executeQuery();

            dataContext.commit();
        } catch (IOException | SQLException ex) {
            throw ex;
        }
    }

    public T find(T obj) throws Exception {
        try {
            T object = null;

            ObjectContext<T> objContext = new ObjectContext<>(obj);
            DataContext dataContext = DatabaseFactory.getInstanceOfDataAccess(objContext.getDatabase());
            SqlStatementSelect<T> sqlStatement = new SqlStatementSelect<>(obj);

            dataContext.begin();

            CommandContext commandContext = new CommandContext(objContext.getDatabase(), sqlStatement.getSql(TiposSelect.ByKey));
            object = objContext.getObject(commandContext.executeReader());

            dataContext.commit();

            return object;
        } catch (IOException | SQLException ex) {
            throw ex;
        }
    }

    public List<T> findAll(T obj) throws Exception {
        try {
            List<T> objects = null;

            ObjectContext<T> objContext = new ObjectContext<>(obj);
            DataContext dataContext = DatabaseFactory.getInstanceOfDataAccess(objContext.getDatabase());
            SqlStatementSelect<T> sqlStatement = new SqlStatementSelect<>(obj);

            dataContext.begin();

            CommandContext commandContext = new CommandContext(objContext.getDatabase(), sqlStatement.getSql(TiposSelect.All));
            objects = objContext.getObjects(commandContext.executeReader());

            dataContext.commit();

            return objects;
        } catch (IOException | SQLException ex) {
            throw ex;
        }
    }
}
```

**OBS.:** Você não precisa criar a classe de forma genérica, você pode criar uma classe de acesso a dados para cada entidade que você criar no modelo citado acima.

E para que o ORM consiga se conectar com o banco de dados, você precisa criar um arquivo de configuração com o nome: `config.properties` e ele deve seguir o modelo abaixo:

```java
sqlite.host = data/testDb.db
sqlite.port = 
sqlite.type = sqlite
sqlite.user = 
sqlite.pwd  = 
sqlite.db   = 
```

## 4. Como Contribuir

Para contribuir, você pode realizar um **fork** do nosso repositório e nos enviar um Pull Request.

## 5. Doação

Caso queria fazer uma doação para o projeto, você pode realizar [aqui](https://twitch.streamlabs.com/aleDsz)

## 6. Suporte

Caso você tenha algum problema ou uma sugestão, você pode nos contatar [aqui](https://github.com/aleDsz/rarframework-net/issues).

## 7. Licença

Cheque [aqui](LICENSE)
