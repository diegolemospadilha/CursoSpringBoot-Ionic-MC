Este projeto está sendo construído com base no curso [Spring Boot, Hibernate, REST, Ionic, JWT, S3, MySQL, MongoDB](https://www.udemy.com/course-dashboard-redirect/?course_id=1360966) ofertado pelo professor [Nelio Alves](https://github.com/acenelio) na Udemy.

### Getting Started

Para executar o projeto, será necessário instalar os seguintes programas:

- [JDK 8 ou superior: Necessário para executar o projeto Java](http://www.oracle.com/technetwork/java/javase/downloads/jdk10-downloads-4416644.html)
- [Maven 3.5.3: Necessário para realizar o build do projeto Java](http://mirror.nbtelecom.com.br/apache/maven/maven-3/3.5.3/binaries/apache-maven-3.5.3-bin.zip)
- [Eclipse: Para desenvolvimento do projeto](https://www.eclipse.org/downloads/packages/release/helios/sr1/eclipse-ide-java-ee-developers)
- [Postman: Utilizado para simulação das requisições](https://www.postman.com/downloads/)

### Instalação
 Para instalação deste projeto em sua máquina, você deve clonà-lo utilizando um repositório de sua preferência
 
 ```shell
cd "diretorio de sua preferencia"
git clone https://github.com/diegolemospadilha/CursoSpringBoot-Ionic-MC.git
```
Se você já possui uma IDE Eclipse instalada, recomendo a instalação do plugin do STS pelo Eclipse Marketing Place( Help > Eclipse Marketplace). Caso não tenha uma IDE, utilize faça o download do [STS](https://spring.io/tools) ( IDE baseada no Eclipse para desenvolvimento de aplicações Spring).

Após isso faça a importação do projeto *(File > Import Spring Getting Start Content)* , apontando como diretório de referência o local onde você clonou o projeto.

### Rodando o projeto
- Primeiramente, faça o download das dependências do projeto, clicando com o botão direito *(Maven > Update Project > Selecione o projeto e clique em OK)*.
- Depois clique com o botão direito do Mouse, selecione Run As> Java Application ou Spring Boot App).
- O seu projeto estará disponível na porta 8080.
- Para ver as requisições disponiveis atualmente acesse a pasta do projeto postman/collections e importe o arquivo contido dentro deste diretório em seu Postman *(Aba Collections > Import)*.
- Será criada uma nova collection para você chamada **curso-spring-ionic**, na qual conterá todos os endpoints criados até o momento.


