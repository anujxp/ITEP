import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { Plus, Building2 } from 'lucide-react'
import Navbar from '../../components/Navbar.jsx'
import PropertyCard from '../../components/PropertyCard.jsx'
import LoadingSpinner from '../../components/LoadingSpinner.jsx'
import Toast from '../../components/Toast.jsx'
import { getMyProperties, deleteProperty } from '../../api/propertyApi.js'

export default function MyProperties() {
  const [properties, setProperties] = useState([])
  const [loading, setLoading] = useState(true)
  const [toast, setToast] = useState(null)

  const showToast = (message, type = 'error') => setToast({ message, type })

  const fetchProperties = () => {
    setLoading(true)
    getMyProperties()
      .then(({ data: res }) => {
        if (res.success) setProperties(res.data ?? [])
      })
      .catch(() => {})
      .finally(() => setLoading(false))
  }

  useEffect(() => { fetchProperties() }, [])

  const handleDelete = async (id) => {
    if (!window.confirm('Delete this property? This cannot be undone.')) return
    try {
      const { data: res } = await deleteProperty(id)
      if (res.success) {
        showToast('Property deleted.', 'success')
        fetchProperties()
      } else {
        showToast(res.message)
      }
    } catch (err) {
      showToast(err.response?.data?.message || 'Delete failed.')
    }
  }

  if (loading) return <LoadingSpinner color="border-t-violet-400" />

  return (
    <div className="min-h-screen bg-gradient-to-br from-[#1a0533] via-[#2d1057] to-[#4a1080]">
      <Navbar />
      <Toast toast={toast} onClose={() => setToast(null)} />
      <main className="max-w-7xl mx-auto px-4 py-8">

        <div className="flex items-center justify-between mb-6">
          <h1 className="font-display font-bold text-3xl text-white">My Properties</h1>
          <Link
            to="/host/properties/add"
            className="inline-flex items-center gap-2 px-5 py-2.5 rounded-xl
              font-semibold text-sm text-white
              bg-gradient-to-r from-[#c77dff] to-[#7b2ff7]
              hover:shadow-lg hover:shadow-violet-500/25 hover:-translate-y-0.5
              transition-all duration-200"
          >
            <Plus size={15} aria-hidden="true" />
            Add Property
          </Link>
        </div>

        {properties.length === 0 ? (
          <div className="text-center py-20 text-white/40">
            <Building2 size={40} className="mx-auto mb-3 opacity-30" aria-hidden="true" />
            <p>You haven't added any properties yet.</p>
            <Link to="/host/properties/add" className="text-violet-400 underline text-sm mt-2 inline-block">
              Add your first property
            </Link>
          </div>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-5">
            {properties.map((p) => (
              <PropertyCard
                key={p.id}
                property={p}
                showStatus
                onDelete={handleDelete}
              />
            ))}
          </div>
        )}
      </main>
    </div>
  )
}
