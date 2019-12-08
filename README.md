![Issues](https://img.shields.io/github/issues/gbzarelli/quaklog-api.svg) 
![Forks](https://img.shields.io/github/forks/gbzarelli/quaklog-api.svg) 
![Stars](https://img.shields.io/github/stars/gbzarelli/quaklog-api.svg) 
![Release Version](https://img.shields.io/github/release/gbzarelli/quaklog-api.svg)

# QuakLog API

<p align="center">
    <img src="./documentation/quaklog.png" height="150">
</p>

A `QuakLog` é um serviço para importação de arquivos de `log` do jogo `Quake`.

Uma vez que o arquivo de `log` é importado ele é transformado em dados legíveis sobre
as diversas partidas registradas pelo jogo, após a transformação ser realizada o documento
referente a cada `partida` ganha uma chave e é registrada em um banco de dados `NoSQL` para que 
seja permitido posteriormente realizar consultas mais detalhadas.

# Tecnologias utilizadas

 - [`Spring boot`](https://spring.io) - Framework base para a API
 - [`MongoDB`](https://www.mongodb.com) - Base de dados não relacional
 - [`Swagger`](https://swagger.io) - Documentação de API de forma dinâmica
 - [`Docker`](https://www.docker.com) - Executa e gerencia aplicações dentro de invólucros chamados containers
 - [`jUnit5 e Mockito`](https://junit.org/junit5/) - Execução de testes
 
# Endpoints

A `QuakLog` disponibiliza os seguintes `endpoints` em sua `API`

#### 1 - Importação (**POST**) do arquivo de log
- PATH: **/game**
- Headers:
    - Content-Type: `multipart/form-data`
    - Log-File-Date: `{logFileDate}`
        * Exemplo do parametro `logFileDate`: `2019-12-07`
- Body: from-data name="file" value: {bytes}
- Resultado esperado: (Code: `201`)
    * Header de resposta:
      * Location: **/game/date/{gameDate}**

#### 2 -  Consulta de jogos por data (**GET**)
- PATH: **/game/date/{gameDate}**
  - Exemplo do parametro `date`: `2019-12-07`
- Resultado esperado: (Code: `200`)
Body:
```text
    {
        "games":[
            "1":{
                uuid: "d37f37d1-0df6-4e9e-9feb-b0362736518a"
                total_kills: 45;
                players: ["Dono da bola", "Isgalamido", "Zeh"]
                kills: {
                    "Dono da bola": 5,
                    "Isgalamido": 18,
                    "Zeh": 20
                }
            },
            "2":{
                uuid: "d37f37d1-0df6-4e9e-9feb-b0362736518b"
                total_kills: 40;
                players: ["Isgalamido", "Zeh"]
                kills: {
                    "Dono da bola": 1,
                    "Isgalamido": 10,
                    "Zeh": 15
                }
            }
        // [n...]
        ]
    }
```

#### 3 -  Consulta de jogo (**GET**)
- PATH: **/game/{UUID}**
- Resultado esperado: (Code: `200`)
Body:
```text
    {
        "game":{
            uuid: "d37f37d1-0df6-4e9e-9feb-b0362736518a"
            total_kills: 45;
            players: ["Dono da bola", "Isgalamido", "Zeh"]
            kills: {
                "Dono da bola": 5,
                "Isgalamido": 18,
                "Zeh": 20
            }
            // TODO MORE DATA HERE
        },
    }
```

# Executando com Docker

Os seguintes passos foram documentados para serem executados em uma plataforna
 `Linux`, porém os comandos com o sistema `Windows` podem ser semelhantes, mas,
 o funcionamento não é totalmente garantido. 
   
- Certifique-se que você tenha as seguintes ferramentas instaladas:
    * [`Docker` ](https://docs.docker.com/install/), 
    * [`Docker Compose` ](https://docs.docker.com/compose/install/),
    * [`Git` ](https://git-scm.com/downloads),

- Efetue o clone do projeto pelo `Git`:

```shell
$ git clone https://github.com/gbzarelli/quaklog-api.git
```

- Acesse a pasta do projeto para iniciarmos a compilação:

```shell
$ cd ./quaklog-api
```

- As instruções a seguir executará atravéz do [`Gradle Build Tools`](https://gradle.org) 
o `clean` do projeto, logo o `build` executará os testes unitários 
para garantir a integridade do projeto e gerar o executável (`.jar`)

```shell
$ ./gradlew clean build
```

- Agora será realizado o `build` do [`Dockerfile`](./Dockerfile) gerando uma imagem 
no repositório local denominada de `luizalabs/quaklog-api`. 

```shell
docker build -t luizalabs/quaklog-api .
```

- Após ter gerado a imagem da API iremos executar o projeto utilizando o [`docker-compose`](./docker-compose.yml).

```shell
$ docker-compose up
```

# Documentação dinâmica com o Swagger

A `Swagger UI` fornece uma estrutura de exibição que é capaz de interpretar os `endpoints` do projeto 
e gera um site de documentação interativa

http://localhost:8080/swagger-ui.html
