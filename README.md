# Clean Architecture (Quarkus e ArchUnit)

**Domain:**. Onde definimos nossos contratos de casos de uso e as entidades do sistema.

**Data:**. Contem as implementações dos contratos de casos de uso do sistema.

**Infra:**. Implementações de adapters para frameworks e bibliotecas externas, é realizada a integração da aplicação com o Hibernate Panache ORM e com o BCrypt.

**Presentation:**. A porta de entrada da nossa aplicação, em que é disponibilizado nossos recursos em endpoints HTTP.

**Main:**. Aqui temos todos os componentes fornecidos pelas camadas de Infra e Data fornecendo uma instância denominada Service para a camada de Presentation realizar a operação detalhada pelo caso de uso em questão para este caso.


# Fluxo

