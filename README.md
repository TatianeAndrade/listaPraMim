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

### Autorização

A aplicação possui dois tipos de usuários Admin e Usuario. Ao receber uma requisição, para determinadas rotas o Backend verifica se o usuario possui privilégios para assim responder a solicitação.
Para isso foi criado um filtro de autorização registrado em SecurityConfig juntamente com o UserDetailsService. Neste filtro é verificado o token a partir de uma função implementada no JWTUtil que informa se o mesmo é válido. Se o usuário não possua o privílégio a requisição é negada retornando o status 403.

### Desempenho

Para melhorar o desempenho da aplicação foi utilizado o Redis (um banco de dados NoSQL do tipo chave valor armazenado em memória RAM). Ele é utilizado como provedor de cache para otimizar o acesso à dados evitando requisições repetitivas à banco de dados que possuem grandes estruturas, complexas e nem sempre performáticas.

Para medir o desempenho foi utilizada a ferramenta JMeter para realização de Testes de vazão e tempo de resposta. Foram obtidos os seguintes gráficos:

#### Tempo de resposta

<p align="center">
  <img src="https://raw.githubusercontent.com/TatianeAndrade/listaPraMim/master/imagens/grafico.png"  width="650"> 
</p>


Como pode ser visto a rota com cache habilitado possui um melhor desempenho apresentado um tempo de resposta menor e estável. Entretanto a rota sem o cache habilitado apresentou um aumento no tempo de resposta ao longo da carga de requisição. Foi utilizado 50 usuários( Threads), durante 60 segundos e um tempo de inicialização de 5 segundos.

#### Vazão

<p align="center">
  <img src="https://raw.githubusercontent.com/TatianeAndrade/listaPraMim/master/imagens/graficoVazao.png"  width="650"> 
</p>

Como pode ser visto a rota com cache habilitado possui uma grande vazão, porém infelizmente não foi possivel chegar ao limite de vazão pois a aplicação e a ferramenta de teste estavam na mesma máquina. Foi utilizado 500 usuários( Threads), durante 60 segundos e um tempo de inicialização de 5 segundos. 

