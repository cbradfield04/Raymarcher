# 🧪 Ray Marcher

A **Java-based ray marching renderer** that draws 3D scenes from signed distance fields (SDFs) in real time — no meshes required.  
Built to showcase **rendering math**, **distance-field modeling**, and **performance-minded Java engineering**.

---

## 🧩 Overview

Instead of tracing rays to polygon meshes, this renderer **marches** rays through space using **Signed Distance Fields (SDFs)**.  
At each step, the ray advances by the distance to the nearest surface until it either hits geometry or exceeds a max range.  
The result: crisp implicit geometry, soft shadows, and stylized lighting with surprisingly little code.

---

## ✨ Features

- 📐 **Signed Distance Fields** – Sphere, box, torus, plane + smooth union/intersection/difference ops  
- 🔦 **Physically-inspired Shading** – Lambert/Phong lighting with ambient term  
- 🌫️ **Ambient Occlusion** – Cheap AO approximation via local field probes  
- 🪞 **Reflections (Optional)** – One-bounce reflections for shiny materials  
- 🧮 **Analytic Normals** – Gradient-based normals via finite differences  
- ⚙️ **Configurable Quality** – Control max steps, epsilon, FOV, and resolution scale  
- 🎛️ **Live Controls** – Toggle lighting features and adjust parameters in real time  
- 🚀 **Optimized Performance** – Early-outs, distance clamping, and bounding volumes  

---

## 🧠 How It Works

1. **Primary Ray** is cast from the camera through each pixel.  
2. **Sphere Tracing:** repeatedly sample SDF, advance by that distance.  
3. **Hit:** when distance < ε or **Miss:** when distance > maxDistance / steps > maxSteps.  
4. **Shading:** compute normal from SDF gradient, evaluate lighting, shadows, AO, and reflections.  
5. **Render:** write final color to the screen buffer.

---

## ⌨️ Controls

| Key | Action |
|-----|--------|
| W / A / S / D | Move camera forward/left/back/right |
| Q / E | Move camera down/up |
| Arrow Keys / Mouse Drag | Rotate camera |
| 1 / 2 / 3 | Switch scenes |
| F | Toggle soft shadows |
| R | Toggle reflections |
| O / P | Decrease / increase max steps |
| [ / ] | Decrease / increase epsilon |
| - / = | Decrease / increase resolution scale |
| H | Toggle HUD |

*(If controls differ, see `Input.java`.)*
