import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import PrivateRoute from './components/PrivateRoute.jsx'

// Public pages
import Landing from './pages/Landing.jsx'
import Login from './pages/auth/Login.jsx'
import TenantRegister from './pages/auth/TenantRegister.jsx'

// Tenant pages
import TenantDashboard from './pages/tenant/TenantDashboard.jsx'
import PropertySearch from './pages/tenant/PropertySearch.jsx'
import PropertyDetail from './pages/tenant/PropertyDetail.jsx'
import MyBookings from './pages/tenant/MyBookings.jsx'

// Host pages
import HostDashboard from './pages/host/HostDashboard.jsx'
import MyProperties from './pages/host/MyProperties.jsx'
import AddProperty from './pages/host/AddProperty.jsx'
import ManageBookings from './pages/host/ManageBookings.jsx'

// Admin pages
import AdminDashboard from './pages/admin/AdminDashboard.jsx'
import PendingProperties from './pages/admin/PendingProperties.jsx'
import ManageHosts from './pages/admin/ManageHosts.jsx'
import CreateHost from './pages/admin/CreateHost.jsx'

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* ── Public ─────────────────────────────────────────── */}
        <Route path="/" element={<Landing />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<TenantRegister />} />

        {/* ── Tenant ─────────────────────────────────────────── */}
        <Route
          path="/tenant/dashboard"
          element={
            <PrivateRoute allowedRole="TENANT">
              <TenantDashboard />
            </PrivateRoute>
          }
        />
        <Route
          path="/tenant/properties"
          element={
            <PrivateRoute allowedRole="TENANT">
              <PropertySearch />
            </PrivateRoute>
          }
        />
        <Route
          path="/tenant/properties/:id"
          element={
            <PrivateRoute allowedRole="TENANT">
              <PropertyDetail />
            </PrivateRoute>
          }
        />
        <Route
          path="/tenant/bookings"
          element={
            <PrivateRoute allowedRole="TENANT">
              <MyBookings />
            </PrivateRoute>
          }
        />

        {/* ── Host ───────────────────────────────────────────── */}
        <Route
          path="/host/dashboard"
          element={
            <PrivateRoute allowedRole="HOST">
              <HostDashboard />
            </PrivateRoute>
          }
        />
        <Route
          path="/host/properties"
          element={
            <PrivateRoute allowedRole="HOST">
              <MyProperties />
            </PrivateRoute>
          }
        />
        <Route
          path="/host/properties/add"
          element={
            <PrivateRoute allowedRole="HOST">
              <AddProperty />
            </PrivateRoute>
          }
        />
        <Route
          path="/host/bookings"
          element={
            <PrivateRoute allowedRole="HOST">
              <ManageBookings />
            </PrivateRoute>
          }
        />

        {/* ── Admin ──────────────────────────────────────────── */}
        <Route
          path="/admin/dashboard"
          element={
            <PrivateRoute allowedRole="ADMIN">
              <AdminDashboard />
            </PrivateRoute>
          }
        />
        <Route
          path="/admin/properties"
          element={
            <PrivateRoute allowedRole="ADMIN">
              <PendingProperties />
            </PrivateRoute>
          }
        />
        <Route
          path="/admin/hosts"
          element={
            <PrivateRoute allowedRole="ADMIN">
              <ManageHosts />
            </PrivateRoute>
          }
        />
        <Route
          path="/admin/hosts/create"
          element={
            <PrivateRoute allowedRole="ADMIN">
              <CreateHost />
            </PrivateRoute>
          }
        />

        {/* ── Fallback ───────────────────────────────────────── */}
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </BrowserRouter>
  )
}
