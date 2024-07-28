# Descrição do Projeto

O projeto é um sistema de um empresa de coleta de resíduos. Trabalhei na área administrativa de uma empresa do ramo, então decidi praticar o que venho aprendendo implementando soluções para esta área.
Um sistema igual a esse foi desenvolvido por mim durante esses anos que estive lá, mas nesse projeto utilizo tudo o que venho aprendendo sobre arquiteturas de sistemas mais modernas e diferente desse primeiro sistema ao qual foi criado utilizando linguagens não tão mais utilizadas como VBA e utilizando banco de dados Access, nesse estou utilizando linguagens e ferramentas mais modernas.

## Informação Importante

O frontend e o backend começaram em repositórios separados e agora eu juntei nesse para ficar mais fácil o gerenciamento.
Aqui estão o link dos outros repositórios caso voce tenha interesse em ver os commits antigos:
[Frontend](https://github.com/victor-vilar/api-springboot-frontend-angular-pa).
[Backend](https://github.com/victor-vilar/api-springboot-pa)


## Tecnologias Utilizadas

### Frontend

- HTML
- CSS
- Typescript
- Angular

### Backend

- Java
- Spring Boot
- Spring Security
- Postgres
- Docker
 

## Como rodar

### Frontend
Pré requisitos
- Node
- Angular Cli

Executar o comando:
`npm install `
E então:
`npm run ng s -o`

Ao iniciar o projeto abre na página de login, para conseguir fazer o login é necessário que o backend esteja rodando. O backend está configurado para ouvir a porta 8080.

### Login e Senha
username: madruga
senha: mama

### Backend
Pré requisitos
- Java 17+
- Maven


Também é possivel utilizar o Docker para iniciar o projeto utilizando o docker compose. Para isso é necessário fazer o comando `docker compose up` na raiz do projeto.

## Empresa de Coleta

As empresas de engenharia que posuem o foco principal na coleta de resíduos possuem(assim como outros ramos) diversos normas e leis ao qual devem ser seguidas para poderem prestar o serviço de modo eficiente. 
Nos últimos anos não só o Brasil mas o mundo esta muito preocupado com os resíduos gerados e a melhor maneira de descarta-los. No Rio de Janeiro o orgão responsável por toda parte ambiental é o Instituto Estadual do Ambiente - INEA. O INEA tambem é quem fornece as licenças de operação que as empresas precisam para serem autorizadas a realizar algum tipo de serviço que envolva de alguma maneira o meio ambiente. Empresas de coleta de resíduos em geral, aterros sanitários, estações de transbordo, cooperativas de reciclagem e etc, todas precisam estar previamente cadastradas e com uma licença de operação emitida pelo INEA para que possam executar os serviços dentro do estado do Rio de Janeiro.
Além disso tambem existe a Companhia Municipal de Limpeza Urbana da Cidade do Rio de Janeiro - COMLURB, que regulariza, autoriza e fiscaliza a frota dessas empresas, impedindo que veículos que não estão adequados para realizar os serviços circulem pela cidade.



## Funcionalidades

É possivel realizar o gerenciamento das seguintes Features:
- Clientes
- Endereços
- Fiscais
- Contratos
- Contas - Com a possibilidade de gerar uma conta com várias parcelas
- Equipamentos
- Resíduos
- Ordens de Serviço
- Emissão do PDF da Ordem de Serviço

## Futuras implementações

- Criação de Ordens de Serviço automaticamente, seguindo o agendamento feito dos itens no cadastro do contrato.
- Atualização dos Contratos com possibilidade de reajuste dos valores dos itens seguindo os indices do contrato.
- Emissão de Relatórios do quantitativo coletado nas ordens de serviço em um período específico.
- Emissão de Relatórios de futuras contas e de contas pagas em um período específico.
- CRUD dos veículos.
- Controle de abastecimento nos veículos.
- Adicionar o Redis para fazer o caching da aplicação.
  
## Contato
Para informações mais detalhadas sobre o sistema por favor entre em contato comigo por um dos links abaixo:
[![Linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/victor-vilar01/)  [![Instagram](https://img.shields.io/badge/Instagram-E4405F?style=for-the-badge&logo=instagram&logoColor=white)](https://www.instagram.com/victorvilar01/)
