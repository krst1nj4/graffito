# Graffito

<p align="center">
  <img src="src/main/resources/images/LogoZaGraffito.png" alt="Graffito logo" width="220">
</p>

**Graffito** is a desktop presentation editor built in Java Swing. It lets you organize presentations
in an explorer-style tree (*Workspace → Projects → Presentations → Slides*), design slides on a
1280×720 canvas using mouse-driven tools (select, move, resize, rotate, zoom, copy/paste, delete),
and save or reload whole projects as pretty-printed JSON files.

The application was developed as a university project for the **DSW (Software Design)** course at
**Računarski fakultet — School of Computing, Belgrade**, and doubles as a practical showcase of the
classic GoF design patterns implemented explicitly and deliberately.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Usage Guide](#usage-guide)
- [Project Structure](#project-structure)
- [Architecture & Design Patterns](#architecture--design-patterns)
- [File Format](#file-format)
- [Team](#team)

## Features

### Project management
- Explorer tree with the fixed hierarchy **Workspace → Project → Presentation → Slide**
  (duplicate sibling names are rejected automatically)
- Inline node renaming via **triple-click** in the tree
- **Drag & drop reordering of slides** within a presentation
- Editable project metadata (**name** and **author**) through the *Edit* dialog
- Tabbed, multi-project workspace — each opened project gets its **own unique color**
  applied to its tabs and views
- **Save / Save As / Load** projects as human-readable JSON files

### Slide editing
- Logical **1280×720 slide canvas** with aspect-fit scaling and mouse-wheel zoom (0.3×–3.0×)
- Interactive tools: **select / lasso multi-select, move, resize, rotate (±90°), delete, copy, paste**
- Slide element types:
  - **Text** elements
  - **Image** elements (lazy-loaded through a proxy — the image is read from disk only when first painted)
  - Vector **logo** elements (drawn programmatically)
- **Image gallery** panel — load JPG/PNG images from disk, pick one from the thumbnails
  and drop it onto the slide
- **Per-slide undo/redo** with unlimited history (`Ctrl+Z` / `Ctrl+Y`)
- **Space occupancy validation** — elements are rasterized into a binary 1280×720 matrix;
  a new element is rejected if total slide coverage would exceed **80%**

### UX
- Message system: user errors, warnings and notifications are shown as dialogs,
  printed to the console, and appended to a log file
- Three window modes with UI scaling: **Normal**, **Small** and **Fullscreen**

## Getting Started

### Prerequisites

- **JDK 22+** (the project targets Java 22)
- **Maven 3.9+**

### Build

```bash
mvn clean compile
```

### Run

```bash
mvn compile exec:java -Dexec.mainClass=raf.graffito.dsw.AppCore
```

…or simply run the `raf.graffito.dsw.AppCore` class from your IDE (IntelliJ IDEA project files are included).

> **Note:** the file logger and the About dialog resolve resources via relative paths,
> so the app expects to be launched from the project root (running via Maven does this by default).

## Usage Guide

1. **Create a project** — select the workspace root in the tree and press *New Node* (`Ctrl+N`).
   Depending on the selected node, the dialog offers the valid child types:
   under a *Project* you may create either a *Presentation* or a standalone *Slide*;
   under a *Presentation* only *Slides* can be added.
2. **Open the project** — select a project and press *Open Project*. Pick its tab color when prompted.
   The project opens in a tabbed view: one tab per presentation, plus a side panel with project info
   (presentation / project / author).
3. **Edit slides** — select a slide from the numbered list on the left. Use the tool panel on the right:
   - *Action tools* — Select, Move, Rotate left/right, Resize, Delete, Zoom, Copy, Paste
   - *Add tools* — Add Text / Add Image / Add Logo (elements are added centered, then dragged into place)
   - *Image gallery* — load images from disk and click a thumbnail to add it
4. **Reorder slides** — drag a slide in the tree and drop it onto a presentation or another slide
   (reordering works within the same presentation only).
5. **Save your work** — *Save* writes the selected project to its file (first save acts as *Save As*).
   *Load* reads a previously saved `.json` project back into the workspace.
6. **Undo / Redo** — `Ctrl+Z` / `Ctrl+Y` operate on the currently edited slide.

## Project Structure

```
src/main/java/raf/graffito/dsw/
├── AppCore.java                  # entry point (main)
├── core/
│   ├── ApplicationFramework.java # app bootstrap (singleton)
│   ├── ActionManager.java        # global action registry
│   ├── GraffRepository.java      # repository contract
│   ├── graff/
│   │   ├── GraffRepositoryImplements.java
│   │   ├── composite/            # Composite pattern (node tree)
│   │   ├── factory/              # node + element factories (Factory pattern)
│   │   ├── model/                # Workspace, Project, Presentation, Slide, elements
│   │   │   └── proxy/            # image Proxy (lazy loading)
│   │   ├── serializer/           # Jackson JSON serialization
│   │   ├── space/                # slide occupancy validation strategies
│   │   ├── state/                # canvas tool states (State pattern)
│   │   ├── strategy/             # commands + undo/redo manager (Command pattern)
│   │   ├── decorator/            # ColorDecorator for opened projects
│   │   └── view/                 # vector logo painter
│   ├── logger/                   # console + file loggers
│   └── messages/                 # message generator (Observer)
├── controller/                   # menu/toolbar actions (New, Save, Load, Edit, ...)
├── gui/swing/
│   ├── MainFrame.java            # main window (singleton)
│   ├── MyMenuBar.java / MyToolBar.java
│   ├── controllers/              # undo/redo, mouse controller, window modes
│   ├── dialogs/                  # New Node, Edit, About Us dialogs
│   ├── toolbar/                  # tool panel sections + image gallery
│   └── views/                    # ProjectView, PresentationView, SlideView
├── observer/                     # Publisher/Subscriber contracts
└── tree/                         # explorer tree (model, view, drag & drop)
```

## Architecture & Design Patterns

| Pattern | Where it lives |
|---|---|
| **Composite** | `core/graff/composite` — `GraffNode`, `GraffNodeComposite`, `GraffNodeLeaf` form the workspace tree |
| **Abstract Factory / Factory Method** | `core/graff/factory` — `GraffNodeStore`, `ProjectFactory`, `PresFactory`, `SlideFactory`, `SlideElementFactory` (+ text/image/logo factories) |
| **Strategy** | `core/graff/space` — `SpaceValidator` with a pluggable `SpaceCheckStrategy` (`BinaryMatrixStrategy` implements the 80% coverage check) |
| **Command** | `core/graff/strategy` — add/delete/move/resize/rotate commands managed by `KomandaManager` (per-slide undo/redo stacks) |
| **State** | `core/graff/state` — `StateManager` switches between Select, Move, Resize, Rotate, Zoom, Add, Delete, Copy and Paste states driving mouse behavior |
| **Decorator** | `core/graff/decorator` — `ColorDecorator` attaches a unique color to each opened project |
| **Proxy** | `core/graff/model/proxy` — `ProxySlideImage` defers loading `RealSlideImage` until first use |
| **Observer** | `observer/` — `MessageGenerator` notifies `MainFrame` and the loggers; model nodes notify their views |
| **Singleton** | `ApplicationFramework`, `MainFrame`, `SpaceValidator` |

## File Format

Projects are persisted as **pretty-printed JSON** (Jackson, `INDENT_OUTPUT`) with a `.json`
extension. Node types are stored polymorphically via an `"@type"` property (`project`,
`presentation`, `slide`, `TextElement`, `LogoElement`, `ImageElement`). Only the selected
project's subtree is saved; parent links are restored recursively on load.

## Team

| Member | Index |
|---|---|
| Krstinja Kostić | 2924 RN |
| Dimitrije Stanojević | 4924 RN |

*Računarski fakultet — School of Computing, Union University, Belgrade*
*Course: DSW (Software Design), 2025/26*
