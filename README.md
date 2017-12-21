# Web service de consumo do feed de noticias da revista Autoesporte

Tecnologia utilizada
> Spring Boot (plataforma Java)

Features
* Cobertura de testes
* Autenticação via JWT
* Dockerização

## Subindo a aplicação via Spring Boot

* Baixe o projeto
* Entre no diretório do projeto
* Execute o comando `mvn spring-boot:run`

## Subindo a aplicação via Docker

* Baixe a imagem do docker `docker pull robsonrigatto/feed-service`
* Execute o comando `docker run -p 8080:8080 -t robsonrigatto/feed-service`


## Utilizando Web service

* Faça uma requisição POST para a URL http://localhost:8080/login com o corpo da requisição com o conteúdo abaixo:
```
{
  "username":"user",
  "password":"password"
}
```
* No retorno, obtenha o valor **Authorization** do header da response da aplicação. O valor deve ter o formato abaixo:
```
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTE0NzEyNTI3fQ.ai3uvG-OK8NaK1tTJTytuWfyH8RH3d7gWL1pHjjyCaUjnlZWWWaX6MBMdJgeomeEJ-n9VaI8rU_n1WjqUHoCNg
```
* Faça uma requisição GET para a URL http://localhost:8080/feeds/autoesporte passando o token obtido acima no campo **Authorization** do header da request
* O retorno em formato JSON virá no retorno da chamada HTTP, como o exemplo abaixo:
```
{
    "feed": [
        {
            "title": "Ford disponibiliza aplicativo de navegação Sygic para New Fiesta e Ecosport",
            "link": "http://revistaautoesporte.globo.com/Noticias/noticia/2017/12/ford-disponibiliza-aplicativo-de-navegacao-sygic-para-new-fiesta-e-ecosport.html",
            "description": [
                {
                    "type": "text",
                    "content": "A Ford anunciou que o aplicativo Sygic, de mapas offline e navegação por GPS, começará a ser disponibilizado para o Sync 3, a central multimídia da montadora. O app fornece informações como rotas, condições do trânsito e radares, operando por meio de comandos de voz e com imagem projetada na tela do carro. O grande ponto positivo do sistema é não precisar de internet móvel para funcionar. O app faz pesquisa e cálculo de navegação da rota totalmente offline."
                },
                {
                    "type": "text",
                    "content": "Os primeiros modelos escolhidos para receber o app são: New Fiesta e EcoSport, mas a Ford disponibilizará a atualização para outros modelos no começo do ano que vem. No primeiro trimestre de 2018, será disponibilizada uma atualização de software para Focus, Fusion e Ranger, que passa a permitir o uso do Sygic."
                },
                {
                    "type": "text",
                    "content": "O Sygic roda em smartphones Android ou iOS, conectados com o veículo via cabo USB. Com isso, o aplicativo é projetado automaticamente na tela do veículo e todos os seus controles passam a ser feitos pelo Sync 3."
                },
                {
                    "type": "image",
                    "content": "http://s2.glbimg.com/8LwBHgrpWyDoALNwFgdQMM8BQiM=/e.glbimg.com/og/ed/f/original/2017/12/20/car-navi-660x350.jpg"
                },
                {
                    "type": "links",
                    "content": [
                        "http://revistaautoesporte.globo.com/busca/click?q=ford&p=0&r=1513801312267&u=http%3A%2F%2Frevistaautoesporte.globo.com%2FNoticias%2Fnoticia%2F2017%2F12%2Fversoes-diesel-da-ford-ranger-ficam-mais-caras.html&t=informacional&d=false&f=false&ss=&o=&cat=",
                        "http://revistaautoesporte.globo.com/busca/click?q=ford&p=1&r=1513801312268&u=http%3A%2F%2Frevistaautoesporte.globo.com%2FNoticias%2Fnoticia%2F2017%2F12%2Fford-aumenta-precos-do-novo-ecosport-de-novo.html&t=informacional&d=false&f=false&ss=&o=&cat="
                    ]
                }
            ]
        },
        {
            "title": "Toyota Yaris, que chegará em 2018, ganha nota máxima de segurança",
            "link": "http://revistaautoesporte.globo.com/Noticias/noticia/2017/12/toyota-yaris-que-chegara-em-2018-ganha-nota-maxima-de-seguranca.html",
            "description": [
            ...
            ]
        },
        ...
    ]
}
```
