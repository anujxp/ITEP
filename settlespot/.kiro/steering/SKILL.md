---
name: react-vite-tailwind4-ui
description: Expert React 18 + Vite + Tailwind CSS v4 UI development with glassmorphism, role-based themes, modern animations, and production-grade component patterns. Use when building or modifying any React component, page, layout, or styling.
---

# React 18 + Vite + Tailwind CSS v4 — UI Skill

## Stack Constraints (NEVER deviate from these)
- **Framework:** React 18 with functional components only — no class components ever
- **Build tool:** Vite 5+
- **Styling:** Tailwind CSS v4 ONLY — no inline styles, no CSS modules, no styled-components
- **Routing:** React Router v6 with `<Routes>` and `<Route>` — no v5 patterns
- **HTTP:** Axios with a shared instance — never use fetch() directly
- **State:** useState + useContext — no Redux, no Zustand, no Recoil
- **Icons:** lucide-react — no FontAwesome, no heroicons
- **Notifications:** Custom toast component — no react-toastify, no sonner
- **Forms:** Controlled components with useState — no react-hook-form, no Formik
- **NO TypeScript** — plain JavaScript (.jsx files)

---

## Tailwind CSS v4 Syntax Rules

Tailwind v4 is a **complete rewrite** — CSS-first config, no tailwind.config.js.

### Setup (vite.config.js)
```javascript
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'

export default defineConfig({
  plugins: [react(), tailwindcss()],
})
```

### Setup (src/index.css) — CSS-first config
```css
@import "tailwindcss";

/* Define custom theme tokens here */
@theme {
  --color-tenant-primary: #00d4ff;
  --color-tenant-bg-from: #0f2027;
  --color-tenant-bg-to: #2c5364;

  --color-host-primary: #c77dff;
  --color-host-bg-from: #1a0533;
  --color-host-bg-to: #4a1080;

  --color-admin-primary: #ff4444;
  --color-admin-bg-from: #0a0a0a;
  --color-admin-bg-to: #2d0505;

  --font-display: 'Syne', sans-serif;
  --font-body: 'DM Sans', sans-serif;

  --radius-glass: 16px;
  --blur-glass: 20px;
}
```

### v4 Class Changes (CRITICAL — old classes break silently)
| Old (v3) | New (v4) |
|---|---|
| `bg-opacity-50` | `bg-white/50` |
| `text-opacity-80` | `text-white/80` |
| `ring-opacity-50` | `ring-white/50` |
| `shadow-lg` | same — unchanged |
| `backdrop-blur-md` | same — unchanged |
| `divide-x` | same — unchanged |
| `@apply` | still works but prefer utility classes |

### Custom CSS Variables in v4
```jsx
// Use CSS vars directly in className
<div className="bg-[var(--color-tenant-primary)]" />
// Or define in @theme and use as normal utility
<div className="bg-tenant-primary" />
```

---

## Project Structure (ALWAYS follow this)

```
settlespot-frontend/
├── index.html
├── vite.config.js
├── package.json
├── src/
│   ├── main.jsx
│   ├── App.jsx
│   ├── index.css                    ← Tailwind v4 @import + @theme
│   │
│   ├── api/
│   │   ├── axios.js                 ← Axios instance + interceptors
│   │   ├── authApi.js
│   │   ├── propertyApi.js
│   │   ├── bookingApi.js
│   │   └── adminApi.js
│   │
│   ├── context/
│   │   └── AuthContext.jsx          ← user, login(), logout()
│   │
│   ├── components/
│   │   ├── PrivateRoute.jsx
│   │   ├── Navbar.jsx
│   │   ├── GlassCard.jsx            ← reusable glass card
│   │   ├── LoadingSpinner.jsx
│   │   ├── Toast.jsx
│   │   ├── StatusBadge.jsx          ← colored status pill
│   │   ├── PropertyCard.jsx
│   │   └── BookingCard.jsx
│   │
│   └── pages/
│       ├── Landing.jsx
│       ├── auth/
│       │   ├── Login.jsx
│       │   └── TenantRegister.jsx
│       ├── tenant/
│       │   ├── TenantDashboard.jsx
│       │   ├── PropertySearch.jsx
│       │   ├── PropertyDetail.jsx
│       │   └── MyBookings.jsx
│       ├── host/
│       │   ├── HostDashboard.jsx
│       │   ├── MyProperties.jsx
│       │   ├── AddProperty.jsx
│       │   └── ManageBookings.jsx
│       └── admin/
│           ├── AdminDashboard.jsx
│           ├── PendingProperties.jsx
│           ├── ManageHosts.jsx
│           └── CreateHost.jsx
```

