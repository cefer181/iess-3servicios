'use client'
import { useState } from 'react'
export default function LoginPage(){
  const [user,setUser]=useState(''); const [pass,setPass]=useState(''); const [err,setErr]=useState(null)
  function onSubmit(e){ e.preventDefault(); if(!user||!pass){setErr('Ingrese usuario y contraseña');return}
    localStorage.setItem('token','demo-token'); window.location.href='/clientes' }
  return (
    <main style={{maxWidth:420}}>
      <h1>Login</h1>
      {err && <p style={{color:'red'}}>{err}</p>}
      <form onSubmit={onSubmit} style={{display:'grid',gap:'0.6rem'}}>
        <input placeholder="usuario" value={user} onChange={e=>setUser(e.target.value)} />
        <input placeholder="contraseña" type="password" value={pass} onChange={e=>setPass(e.target.value)} />
        <button type="submit">Entrar</button>
      </form>
      <p style={{marginTop:'1rem',color:'#555'}}>* Login de demostración</p>
    </main>
  )
}
