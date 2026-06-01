import './App.css' // Ensure your CSS file is imported
import Password from './component/Password'
import ReducerCounter from './component/ReducerCounter'

function App() {
  return (
    <div className="flex items-center justify-center h-screen bg-zinc-900">
      <div className="p-8 text-center bg-zinc-800 rounded-2xl shadow-2xl border border-zinc-700">
        <h1 className="text-4xl font-extrabold text-transparent bg-clip-text bg-gradient-to-r from-cyan-400 to-blue-500 mb-4">
          Tailwind v4 is Live
        </h1>
        <p className="text-zinc-400 font-medium">
          Zero config files. Maximum speed.
        </p>
       
      <Password/>
      </div>
    </div>
  )
}

export default App