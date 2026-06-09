import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { PlusCircle, X } from 'lucide-react'
import Navbar from '../../components/Navbar.jsx'
import GlassCard from '../../components/GlassCard.jsx'
import Toast from '../../components/Toast.jsx'
import { addProperty } from '../../api/propertyApi.js'

const TYPES = ['PG', 'ROOM', 'HOSTEL']

const INITIAL = {
  title: '', description: '', city: '', area: '', address: '',
  rentAmount: '', propertyType: 'PG',
}

export default function AddProperty() {
  const navigate = useNavigate()
  const [form, setForm] = useState(INITIAL)
  const [images, setImages] = useState([''])
  const [loading, setLoading] = useState(false)
  const [toast, setToast] = useState(null)

  const showToast = (message, type = 'error') => setToast({ message, type })

  const handleChange = (e) =>
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }))

  const handleImageChange = (i, value) => {
    setImages((prev) => prev.map((url, idx) => (idx === i ? value : url)))
  }

  const addImageRow = () => setImages((prev) => [...prev, ''])
  const removeImageRow = (i) => setImages((prev) => prev.filter((_, idx) => idx !== i))

  const handleSubmit = async (e) => {
    e.preventDefault()
    if (!form.title || !form.city || !form.rentAmount) {
      showToast('Title, city, and rent are required.')
      return
    }
    setLoading(true)
    try {
      const payload = {
        ...form,
        rentAmount: parseFloat(form.rentAmount),
        images: images.filter((url) => url.trim() !== ''),
      }
      const { data: res } = await addProperty(payload)
      if (res.success) {
        showToast('Property submitted for admin approval!', 'success')
        setTimeout(() => navigate('/host/properties'), 1200)
      } else {
        if (typeof res.data === 'object') {
          showToast(Object.values(res.data).join(' · '))
        } else {
          showToast(res.message || 'Submission failed.')
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
    'placeholder-white/30 focus:outline-none focus:border-violet-400/60 focus:bg-white/15 ' +
    'backdrop-blur-sm transition-all duration-200 text-sm'

  return (
    <div className="min-h-screen bg-gradient-to-br from-[#1a0533] via-[#2d1057] to-[#4a1080]">
      <Navbar />
      <Toast toast={toast} onClose={() => setToast(null)} />
      <main className="max-w-2xl mx-auto px-4 py-8">

        <h1 className="font-display font-bold text-3xl text-white mb-6">
          Add Property
        </h1>

        <GlassCard className="p-8">
          <p className="text-white/50 text-sm mb-6">
            Your property will go to admin review before it's visible to tenants.
          </p>

          <form onSubmit={handleSubmit} noValidate className="flex flex-col gap-5">

            {/* Title */}
            <div className="flex flex-col gap-1.5">
              <label htmlFor="title" className="text-white/70 text-sm font-medium">
                Property title <span className="text-red-400">*</span>
              </label>
              <input id="title" name="title" value={form.title} onChange={handleChange}
                placeholder="Cozy PG near IT Park" required className={inputClass} />
            </div>

            {/* Description */}
            <div className="flex flex-col gap-1.5">
              <label htmlFor="description" className="text-white/70 text-sm font-medium">
                Description
              </label>
              <textarea id="description" name="description" rows="3"
                value={form.description} onChange={handleChange}
                placeholder="Describe your property…"
                className={`${inputClass} resize-none`} />
            </div>

            {/* City + Area */}
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
              <div className="flex flex-col gap-1.5">
                <label htmlFor="city" className="text-white/70 text-sm font-medium">
                  City <span className="text-red-400">*</span>
                </label>
                <input id="city" name="city" value={form.city} onChange={handleChange}
                  placeholder="Pune" required className={inputClass} />
              </div>
              <div className="flex flex-col gap-1.5">
                <label htmlFor="area" className="text-white/70 text-sm font-medium">
                  Area / Locality
                </label>
                <input id="area" name="area" value={form.area} onChange={handleChange}
                  placeholder="Koregaon Park" className={inputClass} />
              </div>
            </div>

            {/* Address */}
            <div className="flex flex-col gap-1.5">
              <label htmlFor="address" className="text-white/70 text-sm font-medium">
                Full address
              </label>
              <input id="address" name="address" value={form.address} onChange={handleChange}
                placeholder="123 Main St, Koregaon Park, Pune" className={inputClass} />
            </div>

            {/* Rent + Type */}
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
              <div className="flex flex-col gap-1.5">
                <label htmlFor="rentAmount" className="text-white/70 text-sm font-medium">
                  Monthly rent (₹) <span className="text-red-400">*</span>
                </label>
                <input id="rentAmount" name="rentAmount" type="number" min="0"
                  value={form.rentAmount} onChange={handleChange}
                  placeholder="8000" required className={inputClass} />
              </div>
              <div className="flex flex-col gap-1.5">
                <label htmlFor="propertyType" className="text-white/70 text-sm font-medium">
                  Property type
                </label>
                <select id="propertyType" name="propertyType"
                  value={form.propertyType} onChange={handleChange}
                  className={`${inputClass} bg-[#2d1057]`}>
                  {TYPES.map((t) => <option key={t} value={t}>{t}</option>)}
                </select>
              </div>
            </div>

            {/* Image URLs */}
            <div className="flex flex-col gap-2">
              <span className="text-white/70 text-sm font-medium">
                Image URLs (optional)
              </span>
              {images.map((url, i) => (
                <div key={i} className="flex gap-2">
                  <input
                    type="url"
                    value={url}
                    onChange={(e) => handleImageChange(i, e.target.value)}
                    placeholder="https://…"
                    aria-label={`Image URL ${i + 1}`}
                    className={`${inputClass} flex-1`}
                  />
                  {images.length > 1 && (
                    <button
                      type="button"
                      onClick={() => removeImageRow(i)}
                      className="p-2.5 rounded-xl text-white/40 hover:text-white/80
                        bg-white/10 hover:bg-white/20 transition-all cursor-pointer"
                      aria-label="Remove image"
                    >
                      <X size={15} aria-hidden="true" />
                    </button>
                  )}
                </div>
              ))}
              <button
                type="button"
                onClick={addImageRow}
                className="self-start flex items-center gap-1.5 text-violet-400 text-sm
                  hover:text-violet-300 transition-colors cursor-pointer mt-1"
              >
                <PlusCircle size={15} aria-hidden="true" />
                Add image URL
              </button>
            </div>

            <button
              type="submit"
              disabled={loading}
              className="w-full py-3 rounded-xl font-semibold text-white
                bg-gradient-to-r from-[#c77dff] to-[#7b2ff7]
                hover:shadow-lg hover:shadow-violet-500/25 hover:-translate-y-0.5
                active:translate-y-0 transition-all duration-200 cursor-pointer
                disabled:opacity-50 disabled:cursor-not-allowed
                flex items-center justify-center gap-2 mt-2"
            >
              {loading
                ? <span className="w-5 h-5 border-2 border-white/30 border-t-white rounded-full animate-spin" />
                : 'Submit for Approval'}
            </button>
          </form>
        </GlassCard>
      </main>
    </div>
  )
}
