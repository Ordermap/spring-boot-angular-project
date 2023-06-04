export interface Users {
  page: number
  per_page: number
  total: number
  total_pages: number
  data: Daum[]
  support: Support
}

export interface Daum {
  id: number
  email: string
  first_name: string
  last_name: string
  avatar: string
}

export interface Support {
  url: string
  text: string
}
