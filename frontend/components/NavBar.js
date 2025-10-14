'use client'
import Link from 'next/link'
import { useEffect, useState } from 'react'

export default function NavBar() {
  const [logged, setLogged] = useState(false)
  useEffect(() => { setLogged(!!localStorage.getItem('token')) }, [])
  function logout() { localStorage.removeItem('token'); window.location.href = '/login' }
  return (
    <nav style={{display:'flex', gap:'1rem', padding:'1rem', borderBottom:'1px solid #ddd'}}>
      <Link href="/">Home</Link>
      {logged && <>
        <Link href="/clientes">Clientes</Link>
        <Link href="/clientes/nuevo">Nuevo cliente</Link>
        <button onClick={logout} style={{marginLeft:'auto'}}>Salir</button>
      </>}
      {!logged && <Link href="/login" style={{marginLeft:'auto'}}>Login</Link>}
    </nav>
  )
}
