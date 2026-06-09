import { useState, useEffect } from 'react'
import { Search, Filter, X } from 'lucide-react'
import Navbar from '../../components/Navbar.jsx'
import GlassCard from '../../components/GlassCard.jsx'
import PropertyCard from '../../components/PropertyCard.jsx'
import LoadingSpinner from '../../components/LoadingSpinner.jsx'
import { getAvailableProperties, searchProperties, filterProperties } from '../../api/propertyApi.js'

const TYPES = ['', 'PG', 'ROOM', 'HOSTEL']

export default function PropertySearch() {
  const [properties, setProperties] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [search, setSearch] = useState({ city: '', type: '', area: '' })
  const [filter, setFilter] = useState({ minRent: '', maxRent: '' })
  const [showFilter, setShowFilter] = useState(false)

  const fetchAll = () => {
    setLoading(true); setError('')
    getAvailableProperties()
      .then(({ data: res }) => {
        if (res.success) setProperties(res.data ?? [])
        else setError(res.message)
      })
      .catch(() => setError('Could not load properties.'))
      .finally(() => setLoading(false))
  }

  useEffect(() => { fetchAll() }, [])

  const handleSearch = async (e) => {
    e.preventDefault()
    if (!search.city && !search.type && !search.area) { fetchAll(); return }
    setLoading(true); setError('')
    try {
      const params = {}
      if (search.city)  params.city = search.city
      if (search.type)  params.type = search.type
      if (search.area)  params.area = search.area
      const { data: res } = await searchProperties(params)
      if (res.success) setProperties(res.data ?? [])
      else setError(res.message)
    } catch { setError('Search failed.') }
    finally { setLoading(false) }
  }

  const handleFilter = async (e) => {
    e.preventDefault()
    setLoading(true); setError('')
    try {
      const params = {}
      if (search.city)   params.city    = search.city
      if (search.type)   params.type    = search.type
      if (filter.minRent) params.minRent = filter.minRent
      if (filter.maxRent) params.maxRent = filter.maxRent
      const { data: res } = await filterProperties(params)
      if (res.success) setProperties(res.data ?? [])
      else setError(res.message)
    } catch { setError('Filter failed.') }
    finally { setLoading(false) }
  }

  const inputClass =
    'px-3 py-2.5 rounded-xl bg-white/10 border border-white/20 text-white text-sm ' +
    'placeholder-white/30 focus:outline-none focus:border-cyan-400/60 ' +
    'backdrop-blur-sm transition-all duration-200'

  return (
    <div className="min-h-screen bg-gradient-to-br from-[#0f2027] via-[#203a43] to-[#2c5364]">
      <Navbar />
      <main className="max-w-7xl mx-auto px-4 py-8">

        <h1 className="font-display font-bold text-3xl text-white mb-6">
          Find a Room
        </h1>

        {/* Search bar */}
        <GlassCard className="p-5 mb-6">
          <form onSubmit={handleSearch} className="flex flex-col sm:flex-row gap-3">
            <div className="flex-1">
              <label htmlFor="city" className="sr-only">City</label>
              <input
                id="city" placeholder="City (e.g. Pune)"
                value={search.city}
                onChange={(e) => setSearch({ ...search, city: e.target.value })}
                className={`${inputClass} w-full`}
              />
            </div>

            <div>
              <label htmlFor="type" className="sr-only">Property type</label>
              <select
                id="type"
                value={search.type}
                onChange={(e) => setSearch({ ...search, type: e.target.value })}
                className={`${inputClass} bg-[#1a3040]`}
              >
                {TYPES.map((t) => (
                  <option key={t} value={t}>{t || 'All types'}</option>
                ))}
              </select>
            </div>

            <div className="flex-1">
              <label htmlFor="area" className="sr-only">Area</label>
              <input
                id="area" placeholder="Area / Locality"
                value={search.area}
                onChange={(e) => setSearch({ ...search, area: e.target.value })}
                className={`${inputClass} w-full`}
              />
            </div>

            <button
              type="submit"
              className="px-5 py-2.5 rounded-xl font-semibold text-sm text-white
                bg-gradient-to-r from-[#667eea] to-[#764ba2]
                hover:shadow-lg hover:shadow-purple-500/25 transition-all duration-200
                cursor-pointer flex items-center gap-2"
            >
              <Search size={15} aria-hidden="true" />
              Search
            </button>

            <button
              type="button"
              onClick={() => setShowFilter((v) => !v)}
              className="px-4 py-2.5 rounded-xl text-sm text-white/70
                bg-white/10 border border-white/15 hover:bg-white/20
                transition-all duration-200 cursor-pointer flex items-center gap-2"
              aria-expanded={showFilter}
              aria-label="Toggle filters"
            >
              <Filter size={15} aria-hidden="true" />
              Filter
            </button>
          </form>

          {/* Price filter row */}
          {showFilter && (
            <form onSubmit={handleFilter} className="flex flex-col sm:flex-row gap-3 mt-4 pt-4 border-t border-white/10">
              <div className="flex-1">
                <label htmlFor="minRent" className="sr-only">Min rent</label>
                <input
                  id="minRent" type="number" placeholder="Min rent (₹)"
                  value={filter.minRent}
                  onChange={(e) => setFilter({ ...filter, minRent: e.target.value })}
                  className={`${inputClass} w-full`}
                />
              </div>
              <div className="flex-1">
                <label htmlFor="maxRent" className="sr-only">Max rent</label>
                <input
                  id="maxRent" type="number" placeholder="Max rent (₹)"
                  value={filter.maxRent}
                  onChange={(e) => setFilter({ ...filter, maxRent: e.target.value })}
                  className={`${inputClass} w-full`}
                />
              </div>
              <button
                type="submit"
                className="px-5 py-2.5 rounded-xl text-sm font-medium text-white
                  bg-cyan-500/20 border border-cyan-400/30
                  hover:bg-cyan-500/30 transition-all duration-200 cursor-pointer"
              >
                Apply
              </button>
              <button
                type="button"
                onClick={() => { setFilter({ minRent: '', maxRent: '' }); fetchAll() }}
                className="px-4 py-2.5 rounded-xl text-sm text-white/50
                  hover:text-white/80 transition-colors cursor-pointer
                  flex items-center gap-1"
              >
                <X size={14} aria-hidden="true" /> Clear
              </button>
            </form>
          )}
        </GlassCard>

        {/* Results */}
        {loading && <LoadingSpinner color="border-t-cyan-400" />}

        {!loading && error && (
          <p className="text-red-400 text-sm text-center py-10">{error}</p>
        )}

        {!loading && !error && properties.length === 0 && (
          <div className="text-center py-16 text-white/40">
            <Building2 size={40} className="mx-auto mb-3 opacity-30" aria-hidden="true" />
            <p>No properties found. Try adjusting your search.</p>
          </div>
        )}

        {!loading && !error && properties.length > 0 && (
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-5">
            {properties.map((p) => (
              <PropertyCard
                key={p.id}
                property={p}
                linkTo={`/tenant/properties/${p.id}`}
              />
            ))}
          </div>
        )}
      </main>
    </div>
  )
}
