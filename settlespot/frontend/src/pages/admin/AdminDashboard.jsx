import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { Users, Building2, CalendarDays, Clock } from 'lucide-react'
import Navbar from '../../components/Navbar.jsx'
import GlassCard from '../../components/GlassCard.jsx'
import LoadingSpinner from '../../components/LoadingSpinner.jsx'
import { getAllHosts, getAllTenants } from '../../api/adminApi.js'
import { getPendingProperties } from '../../api/propertyApi.js'
import { getAllBookings } from '../../api/bookingApi.js'

export default function AdminDashboard() {
  const [stats, setStats] = useState({
    hosts: 0, tenants: 0, pending: 0, bookings: 0,
  })
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    Promise.all([getAllHosts(), getAllTenants(), getPendingProperties(), getAllBookings()])
      .then(([h, t, p, b]) => {
        setStats({
          hosts:    h.data?.data?.length ?? 0,
          tenants:  t.data?.data?.length ?? 0,
          pending:  p.data?.data?.length ?? 0,
          bookings: b.data?.data?.length ?? 0,
        })
      })
      .catch(() => {})
      .finally(() => setLoading(false))
  }, [])

  if (loading) return <LoadingSpinner color="border-t-red-400" />

  return (
    <div className="min-h-screen bg-gradient-to-br from-[#0a0a0a] via-[#1a0000] to-[#2d0505]">
      <Navbar />
      <main className="max-w-7xl mx-auto px-4 py-8">

        <div className="mb-8 animate-fade-up">
          <h1 className="font-display font-bold text-3xl text-white mb-1">
            Admin Dashboard
          </h1>
          <p className="text-white/50 text-sm">Platform overview & controls</p>
        </div>

        {/* Stats */}
        <div className="grid grid-cols-2 lg:grid-cols-4 gap-5 mb-10">
          {[
            { label: 'Total Hosts',       value: stats.hosts,    icon: Users,       color: 'text-red-400'    },
            { label: 'Total Tenants',     value: stats.tenants,  icon: Users,       color: 'text-orange-400' },
            { label: 'Pending Approval',  value: stats.pending,  icon: Clock,       color: 'text-yellow-400' },
            { label: 'Total Bookings',    value: stats.bookings, icon: CalendarDays, color: 'text-white/60'   },
          ].map(({ label, value, icon: Icon, color }) => (
            <GlassCard key={label} className="p-5 flex items-center gap-4">
              <div className={`p-3 rounded-xl bg-white/5 ${color}`}>
                <Icon size={20} aria-hidden="true" />
              </div>
              <div>
                <p className="text-white/40 text-xs font-medium mb-0.5">{label}</p>
                <p className="font-display font-bold text-xl text-white">{value}</p>
              </div>
            </GlassCard>
          ))}
        </div>

        {/* Quick links */}
        <div className="grid grid-cols-1 sm:grid-cols-3 gap-5">
          {[
            {
              title: 'Pending Properties',
              desc: `${stats.pending} properties waiting for approval`,
              to: '/admin/properties',
              icon: Building2,
              label: 'Review Now',
            },
            {
              title: 'Manage Hosts',
              desc: 'View, approve, and toggle host accounts',
              to: '/admin/hosts',
              icon: Users,
              label: 'Manage Hosts',
            },
            {
              title: 'Create New Host',
              desc: 'Onboard a new host — credentials sent via email',
              to: '/admin/hosts/create',
              icon: Users,
              label: 'Create Host',
            },
          ].map(({ title, desc, to, icon: Icon, label }) => (
            <GlassCard key={to} className="p-6 flex flex-col gap-4">
              <div className="p-3 rounded-xl bg-red-500/10 text-red-400 self-start">
                <Icon size={20} aria-hidden="true" />
              </div>
              <div>
                <h2 className="font-display font-semibold text-white mb-1">{title}</h2>
                <p className="text-white/40 text-sm">{desc}</p>
              </div>
              <Link
                to={to}
                className="inline-flex items-center gap-2 px-4 py-2.5 rounded-xl
                  text-sm font-semibold text-white self-start
                  bg-gradient-to-r from-[#ff4444] to-[#cc0000]
                  hover:shadow-lg hover:shadow-red-500/25 hover:-translate-y-0.5
                  transition-all duration-200"
              >
                {label}
              </Link>
            </GlassCard>
          ))}
        </div>
      </main>
    </div>
  )
}
