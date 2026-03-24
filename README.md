## Resources
- Leitura Cap 03
- [Video Tutorial](https://www.youtube.com/watch?v=6XrL5jXmTwM)
- [uml online editor](https://plantuml.com/)

## About thew project
implement an aircraft simulation program based on the class diagram provided to you.

## KEYWORDS
keywords | brief |
---------| ----- |
Observer | - |
Singleton| - |
Factory design patterns | - |
UML class diagram | - |
object-oriented design patterns | - |


- Compile o projeto executando os comandos abaixo na raiz da pasta do seu projeto.

```bash
$ find * -name "*.java" > sources.txt
$ javac @sources.txt
```

## UML diagram

### Diagrama
Nome da classe | <--
--|--
Atributos | <--
Métodos | <--

### Visibilidades
- (-) private
- (+) public
- (#) protected
- (~) package/default

###  Configuração visual do PlantUML

#### `skinparam classAttributeIconSize 0`
- Por padrão, o PlantUML exibe ícones coloridos ao lado dos atributos e métodos das classes para indicar a visibilidade
- `skinparam classAttributeIconSize 0` desativa esses ícones, definindo o tamanho deles como zero.
- Mantendo os símbolos de texto (`+`, `-`, `#`, `~`), deixando o diagrama mais limpo e compacto.

#### `hide empty members`
- Faz com que as seções vazias sejam omitidas, deixando o diagrama mais limpo e compacto.
- Por padrão, o PlantUML renderiza três seções em cada classe — atributos, métodos e uma terceira opcional. 
  - Se uma dessas seções não tiver nada, ele ainda desenha o espaço vazio, deixando o diagrama com "caixas em branco" desnecessárias.


### Notações de relacionamento entre as classes

**`<|--` — Herança (extensão)**
```
Tower <|-- WeatherTower
```
`WeatherTower` herda de `Tower`. A seta aponta para a classe pai. É o "é um" — WeatherTower *é uma* Tower.

---

**`-o` — Agregação**
```
Tower -o Flyable
```
`Tower` agrega `Flyable`. O `o` representa o losango vazio do UML. É o "tem um, mas não depende" — Tower mantém uma lista de Flyables, mas eles existem independentemente dela.

---

**`-*` — Composição**
```
Coordinates -* Aircraft
```
`Aircraft` é composto por `Coordinates`. O `*` representa o losango cheio do UML. É o "tem um e controla o ciclo de vida" — se o Aircraft for destruído, as Coordinates também são.

---

**`<|..` — Realização (implementação de interface)**
```
Flyable <|.. Aircraft
```
`Aircraft` implementa/realiza `Flyable`. A linha pontilhada indica que é uma interface ou classe abstrata sendo implementada, não uma herança concreta.

---

**`-[hidden]-` — Relacionamento invisível (apenas para layout)**
```
JetPlane -[hidden]- AircraftFactory
WeatherTower -[hidden]- WeatherProvider
```
Esse é o único que **não tem significado UML**. É um truque do PlantUML para forçar o posicionamento visual — faz o motor de layout colocar as duas classes próximas ou numa mesma linha, sem desenhar nenhuma seta ou linha no diagrama final.

---

**`<<Singleton>>` — Estereótipo**
```
class AircraftFactory <<Singleton>>
```
Os `<<` `>>` definem um estereótipo UML — uma etiqueta que classifica a classe dentro de um padrão ou papel. Não altera a estrutura, apenas adiciona contexto semântico visível no diagrama.

## Subject
**Você pode utilizar as bibliotecas internas (padrão) do Java**, mas não pode usar bibliotecas externas.

Para esclarecer a diferença entre as regras presentes nas instruções gerais:

*   **"Do not use the default package" (Não use o pacote padrão):** Esta regra refere-se exclusivamente à **organização do seu próprio código**. Ela exige que você declare um `package` no topo dos seus arquivos `.java`, em vez de deixar as classes sem pacote.
*   **Bibliotecas Internas vs. Externas:**
    *   **Permitido:** Você pode usar todos os recursos e funcionalidades principais (core features) da linguagem Java até a versão LTS mais recente. Isso inclui bibliotecas que já vêm com o Java (como `java.util.*`, `java.io.*`, etc.).
    *   **Proibido:** Você **não tem permissão** para usar **bibliotecas externas** (como frameworks de terceiros, bibliotecas baixadas do Maven Central, etc.), ferramentas de build (como Maven ou Gradle) ou geradores de código.

Portanto, o uso das ferramentas padrão que já fazem parte do Java Development Kit (JDK) é esperado, desde que o seu próprio código esteja devidamente organizado em pacotes relevantes criados por você.

## Livro
Em resumo, foque nos capítulos dos padrões Observer, Singleton e Factory
estude a notação no Apêndice B e utilize os Capítulos 1 e 2 como fundamentação teórica 
para garantir que o design do seu simulador seja "limpo, fácil de ler e fácil de mudar" conforme exigido pelo projeto

### Padrões mais comuns
*Se você não é um projetista com experiência em software orientado a objetos,
comece com os padrões mais simples e mais comuns:*
- Abstract Factory (pág. 95)
- Adapter (140)
- Composite (160)
- Decorator (170)
- Factory Method (112)
- Observer (274)
- Strategy (292)
- Template Method (301)