---

## Three Role-Based Glassmorphism Themes

### Glass Mixin (apply to all cards/panels)
```jsx
// GlassCard.jsx
export function GlassCard({ children, className = '' }) {
  return (
    <div className={`
      backdrop-blur-xl bg-white/8 border border-white/15
      rounded-2xl shadow-2xl shadow-black/30
      ${className}
    `}>
      {children}
    </div>
  );
}
```

### Tenant Theme — Deep Ocean Blue
```css
/* Background */
background: linear-gradient(135deg, #0f2027 0%, #203a43 50%, #2c5364 100%);
/* Accent: #00d4ff (cyan) */
/* Button gradient: from-[#667eea] to-[#764ba2] */
/* Glow: shadow-[0_0_40px_rgba(0,212,255,0.15)] */
```

### Host Theme — Royal Purple
```css
/* Background */
background: linear-gradient(135deg, #1a0533 0%, #2d1057 50%, #4a1080 100%);
/* Accent: #c77dff (violet) */
/* Button gradient: from-[#c77dff] to-[#7b2ff7] */
/* Glow: shadow-[0_0_40px_rgba(199,125,255,0.15)] */
```

### Admin Theme — Dark Command Center
```css
/* Background */
background: linear-gradient(135deg, #0a0a0a 0%, #1a0000 50%, #2d0505 100%);
/* Accent: #ff4444 (red) */
/* Button gradient: from-[#ff4444] to-[#cc0000] */
/* Glow: shadow-[0_0_40px_rgba(255,68,68,0.15)] */
```

---

## Component Patterns

### Page Wrapper (use on EVERY page)
```jsx
// Tenant page
<div className="min-h-screen bg-gradient-to-br from-[#0f2027] via-[#203a43] to-[#2c5364]">
  <div className="max-w-7xl mx-auto px-4 py-8">
    {/* content */}
  </div>
</div>
```

### Glass Button
```jsx
// Primary action button
<button className="
  px-6 py-3 rounded-xl font-semibold text-white
  bg-gradient-to-r from-[#667eea] to-[#764ba2]
  hover:shadow-lg hover:shadow-purple-500/25
  hover:-translate-y-0.5 active:translate-y-0
  transition-all duration-200
">
  Book Now
</button>

// Danger button (admin)
<button className="
  px-4 py-2 rounded-lg text-sm font-medium text-white
  bg-gradient-to-r from-[#ff4444] to-[#cc0000]
  hover:shadow-lg hover:shadow-red-500/25
  transition-all duration-200
">
  Delete
</button>
```

### Form Input (glass style)
```jsx
<input className="
  w-full px-4 py-3 rounded-xl
  bg-white/10 border border-white/20
  text-white placeholder-white/40
  focus:outline-none focus:border-cyan-400/60 focus:bg-white/15
  backdrop-blur-sm transition-all duration-200
" />
```

### Status Badge
```jsx
const colors = {
  PENDING: 'bg-yellow-500/20 text-yellow-300 border-yellow-500/30',
  APPROVED: 'bg-green-500/20 text-green-300 border-green-500/30',
  REJECTED: 'bg-red-500/20 text-red-300 border-red-500/30',
  CANCELLED: 'bg-gray-500/20 text-gray-300 border-gray-500/30',
  PENDING_APPROVAL: 'bg-orange-500/20 text-orange-300 border-orange-500/30',
};
<span className={`px-3 py-1 rounded-full text-xs font-semibold border ${colors[status]}`}>
  {status.replace('_', ' ')}
</span>
```

