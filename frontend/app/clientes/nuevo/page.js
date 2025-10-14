'use client'
import { useState, useEffect } from 'react'
import { api } from '@/lib/api'
export default function ClienteNuevo(){
  const [identificacion,setIdentificacion]=useState('')
  const [nombre,setNombre]=useState('')
  const [contrasena,setContrasena]=useState('1234')
  const [estado,setEstado]=useState(true)
  const [msg,setMsg]=useState(null); const [err,setErr]=useState(null)
  useEffect(()=>{ const t=localStorage.getItem('token'); if(!t){window.location.href='/login'} },[])
  async function onSubmit(e){
    e.preventDefault(); setErr(null); setMsg(null)
    try{ await api.post('/api/clientes',{identificacion,nombre,contrasena,estado})
      setMsg('Cliente creado.'); setIdentificacion(''); setNombre('') }
    catch(e){ setErr(String(e)) }
  }
  return (
    <main style={{maxWidth:520}}>
      <h1>Nuevo cliente</h1>
      {msg && <p style={{color:'green'}}>{msg}</p>}
      {err && <p style={{color:'red'}}>{err}</p>}
      <form onSubmit={onSubmit} style={{display:'grid',gap:'0.6rem'}}>
        <input placeholder="Identificación" value={identificacion} onChange={e=>setIdentificacion(e.target.value)} required />
        <input placeholder="Nombre" value={nombre} onChange={e=>setNombre(e.target.value)} required />
        <input placeholder="Contraseña" type="password" value={contrasena} onChange={e=>setContrasena(e.target.value)} />
        <label><input type="checkbox" checked={estado} onChange={e=>setEstado(e.target.checked)} />&nbsp;Activo</label>
        <button type="submit">Guardar</button>
      </form>
    </main>
  )
}
