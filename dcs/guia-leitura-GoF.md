# 📚 Roteiro de Estudo no Livro GoF

O livro se chama **"Design Patterns: Elements of Reusable Object-Oriented Software"** — Gamma, Helm, Johnson, Vlissides (1994).

---

## Resources
[Videos Padrões de Projeto](https://youtube.com/playlist?list=PLbIBj8vQhvm0VY5YrMrafWaQY2EnJ3j8H&si=WpOqXhQ9Gu-kSnkW)

## Antes dos Padrões — Leia Primeiro

### Capítulo 1 — Introdução
Este é o capítulo mais importante do livro. Não pule.

Os conceitos que você já praticou e vai reconhecer:

**"Program to an interface, not an implementation"** *(Programar para uma interface, não uma implementação.)* \
Você fez isso com `Flyable` — `Tower` nunca conhece `Balloon` diretamente.

**"Favor object composition over class inheritance"**. *(Dê preferência à composição de objetos em vez da herança de classes.)* \
Você viu isso na relação `Tower` ↔ `Flyable` — a torre *tem* uma lista de observers em vez de *ser* um observer.

**"Design for change"** *(Projetar para a mudança)* \
O subject pede exatamente isso — *"he knows this will not be the final version"*. Os padrões que você usou permitem adicionar novos tipos de aeronave sem modificar `Tower` ou `AircraftFactory`.

---

## Os 3 Padrões que foram implementados

## Singleton
Capítulo 3 — Página 127 do livro.\
*🔵 Creational Patterns*\
*Como objetos são criados*

Usado em: `WeatherProvider`, `AircraftFactory`, `Logger`, `Parser`\
[A teoria em video](https://youtu.be/x9h8MgAvi_I?si=mBDIdhGlsnMUIH3F)

```
Intenção: garantir que uma classe tenha apenas uma instância
e fornecer um ponto global de acesso a ela.
```

> Muitos padrões podem ser implementados usando Singleton. Ver Abstract Factory (95),
Builder (104) e Prototype (121).

Você aplicou o padrão clássico em todos:
```java
private static WeatherProvider instance;
private WeatherProvider() {}
public static WeatherProvider getInstance() { ... }
```

**Problema do Singleton em ambiente multithread** — seu `getInstance()` atual não é thread-safe:
```java
// ❌ Duas threads podem criar duas instâncias simultaneamente
if (instance == null) {
    instance = new WeatherProvider(); // race condition aqui
}

// ✅ Double-checked locking — GoF discute esta variação
private static volatile WeatherProvider instance;

public static WeatherProvider getInstance() {
    if (instance == null) {
        synchronized (WeatherProvider.class) {
            if (instance == null) {
                instance = new WeatherProvider();
            }
        }
    }
    return instance;
}
```

**Quando NÃO usar Singleton** — o livro discute as desvantagens. Singleton dificulta testes unitários porque cria estado global.

---

## Factory Method
Capítulo 3 — Página 107 do livro.

Usado em: `AircraftFactory.newAircraft()`\
[A teoria em video](https://youtu.be/1rB0PhvAwiU?si=jFWmjDP8kxKOMa1K)

```
Intenção: definir uma interface para criar objetos, mas deixar
as subclasses decidirem qual classe instanciar. 
O Factory Method permite adiar a instanciação para as subclasses.
Também conhecido como: `Virtual Constructor`
```

```java
// O cliente não sabe qual classe concreta está sendo criada
Flyable aircraft = AircraftFactory.getInstance()
    .newAircraft("Balloon", "B1", coordinates);
```

```java
public class Creator {

    // Em Java, métodos não estáticos são virtuais por padrão
    public Product create(ProductId id) {
        if (id == ProductId.MINE) {
            return new MyProduct();
        }
        
        if (id == ProductId.YOURS) {
            return new YourProduct();
        }
        
        // Repetir para os demais produtos...

        return null; // Equivalente ao 'return 0' em C++ para ponteiros
    }
}
```

O `Simulator` nunca faz `new Balloon()` diretamente — sempre delega para a factory.

**Factory Method vs Abstract Factory** — você implementou Factory Method. Abstract Factory é o próximo nível:

```java
Factory Method:
    AircraftFactory.newAircraft("Balloon", ...)  ← um produto

Abstract Factory:
    SimulatorFactory.createAircraft()
    SimulatorFactory.createWeather()             ← família de produtos
    SimulatorFactory.createTower()
```

O livro mostra quando cada um se aplica — vale a leitura comparativa.

---
### 🟢 Behavioral Patterns — Como objetos se comunicam
## Observer
Capítulo 5 — Página 269 do livro.\
Este é o mais rico para estudar.

Usado em: `Tower`, `WeatherTower`, `Flyable`

```
Intenção: definir uma dependência um-para-muitos entre objetos,
de modo que quando um objeto muda de estado, todos os seus
dependentes são notificados automaticamente.
```

O fluxo completo no seu projeto:

```
WeatherTower.changeWeather()     ← Subject muda estado
    → Tower.conditionChanged()   ← notifica observers
        → Flyable.updateConditions()  ← cada Observer reage
```

O que estudar além do que você já fez:

**Push vs Pull model** — seu projeto usa Push (a torre notifica e cada aeronave busca o clima):
```java
// Push — tower empurra a notificação
flyable.updateConditions();

// Pull — observer recebe o subject e puxa o que precisa
flyable.updateConditions(weatherTower);
```

**O problema de consistência** — o livro discute o que acontece se um observer modifica o subject durante a notificação. Você resolveu isso intuitivamente com a cópia da lista:
```java
// Você já fez a solução correta!
for (Flyable flyable : new ArrayList<>(observers)) {
    flyable.updateConditions();
}
```

O livro explica formalmente por que isso é necessário.

---

## Princípios de Arquitetura Aplicados

### Single Responsibility Principle (SRP)
Cada classe tem exatamente um motivo para mudar:

| Classe | Responsabilidade única |
|---|---|
| `Coordinates` | Representar uma posição 3D |
| `WeatherProvider` | Gerar clima por coordenada |
| `AircraftFactory` | Criar aeronaves |
| `Parser` | Interpretar o arquivo de cenário |
| `Logger` | Gravar mensagens em arquivo |
| `Simulation` | Orquestrar o fluxo da simulação |
| `Simulator` | Ponto de entrada da aplicação |

---

### Separação em Camadas
Sua estrutura de pacotes reflete camadas bem definidas:

```
simulator/          ← orquestração (Simulation, Simulator)
├── model/          ← domínio (entidades e contratos)
├── tower/          ← infraestrutura de notificação
├── factory/        ← criação de objetos
├── provider/       ← fonte de dados
├── util/           ← utilitários transversais
└── exceptions/     ← contratos de erro
```

---

### Program to interfaces, not implementations
*Princípio fundamental do GoF, introdução*

`Tower` nunca conhece `Balloon`, `JetPlane` ou `Helicopter` — só conhece `Flyable`:

```java
// Tower.java
private List<Flyable> observers;  // interface, não classe concreta
```

Isso permite adicionar um novo tipo de aeronave sem tocar em `Tower`.

---

## 3. O que você construiu na prática

```
scenario.txt
     ↓
Simulator.main()          ← ponto de entrada
     ↓
Simulation.run()          ← orquestração
     ↓
Parser                    ← leitura e validação
     ↓
AircraftFactory           ← Factory Pattern
     ↓
WeatherTower              ← Observer Pattern (Subject)
     ↓
Balloon/JetPlane/         ← Observer Pattern (Observers)
Helicopter
     ↓
WeatherProvider           ← Singleton Pattern
     ↓
Logger                    ← Singleton Pattern
     ↓
simulation.txt
```

---

## 🏆 Conclusão

Você implementou **3 dos 23 padrões GoF** em um projeto coeso, compilável via terminal, sem frameworks, com arquitetura em camadas e princípios SOLID aplicados. Exatamente o que o subject pede.

Se quiser se aprofundar, o livro do GoF apresenta esses padrões nesta ordem de estudo recomendada para iniciantes: **Singleton → Factory Method → Observer → Strategy → Decorator**. Os dois próximos seriam naturais evoluções deste projeto. 🚀

---

## O que os Padrões Extras te Dariam
Não para o subject — mas para o seu crescimento:
```txt
Template Method → eliminaria duplicação em updateConditions()
Strategy       → tornaria WeatherProvider extensível
Decorator      → permitiria compor comportamentos em runtime
```

# Próximos Padrões Recomendados

Depois de consolidar os três que você usou, estes são os mais naturais para evoluir o projeto:

### Capítulo 4 — Decorator
Página 175 do livro.

Pergunta para motivar o estudo: *"E se você quisesse adicionar comportamento a uma aeronave em runtime sem criar subclasses?"*

```java
// Sem Decorator — você criaria subclasses para tudo
class LoggedBalloon extends Balloon { ... }
class TrackedBalloon extends Balloon { ... }
class LoggedTrackedBalloon extends Balloon { ... } // explosão de classes!

// Com Decorator — composição em runtime
Flyable balloon = new TrackedAircraft(new LoggedAircraft(new Balloon(...)));
```

---

### Capítulo 5 — Strategy
Página 315 do livro.

Pergunta para motivar: *"E se o algoritmo de clima pudesse ser trocado sem modificar `WeatherProvider`?"*

```java
// Sem Strategy
public String getCurrentWeather(Coordinates c) {
    // algoritmo fixo, hardcoded
    int index = Math.abs(c.getLongitude() + ...) % 4;
    return weatherTypes[index];
}

// Com Strategy — algoritmo é injetado
public class WeatherProvider {
    private WeatherStrategy strategy;

    public WeatherProvider(WeatherStrategy strategy) {
        this.strategy = strategy;
    }
}
```

---

### Capítulo 5 — Template Method
Página 325 do livro.

Este é o mais próximo do que você já fez. Olha `updateConditions()` nas três aeronaves — a estrutura é idêntica, só os deltas mudam:

```java
// Balloon, JetPlane e Helicopter têm este template:
public void updateConditions() {
    String weather = weatherTower.getWeather(coordinates); // igual nos 3
    switch (weather) { ... }                               // diferente nos 3
    if (coordinates.getHeight() == 0) { landing(); }      // igual nos 3
}
```

Template Method resolve exatamente essa duplicação — o esqueleto fica na superclasse, os detalhes nas subclasses.

---

## Ordem de Leitura Recomendada

```
1. Capítulo 1 inteiro          ← fundamentos, leia com calma
2. Capítulo 3 — Singleton      ← você já implementou, vai reconhecer tudo
3. Capítulo 3 — Factory Method ← idem
4. Capítulo 5 — Observer       ← o mais rico, leia com atenção
5. Capítulo 5 — Template Method← evolução natural do seu projeto
6. Capítulo 4 — Decorator      ← expande o modelo de aeronaves
7. Capítulo 5 — Strategy       ← evolução do WeatherProvider
```

---

## Uma Dica Final

O livro GoF é denso — cada padrão tem seções de *Motivação*, *Estrutura*, *Participantes*, *Consequências* e *Implementação*. Para cada padrão que você ler, faça esta pergunta:

> *"Onde no meu projeto eu aplicaria este padrão diferente do que fiz, ou onde ele resolveria um problema que ainda tenho?"*

Isso transforma leitura passiva em aprendizado ativo. 🚀