# 📦 Sistema de Gerenciamento de Produtos e Fornecedores

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

## 💻 Sobre o Projeto
- Um sistema de gerenciamento de estoque desenvolvido em Java, focado na persistência de dados de Produtos, Fornecedores e Categorias. O projeto utiliza boas práticas de engenharia de software, incluindo arquitetura **MVC**, padrão **DAO** e segurança de credenciais com variáveis de ambiente.
- Este projeto foi desenvolvido inicialmente como parte do portfólio acadêmico do 2º período de Sistemas de Informação. O objetivo principal foi criar um CRUD completo (Create, Read, Update, Delete) sem o uso de frameworks, para solidificar o entendimento sobre Orientação a Objetos, Estruturas de Dados e a Java Streams API.

## 🚀 Funcionalidades

- **CRUD Completo**: Criação, Leitura, Atualização e Exclusão de:
    - Produtos
    - Fornecedores
    - Categorias
- **Relacionamentos**: Associação de produtos a fornecedores e categorias específicas.
- **Persistência**: Conexão robusta com banco de dados MySQL.
- **Segurança**: Credenciais de banco de dados protegidas via `dotenv` (não expostas no código fonte).

## 🛠️ Tecnologias e Arquitetura

O projeto foi refatorado para seguir padrões de mercado:

- **Linguagem**: Java 17+
- **Gerenciador de Dependências**: Apache Maven
- **Banco de Dados**: MySQL 8.0
- **Segurança**: Dotenv-java (para gestão de variáveis de ambiente)
- **Padrões de Projeto**:
    - **MVC (Model-View-Controller)**: Separação clara entre interface, lógica de controle e dados.
    - **DAO (Data Access Object)**: Camada de abstração para operações no banco de dados.
    - **Singleton**: Garantia de instância única para a conexão com o banco.

## ⚙️ Pré-requisitos

Antes de começar, você precisará ter instalado em sua máquina:
- [Java JDK 17+](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/)
- [MySQL Server](https://dev.mysql.com/downloads/installer/)
- Uma IDE (IntelliJ IDEA, Eclipse ou VS Code)

## 📝 Como rodar o projeto

### 1. Clone o repositório
```bash
git clone [https://github.com/Rosendoxx/Sistema-de-Produtos-e-Fornecedores.git](https://github.com/Rosendoxx/Sistema-de-Produtos-e-Fornecedores.git)
cd Sistema-de-Produtos-e-Fornecedores
```

### 2. Configure o banco de dados
Execute os scripts sql abaixo na seguinte ordem:
- *init.sql*
- *insert.sql*

### 3. Configure as variáveis de ambiente
Na raiz do projeto, crie o arquivo .env e adiciona as credenciais do SQL:
```bash
DB_URL=jdbc:mysql://localhost:3306/GerenciadorEstoque
DB_USER=root
DB_PASSWORD=sua_Senha_Do_MySQL
```

### 4. Execute o projeto
- Se estiver usando uma IDE, espere o Maven baixar as depedências e execute a classe Main.java
- Se for usar o terminal:
```bash
mvn clean install
mvn exec:java -Dexec.mainClass="view.Main"
```