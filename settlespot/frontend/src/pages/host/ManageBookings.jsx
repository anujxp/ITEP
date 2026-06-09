import { useState, useEffect } from 'react'
import { CalendarDays } from 'lucide-react'
import Navbar from '../../components/Navbar.jsx'
import BookingCard from '../../components/BookingCard.jsx'
import LoadingSpinner from '../../components/LoadingSpinner.jsx'
import Toast from '../../components/Toast.jsx'
import { getHostAllBookings, reviewBooking } from '../../api/bookingApi.js'

const TABS = ['ALL', 'PENDING', 'APPROVED', 'REJECTED']

export default function ManageBookings() {
  const [bookings, setBookings] = useState([])
  const [loading, setLoading] = useState(true)
  const [tab, setTab] = useState('ALL')
  const [toast, setToast] = useState(null)

  const showToast = (message, type = 'error') => setToast({ message, type })

  const fetchBookings = () => {
    setLoading(true)
    getHostAllBookings()
      .then(({ data: res }) => {
        if (res.success) setBookings(res.data ?? [])
      })
      .catch(() => {})
      .finally(() => setLoading(false))
  }

  useEffect(() => { fetchBookings() }, [])

  const handleReview = async (id, action) => {
    try {
      const { data: res } = await reviewBooking(id, action)
      if (res.success) {
        showToast(`Booking ${action.toLowerCase()}d.`, 'success')
        fetchBookings()
      } else {
        showToast(res.message)
      }
    } catch (err) {
      showToast(err.response?.data?.message || 'Action failed.')
    }
  }

  const filtered = tab === 'ALL' ? bookings : bookings.filter((b) => b.status === tab)

  if (loading) return <LoadingSpinner color="border-t-violet-400" />

  return (
    <div className="min-h-screen bg-gradient-to-br from-[#1a0533] via-[#2d1057] to-[#4a1080]">
      <Navbar />
      <Toast toast={toast} onClose={() => setToast(null)} />
      <main className="max-w-5xl mx-auto px-4 py-8">

        <h1 className="font-display font-bold text-3xl text-white mb-6">
          Booking Requests
        </h1>

        {/* Tabs */}
        <div className="flex gap-2 mb-6 flex-wrap" role="tablist">
          {TABS.map((t) => (
            <button
              key={t}
              role="tab"
              aria-selected={tab === t}
              onClick={() => setTab(t)}
              className={`px-4 py-2 rounded-xl text-sm font-medium transition-all duration-150 cursor-pointer
                ${tab === t
                  ? 'bg-violet-500/30 text-violet-300 border border-violet-400/30'
                  : 'bg-white/10 text-white/50 hover:text-white/80 border border-white/10'}`}
            >
              {t}
              <span className="ml-1.5 text-xs opacity-60">
                ({t === 'ALL' ? bookings.length : bookings.filter((b) => b.status === t).length})
              </span>
            </button>
          ))}
        </div>

        {filtered.length === 0 ? (
          <div className="text-center py-20 text-white/40">
            <CalendarDays size={40} className="mx-auto mb-3 opacity-30" aria-hidden="true" />
            <p>No {tab !== 'ALL' ? tab.toLowerCase() : ''} bookings found.</p>
          </div>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-5">
            {filtered.map((b) => (
              <BookingCard
                key={b.id}
                booking={b}
                onApprove={(id) => handleReview(id, 'APPROVE')}
                onReject={(id) => handleReview(id, 'REJECT')}
              />
            ))}
          </div>
        )}
      </main>
    </div>
  )
}
