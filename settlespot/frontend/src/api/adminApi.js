import api from './axios'

/**
 * POST /admin/hosts
 * body: { fullName, email, phoneNumber, businessName, officeAddress }
 */
export const createHost = (data) =>
  api.post('/admin/hosts', data)

/** GET /admin/hosts */
export const getAllHosts = () =>
  api.get('/admin/hosts')

/** GET /admin/tenants */
export const getAllTenants = () =>
  api.get('/admin/tenants')

/** PUT /admin/hosts/:id */
export const updateHost = (id, data) =>
  api.put(`/admin/hosts/${id}`, data)

/** PATCH /admin/hosts/:id/approve */
export const approveHost = (id) =>
  api.patch(`/admin/hosts/${id}/approve`)

/** PATCH /admin/hosts/:id/toggle-status */
export const toggleHostStatus = (id) =>
  api.patch(`/admin/hosts/${id}/toggle-status`)

/** DELETE /admin/hosts/:id */
export const deleteHost = (id) =>
  api.delete(`/admin/hosts/${id}`)
