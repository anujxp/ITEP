 
A person pays $8000 as an amount on the sum of $6000 that he had borrowed for 3 years. What will be the rate of interest?
1/1
12.12%
11.11%
 
10.11%
13%
 
Find the number of permutations and combinations of n = 9 and r = 3
0/1
nPr = 504  &  nCr = 84
nPr = 510  &  nCr = 90
 
nPr = 490  &  nCr = 85
nPr = 502  &  nCr = 83
 
30
 litres of a mixture of milk and water contains 10% of water, the water 
to be added, to make the water content 25% in the new mixture. Find how 
many litres water will be added? 
1/1
4 litres
6 litres
 
8 litres
5 litres
 
To
 cover a certain distance, a student has two options: to cycle or to 
walk. If he walks one way and cycles back, it takes him 5 hours. If he 
cycles both ways, it would take him 3 hours. How much time will he take 
if he walks both ways?
1/1
12 Hours
8 Hours
6 Hours
7 Hours
 
 
Two
 trains 140 m and 160 m long are moving towards each other on parallel 
tracks with speeds 40 km / hr and 50 km / hr respectively. How much time
 would they take to pass each other completely ?
1/1
11 Seconds
13 Seconds
12 Seconds
 
10 Seconds
 
To
 complete a work, a person A takes 10 days and another person B takes 15
 days. If they work together, in how much time will they complete the 
work ?
1/1
6 days
 
7 days
5 days
8 days
 
B’s age after 10 years would be equal to 4 times his age 2 years ago. What will be his age 5 years from now?
1/1
12 years
10 years
11 years
 
9 years
 
Find the average of the squares of the first 16 natural numbers.
0/1
93.5
92.2
 
90.2
89
 
 What percentage is 1 of 3000?
1/1
0.03333%
 
0.5%
0.01111%
0.3%
 
A
 person purchases a notebook from a supplier at Rs. 12 for 30 notebooks.
 He sells those notebooks at Rs. 15 for 20 notebooks. Find his profit or
 loss percent.
1/1
88%
90.5%
87.5%
 
86.5%


```python
import math

# Problem 2: nPr and nCr for n=9, r=3
n, r = 9, 3
nPr = math.perm(n, r)
nCr = math.comb(n, r)

# Problem 8: Average of squares of first 16 natural numbers
n_sq = 16
avg_sq = (n_sq + 1) * (2 * n_sq + 1) / 6

# Problem 10: Profit %
cp_unit = 12 / 30
sp_unit = 15 / 20
profit_pct = (sp_unit - cp_unit) / cp_unit * 100

print(f"{nPr=}, {nCr=}")
print(f"{avg_sq=}")
print(f"{profit_pct=}")



```

```text
nPr=504, nCr=84
avg_sq=93.5
profit_pct=87.49999999999999


```

Here are the solutions to each of the math problems provided:

### 1. Rate of Interest

A person pays  (Amount) on a borrowed sum of  (Principal) for  years.

* **Simple Interest ():** 
* **Formula:** 
* 
* 
* **Answer:** ****

### 2. Permutations and Combinations

For  and :

* **Permutations ():** 
* **Combinations ():** 
* **Answer:** ** & **

### 3. Mixture Problem

 litres of mixture contains  water ( litres) and  litres of milk.

* Let  be the amount of water to be added to make water  of the total.
* 
* 
* **Answer:** ** litres**

### 4. Time and Distance (Student)

* Let  be walking time and  be cycling time (one way).
*  hours
*  hours  hours
* Substitute  in the first equation:  hours
* Walking both ways:  hours
* **Answer:** ** Hours**

### 5. Passing Trains

* Total Distance (): 
* Relative Speed (): 
* Convert Speed to m/s: 
* Time: 
* **Answer:** ** Seconds**

### 6. Time and Work

* Person A completes work in  days; Person B in  days.
* Rate together: 
* Time taken:  days
* **Answer:** ** days**

### 7. Age Problem

* Let B's current age be .
* Age after  years: 
* Age  years ago: 
* Equation: 
* Age  years from now: 
* **Answer:** ** years**

### 8. Average of Squares

Average of squares of first  natural numbers = 

* For : 
* **Answer:** ****

### 9. Percentage Calculation

* Percentage: 
* **Answer:** ****

### 10. Profit or Loss Percent

* Cost Price () of  notebooks = Rs.  of 
* Selling Price () of  notebooks = Rs.  of 
* Profit 
* **Answer:** ****
