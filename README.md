# VUTTR(Very Useful Tools to Remember) API

![GitHub repo size](https://img.shields.io/github/repo-size/cristian-95/vuttr-api?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/cristian-95/vuttr-api?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/cristian-95/vuttr-api?style=for-the-badge)

> Uma API RESTful que permite ao usuário ler, registrar, atualizar e deletar ferramentas de um banco de dados.

---
- ### (Trabalho em progresso...)

- [Link da proposta original desse desáfio](https://bossabox.notion.site/Dev-Back-End-04cfd92927a045f6914ab1e2c9002c02)
---

## Como Executar

Para executar este projeto, você precisará do JDK 17 ou mais recente e do Maven instalados em sua máquina.

1. **Clonar o repositório:**
   ```bash
   git clone https://github.com/cristian-95/vuttr-api.git
   ```

2. **Navegar para o diretório do projeto:**
   ```bash
   cd vuttr
   ```

3. **Compilar e empacotar o aplicativo:**
   ```bash
   mvn clean package
   ```

4. **Executar o aplicativo:**
   ```bash
   java -jar target/vuttr-0.0.1-SNAPSHOT.jar
   ```

   Isso iniciará a aplicação Spring Boot. Certifique-se de que o PostgreSQL esteja em execução e configurado
   corretamente.

5. **Acessar a aplicação:**

   Uma vez que a aplicação esteja em execução, você pode acessá-la em `http://localhost:8080` no seu navegador.

**OBS**: Certifique-se de criar um banco de dados e preencher o arquivo de configuração application.yaml corretamente
para poder se conectar ao banco de dados PostgreSQL.

 



