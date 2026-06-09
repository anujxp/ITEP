import { useState, useEffect } from 'react'
import { Building2, CheckCircle, XCircle } from 'lucide-react'
import Navbar from '../../components/Navbar.jsx'
import GlassCard from '../../components/GlassCard.jsx'
import LoadingSpinner from '../../components/LoadingSpinner.jsx'
import Toast from '../../components/Toast.jsx'
import StatusBadge from '../../components/StatusBadge.jsx'
import { getPendingProperties, reviewProperty } from '../../api/propertyApi.js'

export default function PendingProperties() {
  const [properties, setProperties] = useState([])
  const [loading, setLoading] = useState(true)
  const [toast, setToast] = useState(null)
  // Reject modal state
  const [rejectModal, setRejectModal] = useState(null)
  const [reason, setReason] = useState('')
  const [actionLoading, setActionLoading] = useState(false)

  const showToast = (message, type = 'error') => setToast({ message, type })

  const fetchPending = () => {
    setLoading(true)
    getPendingProperties()
      .then(({ data: res }) => {
        if (res.success) setProperties(res.data ?? [])
      })
      .catch(() => {})
      .finally(() => setLoading(false))
  }

  useEffect(() => { fetchPending() }, [])

  const handleApprove = async (id) => {
    setActionLoading(true)
    try {
      const { data: res } = await reviewProperty(id, { action: 'APPROVE' })
      if (res.success) {
        showToast('Property approved.', 'success')
        fetchPending()
      } else showToast(res.message)
    } catch (err) {
      showToast(err.response?.data?.message || 'Approve failed.')
    } finally { setActionLoading(false) }
  }

  const handleReject = async (e) => {
    e.preventDefault()
    if (!rejectModal) return
    setActionLoading(true)
    try {
      const { data: res } = await reviewProperty(rejectModal, { action: 'REJECT', reason })
      if (res.success) {
        showToast('Property rejected.', 'success')
        setRejectModal(null); setReason('')
        fetchPending()
      } else showToast(res.message)
    } catch (err) {
      showToast(err.response?.data?.message || 'Reject failed.')
    } finally { setActionLoading(false) }
  }

  if (loading) return <LoadingSpinner color="border-t-red-400" />

  return (
    <div className="min-h-screen bg-gradient-to-br from-[#0a0a0a] via-[#1a0000] to-[#2d0505]">
      <Navbar />
      <Toast toast={toast} onClose={() => setToast(null)} />
      <main className="max-w-6xl mx-auto px-4 py-8">

        <h1 className="font-display font-bold text-3xl text-white mb-6">
          Pending Properties
        </h1>

        {properties.length === 0 ? (
          <div className="text-center py-20 text-white/40">
            <Building2 size={40} className="mx-auto mb-3 opacity-30" aria-hidden="true" />
            <p>No properties pending approval.</p>
          </div>
        ) : (
          <div className="flex flex-col gap-4">
            {properties.map((p) => (
              <GlassCard key={p.id} className="p-6">
                <div className="flex flex-col sm:flex-row sm:items-start sm:justify-between gap-4">
                  <div className="flex flex-col gap-2 flex-1">
                    <div className="flex items-center gap-3 flex-wrap">
                      <h2 className="font-display font-semibold text-white">{p.title}</h2>
                      <StatusBadge status={p.approvalStatus} />
                    </div>
                    <p className="text-white/50 text-sm">
                      {p.area}, {p.city} · {p.propertyType} · ₹{p.rentAmount?.toLocaleString('en-IN')}/mo
                    </p>
                    {p.description && (
                      <p className="text-white/40 text-sm line-clamp-2">{p.description}</p>
                    )}
                    <p className="text-white/30 text-xs">Host ID: {p.hostId}</p>
                  </div>

                  <div className="flex gap-3 flex-shrink-0">
                    <button
                      onClick={() => handleApprove(p.id)}
                      disabled={actionLoading}
                      className="flex items-center gap-2 px-4 py-2.5 rounded-xl text-sm
                        font-semibold text-white bg-green-500/20 border border-green-500/30
                        hover:bg-green-500/30 transition-all duration-200 cursor-pointer
                        disabled:opacity-50 disabled:cursor-not-allowed"
                    >
                      <CheckCircle size={15} aria-hidden="true" />
                      Approve
                    </button>
                    <button
                      onClick={() => { setRejectModal(p.id); setReason('') }}
                      className="flex items-center gap-2 px-4 py-2.5 rounded-xl text-sm
                        font-semibold text-white bg-red-500/20 border border-red-500/30
                        hover:bg-red-500/30 transition-all duration-200 cursor-pointer"
                    >
                      <XCircle size={15} aria-hidden="true" />
                      Reject
                    </button>
                  </div>
                </div>
              </GlassCard>
            ))}
          </div>
        )}
      </main>

      {/* Reject modal */}
      {rejectModal && (
        <div
          className="fixed inset-0 z-50 flex items-center justify-center px-4 bg-black/60 backdrop-blur-sm"
          role="dialog" aria-modal="true" aria-labelledby="reject-title"
        >
          <GlassCard className="w-full max-w-sm p-7">
            <h2 id="reject-title" className="font-display font-bold text-xl text-white mb-1">
              Reject Property
            </h2>
            <p className="text-white/50 text-sm mb-5">Provide a reason for the host.</p>
            <form onSubmit={handleReject} className="flex flex-col gap-4">
              <div className="flex flex-col gap-1.5">
                <label htmlFor="reason" className="text-white/70 text-sm font-medium">
                  Rejection reason
                </label>
                <textarea
                  id="reason" rows="3"
                  value={reason} onChange={(e) => setReason(e.target.value)}
                  placeholder="e.g. Incomplete information, invalid address…"
                  className="w-full px-4 py-3 rounded-xl bg-white/10 border border-white/20
                    text-white placeholder-white/30 resize-none
                    focus:outline-none focus:border-red-400/60 transition-all duration-200 text-sm"
                />
              </div>
              <div className="flex gap-3">
                <button
                  type="submit"
                  disabled={actionLoading}
                  className="flex-1 py-2.5 rounded-xl font-semibold text-sm text-white
                    bg-gradient-to-r from-[#ff4444] to-[#cc0000]
                    hover:shadow-lg transition-all duration-200 cursor-pointer
                    disabled:opacity-50 disabled:cursor-not-allowed"
                >
                  {actionLoading ? 'Rejecting…' : 'Confirm Reject'}
                </button>
                <button
                  type="button"
                  onClick={() => setRejectModal(null)}
                  className="px-4 py-2.5 rounded-xl text-sm text-white/60
                    bg-white/10 hover:bg-white/20 transition-all duration-200 cursor-pointer"
                >
                  Cancel
                </button>
              </div>
            </form>
          </GlassCard>
        </div>
      )}
    </div>
  )
}
