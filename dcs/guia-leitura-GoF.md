# 📚 Roteiro de Estudo no Livro GoF

O livro se chama **"Design Patterns: Elements of Reusable Object-Oriented Software"** — Gamma, Helm, Johnson, Vlissides (1994).

---

## Resources
[Videos Padrões de Projeto](https://youtube.com/playlist?list=PLbIBj8vQhvm0VY5YrMrafWaQY2EnJ3j8H&si=WpOqXhQ9Gu-kSnkW)

## Antes dos Padrões — Leia Primeiro

### Capítulo 1 — Introdução
Este é o capítulo mais importante do livro. Não pule.

Os conceitos que você já praticou e vai reconhecer:

**"Program to an interface, not an implementation"**
Você fez isso com `Flyable` — `Tower` nunca conhece `Balloon` diretamente.

**"Favor object composition over class inheritance"**
Você viu isso na relação `Tower` ↔ `Flyable` — a torre *tem* uma lista de observers em vez de *ser* um observer.

**"Design for change"**
O subject pede exatamente isso — *"he knows this will not be the final version"*. Os padrões que você usou permitem adicionar novos tipos de aeronave sem modificar `Tower` ou `AircraftFactory`.

---

## Os 3 Padrões que Você Implementou

### Capítulo 3 — Singleton
Página 127 do livro.

O que estudar além do que você já fez:

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

### Capítulo 3 — Factory Method
Página 107 do livro.

O que estudar além do que você já fez:

**Factory Method vs Abstract Factory** — você implementou Factory Method. Abstract Factory é o próximo nível:

```
Factory Method:
    AircraftFactory.newAircraft("Balloon", ...)  ← um produto

Abstract Factory:
    SimulatorFactory.createAircraft()
    SimulatorFactory.createWeather()             ← família de produtos
    SimulatorFactory.createTower()
```

O livro mostra quando cada um se aplica — vale a leitura comparativa.

---

### Capítulo 5 — Observer
Página 293 do livro. Este é o mais rico para estudar.

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

## Próximos Padrões Recomendados

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