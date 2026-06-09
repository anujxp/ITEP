import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { Users, Plus, ToggleLeft, ToggleRight, Trash2, CheckCircle } from 'lucide-react'
import Navbar from '../../components/Navbar.jsx'
import GlassCard from '../../components/GlassCard.jsx'
import LoadingSpinner from '../../components/LoadingSpinner.jsx'
import Toast from '../../components/Toast.jsx'
import { getAllHosts, approveHost, toggleHostStatus, deleteHost } from '../../api/adminApi.js'

export default function ManageHosts() {
  const [hosts, setHosts] = useState([])
  const [loading, setLoading] = useState(true)
  const [toast, setToast] = useState(null)

  const showToast = (message, type = 'error') => setToast({ message, type })

  const fetchHosts = () => {
    setLoading(true)
    getAllHosts()
      .then(({ data: res }) => { if (res.success) setHosts(res.data ?? []) })
      .catch(() => {})
      .finally(() => setLoading(false))
  }

  useEffect(() => { fetchHosts() }, [])

  const handleApprove = async (id) => {
    try {
      const { data: res } = await approveHost(id)
      if (res.success) { showToast('Host approved.', 'success'); fetchHosts() }
      else showToast(res.message)
    } catch (err) { showToast(err.response?.data?.message || 'Failed.') }
  }

  const handleToggle = async (id) => {
    try {
      const { data: res } = await toggleHostStatus(id)
      if (res.success) { showToast('Status updated.', 'success'); fetchHosts() }
      else showToast(res.message)
    } catch (err) { showToast(err.response?.data?.message || 'Failed.') }
  }

  const handleDelete = async (id) => {
    if (!window.confirm('Delete this host? This cannot be undone.')) return
    try {
      const { data: res } = await deleteHost(id)
      if (res.success) { showToast('Host deleted.', 'success'); fetchHosts() }
      else showToast(res.message)
    } catch (err) { showToast(err.response?.data?.message || 'Failed.') }
  }

  if (loading) return <LoadingSpinner color="border-t-red-400" />

  return (
    <div className="min-h-screen bg-gradient-to-br from-[#0a0a0a] via-[#1a0000] to-[#2d0505]">
      <Navbar />
      <Toast toast={toast} onClose={() => setToast(null)} />
      <main className="max-w-6xl mx-auto px-4 py-8">

        <div className="flex items-center justify-between mb-6">
          <h1 className="font-display font-bold text-3xl text-white">Manage Hosts</h1>
          <Link
            to="/admin/hosts/create"
            className="inline-flex items-center gap-2 px-5 py-2.5 rounded-xl
              font-semibold text-sm text-white
              bg-gradient-to-r from-[#ff4444] to-[#cc0000]
              hover:shadow-lg hover:shadow-red-500/25 hover:-translate-y-0.5
              transition-all duration-200"
          >
            <Plus size={15} aria-hidden="true" />
            New Host
          </Link>
        </div>

        {hosts.length === 0 ? (
          <div className="text-center py-20 text-white/40">
            <Users size={40} className="mx-auto mb-3 opacity-30" aria-hidden="true" />
            <p>No hosts yet. Create one to get started.</p>
          </div>
        ) : (
          <div className="flex flex-col gap-4">
            {hosts.map((host) => (
              <GlassCard key={host.id} className="p-5">
                <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
                  <div className="flex flex-col gap-1">
                    <div className="flex items-center gap-2 flex-wrap">
                      <h2 className="font-semibold text-white">{host.fullName}</h2>
                      <span className={`px-2 py-0.5 rounded-full text-xs border font-medium
                        ${host.isHostApproved
                          ? 'bg-green-500/20 text-green-300 border-green-500/30'
                          : 'bg-yellow-500/20 text-yellow-300 border-yellow-500/30'}`}>
                        {host.isHostApproved ? 'Approved' : 'Pending'}
                      </span>
                      <span className={`px-2 py-0.5 rounded-full text-xs border font-medium
                        ${host.isActive
                          ? 'bg-blue-500/20 text-blue-300 border-blue-500/30'
                          : 'bg-gray-500/20 text-gray-400 border-gray-500/30'}`}>
                        {host.isActive ? 'Active' : 'Inactive'}
                      </span>
                    </div>
                    <p className="text-white/50 text-sm">{host.email}</p>
                    {host.businessName && (
                      <p className="text-white/40 text-xs">{host.businessName}</p>
                    )}
                  </div>

                  <div className="flex items-center gap-2 flex-wrap flex-shrink-0">
                    {!host.isHostApproved && (
                      <button
                        onClick={() => handleApprove(host.id)}
                        className="flex items-center gap-1.5 px-3 py-2 rounded-lg text-xs
                          font-medium text-white bg-green-500/20 border border-green-500/30
                          hover:bg-green-500/30 transition-all cursor-pointer"
                      >
                        <CheckCircle size={13} aria-hidden="true" />
                        Approve
                      </button>
                    )}
                    <button
                      onClick={() => handleToggle(host.id)}
                      className="flex items-center gap-1.5 px-3 py-2 rounded-lg text-xs
                        font-medium text-white bg-white/10 border border-white/15
                        hover:bg-white/20 transition-all cursor-pointer"
                      aria-label={host.isActive ? 'Deactivate host' : 'Activate host'}
                    >
                      {host.isActive
                        ? <ToggleRight size={14} className="text-green-400" aria-hidden="true" />
                        : <ToggleLeft size={14} className="text-gray-400" aria-hidden="true" />}
                      {host.isActive ? 'Deactivate' : 'Activate'}
                    </button>
                    <button
                      onClick={() => handleDelete(host.id)}
                      className="flex items-center gap-1.5 px-3 py-2 rounded-lg text-xs
                        font-medium text-white bg-red-500/20 border border-red-500/30
                        hover:bg-red-500/30 transition-all cursor-pointer"
                      aria-label={`Delete ${host.fullName}`}
                    >
                      <Trash2 size={13} aria-hidden="true" />
                      Delete
                    </button>
                  </div>
                </div>
              </GlassCard>
            ))}
          </div>
        )}
      </main>
    </div>
  )
}
