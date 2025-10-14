'use client'
import { useEffect, useState } from 'react'
import { useParams } from 'next/navigation'
import { api } from '@/lib/api'

export default function MovimientosPage() {
  const { id } = useParams()       // id de la cuenta
  const [tipo, setTipo] = useState('CREDITO')
  const [monto, setMonto] = useState('5')
  const [movs, setMovs] = useState([])
  const [error, setError] = useState(null)

  async function cargar() {
    setError(null)
    try {
      const data = await api.get(`/api/cuentas/${id}/movimientos`)
      setMovs(data)
    } catch (e) {
      setError(String(e?.message || e))
    }
  }

  useEffect(() => { cargar() }, [id])

  async function registrar() {
    setError(null)
    const num = Number(monto)
    if (!Number.isFinite(num) || num <= 0) { setError('Monto inválido'); return }
    try {
      await api.post(`/api/cuentas/${id}/movimientos`, {
        tipo,
        monto: Number(num.toFixed(2))   // número, no string
      })
      setMonto('5')
      await cargar()
    } catch (e) {
      // Si el backend envía { error: "Saldo no disponible" } lo mostramos
      const msg = e?.details?.error || e?.details || e?.message || String(e)
      setError(msg)
    }
  }

  return (
    <main style={{padding:'2rem'}}>
      <h1>Movimientos de la cuenta #{id}</h1>
      {error && <p style={{color:'crimson'}}>Error: {error}</p>}

      <div style={{display:'flex', gap:'0.5rem', marginBottom:'1rem'}}>
        <select value={tipo} onChange={e=>setTipo(e.target.value)}>
          <option value="CREDITO">Crédito (+)</option>
          <option value="DEBITO">Débito (-)</option>
        </select>
        <input value={monto} onChange={e=>setMonto(e.target.value)} />
        <button onClick={registrar}>Registrar</button>
      </div>

      <table border="1" cellPadding="6" style={{borderCollapse:'collapse', minWidth: '640px'}}>
        <thead>
          <tr>
            <th>Fecha</th><th>Tipo</th><th>Monto</th><th>Saldo disponible</th>
          </tr>
        </thead>
        <tbody>
          {movs.length === 0 && (
            <tr><td colSpan={4}>Sin movimientos</td></tr>
          )}
          {movs.map(m => (
            <tr key={m.id}>
              <td>{new Date(m.fecha).toLocaleString()}</td>
              <td>{m.tipo}</td>
              <td>{m.monto}</td>
              <td>{m.saldoPosterior}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </main>
  )
}

