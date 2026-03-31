# 🦅 Puk3pFlyFix

**Puk3pFlyFix** is a lightweight and efficient Minecraft 1.8 plugin created to patch teleportation flight bugs. It automatically disables a player's flight mode whenever they teleport, preventing movement glitches and unfair advantages.

---

## ✨ Features

- **Automatic Fly Removal** — Instantly disables flight mode the moment a player teleports to a new location.
- **Bug Prevention** — Stops players from bypassing server restrictions or glitching through blocks after a teleport.
- **Configurable Messages** — All player-facing messages are editable via `messages.yml` with color code support.
- **Lightweight** — Highly optimized code that won't cause lag or impact your server's performance.
- **Plug & Play** — No complicated setup required. Just drop it in your `plugins/` folder and restart the server!

---

## 📦 Installation

1. Download the latest `Puk3pFlyFix-x.x.x.jar` from the [Releases](../../releases) page.
2. Place the JAR file into your server's `plugins/` directory.
3. Restart (or reload) your server.
4. Done! The plugin is active immediately.

---

## � Commands

| Command           | Description                          | Permission         |
|-------------------|--------------------------------------|--------------------|
| `/flyfix reload`  | Reload messages configuration        | `puk3pflyfix.admin` |

**Aliases:** `/ffix`

---

## �🔒 Permissions

| Permission             | Description                                | Default |
|------------------------|--------------------------------------------|---------|
| `puk3pflyfix.bypass`   | Allows a player to keep fly on teleport    | OP      |
| `puk3pflyfix.admin`    | Allows usage of admin commands like reload | OP      |

---

## ⚙️ How It Works

The plugin listens for `PlayerTeleportEvent`. When a player teleports:

1. If the player is in **Creative** or **Spectator** mode → no action taken.
2. If the player has the `puk3pflyfix.bypass` permission → no action taken.
3. Otherwise → **flight is disabled** (`allowFlight` and `isFlying` are set to `false`) and the player receives a configurable message.

---

## 🏗️ Architecture (SOLID)

The project follows **SOLID** principles with clean separation of concerns:

| Class | Responsibility |
|-------|---------------|
| `Puk3pFlyFix` | Composition root — wires dependencies |
| `TeleportListener` | Listens for teleport events, delegates logic |
| `PlayerFlightHandler` | Disables flight and notifies the player |
| `BukkitPermissionChecker` | Checks gamemode and permissions |
| `MessageManager` | Loads and parses messages from YAML |
| `FlyFixCommand` | Handles `/flyfix` commands |

All classes depend on **interfaces** (`FlightHandler`, `PermissionChecker`, `MessageProvider`), not concrete implementations.

---

## 🛠️ Building from Source

**Requirements:** JDK 17+ (compiles to Java 8 bytecode), Maven 3.6+

```bash
git clone https://github.com/YOUR_USERNAME/Puk3pFlyFix.git
cd Puk3pFlyFix
mvn clean package
```

The compiled JAR will be in `target/Puk3pFlyFix-<version>.jar`.

---

## 🔄 CI/CD

This project uses **GitHub Actions** for continuous integration and delivery.

### Build Pipeline (`build.yml`) — on push/PR

| Step | Tool | What it does |
|------|------|--------------|
| 1 | Spotless (ktlint) | Enforces consistent Kotlin code style |
| 2 | Maven | Compiles all sources |
| 3 | JUnit 5 | Runs all unit tests |
| 4 | SpotBugs | Detects potential bugs in compiled bytecode |
| 5 | Maven | Packages the final JAR and uploads as artifact |

### Release Pipeline (`release.yml`) — on `v*` tag

Runs the same 5 quality steps, then creates a **GitHub Release** with `Puk3pFlyFix-<version>.jar` attached.

---

## 🪝 Git Hooks

Two hooks are included in `.githooks/`:

### pre-commit (fast, every commit)
1. **Spotless** — blocks commit if formatting is off (fix with `mvn spotless:apply`)
2. **Compile** — blocks commit if code doesn't compile

### pre-push (full verify, before push)
1. **Spotless** — code formatting check
2. **Compile** — compilation check
3. **Tests** — runs all unit tests
4. **SpotBugs** — static analysis

To enable hooks after cloning:
```bash
git config core.hooksPath .githooks
```

---

## 📜 License

This project is licensed under the [MIT License](LICENSE).

---

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

> Made with ❤️ by **Puk3p**
