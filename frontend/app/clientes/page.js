'use client'
import { useEffect, useState } from 'react'
import { api } from '@/lib/api'
export default function ClientesList(){
  const [data,setData]=useState([]); const [error,setError]=useState(null)
  useEffect(()=>{
    const token=localStorage.getItem('token'); if(!token){window.location.href='/login'; return}
    api.get('/api/clientes').then(setData).catch(e=>setError(String(e)))
  },[])
  return (
    <main>
      <h1>Clientes</h1>
      {error && <p style={{color:'red'}}>Error: {error}</p>}
      {!error && data.length===0 && <p>No hay clientes.</p>}
      <table border="1" cellPadding="6" style={{borderCollapse:'collapse'}}>
        <thead><tr><th>ID</th><th>Identificación</th><th>Nombre</th><th>Estado</th></tr></thead>
        <tbody>
          {data.map(c=>(
            <tr key={c.id}>
              <td>{c.id}</td><td>{c.identificacion}</td><td>{c.nombre}</td>
              <td>{c.estado?'Activo':'Inactivo'}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </main>
  )
}
