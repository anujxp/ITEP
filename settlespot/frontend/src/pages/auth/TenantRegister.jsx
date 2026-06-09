import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import { Home, UserPlus } from 'lucide-react'
import { registerTenant } from '../../api/authApi.js'
import GlassCard from '../../components/GlassCard.jsx'
import Toast from '../../components/Toast.jsx'

const INITIAL = {
  fullName: '',
  email: '',
  password: '',
  phoneNumber: '',
  age: '',
  occupation: '',
  permanentAddress: '',
}

export default function TenantRegister() {
  const navigate = useNavigate()
  const [form, setForm] = useState(INITIAL)
  const [loading, setLoading] = useState(false)
  const [toast, setToast] = useState(null)

  const showToast = (message, type = 'error') => setToast({ message, type })

  const handleChange = (e) =>
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }))

  const handleSubmit = async (e) => {
    e.preventDefault()
    if (!form.fullName || !form.email || !form.password) {
      showToast('Full name, email, and password are required.')
      return
    }
    setLoading(true)
    try {
      const { data: res } = await registerTenant({
        ...form,
        age: form.age ? parseInt(form.age) : undefined,
      })
      if (res.success) {
        showToast('Account created! Please log in.', 'success')
        setTimeout(() => navigate('/login'), 1200)
      } else {
        // Handle validation errors (data might be an object of field errors)
        if (typeof res.data === 'object' && res.data !== null) {
          const msgs = Object.values(res.data).join(' · ')
          showToast(msgs)
        } else {
          showToast(res.message || 'Registration failed.')
        }
      }
    } catch (err) {
      showToast(err.response?.data?.message || 'Something went wrong.')
    } finally {
      setLoading(false)
    }
  }

  const inputClass =
    'w-full px-4 py-3 rounded-xl bg-white/10 border border-white/20 text-white ' +
    'placeholder-white/30 focus:outline-none focus:border-cyan-400/60 focus:bg-white/15 ' +
    'backdrop-blur-sm transition-all duration-200'

  return (
    <div className="min-h-screen bg-gradient-to-br from-[#0f2027] via-[#203a43] to-[#2c5364]
      flex items-center justify-center px-4 py-12">

      <Toast toast={toast} onClose={() => setToast(null)} />

      <div className="w-full max-w-lg animate-fade-up">
        <Link
          to="/"
          className="flex items-center justify-center gap-2 mb-8
            font-display font-bold text-2xl text-cyan-400"
        >
          <Home size={26} aria-hidden="true" />
          SettleSpot
        </Link>

        <GlassCard className="p-8">
          <h1 className="font-display font-bold text-2xl text-white mb-1">
            Create your account
          </h1>
          <p className="text-white/50 text-sm mb-8">
            Join thousands of tenants finding their perfect room
          </p>

          <form onSubmit={handleSubmit} noValidate className="flex flex-col gap-5">
            {/* Row: Full name + Email */}
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
              <div className="flex flex-col gap-1.5">
                <label htmlFor="fullName" className="text-white/70 text-sm font-medium">
                  Full name <span aria-hidden="true" className="text-red-400">*</span>
                </label>
                <input
                  id="fullName" name="fullName" type="text"
                  value={form.fullName} onChange={handleChange}
                  placeholder="John Doe" required className={inputClass}
                />
              </div>
              <div className="flex flex-col gap-1.5">
                <label htmlFor="email" className="text-white/70 text-sm font-medium">
                  Email <span aria-hidden="true" className="text-red-400">*</span>
                </label>
                <input
                  id="email" name="email" type="email"
                  value={form.email} onChange={handleChange}
                  placeholder="you@example.com" required className={inputClass}
                />
              </div>
            </div>

            {/* Password */}
            <div className="flex flex-col gap-1.5">
              <label htmlFor="password" className="text-white/70 text-sm font-medium">
                Password <span aria-hidden="true" className="text-red-400">*</span>
              </label>
              <input
                id="password" name="password" type="password"
                value={form.password} onChange={handleChange}
                placeholder="Min. 6 characters" required className={inputClass}
              />
            </div>

            {/* Row: Phone + Age */}
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
              <div className="flex flex-col gap-1.5">
                <label htmlFor="phoneNumber" className="text-white/70 text-sm font-medium">
                  Phone number
                </label>
                <input
                  id="phoneNumber" name="phoneNumber" type="tel"
                  value={form.phoneNumber} onChange={handleChange}
                  placeholder="+91 98765 43210" className={inputClass}
                />
              </div>
              <div className="flex flex-col gap-1.5">
                <label htmlFor="age" className="text-white/70 text-sm font-medium">
                  Age
                </label>
                <input
                  id="age" name="age" type="number" min="18"
                  value={form.age} onChange={handleChange}
                  placeholder="22" className={inputClass}
                />
              </div>
            </div>

            {/* Occupation */}
            <div className="flex flex-col gap-1.5">
              <label htmlFor="occupation" className="text-white/70 text-sm font-medium">
                Occupation
              </label>
              <input
                id="occupation" name="occupation" type="text"
                value={form.occupation} onChange={handleChange}
                placeholder="Student / Software Engineer..." className={inputClass}
              />
            </div>

            {/* Permanent Address */}
            <div className="flex flex-col gap-1.5">
              <label htmlFor="permanentAddress" className="text-white/70 text-sm font-medium">
                Permanent address
              </label>
              <input
                id="permanentAddress" name="permanentAddress" type="text"
                value={form.permanentAddress} onChange={handleChange}
                placeholder="123 Home St, City" className={inputClass}
              />
            </div>

            <button
              type="submit"
              disabled={loading}
              className="w-full py-3 rounded-xl font-semibold text-white
                bg-gradient-to-r from-[#667eea] to-[#764ba2]
                hover:shadow-lg hover:shadow-purple-500/25 hover:-translate-y-0.5
                active:translate-y-0 transition-all duration-200 cursor-pointer
                disabled:opacity-50 disabled:cursor-not-allowed
                flex items-center justify-center gap-2"
            >
              {loading
                ? <span className="w-5 h-5 border-2 border-white/30 border-t-white rounded-full animate-spin" />
                : <><UserPlus size={17} aria-hidden="true" /> Create Account</>}
            </button>
          </form>

          <p className="text-white/40 text-sm text-center mt-6">
            Already have an account?{' '}
            <Link to="/login" className="text-cyan-400 hover:text-cyan-300 underline">
              Sign in
            </Link>
          </p>
        </GlassCard>
      </div>
    </div>
  )
}
