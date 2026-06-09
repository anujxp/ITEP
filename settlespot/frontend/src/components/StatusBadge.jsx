const COLORS = {
  PENDING:          'bg-yellow-500/20 text-yellow-300 border-yellow-500/30',
  APPROVED:         'bg-green-500/20 text-green-300 border-green-500/30',
  REJECTED:         'bg-red-500/20 text-red-300 border-red-500/30',
  CANCELLED:        'bg-gray-500/20 text-gray-300 border-gray-500/30',
  PENDING_APPROVAL: 'bg-orange-500/20 text-orange-300 border-orange-500/30',
}

/**
 * Colored status pill.
 * <StatusBadge status="APPROVED" />
 */
export default function StatusBadge({ status }) {
  const classes = COLORS[status] ?? 'bg-white/10 text-white/60 border-white/20'
  const label = status?.replace(/_/g, ' ') ?? 'UNKNOWN'

  return (
    <span className={`px-3 py-1 rounded-full text-xs font-semibold border ${classes}`}>
      {label}
    </span>
  )
}
