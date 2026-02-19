import React, { useEffect, useState } from "react";

import Api from "./components/Api";
import axios from "axios";

const App = () => {
  const getTodayDate = () => {
    const today = new Date();
    const dd = String(today.getDate()).padStart(2, "0");
    const mm = String(today.getMonth() + 1).padStart(2, "0");
    const yyyy = today.getFullYear();
    return `${dd}/${mm}/${yyyy}`;
  };

  useEffect(() => {
    loadTask();
  }, []);

  const [defaultStatus, setDefaultStatus] = useState("active");
  const [priorityList, setPriorityList] = useState([
    { priorityId: 1, value: "Low" },
    { priorityId: 2, value: "Medium" },
    { priorityId: 3, value: "High" },
  ]);

  const [task, setTask] = useState({
    id: null,
    title: "",
    date: getTodayDate(),
    pid: 1,
    status: "active",
  });
  const [TaskList, setTaskList] = useState([]);
  useEffect(() => {
    loadTask();
  }, []);

  const addTask = async (e) => {
    if (e) e.preventDefault();
    try {
      if (task.id) {
        await axios.put(Api.UPDATE_TASK, task);
        alert("Task Updated Successfully....");
      } else {
        await axios.post(Api.SAVE_TASK, task);
        alert("Task Saved....");
      }
      loadTask();
      setTask({
        id: null,
        title: "",
        date: getTodayDate(),
        pid: 1,
        status: "active",
      });
    } catch (err) {
      console.error("Error processing task:...", err);
    }
  };

  const loadTask = async () => {
    try {
      let response = await axios.get(Api.LOAD_TASKS);
      setTaskList(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  const changeTaskStatus = async (task, newStatus) => {
    try {
      await axios.patch(`${Api.PATCH_TASK}${task.id}?status=${newStatus}`);
      const updatedList = TaskList.map((item) => {
        if (item.id === task.id) {
          return { ...item, status: newStatus };
        }
        return item;
      });
      setTaskList(updatedList);
    } catch (err) {
      console.error("Failed to update status in database:", err);
      alert("Could not update status. Please check your connection.");
    }
  };

  const editTask = (taskObject) => {
    setTask({
      id: taskObject.id,
      title: taskObject.title,
      date: taskObject.date,
      pid: taskObject.pid,
      status: taskObject.status,
    });

    window.scrollTo(0, 0);
  };

  const deleteTask = async (id) => {
    if (window.confirm("Are you sure you want to delete this task?")) {
      try {
        await axios.delete(`${Api.DELETE_TASK}${id}`);
        alert("Task Deleted!");
        loadTask();
      } catch (err) {
        console.error("Error deleting task:", err);
        alert("Failed to delete the task.");
      }
    }
  };

  return (
    <>
      <header className="bg-danger text-white p-1">
        <h2 className="text-center">ToDo App</h2>
      </header>

      <form onSubmit={addTask}>
        <div className="row ms-3 me-3 mt-3">
          <div className="col-md-6">
            <input
              type="text"
              name="title"
              value={task.title}
              onChange={(e) => setTask({ ...task, title: e.target.value })}
              placeholder="Enter title"
              className="form-control"
              required
            />
          </div>
          <div className="col-md-6">
            <input
              type="text"
              name="date"
              value={task.date}
              onChange={(e) => setTask({ ...task, date: e.target.value })}
              placeholder="DATE"
              className="form-control"
              required
            />
          </div>
        </div>

        <div className="row mt-3 ms-3 me-3">
          <div className="col-md-6">
            <select
              name="pid"
              value={task.pid}
              onChange={(e) =>
                setTask({ ...task, pid: parseInt(e.target.value) })
              }
              className="form-control"
            >
              {priorityList.map((p) => (
                <option key={p.priorityId} value={p.priorityId}>
                  {p.value}
                </option>
              ))}
            </select>
          </div>
          <div className="col-md-6">
  <select
    name="status"
    value={task.status}
    onChange={(e) => setTask({ ...task, status: e.target.value })}
    className="form-control"
  >
    <option value="active">Active</option>
    <option value="deactive">Deactive</option>
  </select>
</div>
        </div>

        <div className="mt-3 ms-5">
          <button type="submit" className="btn btn-success me-2 ms-5">
            {task.id ? "Update Task" : "Add Task"}
          </button>
        </div>
      </form>

      <div className="container mt-3">
        <div className="">
          <button
            disabled={defaultStatus == "active" ? true : false}
            onClick={() => setDefaultStatus("active")}
            className="btn btn-success"
          >
            Active(
            {
              TaskList.filter((task) => {
                return task.status == "active";
              }).length
            }
            )
          </button>
          <button
            disabled={defaultStatus == "deactive" ? true : false}
            onClick={() => setDefaultStatus("deactive")}
            className="btn btn-primary ms-3"
          >
            Deactive(
            {
              TaskList.filter((task) => {
                return task.status == "deactive";
              }).length
            }
            )
          </button>
        </div>
        <table className="table table-control">
          <thead>
            <tr>
              <th>S.no.</th>
              <th>Title</th>
              <th>Date</th>
              <th>Prority</th>
              <th>Status</th>
              <th>Edit</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            {TaskList.filter((task) => {
              return task.status == defaultStatus;
            })
              .sort((a, b) => {
                return b.pid - a.pid;
              })
              .map((task, index) => {
                return (
                  <tr
                    className={
                      task.pid == 1
                        ? "table-success"
                        : task.pid == 2
                          ? "table-warning"
                          : "table-danger"
                    }
                    key={index}
                  >
                    <td>{index * 1 + 1}</td>
                    <td>{task.title}</td>
                    <td>{task.date}</td>
                    <td>
                      {
                        priorityList.find((priorityObj) => {
                          return priorityObj.priorityId == task.pid;
                        }).value
                      }
                    </td>
                    <td>
                      {task.status == "active" ? (
                        <button
                          className="btn btn-outline-warning"
                          onClick={() => {
                            changeTaskStatus(task, "deactive");
                          }}
                        >
                          Deactive
                        </button>
                      ) : (
                        <button
                          onClick={() => changeTaskStatus(task, "active")}
                          className="btn btn-outline-warning"
                        >
                          Active
                        </button>
                      )}
                    </td>
                    <td>
                      <button
                        className="btn btn-outline-info"
                        onClick={() => editTask(task)}
                      >
                        Edit
                      </button>
                    </td>
                    <td>
                      <button
                        className="btn btn-outline-danger"
                        onClick={() => deleteTask(task.id)}
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                );
              })}
          </tbody>
        </table>
      </div>
    </>
  );
};

export default App;
