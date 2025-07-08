import { useEffect, useState } from 'react'
import { getOverstayers } from './services/api'
import OverstayersList from './components/OverstayersList'

export default function App() {
  const [data, setData] = useState<any[]>([])

  useEffect(() => {
    getOverstayers().then(setData)
  }, [])

  return (
    <div style={{ padding: 20 }}>
      <h1>Visa Overstayers</h1>
      <OverstayersList rows={data} />
    </div>
  )
}
