# Estudo

## Capítulo 3 — Singleton
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
