/**
 * Full-page centered spinner.
 * Pass `color` prop to match the active theme accent.
 */
export default function LoadingSpinner({ color = 'border-t-cyan-400' }) {
  return (
    <div className="min-h-screen flex items-center justify-center">
      <div
        className={`w-12 h-12 rounded-full border-2 border-white/20 ${color} animate-spin`}
        role="status"
        aria-label="Loading"
      />
    </div>
  )
}
