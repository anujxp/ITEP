import { CalendarDays, User, IndianRupee, Star } from 'lucide-react'
import StatusBadge from './StatusBadge.jsx'
import GlassCard from './GlassCard.jsx'

/**
 * Reusable booking card.
 * Props:
 *   booking       — booking object from API
 *   onCancel      — tenant cancel handler
 *   onRate        — tenant rate handler
 *   onApprove     — host approve handler
 *   onReject      — host reject handler
 */
export default function BookingCard({
  booking,
  onCancel,
  onRate,
  onApprove,
  onReject,
}) {
  const today = new Date()
  const checkOut = new Date(booking.checkOutDate)
  const canRate =
    !booking.isRated &&
    booking.status === 'APPROVED' &&
    checkOut < today

  return (
    <GlassCard className="p-5 flex flex-col gap-3">
      {/* Header */}
      <div className="flex items-start justify-between gap-2">
        <h3 className="font-display font-semibold text-white text-sm leading-snug">
          {booking.propertyTitle}
        </h3>
        <StatusBadge status={booking.status} />
      </div>

      {/* Tenant / Host name */}
      {(booking.tenantName || booking.hostName) && (
        <div className="flex items-center gap-1.5 text-white/50 text-xs">
          <User size={12} aria-hidden="true" />
          {booking.tenantName ?? booking.hostName}
        </div>
      )}

      {/* Dates */}
      <div className="flex items-center gap-1.5 text-white/60 text-xs">
        <CalendarDays size={13} aria-hidden="true" />
        {booking.checkInDate} → {booking.checkOutDate}
      </div>

      {/* Amount */}
      <div className="flex items-center gap-0.5 text-white/80 text-sm font-semibold">
        <IndianRupee size={14} aria-hidden="true" />
        {booking.totalAmount?.toLocaleString('en-IN')}
      </div>

      {/* Rating */}
      {booking.isRated && (
        <div className="flex items-center gap-1 text-yellow-400 text-xs">
          {Array.from({ length: booking.rating }).map((_, i) => (
            <Star key={i} size={12} fill="currentColor" aria-hidden="true" />
          ))}
          {booking.ratingComment && (
            <span className="text-white/40 ml-1">"{booking.ratingComment}"</span>
          )}
        </div>
      )}

      {/* Action buttons */}
      <div className="flex flex-wrap gap-2 mt-1">
        {onCancel && ['PENDING', 'APPROVED'].includes(booking.status) && (
          <button
            onClick={() => onCancel(booking.id)}
            className="px-3 py-1.5 rounded-lg text-xs font-medium text-white
              bg-white/10 hover:bg-white/20 border border-white/15
              transition-all duration-200 cursor-pointer"
          >
            Cancel
          </button>
        )}

        {onRate && canRate && (
          <button
            onClick={() => onRate(booking)}
            className="px-3 py-1.5 rounded-lg text-xs font-medium text-white
              bg-gradient-to-r from-yellow-500/70 to-orange-500/70
              hover:shadow-lg hover:shadow-yellow-500/20
              transition-all duration-200 cursor-pointer"
          >
            Rate Stay
          </button>
        )}

        {onApprove && booking.status === 'PENDING' && (
          <button
            onClick={() => onApprove(booking.id)}
            className="px-3 py-1.5 rounded-lg text-xs font-medium text-white
              bg-gradient-to-r from-green-500/70 to-emerald-600/70
              hover:shadow-lg hover:shadow-green-500/20
              transition-all duration-200 cursor-pointer"
          >
            Approve
          </button>
        )}

        {onReject && booking.status === 'PENDING' && (
          <button
            onClick={() => onReject(booking.id)}
            className="px-3 py-1.5 rounded-lg text-xs font-medium text-white
              bg-gradient-to-r from-red-500/70 to-red-700/70
              hover:shadow-lg hover:shadow-red-500/20
              transition-all duration-200 cursor-pointer"
          >
            Reject
          </button>
        )}
      </div>
    </GlassCard>
  )
}
