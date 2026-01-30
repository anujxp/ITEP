substract = (a,b) =>  a-b;

console.log(substract(8,6));

function mathManager(x, y, operation) {
    console.log("Starting the calculation...");
    return operation(x, y); 
}

console.log(mathManager(50, 20,substract));
// substract = (a,b) =>  a-b;

console.log(substract(8,6));

function mathManager(x, y, operation) {
    console.log("Starting the calculation...");
    return operation(x, y); 
}

console.log(mathManager(50, 20,substract));