### Loading State
```jsx
// Full-page loading
<div className="min-h-screen flex items-center justify-center">
  <div className="w-12 h-12 border-2 border-white/20 border-t-cyan-400 rounded-full animate-spin" />
</div>

// Inline skeleton
<div className="h-4 bg-white/10 rounded-lg animate-pulse w-3/4" />
```

### Toast Notification
```jsx
// Show toast with state
const [toast, setToast] = useState(null);

const showToast = (message, type = 'success') => {
  setToast({ message, type });
  setTimeout(() => setToast(null), 3000);
};

// Render
{toast && (
  <div className={`
    fixed top-4 right-4 z-50 px-6 py-4 rounded-xl
    backdrop-blur-xl border shadow-2xl
    transition-all duration-300 animate-slide-in
    ${toast.type === 'success'
      ? 'bg-green-500/20 border-green-500/30 text-green-300'
      : 'bg-red-500/20 border-red-500/30 text-red-300'}
  `}>
    {toast.message}
  </div>
)}
```

---

## API Integration Patterns

### Axios Setup
```javascript
// src/api/axios.js
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
  headers: { 'Content-Type': 'application/json' },
});

api.interceptors.request.use(config => {
  const token = localStorage.getItem('token');
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

api.interceptors.response.use(
  res => res,
  err => {
    if (err.response?.status === 401) {
      localStorage.clear();
      window.location.href = '/login';
    }
    return Promise.reject(err);
  }
);

export default api;
```

### API call pattern (always destructure response.data)
```javascript
// All Spring Boot endpoints return: { success, message, data }
const { data: response } = await api.post('/auth/login', payload);
// response.success === true/false
// response.message === "Login successful"
// response.data === { token, userId, role, fullName }
```

### useEffect data fetch pattern
```jsx
const [items, setItems] = useState([]);
const [loading, setLoading] = useState(true);
const [error, setError] = useState('');

useEffect(() => {
  const fetchData = async () => {
    try {
      const { data: res } = await api.get('/properties/available');
      if (res.success) setItems(res.data);
      else setError(res.message);
    } catch (err) {
      setError(err.response?.data?.message || 'Something went wrong');
    } finally {
      setLoading(false);
    }
  };
  fetchData();
}, []);
```

---

## Typography
```html
<!-- In index.html <head> -->
<link href="https://fonts.googleapis.com/css2?family=Syne:wght@400;500;600;700;800&family=DM+Sans:ital,wght@0,300;0,400;0,500;0,600&display=swap" rel="stylesheet">
```
- Headings: `font-display` (Syne) — bold, geometric
- Body: `font-body` (DM Sans) — clean, readable
- Code/numbers: `font-mono` (system)

---

## Animations (pure Tailwind v4)
```css
/* Add to index.css */
@keyframes slide-in {
  from { opacity: 0; transform: translateX(100%); }
  to   { opacity: 1; transform: translateX(0); }
}
@keyframes fade-up {
  from { opacity: 0; transform: translateY(20px); }
  to   { opacity: 1; transform: translateY(0); }
}
@keyframes pulse-glow {
  0%, 100% { box-shadow: 0 0 20px rgba(0, 212, 255, 0.1); }
  50%       { box-shadow: 0 0 40px rgba(0, 212, 255, 0.3); }
}
```

---

## Accessibility & Quality Rules
- Every `<img>` must have `alt` attribute
- Every form input must have a label (visible or `sr-only`)
- Interactive elements need `cursor-pointer`
- Disabled states: `opacity-50 cursor-not-allowed`
- Loading states must be shown during API calls — never leave UI frozen
- Error messages must be shown from `response.data.message` — never `console.log` only
- Mobile responsive: always use `sm:`, `md:`, `lg:` breakpoints — no desktop-only layouts
- Empty states: always show a friendly message when a list is empty (never render nothing)