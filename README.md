# Java Bidding System (Swing + JDBC)

A robust desktop-based auction management system developed in Java. This project demonstrates advanced Object-Oriented Programming (OOP) concepts, the DAO design pattern, and database integration using JDBC.

##  Technical Architecture

This project follows professional software engineering patterns to ensure separation of concerns:

- **Singleton Pattern (`AuctionStateSingleton`):** Used to maintain a single, consistent state of the auction across multiple windows.
- **DAO Pattern (`UserDAO`, `ItemDAO`, `BidDAO`):** Decouples the business logic from the persistence layer (MySQL), making the code more maintainable.
- **Manager Beans (`BidManagerBean`, `AuctionManagerBean`):** Encapsulates the core business logic and rules for processing bids and managing auction timelines.
- **Session Management:** Tracks logged-in users and their permissions throughout the application lifecycle.



##  Key Features

- **Secure Login:** User authentication system with session tracking.
- **Live Bidding Dashboard:** Real-time view of available items and current highest bids.
- **Bid Validation:** Complex logic to ensure new bids exceed the current price and update the state instantly.
- **Database Persistence:** All data is stored in a MySQL database via JDBC, ensuring no data loss between sessions.
- **Desktop GUI:** Built using Java Swing for a clean, functional user interface.

##  Tech Stack

- **Language:** Java (JDK 17+)
- **Database:** MySQL
- **Connectivity:** JDBC (Java Database Connectivity)
- **Architecture:** DAO & Singleton Design Patterns
- **IDE:** IntelliJ IDEA

## Project Structure

```text
src/
├── AuctionStateSingleton.java  # Singleton for global state
├── DBConnection.java           # Centralized database connectivity
├── DAO/                        # Data Access Objects (Item, User, Bid)
├── Beans/                      # Business logic (Manager Beans)
└── UI/                         # GUI Windows (Login, Dashboard, ItemPage)
