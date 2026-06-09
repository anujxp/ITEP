import { MapPin, Home, Star, IndianRupee } from 'lucide-react'
import { useNavigate } from 'react-router-dom'
import StatusBadge from './StatusBadge.jsx'
import GlassCard from './GlassCard.jsx'

const TYPE_LABELS = { PG: 'PG', ROOM: 'Room', HOSTEL: 'Hostel' }

/**
 * Reusable property card.
 * Props:
 *   property   — property object from API
 *   showStatus — show approval status badge (host/admin view)
 *   onDelete   — optional delete handler (host view)
 *   linkTo     — navigate on card click (tenant view)
 */
export default function PropertyCard({
  property,
  showStatus = false,
  onDelete,
  linkTo,
}) {
  const navigate = useNavigate()

  const handleClick = () => {
    if (linkTo) navigate(linkTo)
  }

  return (
    <GlassCard
      className={`p-5 flex flex-col gap-3 transition-all duration-200
        ${linkTo ? 'cursor-pointer hover:-translate-y-1 hover:shadow-cyan-500/10' : ''}
      `}
      onClick={handleClick}
    >
      {/* Title row */}
      <div className="flex items-start justify-between gap-2">
        <h3 className="font-display font-semibold text-white text-base leading-snug line-clamp-2">
          {property.title}
        </h3>
        {showStatus && <StatusBadge status={property.approvalStatus} />}
      </div>

      {/* Location */}
      <div className="flex items-center gap-1.5 text-white/60 text-sm">
        <MapPin size={14} aria-hidden="true" />
        <span>{property.area}, {property.city}</span>
      </div>

      {/* Type + Rating */}
      <div className="flex items-center justify-between text-sm">
        <span className="flex items-center gap-1.5 text-white/50">
          <Home size={14} aria-hidden="true" />
          {TYPE_LABELS[property.propertyType] ?? property.propertyType}
        </span>
        {property.averageRating > 0 && (
          <span className="flex items-center gap-1 text-yellow-400">
            <Star size={13} fill="currentColor" aria-hidden="true" />
            {property.averageRating?.toFixed(1)}
            <span className="text-white/30">({property.totalRatings})</span>
          </span>
        )}
      </div>

      {/* Rent + Actions */}
      <div className="flex items-center justify-between mt-1">
        <span className="flex items-center gap-0.5 text-white font-semibold">
          <IndianRupee size={15} aria-hidden="true" />
          {property.rentAmount?.toLocaleString('en-IN')}
          <span className="text-white/40 font-normal text-xs ml-1">/mo</span>
        </span>

        {onDelete && (
          <button
            onClick={(e) => { e.stopPropagation(); onDelete(property.id) }}
            className="px-3 py-1.5 rounded-lg text-xs font-medium text-white
              bg-gradient-to-r from-red-500/80 to-red-700/80
              hover:shadow-lg hover:shadow-red-500/25 transition-all duration-200
              cursor-pointer"
            aria-label={`Delete ${property.title}`}
          >
            Delete
          </button>
        )}
      </div>
    </GlassCard>
  )
}
