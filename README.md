# 📬 SmartMailSuite

**SmartMailSuite** is a full-stack AI-powered email assistant that helps you draft intelligent, context-aware replies—effortlessly. It includes a Chrome Extension, a Spring Boot backend, and a React-based frontend, all working together to enhance your email experience.

---

## 🚀 Features

- 🧠 **AI-Powered Replies** using Gemini API
- 📎 **Chrome Extension** for Gmail with a one-click "AI Reply" button
- ⚙️ **Spring Boot Backend** to handle AI requests securely
- 💻 **React Frontend** for manual email reply generation
- 🌐 **CORS-enabled API** ready for deployment (Hosted on Render)
- 🔐 **Environment Variable Support** for keys & config

---

## 🏗️ Project Structure

SmartMailSuite/
├── chrome-extension/ # Gmail extension (JS, HTML, CSS)
├── spring-backend/ # Java + Spring Boot backend
├── react-frontend/ # React web interface
└── README.md # You're reading it!

## 💻 Local Setup Guide

### . 🌐 Chrome Extension
- Open Chrome and go to `chrome://extensions`
- Enable **Developer Mode**
- Click **Load Unpacked** and select the `chrome-extension/` folder
- Open Gmail → Click on **AI Reply** to generate email
---

## 🧠 Tech Stack

Layer	Technology
Backend	Java 17, Spring Boot
Frontend	React, Material UI
Extension	HTML, CSS, JavaScript
AI Service	Google Gemini API
Deployment	Render

---

## 🧪 Testing

✅ Tested on Gmail with multiple email tones

✅ CORS setup for cross-origin requests

✅ Handles slow API response & errors gracefully
