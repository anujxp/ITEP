import { useEffect } from 'react'
import { CheckCircle, XCircle } from 'lucide-react'

/**
 * Floating toast notification.
 *
 * Usage:
 *   const [toast, setToast] = useState(null)
 *   const showToast = (message, type = 'success') => {
 *     setToast({ message, type })
 *   }
 *   <Toast toast={toast} onClose={() => setToast(null)} />
 */
export default function Toast({ toast, onClose }) {
  useEffect(() => {
    if (!toast) return
    const timer = setTimeout(onClose, 3500)
    return () => clearTimeout(timer)
  }, [toast, onClose])

  if (!toast) return null

  const isSuccess = toast.type === 'success'

  return (
    <div
      role="alert"
      aria-live="polite"
      className={`
        fixed top-5 right-5 z-50 flex items-center gap-3
        px-5 py-4 rounded-xl backdrop-blur-xl border shadow-2xl
        animate-slide-in cursor-pointer
        ${isSuccess
          ? 'bg-green-500/20 border-green-500/30 text-green-300'
          : 'bg-red-500/20 border-red-500/30 text-red-300'}
      `}
      onClick={onClose}
    >
      {isSuccess
        ? <CheckCircle size={18} aria-hidden="true" />
        : <XCircle size={18} aria-hidden="true" />}
      <span className="text-sm font-medium">{toast.message}</span>
    </div>
  )
}
