import api from './axios'

/**
 * POST /auth/login
 * Body: { email, password }
 * Returns ApiResponse<AuthResponse>
 */
export const login = (credentials) =>
  api.post('/auth/login', credentials)

/**
 * POST /auth/tenants/register
 * Body: { fullName, email, password, phoneNumber, age, occupation, permanentAddress }
 * Returns ApiResponse<UserResponse>
 */
export const registerTenant = (data) =>
  api.post('/auth/tenants/register', data)
