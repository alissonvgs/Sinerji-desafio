## Desafio Sinerji

Desafio Backend da empresa Sinerji

## Tecnologias utilizadas

![JAVA](https://img.shields.io/badge/Java_17-green.svg)
![SpringBoot](https://img.shields.io/badge/SpringBoot-green.svg)
![AWS RDS](https://img.shields.io/badge/AWS_RDS-purple.svg)
![PGSQL](https://img.shields.io/badge/PGSQL-blue.svg)
![REST API](https://img.shields.io/badge/REST_API-green.svg)

## Documentação da API

#### Um método que receba uma lista de funcionários, mês e ano e retorne o valor total pago (salário e benefício) a esses funcionários no mês

```http
GET /funcionarios/valor-total-pago?ids=1,2,3,4,5,6,7,8&mes=01&ano=2010

```

#### Um método que receba uma lista de funcionários, mês e ano e retorne o total pago somente em salários no mês.

```http
GET /funcionarios/valor-total-pago-sem-beneficio?ids=1,2,3,4,5,6&mes=01&ano=2017
```

#### Um método que receba uma lista *somente* com os funcionários que recebem benefícios, mês e ano e retorne o total pago em benefícios no mês.
```http
GET /funcionarios/valor-total-pago-somente-beneficio?ids=1,2,3,4,5,6&mes=03&ano=2021
```

#### Um método que receba uma lista de funcionários, mês e ano e retorne o que recebeu o valor mais alto no mês.
```http
GET /funcionarios/funcionario-maior-salario?ids=1,2,3,4,5,6&mes=01&ano=2021
```

#### Um método que receba uma lista somente com os funcionários que recebem benefícios, mês e ano e retorne o nome do funcionário que recebeu o valor mais alto em benefícios no mês.
```http
GET /funcionarios/funcionario-maior-beneficio?ids=1,2,3,4,5,6&mes=01&ano=2021
```

#### Um método que receba uma lista de vendedores, mês e ano e retorne o que mais vendeu no mês.
```http
GET /funcionarios/funcionario-maior-venda?ids=1,2,3,4,5,6,7,8,9,10&mes=12&ano=2021
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `api_key` | `Integer` | **Obrigatório**. Os ids dos funcionarios. |
| `api_key` | `Integer` | **Obrigatório**. O mês. |
| `api_key` | `Integer` | **Obrigatório**. O ano. |
