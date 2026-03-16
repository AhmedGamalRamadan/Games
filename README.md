# RAWG Games App

An Android app that displays games using the [RAWG Video Games Database API](https://rawg.io/apidocs). Built with **Kotlin**, **Jetpack Compose**, and modern Android architecture patterns.

---

## 🛠 Tech Stack

- **Kotlin** – main programming language.  
- **Jetpack Compose** – declarative UI.  
- **MVVM** – architecture pattern.  
- **Koin** – dependency injection.
- **Retrofit** – networking.
- **Navigation Compose** – app navigation.
- **Safe Args** – type-safe navigation parameters.
- **Coroutines + Flow** – asynchronous data streams.
- **Room + Offline-First Approach** – local database caching with Flow to provide offline support, reduce network calls, and serve as a single source of truth.  
- **JUnit & MockK**: For unit testing and mocking.

---
### 📦 Offline Support (Room + Offline First)

The app implements an **offline-first architecture** using **Room database**:

**Flow:**
1. Load data from Room database (local cache).  
2. If data exists → display immediately.  
3. Fetch latest data from RAWG API.  
4. Save updated data to Room.  
5. UI updates automatically via **Flow**. 
---

## 📱 Features

1. **Search Games** – Search for games by name using RAWG API.  
2. **Tabs for Genres** – Horizontal tabs to filter games by type/genre.  
3. **LazyVerticalGrid Game List** – Vertical scrollable list of games with images and release dates.  
4. **Game Details Screen** – Navigate to a detailed screen showing:
   - Game cover image  
   - Full description  
   - Release date    
   - Ratings  

---

## 🔗 API Endpoints


- **Get Games by Genre:**  
`GET https://api.rawg.io/api/games?genres={slug}&page_size=20&key=YOUR_API_KEY`  

- **Game Details:**  
`GET https://api.rawg.io/api/games/{id_or_slug}?key=YOUR_API_KEY`  


> Replace `YOUR_API_KEY` with your RAWG API key.

---

## 🖥 App Flow

1. **Home Screen:**  
   - Search bar for games  
   - Horizontal genre tabs  
   - LazyVerticalGrid showing games filtered by selected genre  

2. **Game Details Screen:**  
   - Navigate from a game item  
   - Displays full game details and images  

---

## ⚡ Architecture block diagram
![Android Architecture](https://github.com/lofcoding/AndroidArchitectureSample/assets/109604722/ed29d956-1154-4518-9107-e4e1a34b4a35)


## 📸 Screenshots

<table>
  <tr> 
    <td><img src="https://github.com/user-attachments/assets/7f095953-9ca2-41ab-8711-d2854dd1ca03" alt="splash" width="240"></td>
    <td><img src="https://github.com/user-attachments/assets/5d820722-39aa-41a1-b19a-1f895daa0b4b" alt="splash" width="240"></td>
     
  </tr>
    <tr>
      <td><img src="https://github.com/user-attachments/assets/40288839-b69f-4732-9785-498ab035aa64" alt="splash" width="240"></td>
     <td><img src="https://github.com/user-attachments/assets/1f83072a-2fe4-441f-bc8e-0bebdd112fe1" alt="splash" width="240"></td>
  </tr>
</table>

##  Feature Checklist

### Game List Screen
- ✅ Display **game name**
- ✅ Display **game image (cover / background_image)**
- ✅ Display **game rating**
- ✅ Implement **pagination / load more on scroll**
- ✅ Implement **local search filter** (without making a new API call)
- ✅ Handle **UI states**:
  -  Loading
  -  Error
  -  Empty list
  -  Success

### Game Details Screen
- ✅ Display **game name**
- ✅ Display **game image**
- ✅ Display **game rating**
- ✅ Display **release date**
- ✅ Display **full description**

### Project & Documentation
- ✅ Project **builds successfully**
- ✅ Include **README file** with:
  -  Tech stack
  -  Features
  -  API endpoints
  -  App flow / screenshots

## 🧪 Testing


## 🗂️ Project Modules

### 1. **App Module**
   - Entry point of the application.
   - Contains UI and app-level configurations.

### 2. **Data Module**
   - Implements repository patterns.

### 3. **Domain Module**
   - Holds business logic and use case classes.
   - Acts as an intermediary between the `Data` module and `App` module.

---

## 📝 Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/AhmedGamalRamadan/Games.git


## Connect with Me 🌐
Let's connect! Feel free to reach out on LinkedIn.
<p align="left">
<a href="https://www.linkedin.com/in/ahmed-gamal-ramadan/" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/linked-in-alt.svg" alt="https://www.linkedin.com/in/ahmed-gamal-97509328a/" height="30" width="40" /></a>
</p>

