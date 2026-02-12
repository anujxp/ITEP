function AddStudent() {
  const rollInput = useRef();
  const nameInput = useRef();
  const genderInput = useRef();
  const branchInput = useRef();

  let saveStudent = () => {
    let roll = rollInput.current.value.trim();
    let name = nameInput.current.value.trim();
    let gender = genderInput.current.value;
    let branch = branchInput.current.value;
    if (!roll) {
      setRollError("Roll required");
      return;
    }

    if (isRollExist(roll)) {
      setRollError("Roll number already exists");
      return;
    } else {
      setRollError("");
    }
  };
  return (
    <>
      <div className="container mt-3">
        <div className="row">
          <div className="col-md-6">
            <input
              ref={rollInput}
              type="text"
              placeholder="Enter roll number"
              className="form-control"
              onChange={(e) => {
                if (isRollExist(e.target.value.trim())) {
                  setRollError("Roll number  already exists");
                } else {
                  setRollError("");
                }
              }}
            />
            {rollError && <small className="text-danger">{rollError}</small>}
          </div>

          <div className="col-md-6">
            <input
              ref={nameInput}
              type="text"
              placeholder="Enter student name"
              className="form-control"
            />
          </div>
        </div>

        <div className="row mt-3">
          <div className="col-md-6">
            <select ref={genderInput} className="form-control">
              <option value="male">Male</option>
              <option value="female">Female</option>
            </select>
          </div>

          <div className="col-md-6">
            <select ref={branchInput} className="form-control">
              {branchList.map((branch) => (
                <option key={branch} value={branch}>
                  {branch}
                </option>
              ))}
            </select>
          </div>
        </div>

        <div className="row mt-3 mb-3">
          <div className="col-md-6">
            <button onClick={addStudent} className="btn btn-success">
              ADD
            </button>
          </div>

          <div className="col-md-6">
            <button
              onClick={() => setBranchFilter("CS")}
              className="btn btn-danger"
            >
              CS(
              {studentList.filter((s) => s.branch == "CS").length})
            </button>

            <button
              onClick={() => setBranchFilter("IT")}
              className="btn btn-primary ms-2"
            >
              IT(
              {studentList.filter((s) => s.branch == "IT").length})
            </button>

            <button
              onClick={() => setBranchFilter("CV")}
              className="btn btn-warning ms-2"
            >
              CV(
              {studentList.filter((s) => s.branch == "CV").length})
            </button>

            <button
              onClick={() => setBranchFilter("MECH")}
              className="btn btn-info ms-2"
            >
              MECH(
              {studentList.filter((s) => s.branch == "MECH").length})
            </button>

            <button
              onClick={() => setBranchFilter("All")}
              className="btn btn-secondary ms-2"
            >
              Total({studentList.length})
            </button>
          </div>
        </div>
      </div>
    </>
  );
}
export default AddStudent;
