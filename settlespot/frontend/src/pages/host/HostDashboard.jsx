import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { Building2, CalendarDays, Clock, Plus } from 'lucide-react'
import Navbar from '../../components/Navbar.jsx'
import GlassCard from '../../components/GlassCard.jsx'
import LoadingSpinner from '../../components/LoadingSpinner.jsx'
import { useAuth } from '../../context/AuthContext.jsx'
import { getMyProperties } from '../../api/propertyApi.js'
import { getHostAllBookings } from '../../api/bookingApi.js'

export default function HostDashboard() {
  const { user } = useAuth()
  const [stats, setStats] = useState({
    properties: 0,
    approved: 0,
    pending: 0,
    bookings: 0,
  })
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    Promise.all([getMyProperties(), getHostAllBookings()])
      .then(([propRes, bookRes]) => {
        const props    = propRes.data?.data ?? []
        const bookings = bookRes.data?.data ?? []
        setStats({
          properties: props.length,
          approved:   props.filter((p) => p.approvalStatus === 'APPROVED').length,
          pending:    props.filter((p) => p.approvalStatus === 'PENDING_APPROVAL').length,
          bookings:   bookings.length,
        })
      })
      .catch(() => {})
      .finally(() => setLoading(false))
  }, [])

  if (loading) return <LoadingSpinner color="border-t-violet-400" />

  return (
    <div className="min-h-screen bg-gradient-to-br from-[#1a0533] via-[#2d1057] to-[#4a1080]">
      <Navbar />
      <main className="max-w-7xl mx-auto px-4 py-8">

        <div className="mb-8 animate-fade-up">
          <h1 className="font-display font-bold text-3xl text-white mb-1">
            Host Dashboard
          </h1>
          <p className="text-white/50 text-sm">
            Welcome back, {user?.fullName?.split(' ')[0]}
          </p>
        </div>

        {/* Stats */}
        <div className="grid grid-cols-2 lg:grid-cols-4 gap-5 mb-10">
          {[
            { label: 'My Properties',  value: stats.properties, icon: Building2,   color: 'text-violet-400' },
            { label: 'Live Listings',  value: stats.approved,   icon: Building2,   color: 'text-green-400'  },
            { label: 'Pending Review', value: stats.pending,    icon: Clock,       color: 'text-yellow-400' },
            { label: 'Total Bookings', value: stats.bookings,   icon: CalendarDays, color: 'text-cyan-400'  },
          ].map(({ label, value, icon: Icon, color }) => (
            <GlassCard key={label} className="p-5 flex items-center gap-4">
              <div className={`p-3 rounded-xl bg-white/10 ${color}`}>
                <Icon size={20} aria-hidden="true" />
              </div>
              <div>
                <p className="text-white/50 text-xs font-medium mb-0.5">{label}</p>
                <p className="font-display font-bold text-xl text-white">{value}</p>
              </div>
            </GlassCard>
          ))}
        </div>

        {/* Quick links */}
        <div className="grid grid-cols-1 sm:grid-cols-2 gap-5">
          <GlassCard className="p-6 flex flex-col gap-4">
            <h2 className="font-display font-semibold text-white">My Properties</h2>
            <p className="text-white/50 text-sm">
              View, update, or delete your listings
            </p>
            <div className="flex gap-3 flex-wrap">
              <Link
                to="/host/properties"
                className="inline-flex items-center gap-2 px-4 py-2.5 rounded-xl
                  font-semibold text-sm text-white
                  bg-gradient-to-r from-[#c77dff] to-[#7b2ff7]
                  hover:shadow-lg hover:shadow-violet-500/25 hover:-translate-y-0.5
                  transition-all duration-200"
              >
                <Building2 size={14} aria-hidden="true" />
                View All
              </Link>
              <Link
                to="/host/properties/add"
                className="inline-flex items-center gap-2 px-4 py-2.5 rounded-xl
                  font-semibold text-sm text-white bg-white/10 border border-white/15
                  hover:bg-white/20 transition-all duration-200"
              >
                <Plus size={14} aria-hidden="true" />
                Add New
              </Link>
            </div>
          </GlassCard>

          <GlassCard className="p-6 flex flex-col gap-4">
            <h2 className="font-display font-semibold text-white">Booking Requests</h2>
            <p className="text-white/50 text-sm">
              Review and approve/reject tenant booking requests
            </p>
            <Link
              to="/host/bookings"
              className="inline-flex items-center gap-2 px-4 py-2.5 rounded-xl
                font-semibold text-sm text-white bg-white/10 border border-white/15
                hover:bg-white/20 transition-all duration-200 self-start"
            >
              <CalendarDays size={14} aria-hidden="true" />
              Manage Bookings
            </Link>
          </GlassCard>
        </div>
      </main>
    </div>
  )
}
