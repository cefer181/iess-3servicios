export const API_BASE = process.env.NEXT_PUBLIC_API_BASE || 'http://localhost:8080'
async function handle(res){
  if(!res.ok){ const t=await res.text().catch(()=>'' ); throw new Error(`HTTP ${res.status} - ${t}`)}
  const ct=res.headers.get('content-type')||''
  return ct.includes('application/json')?res.json():res.text()
}
export const api={
  get:(p)=>fetch(`${API_BASE}${p}`,{credentials:'include'}).then(handle),
  post:(p,b)=>fetch(`${API_BASE}${p}`,{
    method:'POST', headers:{'Content-Type':'application/json'}, credentials:'include', body:JSON.stringify(b)
  }).then(handle),
}
