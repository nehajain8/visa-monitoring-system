import axios from 'axios'

export async function getOverstayers() {
  const res = await axios.get('/api/overstayers')
  return res.data
}
