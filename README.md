# 📦 Sistema de Gerenciamento de Estoque (CRUD Java)

> Um sistema robusto de gerenciamento de produtos e fornecedores desenvolvido em Java Puro (Vanilla Java), focado em boas práticas de programação e manipulação eficiente de dados.

![Badge em Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Badge Status](https://img.shields.io/badge/Status-Concluído-green?style=for-the-badge)

## 💻 Sobre o Projeto

Este projeto foi desenvolvido como parte do portfólio acadêmico do 2º período de Sistemas de Informação. O objetivo principal foi criar um **CRUD completo (Create, Read, Update, Delete)** sem o uso de frameworks, para solidificar o entendimento sobre **Orientação a Objetos**, **Estruturas de Dados** e a **Java Streams API**.

Diferente de CRUDS básicos, este sistema implementa conceitos reais de mercado, como **Soft Delete** (exclusão lógica) e buscas otimizadas utilizando **HashMaps**.

## ✨ Funcionalidades Principais

* **Gerenciamento de Produtos:** Cadastro, edição e listagem com validação de dados.
* **Gerenciamento de Fornecedores:** Associação direta entre produtos e fornecedores.
* **Categorização:** Uso de Enums para controle rígido de categorias.
* **Relatórios Inteligentes:** Listagem filtrada e ordenada utilizando **Java Streams**:
    * Ordenar por Preço, Nome ou Categoria.
    * Filtrar produtos de um fornecedor específico.
* **Exclusão Lógica (Soft Delete):** Registros são desativados em vez de apagados, mantendo a integridade do histórico.
* **Tratamento de Erros:** Sistema robusto contra entradas inválidas (ex: digitar letras em campos numéricos) utilizando `try-catch` e loops de validação.

## 🛠️ Tecnologias e Conceitos Aplicados

* **Java 17+** (Linguagem principal)
* **Java Streams API & Lambdas** (Para filtros e ordenação funcionais)
* **Collections Framework** (`HashMap` para acesso O(1) e `ArrayList`)
* **POO Avançada** (Polimorfismo, Sobrescrita, Encapsulamento)
* **Clean Code** (Nomes significativos, Single Responsibility Principle)
* **Tratamento de Exceções** (`InputMismatchException`)

## 🧠 Destaques Técnicos

### 1. Uso de Streams para Ordenação
Em vez de usar laços `for` aninhados complexos, utilizei a API de Streams para criar consultas declarativas e limpas:

```java
// Exemplo real do código: Ordenando fornecedores por nome
fornecedores.entrySet()
    .stream()
    .sorted((f1, f2) -> f1.getValue().getNome().compareToIgnoreCase(f2.getValue().getNome()))
    .forEach(fornecedor -> System.out.println(fornecedor));

```

### 2. Validação de Entrada Robusta

Implementação de um mecanismo de leitura seguro que impede o programa de travar (crash) caso o usuário digite um input inesperado:

```public static int lerInt() {
    while(true) {
        try {
            return in.nextInt(); 
        } catch(InputMismatchException e) {
            in.nextLine(); // Limpa o buffer
            System.out.println("Erro: Informe apenas números inteiros.");
        }
    }
}
```

### 3. Como Rodar o projeto

**Pré-Requitos:**
    * *Java JDK11 ou superior*

**1º Clone o repositório**
    git clone https://github.com/Rosendoxx/Sistema-de-Produtos-e-Fornecedores.git

**2º Compile os arquivos**
    javac -d bin Sistema-de-Produtos-e-Fornecedores/programa*.java Sistema-de-Produtos-e-Fornecedores/modelo*.java

**3º Execute o programa**
    java -cp bin programa.Principal

### 4. Melhorias futuras:
* **Implementar persitência de dados**
* **Criar uma interface gráfica simples**

## Desenvolvido por Leandro T. Rosendo
