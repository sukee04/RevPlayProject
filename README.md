# 🎵 RevPlay – Console-Based Music Player Application

RevPlay is a **console-based music player application** developed using **Java and JDBC**.  
It supports **User** and **Artist** roles, allowing artists to upload songs and albums, and users to listen to music, manage playlists, mark favorites, and track play counts.

The project follows a **layered architecture** with clear separation of concerns using **Model, DAO, Service, UI, and Configuration** layers.

---

📌 Features

### 👤 User Features
- User Registration & Login
- Change Password
- Browse Songs Library
- Play Songs (Play / Pause / Next simulation)
- Track Song Play Count
- Create Playlists
- View & Delete Playlists
- Add Songs to Favorites
- View Favorite Songs

### 🎤 Artist Features
- Artist Registration & Login
- Create Albums
- Upload Songs with Details
- View Uploaded Songs & Albums
- Delete Songs / Albums
- View Play Count of Songs

### ⚙️ Common Features
- Role-based login (User / Artist)
- Console-based menu navigation
- Logging using **Log4j2**

## 🧱 Project Architecture

The project follows a **layered structure**:
---
com.example.RevPlay
│
├── config → Database connection & configuration
├── model → Entity classes (User, Artist, Song, Album, Playlist, etc.)
├── dao → Database access layer (JDBC)
├── service → Business logic layer
├── ui → Console-based user interface
└── main → Application entry point


---

## 🗄️ Database Design

The project uses **MySQL** with proper relational design and foreign key constraints.

### Main Tables:
- `users`
- `artists`
- `albums`
- `songs`
- `playlists`
- `favorites`

### Relationships:
- Artist → Albums → Songs
- Users → Playlists
- Users ↔ Songs (Favorites – Many-to-Many)

---

## 🛠️ Technologies Used

- **Java (JDK 21)**
- **JDBC**
- **MySQL**
- **Maven**
- **Log4j2**
- **IntelliJ IDEA / Eclipse**
- **Git & GitHub**

---

## 📂 Project Setup

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/sukee04/RevPlayProject.git

