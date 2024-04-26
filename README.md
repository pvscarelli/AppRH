# Instruções de Configuração do Aplicativo

## Pré-requisitos
- Spring
- Angular 17
- Banco de dados configurado

## Configuração do Banco de Dados
1. Após baixar os arquivos, se estiver usando **PostgreSQL**:
 - Vá até o arquivo `DataConfiguration.java`.
 - Na seção do Spring, configure a conexão com seu banco de dados.

2. Se estiver usando outro banco de dados:
 - Adicione a dependência do seu banco de dados no arquivo `pom.xml` do back-end.
 - Recomenda-se procurar a dependência em Spring Initializr.
 - Configure a conexão no `DataConfiguration.java`.

## Inicialização do Servidor Backend
- Navegue até o arquivo `RhApplication.java`.
- Clique em "run".
- O servidor backend deve iniciar na porta `8080` por padrão.

## Inicialização do Servidor Angular
- No terminal, navegue até o diretório raiz do projeto Angular.
- Execute o comando `npm install`.
- Após a instalação, execute `ng serve`.
- O servidor Angular deve iniciar na porta `4200` por padrão.

## Acesso ao Aplicativo
- Com ambos os servidores online, acesse o aplicativo pelo navegador na URL: `http://localhost:4200/`.
