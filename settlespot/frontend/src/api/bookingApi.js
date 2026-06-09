import api from './axios'

// ── Tenant ─────────────────────────────────────────────────────────────────

/**
 * POST /bookings
 * body: { propertyId, checkInDate, checkOutDate }
 */
export const createBooking = (data) =>
  api.post('/bookings', data)

/** GET /bookings/my-bookings */
export const getMyBookings = () =>
  api.get('/bookings/my-bookings')

/** DELETE /bookings/:id  (cancel) */
export const cancelBooking = (id) =>
  api.delete(`/bookings/${id}`)

/**
 * POST /bookings/:id/rate
 * body: { rating: 1-5, comment?: string }
 */
export const rateBooking = (id, data) =>
  api.post(`/bookings/${id}/rate`, data)

// ── Host ───────────────────────────────────────────────────────────────────

/** GET /bookings/host/pending */
export const getHostPendingBookings = () =>
  api.get('/bookings/host/pending')

/** GET /bookings/host/all */
export const getHostAllBookings = () =>
  api.get('/bookings/host/all')

/**
 * PATCH /bookings/:id/review?action=APPROVE|REJECT
 */
export const reviewBooking = (id, action) =>
  api.patch(`/bookings/${id}/review`, null, { params: { action } })

// ── Admin ──────────────────────────────────────────────────────────────────

/** GET /bookings/admin/all */
export const getAllBookings = () =>
  api.get('/bookings/admin/all')
