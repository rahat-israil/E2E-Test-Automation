# 🛒 E-Commerce End-to-End Test Automation

An end-to-end UI test automation project built with **Java** and **Selenium WebDriver** that automates the complete customer journey of an e-commerce website: **user registration → login → product selection → checkout → order confirmation**.

 **Application Under Test:** [Automation Exercise](https://www.automationexercise.com/)

---

## 📌 Table of Contents

- [Overview](#-overview)
- [Key Features](#-key-features)
- [Test Flow](#-test-flow)
- [Test Scenarios Covered](#-test-scenarios-covered)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Setup & Installation](#-setup--installation)
- [How to Run](#-how-to-run)
- [Sample Console Output](#-sample-console-output)
- [Test Data](#-test-data)
- [Design Decisions](#-design-decisions)
- [Author](#-author)

---

## 📖 Overview

This project automates the most critical business flow of an online shopping application in a single, continuous end-to-end run. A fresh user account is created on every execution, then browser closed and Starts a new browser session log in, add products to the cart, and complete a purchase with payment details.

Each stage is verified with a **PASS / FAIL assertion** so the result of every step is visible directly in the console.

The automation is divided into three main components:

1. **Registration**
   - Opens the website
   - Navigates to Signup/Login
   - Creates a new user account
   - Enters personal and address information
   - Verifies successful registration
   - Closed browser

2. **Login**
   - Starts a new browser session
   - Logs in using the credentials created during registration
   - Verifies successful login

3. **Checkout**
   - Navigates to the Products page
   - Selects two products
   - Adds both products to the cart
   - Opens the cart
   - Proceeds to checkout
   - Adds an order comment
   - Enters payment information
   - Places the order
   - Verifies the `ORDER PLACED!` confirmation
   - Closed browser

---

## ✨ Key Features

- **Complete E2E coverage** - One run covers registration, login, cart and checkout.
- **Dynamic test data** - A unique email is generated with `System.currentTimeMillis()`, so the test never fails due to a duplicate account.
- **Centralized test data** - All inputs (credentials, address, card details) live in `TestData.java`; no hard-coded values inside the test classes.
- **Ad blocking with CDP** - Ad-network URLs (`googlesyndication`, `doubleclick`, `googleadservices`, etc.) are blocked using `Network.setBlockedURLs`. This prevents ad overlays and iframes from intercepting clicks and making tests flaky.
- **Explicit waits** - `WebDriverWait` with `ExpectedConditions` (visibility / clickable) is used on dynamic elements such as modals and payment forms instead of hard sleeps.
- **Session reuse** - `Login` returns the live `WebDriver` instance, which is passed into `Checkout`, so the flow continues in the same authenticated browser session.
- **Built-in result verification** - Every module prints a clear `PASSED` / `FAILED` result.
- **Locator strategies** - XPath are used.
- **Dropdown handling** - Selenium's `Select` class is used for date of birth and country fields.

---

## 🔄 Test Flow

```
Launch Chrome + Block Ads
        │
        ▼
┌─────────────────┐
│  1. REGISTRATION│  Signup → Account info → Address → Continue → Verify logged in
└─────────────────┘
        │  (browser closed, fresh session)
        ▼
┌─────────────────┐
│  2. LOGIN       │  Enter email + password → Login → Verify logged in
└─────────────────┘
        │  (same browser session passed forward)
        ▼
┌─────────────────┐
│  3. CHECKOUT    │  Add 2 products → Cart → Checkout → Comment → Payment → Verify "ORDER PLACED!"
└─────────────────┘
        │
        ▼
   Close browser
```
---

## ✅ Test Scenarios Covered

| # | Module | Scenario | Verification |
|---|--------|----------|--------------|
| 1 | **Registration** | Sign up with a unique name & email, fill account info, date of birth and full address details | "Logged in as ..." link appears in the header after account creation |
| 2 | **Login** | Log in with the newly registered credentials | Logged-in user link is present in the header |
| 3 | **Checkout** | Add 2 products to cart → proceed to checkout → add order comment → enter card details → place order | "ORDER PLACED!" confirmation message is displayed |

---

## 🛠 Tech Stack

| Category | Tool / Technology |
|----------|-------------------|
| Language | Java |
| Automation Framework | Selenium WebDriver 4 |
| Browser | Google Chrome (ChromeDriver) |
| Browser Protocol | Chrome DevTools Protocol (CDP) |
| IDE | IntelliJ IDEA |
| Version Control | Git & GitHub |

---

## 📂 Project Structure

```
E2E Automation
├── src
│   ├── AdBlocker.java      # Blocks ad-network requests using Chrome DevTools Protocol
│   ├── Registration.java   # New user sign-up flow
│   ├── Login.java          # User login flow (returns the active driver session)
│   ├── Checkout.java       # Add to cart, payment and order verification
│   ├── TestData.java       # Centralized test data (single source of truth)
│   └── Main.java           # Entry point - runs Registration → Login → Checkout
├── .gitignore
└── E2E Automation.iml
```
---
## 📋 Prerequisites

Make sure the following are installed:

- **JDK 11** or higher
- **Google Chrome** (latest version)
- **IntelliJ IDEA** (or any Java IDE)
- **Selenium Java 4.x** libraries (JAR files)
- Internet connection

> ℹ️ Selenium 4.6+ includes **Selenium Manager**, which downloads the matching ChromeDriver automatically - no manual driver setup is required.

---

## ⚙️ Setup & Installation

1. **Clone the repository**
   ```bash
   https://github.com/rahat-israil/E2E-Test-Automation.git
   ```

2. **Open the project** in IntelliJ IDEA (`File → Open` → select the project folder).

3. **Add Selenium libraries**
   - Download the Selenium Java 4.x package from the [official Selenium site](https://www.selenium.dev/downloads/), and unzip the folder then
   - `File → Project Structure → Modules → + → Select unzip folder` and add all the JAR files.

4. **Set the Project SDK** to JDK 11 or higher (`File → Project Structure → Project`).

---

## ▶️ How to Run

1. Open `src/Main.java`.
2. Click the ▶ **Run** button next to the `main` method (or press `Shift + F10`).
3. Chrome will launch and execute the full flow automatically.
4. Check the results in the IntelliJ **Run** console.

`Main.java` executes the modules in this order:

```java
public class Main {
    public static void main(String[] args) {
        new Registration().run();                 // 1. Create a new account
        WebDriver driver = new Login().run();     // 2. Log in with that account
        new Checkout().run(driver);               // 3. Add to cart & place order
    }
}
```

Each module can also be run independently for debugging (Login and Checkout depend on an account already existing).

---

## 🖥 Sample Console Output

```
Registration PASSED: Logged in as Rahat
Login PASSED: Logged in as Rahat
Checkout PASSED: Order placed successfully
```

---

## 🗂 Test Data

All test inputs are defined in `TestData.java`:

| Field | Value / Strategy |
|-------|------------------|
| Email | Generated at runtime (`rahat<timestamp>@gmail.com`) so every run creates a unique account |
| Password | Predefined test password, shared by Registration and Login |
| Personal & Address info | Predefined dummy values, read by `Registration` |
| Order comment & Payment | Predefined dummy values, read by `Checkout` |

All values are stored as `public static` fields, so **Registration, Login and Checkout access the same data directly via `TestData.<field>`** — nothing is duplicated across classes, and a single change in `TestData.java` updates every module.

> ⚠️ Only dummy/test data is used. No real personal or payment information is included in this project.

---

## 🧠 Design Decisions

| Decision | Reason |
|----------|--------|
| Separate class per module | Keeps each flow readable, reusable and easy to maintain |
| `TestData` class | Change data in one place instead of editing every test |
| Timestamp-based email | Guarantees a unique account on every run |
| CDP ad blocking | Ads on the site overlay elements and cause `ElementClickInterceptedException` |
| Explicit waits over `Thread.sleep()` | Faster and more stable execution |
| `Login` returns `WebDriver` | Lets `Checkout` continue in the same logged-in session |

---

## 👨‍💻 Author

**Rahat Bin Israil**  
*Manual and Automation Software QA Engineer*

- 📍 Dhaka, Bangladesh
- 💼 [https://www.linkedin.com/in/rahat-israil/](https://www.linkedin.com/in/rahat-israil/)
- 🐙 [https://github.com/rahat-israil](https://github.com/rahat-israil)
- 📧 [rahat.bin.israil@gmail.com](mailto:rahat.bin.israil@gmail.com)

---

⭐ If you found this project useful, feel free to star the repository!
