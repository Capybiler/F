1. Simple Arithmetic Operations
```
(setq x (plus 5 10))  ; x = 15
(setq y (minus 20 5)) ; y = 15
(setq z (times x y))  ; z = 225
(divide z 15)         ; 15
```
2. Defining and Using Functions
```
(func addSquares (a b) (plus (times a a) (times b b)))
(addSquares 3 4)  ; 25
```
3. Conditional Logic
```
(setq x 10)
(cond (less x 5) (times x 2) (plus x 5))  ; 15
```
4. Recursive Function for Factorial
```
(func factorial (n)
    (cond (less n 2) 1
          (times n (factorial (minus n 1)))))
(factorial 5)  ; 120
```
5. Working with Lists
```
(setq myList '(1 2 3 4 5))
(head myList)  ; 1
(tail myList)  ; (2 3 4 5)
(cons 0 myList)  ; (0 1 2 3 4 5)
```
6. Logical Operations
```
(setq a true)
(setq b false)
(and a b)  ; false
(or a b)   ; true
(not b)    ; true
```
7. Evaluating Expressions
```
(setq expression '(plus 2 3))
(eval expression)  ; 5
```
8. Creating a Lambda Function
```
(setq addOne (lambda (x) (plus x 1)))
(addOne 10)  ; 11
```
9. Using prog for Sequential Execution
```
(prog (x y z)
  (setq x 10)
  (setq y 20)
  (setq z (plus x y))
  z)  ; 30
```
10. Using while Loop for Iteration
```
(setq sum 0)
(setq i 1)
(while (less i 6)
  (setq sum (plus sum i))
  (setq i (plus i 1)))
sum  ; 15
```
11. Defining and Using a Higher-Order Function
```
(func applyTwice (f x)
  (f (f x)))
(applyTwice (lambda (y) (plus y 3)) 4)  ; 10
```
12. Breaking a Loop
```
(setq i 0)
(while true
  (setq i (plus i 1))
  (cond (greater i 10) (break)))
i  ; 11
```
13. Conditional Assignment Using cond
```
(setq grade 85)
(setq result (cond (greater grade 90) 'A
                   (greater grade 80) 'B
                   'C))  ; 'B
```
14. Working with Boolean Predicates
```
(setq x 10)
(setq y 5.5)
(isint x)  ; true
(isreal y)  ; true
(isbool true)  ; true
(isnull null)  ; true
(islist '(1 2 3))  ; true
```
15. Nested Conditional Expressions
```
(setq a 5)
(setq b 10)
(setq c 15)
(cond (equal a b) 'equal
      (greater c b) (cond (less c a) 'smaller 'larger)
      'nonequal)  ; 'larger
```
16. Fibonacci Sequence
```
(func fibonacci (n)
    (cond (lesseq n 0) 0
          (lesseq n 1) 1
          (plus (fibonacci (minus n 1)) (fibonacci (minus n 2)))))

(fibonacci 10)  ; 55
```
17. Binary search
```
(func binarySearch (lst target start end)
    (cond (greater start end) -1
          (let (mid (/ (plus start end) 2))
               (cond (equal (nth lst mid) target) mid
                     (less target (nth lst mid)) (binarySearch lst target start (minus mid 1))
                     (binarySearch lst target (plus mid 1) end)))))

(func nth (lst n)
    (cond (equal n 0) (head lst)
          (nth (tail lst) (minus n 1))))

(binarySearch '(1 2 3 4 5 6 7 8 9) 7 0 8)  ; 6
```
