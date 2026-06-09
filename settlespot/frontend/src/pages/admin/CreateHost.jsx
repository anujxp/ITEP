import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { UserPlus } from 'lucide-react'
import Navbar from '../../components/Navbar.jsx'
import GlassCard from '../../components/GlassCard.jsx'
import Toast from '../../components/Toast.jsx'
import { createHost } from '../../api/adminApi.js'

const INITIAL = {
  fullName: '',
  email: '',
  phoneNumber: '',
  businessName: '',
  officeAddress: '',
}

export default function CreateHost() {
  const navigate = useNavigate()
  const [form, setForm] = useState(INITIAL)
  const [loading, setLoading] = useState(false)
  const [toast, setToast] = useState(null)

  const showToast = (message, type = 'error') => setToast({ message, type })

  const handleChange = (e) =>
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }))

  const handleSubmit = async (e) => {
    e.preventDefault()
    if (!form.fullName || !form.email) {
      showToast('Full name and email are required.')
      return
    }
    setLoading(true)
    try {
      const { data: res } = await createHost(form)
      if (res.success) {
        showToast('Host created! Credentials sent via email.', 'success')
        setTimeout(() => navigate('/admin/hosts'), 1400)
      } else {
        if (typeof res.data === 'object') {
          showToast(Object.values(res.data).join(' · '))
        } else {
          showToast(res.message || 'Creation failed.')
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
    'placeholder-white/30 focus:outline-none focus:border-red-400/60 focus:bg-white/15 ' +
    'backdrop-blur-sm transition-all duration-200 text-sm'

  return (
    <div className="min-h-screen bg-gradient-to-br from-[#0a0a0a] via-[#1a0000] to-[#2d0505]">
      <Navbar />
      <Toast toast={toast} onClose={() => setToast(null)} />
      <main className="max-w-lg mx-auto px-4 py-8">

        <h1 className="font-display font-bold text-3xl text-white mb-6">
          Create Host
        </h1>

        <GlassCard className="p-8">
          <p className="text-white/50 text-sm mb-6">
            A random password will be generated and sent to the host's email.
          </p>

          <form onSubmit={handleSubmit} noValidate className="flex flex-col gap-5">
            <div className="flex flex-col gap-1.5">
              <label htmlFor="fullName" className="text-white/70 text-sm font-medium">
                Full name <span className="text-red-400">*</span>
              </label>
              <input id="fullName" name="fullName" type="text"
                value={form.fullName} onChange={handleChange}
                placeholder="Host Name" required className={inputClass} />
            </div>

            <div className="flex flex-col gap-1.5">
              <label htmlFor="email" className="text-white/70 text-sm font-medium">
                Email address <span className="text-red-400">*</span>
              </label>
              <input id="email" name="email" type="email"
                value={form.email} onChange={handleChange}
                placeholder="host@example.com" required className={inputClass} />
            </div>

            <div className="flex flex-col gap-1.5">
              <label htmlFor="phoneNumber" className="text-white/70 text-sm font-medium">
                Phone number
              </label>
              <input id="phoneNumber" name="phoneNumber" type="tel"
                value={form.phoneNumber} onChange={handleChange}
                placeholder="+91 98765 43210" className={inputClass} />
            </div>

            <div className="flex flex-col gap-1.5">
              <label htmlFor="businessName" className="text-white/70 text-sm font-medium">
                Business name
              </label>
              <input id="businessName" name="businessName" type="text"
                value={form.businessName} onChange={handleChange}
                placeholder="City Stays Pvt. Ltd." className={inputClass} />
            </div>

            <div className="flex flex-col gap-1.5">
              <label htmlFor="officeAddress" className="text-white/70 text-sm font-medium">
                Office address
              </label>
              <input id="officeAddress" name="officeAddress" type="text"
                value={form.officeAddress} onChange={handleChange}
                placeholder="Office 101, Biz Park, Pune" className={inputClass} />
            </div>

            <button
              type="submit"
              disabled={loading}
              className="w-full py-3 rounded-xl font-semibold text-white
                bg-gradient-to-r from-[#ff4444] to-[#cc0000]
                hover:shadow-lg hover:shadow-red-500/25 hover:-translate-y-0.5
                active:translate-y-0 transition-all duration-200 cursor-pointer
                disabled:opacity-50 disabled:cursor-not-allowed
                flex items-center justify-center gap-2 mt-2"
            >
              {loading
                ? <span className="w-5 h-5 border-2 border-white/30 border-t-white rounded-full animate-spin" />
                : <><UserPlus size={16} aria-hidden="true" /> Create Host</>}
            </button>
          </form>
        </GlassCard>
      </main>
    </div>
  )
}
