'use client'
import { useEffect } from 'react'
export default function Home(){
  useEffect(()=>{
    const logged=!!localStorage.getItem('token')
    window.location.href = logged ? '/clientes' : '/login'
  },[])
  return <p>Redirigiendo…</p>
}
