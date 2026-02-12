import { use, useRef, useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import Data from "./Data";
function App() {
  const [studentList, setStudentList] = useState(Data);
  const [branchList, setBranchList] = useState(["CS", "IT", "CV", "MECH"]);
  const [branchFilter, setBranchFilter] = useState("All");

  let rollInput = useRef();
  let nameInput = useRef();
  let genderIntput = useRef();
  let branchInput = useRef();



  const addStudent = ()=>{
    let roll = rollInput.current.value;
    let name = nameInput.current.value;
    let gender = genderInput.current.value;
    let branch = branchInput.current.value;
    let newStudent = {roll,name,gender,branch};
    setStudentList([...studentList,newStudent])
  };
  return (
    <>
      <div className="bg-danger text-white p-3 ">
        <h2 className="text-center mb-4">Student Management system</h2>
      </div>
      <div className="row mt-3">
        <div className="col-md-6 ">
          <input
            ref={rollInput}
            type="text"
            placeholder="Enter roll Number"
            className="form-control"
          />
        </div>
        <div className="col-md-6 mt-3">
          <input
            ref={nameInput}
            type="text"
            placeholder=" Enter Student name"
            className="form-control"
          />
        </div>
      </div>
      <div className="row mt-3">
        <div className="col-md-6">
          <select ref={genderIntput} className="form-control">
            <option value="0">Select</option>
            <option value="male">Male</option>
            <option value="female">Female</option>
          </select>
        </div>
        <div className="col-md-6">
          <select ref={branchInput} className="form-control mt-3">
            {branchList.map((branch, index) => {
              return (
                <option key={branch} value={branch}>
                  {branch}
                </option>
              );
            })}
          </select>
        </div>
      </div>
      <div className="row mt-3 mb-3">
        <div className="col-md-6">
          <button onClick={addStudent} className="btn btn-success">ADD</button>
        </div>
         <div className="col-md-6"> <div className="col-md-6">
          <button onClick={()=>setBranchFilter("CS")} className="btn btn-danger">CS({studentList.filter((student)=>{return student.branch == "CS"}).length})</button>
          <button onClick={()=>setBranchFilter("IT")} className="btn btn-primary ms-2">IT({studentList.filter((student)=>{return student.branch == "IT"}).length})</button>
          <button onClick={()=>setBranchFilter("CV")} className="btn btn-warning ms-2">CV({studentList.filter((student)=>{return student.branch == "CV"}).length})</button>
          <button onClick={()=>setBranchFilter("MECH")} className="btn btn-info ms-2">MECH({studentList.filter((student)=>{return student.branch == "MECH"}).length})</button>
          <button onClick={()=>setBranchFilter("All")} className="btn btn-secondary ms-2">Total({studentList.length})</button>
        </div>
      </div>
      </div>
    </>
  );
}


export default App;
