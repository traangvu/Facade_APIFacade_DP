~~# API Facade Example - Simplifying API Access in Java

## 🌟 Overview

This project demonstrates the **Facade Design Pattern** by simplifying access to JSON-based APIs.

Instead of manually opening connections, reading HTTP responses, and parsing JSON, users can now access API data with one clear and reusable method:

```java
String value = apiFacade.getAttributeValueFromJson("https://api.example.com", "rates.USD");

🎯 What is the Facade Pattern?
The Facade Pattern is a structural design pattern that provides a simplified interface to a larger, often more complex, body of code (such as a set of APIs or subsystems).

✳️ Core Idea:
"Hide the complexity of a system by providing a unified, easy-to-use interface."

Instead of exposing every underlying detail (e.g., HTTP connections, JSON parsing), the facade wraps them into one convenient access point.

💡 Facade in This Project
✅ Problem:
Using an external API (e.g., Chuck Norris jokes or foreign exchange rates) involved:

Opening an HTTP connection

Handling I/O

Reading buffered data

Parsing JSON

Extracting the correct value

Managing exceptions

✅ Solution (with Facade):
The ApiFacade class encapsulates all of this. Clients only need to provide:

A URL string

A JSON attribute path (e.g., "value" or "rates.USD")

They receive the desired value as a String, with robust error handling and no need to touch the low-level logic.

ApiFacade apiFacade = new ApiFacade();

// Get Chuck Norris joke
String joke = apiFacade.getAttributeValueFromJson("https://api.chucknorris.io/jokes/random", "value");

// Get EUR to USD exchange rate
String usdRate = apiFacade.getAttributeValueFromJson("https://open.er-api.com/v6/latest/EUR", "rates.USD");
🔄 Comparison with Other Design Patterns
Pattern	Key Concept	How It Differs from Facade
Adapter	Converts one interface to another	Facade doesn’t change interfaces, it simplifies them
Proxy	Controls access (e.g., lazy loading, caching)	Facade simplifies usage, not control access
Decorator	Adds new behavior dynamically	Facade doesn’t modify behavior, it hides complexity
Mediator	Manages communication between classes	Facade exposes a shortcut, not coordination logic
Builder	Constructs complex objects step-by-step	Facade doesn’t build objects — it offers easy access
Factory	Creates instances without exposing creation logic	Facade operates on existing systems, not on creation
Singleton	Restricts a class to a single instance	Facade can use Singleton, but its goal is interface simplification
Command	Encapsulates a request as an object	Facade isn’t about action encapsulation
Strategy	Swappable algorithms or behavior	Facade isn’t configurable; it hides internal complexity
Observer	Event-driven updates	Facade isn’t reactive — it’s about synchronous simplicity

✅ When to Use Facade
When a subsystem is complex or confusing

When there is need for the simple common usage scenarios

When the client code decoupling from specific libraries or APIs is needed

To promote readability, testability, and reusability

📘 Resources
Facade Pattern - Refactoring Guru