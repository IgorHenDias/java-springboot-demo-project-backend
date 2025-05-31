
# Projeto: API de Gerenciamento de Usuários

## Descrição
Esta é uma API REST desenvolvida com **Java** e **Spring Boot**. Ela permite realizar operações básicas de CRUD (Create, Read, Update, Delete) para gerenciamento de usuários, utilizando boas práticas como SOLID, DTOs e validações.

## Funcionalidades

- ✅ Criar um novo usuário  
- 🔍 Listar todos os usuários ativos  
- 🔍 Buscar usuário por ID  
- ✏️ Atualizar dados de um usuário  
- ❌ Desabilitar um usuário (soft delete)

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (banco de dados em memória)
- Maven
- Lombok (para reduzir boilerplate)
- DTO + Mapper
- Validações personalizadas

## Pré-requisitos

Certifique-se de ter instalado:

- [Java JDK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)  
- [Maven](https://maven.apache.org/)  
- IDE recomendada: IntelliJ IDEA ou VS Code

## Como Rodar o Projeto

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/sua-api.git

# Entre na pasta do projeto
cd sua-api

# Execute o projeto com Maven
./mvnw spring-boot:run
```

## Endpoints da API

> A aplicação estará rodando em: `http://localhost:8080`

### 1. Criar um usuário
```http
POST /users
```
**Body (JSON):**
```json
{
  "name": "João",
  "email": "joao@email.com"
}
```

### 2. Listar todos os usuários
```http
GET /users
```

### 3. Buscar usuário por ID
```http
GET /users/{id}
```

### 4. Atualizar usuário
```http
PUT /users/{id}
```
**Body (JSON):**
```json
{
  "name": "João da Silva",
  "email": "joao.silva@email.com"
}
```

### 5. Desabilitar (deletar logicamente) um usuário
```http
DELETE /users/{id}
```

## Estrutura do Projeto

```
src/
├── controller         # Camada de entrada (ex: UsersController)
├── service            # Regras de negócio (ex: UsersServiceImpl)
├── repository         # Comunicação com o banco de dados
├── dto                # Objetos usados para transferir dados
├── entity             # Modelos de banco de dados
├── util               # Validadores e mapeadores auxiliares
└── exception          # Classes para tratamento de erros
```

## Banco de Dados

- Utiliza **H2 Database**, um banco em memória.
- Para acessar o console H2:
```
http://localhost:8080/h2-console
```
**Configurações:**
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Senha: (deixe em branco)

## Autor

Igor Henrique Dias  
[LinkedIn](https://www.linkedin.com/in/igorhdias/)

## Licença

Este projeto está sob a licença MIT.

---

## 🧪 Exemplo Comentado do Projeto

Este projeto Spring Boot possui uma estrutura clara e simples para gerenciamento de usuários. Abaixo, explicamos o funcionamento das principais classes com comentários.

### ✅ Classe: UsersController

```java
@RestController // Indica que essa classe lida com requisições HTTP e retorna JSON
@RequestMapping("/users") // Define o prefixo das rotas da API
public class UsersController {

    private final UsersService usersService;

    // Injeção do serviço através do construtor (boa prática de Spring)
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping // Rota POST para criar novo usuário
    public String addUser(@RequestBody UsersDTO usersDTO) {
        return usersService.addUser(usersDTO); // Envia os dados para o serviço salvar
    }

    @GetMapping // Rota GET para listar todos os usuários ativos
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}") // GET para buscar usuário por ID
    public Users getUserById(@PathVariable Long id) {
        return usersService.getUserById(id);
    }

    @PutMapping("/{id}") // PUT para atualizar dados do usuário
    public String updateUser(@PathVariable Long id, @RequestBody UsersDTO usersDTO) {
        return usersService.updateUser(id, usersDTO);
    }

    @DeleteMapping("/{id}") // DELETE para desabilitar (soft delete) o usuário
    public String disableUser(@PathVariable Long id) {
        return usersService.disableUser(id);
    }
}
```

### 🧱 Classe: Users (entidade JPA)

```java
@Entity // Indica que é uma entidade JPA (tabela no banco)
@Table(name = "users") // Define o nome da tabela
public class Users {

    @Id // Chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento
    private Long id;

    @Column(name = "name", nullable = false) // Coluna "name" obrigatória
    private String name;

    @Column(name = "email", nullable = false, unique = true) // Coluna "email", única e obrigatória
    private String email;

    @Column(nullable = false) // Campo que indica se o usuário está ativo
    private boolean enabled = true;

    // Construtores, getters e setters omitidos para brevidade
}
```

### 📦 Classe: UsersDTO (Data Transfer Object)

```java
@Getter @Setter // Lombok gera os métodos get/set
@AllArgsConstructor // Lombok gera construtor com todos os campos
@NoArgsConstructor  // Lombok gera construtor sem parâmetros
public class UsersDTO {
    private String name;
    private String email;
}
```

Esse DTO é usado para **receber dados** da requisição, evitando expor diretamente a entidade do banco.

---

Esses exemplos mostram como Spring Boot facilita a criação de APIs organizadas e reutilizáveis, separando claramente as responsabilidades.
