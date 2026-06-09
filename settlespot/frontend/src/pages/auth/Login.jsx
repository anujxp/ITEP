import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import { Home, Eye, EyeOff, LogIn } from 'lucide-react'
import { login as loginApi } from '../../api/authApi.js'
import { useAuth } from '../../context/AuthContext.jsx'
import GlassCard from '../../components/GlassCard.jsx'
import Toast from '../../components/Toast.jsx'

export default function Login() {
  const navigate = useNavigate()
  const { login } = useAuth()

  const [form, setForm] = useState({ email: '', password: '' })
  const [showPassword, setShowPassword] = useState(false)
  const [loading, setLoading] = useState(false)
  const [toast, setToast] = useState(null)

  const showToast = (message, type = 'error') => setToast({ message, type })

  const handleChange = (e) =>
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }))

  const handleSubmit = async (e) => {
    e.preventDefault()
    if (!form.email || !form.password) {
      showToast('Please fill in all fields.')
      return
    }
    setLoading(true)
    try {
      const { data: res } = await loginApi(form)
      if (res.success) {
        login(res.data, res.data.token)
        showToast('Welcome back!', 'success')
        const role = res.data.role
        setTimeout(() => {
          if (role === 'ADMIN') navigate('/admin/dashboard')
          else if (role === 'HOST') navigate('/host/dashboard')
          else navigate('/tenant/dashboard')
        }, 600)
      } else {
        showToast(res.message || 'Login failed.')
      }
    } catch (err) {
      showToast(err.response?.data?.message || 'Something went wrong.')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-[#0f2027] via-[#203a43] to-[#2c5364]
      flex items-center justify-center px-4">

      <Toast toast={toast} onClose={() => setToast(null)} />

      <div className="w-full max-w-md animate-fade-up">
        {/* Brand */}
        <Link
          to="/"
          className="flex items-center justify-center gap-2 mb-8 font-display
            font-bold text-2xl text-cyan-400"
        >
          <Home size={26} aria-hidden="true" />
          SettleSpot
        </Link>

        <GlassCard className="p-8">
          <h1 className="font-display font-bold text-2xl text-white mb-1">
            Welcome back
          </h1>
          <p className="text-white/50 text-sm mb-8">
            Sign in to continue to your account
          </p>

          <form onSubmit={handleSubmit} noValidate className="flex flex-col gap-5">
            {/* Email */}
            <div className="flex flex-col gap-1.5">
              <label htmlFor="email" className="text-white/70 text-sm font-medium">
                Email address
              </label>
              <input
                id="email"
                name="email"
                type="email"
                autoComplete="email"
                value={form.email}
                onChange={handleChange}
                placeholder="you@example.com"
                required
                className="w-full px-4 py-3 rounded-xl bg-white/10 border border-white/20
                  text-white placeholder-white/30
                  focus:outline-none focus:border-cyan-400/60 focus:bg-white/15
                  backdrop-blur-sm transition-all duration-200"
              />
            </div>

            {/* Password */}
            <div className="flex flex-col gap-1.5">
              <label htmlFor="password" className="text-white/70 text-sm font-medium">
                Password
              </label>
              <div className="relative">
                <input
                  id="password"
                  name="password"
                  type={showPassword ? 'text' : 'password'}
                  autoComplete="current-password"
                  value={form.password}
                  onChange={handleChange}
                  placeholder="••••••••"
                  required
                  className="w-full px-4 py-3 rounded-xl bg-white/10 border border-white/20
                    text-white placeholder-white/30 pr-11
                    focus:outline-none focus:border-cyan-400/60 focus:bg-white/15
                    backdrop-blur-sm transition-all duration-200"
                />
                <button
                  type="button"
                  onClick={() => setShowPassword((v) => !v)}
                  className="absolute right-3 top-1/2 -translate-y-1/2
                    text-white/40 hover:text-white/70 transition-colors cursor-pointer"
                  aria-label={showPassword ? 'Hide password' : 'Show password'}
                >
                  {showPassword
                    ? <EyeOff size={17} aria-hidden="true" />
                    : <Eye size={17} aria-hidden="true" />}
                </button>
              </div>
            </div>

            {/* Submit */}
            <button
              type="submit"
              disabled={loading}
              className="w-full py-3 rounded-xl font-semibold text-white
                bg-gradient-to-r from-[#667eea] to-[#764ba2]
                hover:shadow-lg hover:shadow-purple-500/25 hover:-translate-y-0.5
                active:translate-y-0 transition-all duration-200 cursor-pointer
                disabled:opacity-50 disabled:cursor-not-allowed flex items-center
                justify-center gap-2"
            >
              {loading
                ? <span className="w-5 h-5 border-2 border-white/30 border-t-white rounded-full animate-spin" />
                : <><LogIn size={17} aria-hidden="true" /> Sign In</>}
            </button>
          </form>

          <p className="text-white/40 text-sm text-center mt-6">
            New tenant?{' '}
            <Link to="/register" className="text-cyan-400 hover:text-cyan-300 underline">
              Create an account
            </Link>
          </p>
        </GlassCard>
      </div>
    </div>
  )
}
