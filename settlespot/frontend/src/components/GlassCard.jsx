/**
 * Reusable glassmorphism card.
 * Uses the .glass-card class from index.css for the blur/backdrop effect
 * and Tailwind for padding/shadow.
 */
export default function GlassCard({ children, className = '' }) {
  return (
    <div className={`glass-card shadow-2xl shadow-black/30 ${className}`}>
      {children}
    </div>
  )
}
