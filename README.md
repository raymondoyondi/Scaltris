# Scaltris 🕹️

**Scaltris** is a modern, robust implementation of the classic arcade game Tetris, engineered using the **Scala** programming language. This project explores the synergy between functional programming (FP) and object-oriented programming (OOP) to deliver a clean, maintainable, and highly responsive gaming experience.

---

## 🚀 Overview

The goal of Scaltris is to leverage Scala’s powerful type system and immutable data structures to recreate the iconic Tetris mechanics. By using a functional approach to handle game states and piece rotations, the engine ensures a bug-free experience with a predictable logic flow.



---

## ✨ Key Features

* **Hybrid Architecture:** Combines Scala's OOP for UI components and FP for core game logic.
* **Immutable State Management:** Game snapshots are handled through immutable collections, reducing side effects.
* **Intuitive Controls:** Classic keyboard mapping for rotation, lateral movement, and hard drops.
* **Dynamic Scoring:** Built-in scoring system that rewards multi-line clears (Single, Double, Triple, and the elusive Tetris).
* **Responsive Rendering:** A clean GUI that scales correctly and provides visual feedback for the next upcoming piece.

---

## 🛠️ Technical Stack

| Component | Technology |
| :--- | :--- |
| **Language** | Scala |
| **Build Tool** | sbt (Scala Build Tool) |
| **Library** | Scala Swing / JavaFX (for GUI) |
| **Logic** | Functional State Transitions |

---

## 🎮 How to Play

### Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/raymondoyondi/Scaltris.git](https://github.com/raymondoyondi/Scaltris.git)
    cd Scaltris
    ```

2.  **Build and Run:**
    Ensure you have `sbt` installed.
    ```bash
    sbt run
    ```

### Controls

* **Left/Right Arrows:** Move piece laterally
* **Up Arrow:** Rotate piece clockwise
* **Down Arrow:** Soft drop (increase fall speed)
* **Spacebar:** Hard drop (instant placement)
* **P:** Pause game

---

## 🧩 Core Logic: The "SRS" System

Scaltris implements a simplified version of the **Super Rotation System (SRS)**. The movement logic can be represented by the transformation of coordinates $(x, y)$ within the grid:

$$f(x, y) \rightarrow (x', y')$$

Where the collision detection ensures:
$$(x', y') \notin \text{OccupiedCells} \cup \text{Boundaries}$$



---

## 🤝 Contributing

Contributions are welcome! If you have ideas for improving the ghost-piece rendering, adding levels, or optimizing the engine logic:

1.  Fork the Project
2.  Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the Branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request

---

## 📝 License

Distributed under the MIT License. See `LICENSE` for more information.
