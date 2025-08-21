# 📝 Todo Management Web Application

A simple, modular React-based web application for managing a todo list. Built as part of a hands-on React learning project.

---

## 🚀 Features

- Add, edit, and delete todos
- Mark todos as completed
- Filter by status: All / Active / Completed
- Display statistics (total and completed)
- Sanitize user inputs to prevent XSS
- Modular component structure
- Mobile-first responsive design

---

## 📁 Folder Structure

```
todo-app/
├── public/
│   └── index.html
├── src/
│   ├── components/
│   │   ├── TodoHeader.jsx
│   │   ├── TodoForm.jsx
│   │   ├── TodoList.jsx
│   │   ├── TodoItem.jsx
│   │   ├── TodoFilter.jsx
│   │   └── TodoStats.jsx
│   ├── styles/
│   │   ├── TodoHeader.css
│   │   ├── TodoForm.css
│   │   ├── TodoList.css
│   │   ├── TodoItem.css
│   │   ├── TodoFilter.css
│   │   └── TodoStats.css
│   ├── App.jsx
│   ├── App.css
│   ├── main.jsx
│   └── api/ (optional)
│       └── todoService.js
├── package.json
├── README.md
└── vite.config.js
```

---

## 🛠️ Tech Stack

- **Frontend**: React + Vite
- **Styling**: Plain CSS (mobile-first)
- **State Management**: React hooks (`useState`, `useEffect`)

---

## 📦 Getting Started


### 1. Install dependencies
```bash
npm install
```

### 2. Run locally
```bash
npm run dev
```
Visit `http://localhost:5173`

---

## Security Considerations

- Input fields are sanitized to prevent script injection (XSS)
- Future API integration can include:
  - Rate limiting (`express-rate-limit`)
  - CORS policies (`cors` middleware)
  - Authentication for protected endpoints

---

## Future Improvements

- Add persistent storage via backend or localStorage
- User authentication
- Drag-and-drop sorting
- Testing with Jest & React Testing Library
- Remote back-up respository using GitHub
- Deployment on Netlify or Vercel

---

## Contact / Author
Huiying Tan  


---

## License

This project is for educational and demo purposes.