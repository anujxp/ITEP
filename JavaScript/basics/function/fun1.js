    function Employee(id,name, department,salary){
        this.id = id ;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.displayInfo = function (){
            console.log(this.id);
        }
    }

    let a = new Employee(100,"atul","it",20000);
    a.displayInfo();
