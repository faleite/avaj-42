# Padrões de Projeto (Gang of Four)

🎓 *Padrões e Arquitetura Aplicados no Projeto*

### 🔵 Creational Patterns 
*Como objetos são criados* \
[Video](https://youtu.be/x9h8MgAvi_I?si=mBDIdhGlsnMUIH3F)
## Singleton — *GoF, cap. 3* 
Usado em: `WeatherProvider`, `AircraftFactory`, `Logger`, `Parser`

```
Intenção: garantir que uma classe tenha apenas uma instância
e fornecer um ponto global de acesso a ela.
```

Você aplicou o padrão clássico em todos:
```java
private static WeatherProvider instance;
private WeatherProvider() {}
public static WeatherProvider getInstance() { ... }
```

---

**Factory Method** — *GoF, cap. 3*
Usado em: `AircraftFactory.newAircraft()`

```
Intenção: definir uma interface para criar objetos, mas deixar
as subclasses decidirem qual classe instanciar.
```

```java
// O cliente não sabe qual classe concreta está sendo criada
Flyable aircraft = AircraftFactory.getInstance()
    .newAircraft("Balloon", "B1", coordinates);
```

O `Simulator` nunca faz `new Balloon()` diretamente — sempre delega para a factory.

---

### 🟢 Behavioral Patterns — Como objetos se comunicam

**Observer** — *GoF, cap. 5*
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

---

## 2. Princípios de Arquitetura Aplicados

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