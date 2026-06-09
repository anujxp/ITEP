import api from './axios'

// ── Tenant ─────────────────────────────────────────────────────────────────

/** GET /properties/available */
export const getAvailableProperties = () =>
  api.get('/properties/available')

/** GET /properties/search?city=&type=&area= */
export const searchProperties = (params) =>
  api.get('/properties/search', { params })

/** GET /properties/filter?city=&type=&minRent=&maxRent= */
export const filterProperties = (params) =>
  api.get('/properties/filter', { params })

/** GET /properties/:id */
export const getPropertyById = (id) =>
  api.get(`/properties/${id}`)

// ── Host ───────────────────────────────────────────────────────────────────

/** GET /properties/my-properties */
export const getMyProperties = () =>
  api.get('/properties/my-properties')

/** POST /properties  (body: PropertyDTO) */
export const addProperty = (data) =>
  api.post('/properties', data)

/** PUT /properties/:id */
export const updateProperty = (id, data) =>
  api.put(`/properties/${id}`, data)

/** DELETE /properties/:id */
export const deleteProperty = (id) =>
  api.delete(`/properties/${id}`)

// ── Admin ──────────────────────────────────────────────────────────────────

/** GET /properties/admin/pending */
export const getPendingProperties = () =>
  api.get('/properties/admin/pending')

/**
 * PATCH /properties/admin/:id/review
 * body: { action: 'APPROVE' | 'REJECT', reason?: string }
 */
export const reviewProperty = (id, data) =>
  api.patch(`/properties/admin/${id}/review`, data)
