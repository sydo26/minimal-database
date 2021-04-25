# minimal-database

## Instalação

Em `Releases` o arquivo .jar estará disponível, tanto o arquivo sem nenhuma dependência como o com todas.
Basta baixar e caso utilize do maven, siga os seguintes passos:

No seu arquivo pom.xml adicione:

```xml
<repositories>
  <repository>
    <id>project.local</id>
    <name>project</name>
    <url>file:${project.basedir}/repo</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.sydo26</groupId>
  <artifactId>sydo26-database</artifactId>
  <version>${version}</version>
</dependency>
```

O `repositório` mostrado acima seria seu repositório maven local.

O valor de `${version}` deverá ser a versão que você deseja incluir. Ex: `1.0-SNAPSHOT` que é a primeira versão deste projeto.

Caso não esteja utilizando maven, você pode colocar o arquivo `.jar` em `/lib`.

## Utilização

```java

    // Abre conexão com o banco de dados
    Database db = new Database(host, port, database, user, password, Driver.POSTGRESQL_DRIVER)

    // Schemas
    db.addSchema(new UserSchema()); // a schema aqui, é uma forma de executar querys pré-definidas separadamente.

    // Run schemas (executa todas as schemas)
    db.callSchemas();

    db.q(); // Retorna uma Query, que poderá realizar comandos como: executeQuery, execute, executeUpdate,...
    db.c(); // Retorna a connection

    // Close
    db.close();
```

## Criar schema

Existe uma classe chamada `Schema`, caso queira criar uma nova Schema, basta exportá-la em sua própria classe. Temos como exemplo a classe `Table` que já está incluída na lib e é um modelo para schemas do tipo Table.

## Créditos

[GITHUB](https://github.com/sydo26)
