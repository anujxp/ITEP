import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { Search, CalendarDays, Star, Building2 } from 'lucide-react'
import Navbar from '../../components/Navbar.jsx'
import GlassCard from '../../components/GlassCard.jsx'
import LoadingSpinner from '../../components/LoadingSpinner.jsx'
import { useAuth } from '../../context/AuthContext.jsx'
import { getMyBookings } from '../../api/bookingApi.js'

export default function TenantDashboard() {
  const { user } = useAuth()
  const [bookings, setBookings] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    getMyBookings()
      .then(({ data: res }) => {
        if (res.success) setBookings(res.data ?? [])
      })
      .catch(() => {})
      .finally(() => setLoading(false))
  }, [])

  const approved  = bookings.filter((b) => b.status === 'APPROVED').length
  const pending   = bookings.filter((b) => b.status === 'PENDING').length
  const total     = bookings.length

  if (loading) return <LoadingSpinner color="border-t-cyan-400" />

  return (
    <div className="min-h-screen bg-gradient-to-br from-[#0f2027] via-[#203a43] to-[#2c5364]">
      <Navbar />
      <main className="max-w-7xl mx-auto px-4 py-8">

        {/* Welcome */}
        <div className="mb-8 animate-fade-up">
          <h1 className="font-display font-bold text-3xl text-white mb-1">
            Welcome, {user?.fullName?.split(' ')[0]} 👋
          </h1>
          <p className="text-white/50 text-sm">
            Here's an overview of your rental activity
          </p>
        </div>

        {/* Stats */}
        <div className="grid grid-cols-1 sm:grid-cols-3 gap-5 mb-10">
          {[
            { label: 'Total Bookings', value: total,    icon: CalendarDays, color: 'text-cyan-400' },
            { label: 'Active Stays',   value: approved, icon: Building2,    color: 'text-green-400' },
            { label: 'Pending',        value: pending,  icon: Star,         color: 'text-yellow-400' },
          ].map(({ label, value, icon: Icon, color }) => (
            <GlassCard key={label} className="p-6 flex items-center gap-5">
              <div className={`p-3 rounded-xl bg-white/10 ${color}`}>
                <Icon size={22} aria-hidden="true" />
              </div>
              <div>
                <p className="text-white/50 text-xs font-medium mb-0.5">{label}</p>
                <p className="font-display font-bold text-2xl text-white">{value}</p>
              </div>
            </GlassCard>
          ))}
        </div>

        {/* Quick links */}
        <div className="grid grid-cols-1 sm:grid-cols-2 gap-5">
          <GlassCard className="p-6 flex flex-col gap-4">
            <h2 className="font-display font-semibold text-white">Find a Room</h2>
            <p className="text-white/50 text-sm">
              Browse hundreds of approved listings in your city
            </p>
            <Link
              to="/tenant/properties"
              className="inline-flex items-center gap-2 px-5 py-2.5 rounded-xl
                font-semibold text-sm text-white
                bg-gradient-to-r from-[#667eea] to-[#764ba2]
                hover:shadow-lg hover:shadow-purple-500/25 hover:-translate-y-0.5
                transition-all duration-200 self-start"
            >
              <Search size={15} aria-hidden="true" />
              Browse Properties
            </Link>
          </GlassCard>

          <GlassCard className="p-6 flex flex-col gap-4">
            <h2 className="font-display font-semibold text-white">My Bookings</h2>
            <p className="text-white/50 text-sm">
              View, cancel, or rate your current and past bookings
            </p>
            <Link
              to="/tenant/bookings"
              className="inline-flex items-center gap-2 px-5 py-2.5 rounded-xl
                font-semibold text-sm text-white bg-white/10 border border-white/15
                hover:bg-white/20 transition-all duration-200 self-start"
            >
              <CalendarDays size={15} aria-hidden="true" />
              View Bookings
            </Link>
          </GlassCard>
        </div>
      </main>
    </div>
  )
}
