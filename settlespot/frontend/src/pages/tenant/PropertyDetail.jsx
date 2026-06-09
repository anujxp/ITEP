import { useState, useEffect } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import {
  MapPin, Home, Star, IndianRupee, Calendar, ArrowLeft, CheckCircle
} from 'lucide-react'
import Navbar from '../../components/Navbar.jsx'
import GlassCard from '../../components/GlassCard.jsx'
import LoadingSpinner from '../../components/LoadingSpinner.jsx'
import Toast from '../../components/Toast.jsx'
import { getPropertyById } from '../../api/propertyApi.js'
import { createBooking } from '../../api/bookingApi.js'

export default function PropertyDetail() {
  const { id } = useParams()
  const navigate = useNavigate()

  const [property, setProperty] = useState(null)
  const [loading, setLoading] = useState(true)
  const [toast, setToast] = useState(null)
  const [booking, setBooking] = useState({ checkInDate: '', checkOutDate: '' })
  const [bookingLoading, setBookingLoading] = useState(false)

  const showToast = (message, type = 'error') => setToast({ message, type })

  useEffect(() => {
    getPropertyById(id)
      .then(({ data: res }) => {
        if (res.success) setProperty(res.data)
      })
      .catch(() => {})
      .finally(() => setLoading(false))
  }, [id])

  const handleBook = async (e) => {
    e.preventDefault()
    if (!booking.checkInDate || !booking.checkOutDate) {
      showToast('Please select both check-in and check-out dates.')
      return
    }
    setBookingLoading(true)
    try {
      const { data: res } = await createBooking({
        propertyId: id,
        checkInDate: booking.checkInDate,
        checkOutDate: booking.checkOutDate,
      })
      if (res.success) {
        showToast('Booking request sent to host!', 'success')
        setTimeout(() => navigate('/tenant/bookings'), 1200)
      } else {
        showToast(res.message || 'Booking failed.')
      }
    } catch (err) {
      showToast(err.response?.data?.message || 'Booking failed.')
    } finally {
      setBookingLoading(false)
    }
  }

  if (loading) return <LoadingSpinner color="border-t-cyan-400" />

  if (!property) {
    return (
      <div className="min-h-screen bg-gradient-to-br from-[#0f2027] via-[#203a43] to-[#2c5364]">
        <Navbar />
        <p className="text-white/50 text-center pt-20">Property not found.</p>
      </div>
    )
  }

  const today = new Date().toISOString().split('T')[0]

  return (
    <div className="min-h-screen bg-gradient-to-br from-[#0f2027] via-[#203a43] to-[#2c5364]">
      <Navbar />
      <Toast toast={toast} onClose={() => setToast(null)} />
      <main className="max-w-4xl mx-auto px-4 py-8">

        {/* Back */}
        <button
          onClick={() => navigate(-1)}
          className="flex items-center gap-2 text-white/50 hover:text-white
            transition-colors mb-6 cursor-pointer text-sm"
        >
          <ArrowLeft size={16} aria-hidden="true" />
          Back to results
        </button>

        <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">

          {/* Details */}
          <GlassCard className="p-7 lg:col-span-2 flex flex-col gap-5">
            <div>
              <h1 className="font-display font-bold text-2xl text-white mb-2">
                {property.title}
              </h1>
              <div className="flex flex-wrap gap-4 text-white/60 text-sm">
                <span className="flex items-center gap-1.5">
                  <MapPin size={14} aria-hidden="true" />
                  {property.area}, {property.city}
                </span>
                <span className="flex items-center gap-1.5">
                  <Home size={14} aria-hidden="true" />
                  {property.propertyType}
                </span>
                {property.averageRating > 0 && (
                  <span className="flex items-center gap-1 text-yellow-400">
                    <Star size={14} fill="currentColor" aria-hidden="true" />
                    {property.averageRating?.toFixed(1)}
                    ({property.totalRatings} review{property.totalRatings !== 1 ? 's' : ''})
                  </span>
                )}
              </div>
            </div>

            {/* Address */}
            <div>
              <h2 className="text-white/70 text-xs font-semibold uppercase tracking-widest mb-1">
                Address
              </h2>
              <p className="text-white/60 text-sm">{property.address}</p>
            </div>

            {/* Description */}
            <div>
              <h2 className="text-white/70 text-xs font-semibold uppercase tracking-widest mb-1">
                About this property
              </h2>
              <p className="text-white/70 text-sm leading-relaxed">{property.description}</p>
            </div>

            {/* Availability */}
            <div className="flex items-center gap-2">
              <CheckCircle
                size={16}
                className={property.isAvailable ? 'text-green-400' : 'text-red-400'}
                aria-hidden="true"
              />
              <span className={`text-sm font-medium ${property.isAvailable ? 'text-green-400' : 'text-red-400'}`}>
                {property.isAvailable ? 'Available now' : 'Currently occupied'}
              </span>
            </div>
          </GlassCard>

          {/* Booking widget */}
          <GlassCard className="p-6 flex flex-col gap-5 self-start">
            <div className="flex items-end gap-1">
              <IndianRupee size={18} className="text-cyan-400 mb-0.5" aria-hidden="true" />
              <span className="font-display font-bold text-3xl text-white">
                {property.rentAmount?.toLocaleString('en-IN')}
              </span>
              <span className="text-white/40 text-sm mb-1">/month</span>
            </div>

            {property.isAvailable ? (
              <form onSubmit={handleBook} className="flex flex-col gap-4">
                <div className="flex flex-col gap-1.5">
                  <label htmlFor="checkIn" className="text-white/70 text-xs font-medium">
                    Check-in date
                  </label>
                  <div className="relative">
                    <Calendar
                      size={14}
                      className="absolute left-3 top-1/2 -translate-y-1/2 text-white/40"
                      aria-hidden="true"
                    />
                    <input
                      id="checkIn" type="date" min={today}
                      value={booking.checkInDate}
                      onChange={(e) => setBooking({ ...booking, checkInDate: e.target.value })}
                      required
                      className="w-full pl-9 pr-3 py-2.5 rounded-xl bg-white/10 border border-white/20
                        text-white text-sm focus:outline-none focus:border-cyan-400/60
                        transition-all duration-200 [color-scheme:dark]"
                    />
                  </div>
                </div>

                <div className="flex flex-col gap-1.5">
                  <label htmlFor="checkOut" className="text-white/70 text-xs font-medium">
                    Check-out date
                  </label>
                  <div className="relative">
                    <Calendar
                      size={14}
                      className="absolute left-3 top-1/2 -translate-y-1/2 text-white/40"
                      aria-hidden="true"
                    />
                    <input
                      id="checkOut" type="date" min={booking.checkInDate || today}
                      value={booking.checkOutDate}
                      onChange={(e) => setBooking({ ...booking, checkOutDate: e.target.value })}
                      required
                      className="w-full pl-9 pr-3 py-2.5 rounded-xl bg-white/10 border border-white/20
                        text-white text-sm focus:outline-none focus:border-cyan-400/60
                        transition-all duration-200 [color-scheme:dark]"
                    />
                  </div>
                </div>

                <button
                  type="submit"
                  disabled={bookingLoading}
                  className="w-full py-3 rounded-xl font-semibold text-white text-sm
                    bg-gradient-to-r from-[#667eea] to-[#764ba2]
                    hover:shadow-lg hover:shadow-purple-500/25 hover:-translate-y-0.5
                    transition-all duration-200 cursor-pointer
                    disabled:opacity-50 disabled:cursor-not-allowed
                    flex items-center justify-center gap-2"
                >
                  {bookingLoading
                    ? <span className="w-4 h-4 border-2 border-white/30 border-t-white rounded-full animate-spin" />
                    : 'Request to Book'}
                </button>
              </form>
            ) : (
              <p className="text-red-400/80 text-sm text-center py-4">
                This property is currently unavailable.
              </p>
            )}
          </GlassCard>
        </div>
      </main>
    </div>
  )
}
