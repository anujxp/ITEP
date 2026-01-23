function greet(name,callback){
    console.log("hello "+ name);
    callback();

}
function callMe(){
    console.log("i am the call back function...");

}

greet("anuj",callMe);