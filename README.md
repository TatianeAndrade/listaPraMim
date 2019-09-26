# Lista pra mim

<p align="center">
  <img src="https://raw.githubusercontent.com/TatianeAndrade/listaPraMim/master/imagens/lista.png" width="150"> 
</p>

O Lista pra mim©, é um app que vai revolucionar a forma como você faz compras. Quanto mais você usar o Lista pra mim, mais rápido vai ser gerar novas listas de compras. E você ainda pode economizar anotando preços e locais de compra. Depois é só deixar o Lista pra mim© indicar o melhor lugar para você fazer suas compras.

### Instalação/Execução
- Baixe o [projeto](https://github.com/TatianeAndrade/listaPraMim/archive/master.zip)
- Utilize o comando `mvn spring-boot:run` para executar o projeto
- Utilize um cliente REST de sua preferência, sugerimos o [postman](https://www.getpostman.com/)


### Endpoints

<p align="center">
  <img src="https://raw.githubusercontent.com/TatianeAndrade/listaPraMim/master/imagens/endpoints.png"  width="650"> 
</p>

### Arquitetura

A arquitetura do servidor foi desenvolvida utilizando linguagem Java juntamente com o framework Spring Boot. O modelo de designer adotado foi o MVC. Foi utilizado o SGBD relacional PostgreSQL. 

<p align="center">
  <img src="https://raw.githubusercontent.com/TatianeAndrade/listaPraMim/master/imagens/arquitetura.png"  width="650"> 
</p>

### Autenticação

A entidade Usuario foi utilizada para conter os atributos de autenticação email(username) e senha(password). O JWT(JSON Web Token) foi escolhido como o mecanismo de segurança para identificação do usuário. O usuário na fase de autenticação envia email e senha e caso sejam válidos o JWT gera um Token com uma determinada validade que será utilizado em suas requisições.

<p align="center">
  <img src="https://raw.githubusercontent.com/TatianeAndrade/listaPraMim/master/imagens/autenticacao.png"  width="650"> 
</p>
