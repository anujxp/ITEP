import { useRef, useState } from "react";
import Data from "./Data";
import Header from "./component/Header";
import AddStudent from "./component/AddStudent";

function App() {
  const [studentList, setStudentList] = useState(Data);
  const [branchList, setBranchList] = useState(["CS", "IT", "CV", "MECH"]);
  const [branchFilter, setBranchFilter] = useState("All");
  const [rollError, setRollError] = useState("");

  const rollInput = useRef();
  const nameInput = useRef();
  const genderInput = useRef();
  const branchInput = useRef();

  const isRollExist = (roll) => {
    return studentList.some((student) => student.roll == roll);
  };

  const addStudent = (roll,name,gender,branch) => {
    
    

    let newStudent = { roll, name, gender, branch };
    setStudentList([...studentList, newStudent]);

    rollInput.current.value = "";
    nameInput.current.value = "";
  };

  const removeStudent = (roll) => {
    if (window.confirm("Do you want to delete it?")) {
      let index = studentList.findIndex((student) => student.roll == roll);
      studentList.splice(index, 1);
      setStudentList([...studentList]);
    }
  };

  const filteredList = studentList.filter(
    (student) => branchFilter == "All" || branchFilter == student.branch,
  );

  return (
    <>
      <Header />
      <AddStudent/>

      <table className="table table-striped container">
        <thead>
          <tr>
            <th>S.no</th>
            <th>Roll number</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Branch</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {filteredList.map((student, index) => (
            <tr key={student.roll}>
              <td>{index + 1}</td>
              <td>{student.roll}</td>
              <td>{student.name}</td>
              <td>{student.gender}</td>
              <td>{student.branch}</td>
              <td>
                <button
                  onClick={() => removeStudent(student.roll)}
                  className="btn btn-outline-danger"
                >
                  Remove
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
}

export default App;
