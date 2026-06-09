import { Link } from 'react-router-dom'
import { Home, Search, Star, Shield } from 'lucide-react'
import GlassCard from '../components/GlassCard.jsx'

const FEATURES = [
  {
    icon: Search,
    title: 'Find Your Room',
    desc: 'Browse PGs, hostels, and independent rooms across your city.',
  },
  {
    icon: Star,
    title: 'Verified Listings',
    desc: 'Every property goes through admin approval before it goes live.',
  },
  {
    icon: Shield,
    title: 'Secure Booking',
    desc: 'Book with confidence — hosts review and confirm your request.',
  },
]

export default function Landing() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-[#0f2027] via-[#203a43] to-[#2c5364] text-white">

      {/* ── Nav ──────────────────────────────────────────────── */}
      <header className="px-6 py-5 flex items-center justify-between max-w-7xl mx-auto">
        <div className="flex items-center gap-2 font-display font-bold text-xl text-cyan-400">
          <Home size={22} aria-hidden="true" />
          SettleSpot
        </div>
        <div className="flex items-center gap-3">
          <Link
            to="/login"
            className="px-4 py-2 rounded-lg text-sm text-white/70 hover:text-white
              hover:bg-white/10 transition-all duration-150"
          >
            Login
          </Link>
          <Link
            to="/register"
            className="px-4 py-2 rounded-xl text-sm font-semibold text-white
              bg-gradient-to-r from-[#667eea] to-[#764ba2]
              hover:shadow-lg hover:shadow-purple-500/25 hover:-translate-y-0.5
              transition-all duration-200"
          >
            Get Started
          </Link>
        </div>
      </header>

      {/* ── Hero ─────────────────────────────────────────────── */}
      <main>
        <section className="text-center px-6 pt-20 pb-24 max-w-3xl mx-auto animate-fade-up">
          <h1 className="font-display text-5xl sm:text-6xl font-bold leading-tight mb-5">
            Find Your Perfect{' '}
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-cyan-400 to-blue-400">
              Settle Spot
            </span>
          </h1>
          <p className="text-white/60 text-lg mb-10 max-w-xl mx-auto leading-relaxed">
            The easiest way to find and book PGs, hostels, and rooms.
            Transparent listings, real hosts, zero hassle.
          </p>
          <div className="flex flex-col sm:flex-row items-center justify-center gap-4">
            <Link
              to="/register"
              className="w-full sm:w-auto px-8 py-3.5 rounded-xl font-semibold text-white
                bg-gradient-to-r from-[#667eea] to-[#764ba2]
                hover:shadow-xl hover:shadow-purple-500/30 hover:-translate-y-0.5
                transition-all duration-200"
            >
              Find a Room
            </Link>
            <Link
              to="/login"
              className="w-full sm:w-auto px-8 py-3.5 rounded-xl font-semibold
                glass-card text-white/80 hover:text-white hover:bg-white/15
                transition-all duration-200"
            >
              I'm a Host
            </Link>
          </div>
        </section>

        {/* ── Features ─────────────────────────────────────────── */}
        <section className="px-6 pb-20 max-w-5xl mx-auto" aria-label="Features">
          <div className="grid grid-cols-1 sm:grid-cols-3 gap-6">
            {FEATURES.map(({ icon: Icon, title, desc }) => (
              <GlassCard key={title} className="p-7 text-center flex flex-col items-center gap-4">
                <div className="p-3 rounded-xl bg-cyan-400/10 text-cyan-400">
                  <Icon size={24} aria-hidden="true" />
                </div>
                <h2 className="font-display font-semibold text-white">{title}</h2>
                <p className="text-white/50 text-sm leading-relaxed">{desc}</p>
              </GlassCard>
            ))}
          </div>
        </section>
      </main>
    </div>
  )
}
