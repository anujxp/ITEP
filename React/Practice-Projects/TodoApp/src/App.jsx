import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import TodoData from './TodoData'

function App() {
  const [TaskList,setTaskList] = useState(TodoData);
   const [priorityList,setPriorityList] = useState([
    {"priorityId":1,"value":"Low"},
    {"priorityId":2, "value":"Medium"},
    {"priorityId":3, "value":"High"}
  ]);

  return (
    <>
     <header className="bg-danger text-white p-1">
      <h2 className="text-center">ToDo App</h2>
    </header>
    <div className='container'>

      <table className="table">
        <thead>
          <tr>
           <th>S.no</th>
          <th>Title</th>
          <th>Date</th>
          <th>Priority</th>
          <th>Change Status</th>
          </tr>
        </thead>
        <tbody>
        {TaskList.map((task,index)=>{ 
          return( <tr key={index}>
            <td>{index*1+1}</td>
          <td>{task.title}</td>
          <td>{task.date}</td>
          <td>{priorityList.find((priorityObj)=>{return priorityObj.priorityId == task.pid}).value}</td>
          <td>{task.status == "active" ? <button className='btn btn-danger'>Deactive</button> :<button>Active</button>}</td>
          </tr>
         );
        })}
        </tbody>
      </table>
    </div>
    </>
  )
}

export default App
