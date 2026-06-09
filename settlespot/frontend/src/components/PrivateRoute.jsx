import { Navigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext.jsx'

/**
 * Wraps a protected route.
 * - No user → redirect to /login
 * - Wrong role → redirect to /login
 */
export default function PrivateRoute({ children, allowedRole }) {
  const { user } = useAuth()

  if (!user) return <Navigate to="/login" replace />

  if (allowedRole && user.role !== allowedRole)
    return <Navigate to="/login" replace />

  return children
}
