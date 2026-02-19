import React from "react";

const Form = () => {
  return (
    <>
      <div className="container mt-3"></div>
      <div className="row ms-3 me-3">
        <div className="col-md-6">
          <input type="text" placeholder="Enter title " className="form-control" />
        </div>
        <div className="col-md-6">
          <input type="text" placeholder="Enter title "  className="form-control"/>
        </div>
    </div>
    <div className="row mt-3 ms-3 me-3" >
        <div className="col-md-6">
          <input type="text" placeholder="Enter title " className="form-control" />
        </div>
        <div className="col-md-6">
          <input type="text" placeholder="Enter title " className="form-control" />
        </div>
      </div>
    </>
  );
};

export default Form;
