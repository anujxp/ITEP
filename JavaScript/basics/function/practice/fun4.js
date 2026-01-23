// function greeter(a){
//     return (name) => {
//         return `hello ${name}`;
//         }
// }
// const IndianGreeter = greeter("anuj")


function createDiscount(percentage){
    return (amount)=>{
        let discountValue = amount * (percentage / 100);
        return amount - discountValue;
    };
}

const studenDiscount = createDiscount(10);
const vipDiscount = createDiscount(20);

console.log(studenDiscount(1000));
console.log(vipDiscount(1000));

