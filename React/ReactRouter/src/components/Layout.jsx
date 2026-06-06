import React from 'react'
import { Outlet, Link } from 'react-router-dom'
import Header from './Header'
import Footer from './Footer'

export default function Layout() {
  return (
    <div className="min-h-screen bg-zinc-950 text-white font-sans flex flex-col">
      
     <Header/>
      {/* The Dynamic Center (This is the only part that changes!) */}
      {/* <main className="flex-grow flex items-center justify-center p-8"> */}
        <Outlet /> 
      {/* </main> */}
    <Footer/>
      
    </div>
  )
}