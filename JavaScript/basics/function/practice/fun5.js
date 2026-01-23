function powerFactory(n){
    return (value) => {
       return  value**n;        
    }
}
const square = powerFactory(2);
const cube = powerFactory(3);
console.log(square(2));
console.log(cube(2));

