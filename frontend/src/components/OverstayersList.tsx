type Props = { rows: any[] }

export default function OverstayersList({ rows }: Props) {
  if (!rows.length) return <p>No overstayers today! 🎉</p>
  return (
    <table>
      <thead>
        <tr>
          <th>Passport No</th>
          <th>Visa ID</th>
          <th>Expiry</th>
          <th>Last Entry</th>
          <th>Last Exit</th>
        </tr>
      </thead>
      <tbody>
        {rows.map(r => (
          <tr key={r.passportNo + r.visaId}>
            <td>{r.passportNo}</td>
            <td>{r.visaId}</td>
            <td>{r.expiryDate}</td>
            <td>{r.lastEntry}</td>
            <td>{r.lastExit}</td>
          </tr>
        ))}
      </tbody>
    </table>
  )
}
