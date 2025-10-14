import NavBar from '@/components/NavBar'
export const metadata = { title: 'IESS Front' }
export default function RootLayout({ children }) {
  return (
    <html lang="es">
      <body style={{margin:0, fontFamily:'system-ui'}}>
        <NavBar />
        <div style={{padding:'1rem 2rem'}}>{children}</div>
      </body>
    </html>
  )
}
