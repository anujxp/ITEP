import { useState, useEffect } from 'react'
import { CalendarDays } from 'lucide-react'
import Navbar from '../../components/Navbar.jsx'
import BookingCard from '../../components/BookingCard.jsx'
import LoadingSpinner from '../../components/LoadingSpinner.jsx'
import GlassCard from '../../components/GlassCard.jsx'
import Toast from '../../components/Toast.jsx'
import { getMyBookings, cancelBooking, rateBooking } from '../../api/bookingApi.js'

export default function MyBookings() {
  const [bookings, setBookings] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [toast, setToast] = useState(null)

  // Rating modal state
  const [ratingModal, setRatingModal] = useState(null) // booking object
  const [ratingForm, setRatingForm] = useState({ rating: 5, comment: '' })
  const [ratingLoading, setRatingLoading] = useState(false)

  const showToast = (message, type = 'error') => setToast({ message, type })

  const fetchBookings = () => {
    setLoading(true)
    getMyBookings()
      .then(({ data: res }) => {
        if (res.success) setBookings(res.data ?? [])
        else setError(res.message)
      })
      .catch(() => setError('Could not load bookings.'))
      .finally(() => setLoading(false))
  }

  useEffect(() => { fetchBookings() }, [])

  const handleCancel = async (id) => {
    try {
      const { data: res } = await cancelBooking(id)
      if (res.success) {
        showToast('Booking cancelled.', 'success')
        fetchBookings()
      } else {
        showToast(res.message)
      }
    } catch (err) {
      showToast(err.response?.data?.message || 'Cancel failed.')
    }
  }

  const openRating = (booking) => {
    setRatingModal(booking)
    setRatingForm({ rating: 5, comment: '' })
  }

  const handleRate = async (e) => {
    e.preventDefault()
    if (!ratingModal) return
    setRatingLoading(true)
    try {
      const { data: res } = await rateBooking(ratingModal.id, ratingForm)
      if (res.success) {
        showToast('Rating submitted!', 'success')
        setRatingModal(null)
        fetchBookings()
      } else {
        showToast(res.message)
      }
    } catch (err) {
      showToast(err.response?.data?.message || 'Rating failed.')
    } finally {
      setRatingLoading(false)
    }
  }

  if (loading) return <LoadingSpinner color="border-t-cyan-400" />

  return (
    <div className="min-h-screen bg-gradient-to-br from-[#0f2027] via-[#203a43] to-[#2c5364]">
      <Navbar />
      <Toast toast={toast} onClose={() => setToast(null)} />

      <main className="max-w-5xl mx-auto px-4 py-8">
        <h1 className="font-display font-bold text-3xl text-white mb-6">
          My Bookings
        </h1>

        {error && <p className="text-red-400 text-sm mb-4">{error}</p>}

        {!loading && bookings.length === 0 && (
          <div className="text-center py-20 text-white/40">
            <CalendarDays size={40} className="mx-auto mb-3 opacity-30" aria-hidden="true" />
            <p>You haven't made any bookings yet.</p>
          </div>
        )}

        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-5">
          {bookings.map((b) => (
            <BookingCard
              key={b.id}
              booking={b}
              onCancel={handleCancel}
              onRate={openRating}
            />
          ))}
        </div>
      </main>

      {/* Rating Modal */}
      {ratingModal && (
        <div
          className="fixed inset-0 z-50 flex items-center justify-center px-4
            bg-black/60 backdrop-blur-sm"
          role="dialog"
          aria-modal="true"
          aria-labelledby="rate-modal-title"
        >
          <GlassCard className="w-full max-w-sm p-7">
            <h2 id="rate-modal-title" className="font-display font-bold text-xl text-white mb-1">
              Rate your stay
            </h2>
            <p className="text-white/50 text-sm mb-6">{ratingModal.propertyTitle}</p>

            <form onSubmit={handleRate} className="flex flex-col gap-5">
              <div className="flex flex-col gap-1.5">
                <label htmlFor="rating" className="text-white/70 text-sm font-medium">
                  Rating (1–5)
                </label>
                <input
                  id="rating" type="range" min="1" max="5" step="1"
                  value={ratingForm.rating}
                  onChange={(e) => setRatingForm({ ...ratingForm, rating: parseInt(e.target.value) })}
                  className="accent-cyan-400"
                />
                <div className="flex justify-between text-white/40 text-xs px-0.5">
                  {[1,2,3,4,5].map((n) => (
                    <span key={n} className={n <= ratingForm.rating ? 'text-yellow-400' : ''}>
                      {'★'}
                    </span>
                  ))}
                </div>
              </div>

              <div className="flex flex-col gap-1.5">
                <label htmlFor="comment" className="text-white/70 text-sm font-medium">
                  Comment (optional)
                </label>
                <textarea
                  id="comment" rows="3"
                  value={ratingForm.comment}
                  onChange={(e) => setRatingForm({ ...ratingForm, comment: e.target.value })}
                  placeholder="How was your stay?"
                  className="w-full px-4 py-3 rounded-xl bg-white/10 border border-white/20
                    text-white placeholder-white/30 resize-none
                    focus:outline-none focus:border-cyan-400/60 transition-all duration-200"
                />
              </div>

              <div className="flex gap-3">
                <button
                  type="submit"
                  disabled={ratingLoading}
                  className="flex-1 py-2.5 rounded-xl font-semibold text-sm text-white
                    bg-gradient-to-r from-yellow-500/80 to-orange-500/80
                    hover:shadow-lg transition-all duration-200 cursor-pointer
                    disabled:opacity-50 disabled:cursor-not-allowed"
                >
                  {ratingLoading ? 'Submitting…' : 'Submit Rating'}
                </button>
                <button
                  type="button"
                  onClick={() => setRatingModal(null)}
                  className="px-4 py-2.5 rounded-xl text-sm text-white/60
                    bg-white/10 hover:bg-white/20 transition-all duration-200 cursor-pointer"
                >
                  Cancel
                </button>
              </div>
            </form>
          </GlassCard>
        </div>
      )}
    </div>
  )
}
