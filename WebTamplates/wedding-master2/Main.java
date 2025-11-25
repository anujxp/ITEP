

class Student implements Comparable<Student>{
    int id;
    String name;

    public Student(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Student(){
    }
    public int compareTo(Student other){
        return this.id - other.id;
    }

}

class Main{
    public static void main(String args[]){
        Student s1 = new Student(1, "Harsh");
        Student s2 = new Student(2, "Ram");
        
        s1.compareTo(s2);
    }
}