    class Employee{
        constructor(id,name, department,salary){
        this.id = id ;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.displayInfo = function (){
            console.log(this.id);
            console.log(this.name);
            console.log(this.department);
            console.log(this.salary);
        }
    }
    }

    let a = new Employee(100,"atul","it",20000);
    a.displayInfo();
