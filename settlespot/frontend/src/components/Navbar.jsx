import { Link, useNavigate } from 'react-router-dom'
import { LogOut, Home, Search, CalendarDays, Building2, Users, ClipboardList } from 'lucide-react'
import { useAuth } from '../context/AuthContext.jsx'

const NAV_LINKS = {
  TENANT: [
    { to: '/tenant/dashboard', label: 'Dashboard', icon: Home },
    { to: '/tenant/properties', label: 'Browse', icon: Search },
    { to: '/tenant/bookings', label: 'My Bookings', icon: CalendarDays },
  ],
  HOST: [
    { to: '/host/dashboard', label: 'Dashboard', icon: Home },
    { to: '/host/properties', label: 'Properties', icon: Building2 },
    { to: '/host/bookings', label: 'Bookings', icon: CalendarDays },
  ],
  ADMIN: [
    { to: '/admin/dashboard', label: 'Dashboard', icon: Home },
    { to: '/admin/properties', label: 'Properties', icon: Building2 },
    { to: '/admin/hosts', label: 'Hosts', icon: Users },
  ],
}

const ACCENT = {
  TENANT: 'text-cyan-400',
  HOST: 'text-violet-400',
  ADMIN: 'text-red-400',
}

const BORDER = {
  TENANT: 'border-cyan-400/20',
  HOST: 'border-violet-400/20',
  ADMIN: 'border-red-400/20',
}

export default function Navbar() {
  const { user, logout } = useAuth()
  const navigate = useNavigate()

  if (!user) return null

  const links = NAV_LINKS[user.role] ?? []
  const accent = ACCENT[user.role] ?? 'text-white'
  const border = BORDER[user.role] ?? 'border-white/10'

  const handleLogout = () => {
    logout()
    navigate('/login')
  }

  return (
    <nav
      className={`sticky top-0 z-40 w-full glass-card rounded-none border-b ${border}
        px-4 sm:px-6 py-3 flex items-center justify-between`}
      aria-label="Main navigation"
    >
      {/* Brand */}
      <Link
        to={`/${user.role.toLowerCase()}/dashboard`}
        className={`font-display font-bold text-lg ${accent}`}
      >
        SettleSpot
      </Link>

      {/* Links */}
      <ul className="hidden sm:flex items-center gap-1" role="list">
        {links.map(({ to, label, icon: Icon }) => (
          <li key={to}>
            <Link
              to={to}
              className="flex items-center gap-1.5 px-3 py-2 rounded-lg text-sm
                text-white/70 hover:text-white hover:bg-white/10
                transition-all duration-150"
            >
              <Icon size={15} aria-hidden="true" />
              {label}
            </Link>
          </li>
        ))}
      </ul>

      {/* User + Logout */}
      <div className="flex items-center gap-3">
        <span className="hidden md:block text-xs text-white/40 max-w-[120px] truncate">
          {user.fullName}
        </span>
        <button
          onClick={handleLogout}
          className="flex items-center gap-1.5 px-3 py-2 rounded-lg text-sm
            text-white/60 hover:text-white hover:bg-white/10
            transition-all duration-150 cursor-pointer"
          aria-label="Log out"
        >
          <LogOut size={15} aria-hidden="true" />
          <span className="hidden sm:inline">Logout</span>
        </button>
      </div>
    </nav>
  )
}
