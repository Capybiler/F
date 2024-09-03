### Test Case 1: Basic Arithmetic Operations
**Input:**
```lisp
(plus 1 2)
(minus 5 3)
(times 4 3)
(divide 10 2)
```
**Expected Output:**
```
3
2
12
5
```

### Test Case 2: Variable Assignment and Usage
**Input:**
```lisp
(setq a 10)
(setq b (plus a 5))
a
b
```
**Expected Output:**
```
10
15
```

### Test Case 3: Function Definition and Invocation
**Input:**
```lisp
(func square (x) (times x x))
(square 4)
(square 10)
```
**Expected Output:**
```
16
100
```

### Test Case 4: Conditional Evaluation with `cond`
**Input:**
```lisp
(setq x 10)
(cond (less x 5) 'less-than-five (greater x 5) 'greater-than-five)
```
**Expected Output:**
```
'greater-than-five
```

### Test Case 5: Looping with `while`
**Input:**
```lisp
(setq counter 0)
(while (less counter 3)
       (setq counter (plus counter 1))
)
counter
```
**Expected Output:**
```
3
```

### Test Case 6: List Operations
**Input:**
```lisp
(setq mylist '(1 2 3 4))
(head mylist)
(tail mylist)
(cons 0 mylist)
```
**Expected Output:**
```
1
(2 3 4)
(0 1 2 3 4)
```

### Test Case 7: Logical Operations
**Input:**
```lisp
(and true false)
(or true false)
(not true)
(xor true false)
```
**Expected Output:**
```
false
true
false
true
```

### Test Case 8: Type Checking Predicates
**Input:**
```lisp
(setq a 5)
(setq b true)
(setq c '(1 2 3))
(isint a)
(isbool b)
(islist c)
(isnull a)
```
**Expected Output:**
```
true
true
true
false
```

### Test Case 9: Lambda Function and Evaluation
**Input:**
```lisp
(setq addTwo (lambda (x) (plus x 2)))
(addTwo 5)
((lambda (y) (times y 10)) 3)
```
**Expected Output:**
```
7
30
```

### Test Case 10: Nested Function Calls
**Input:**
```lisp
(plus (times 2 3) (minus 10 4))
```
**Expected Output:**
```
12
```

### Test Case 11: Quoting
**Input:**
```lisp
(setq mylist '(plus 1 2))
(eval mylist)
```
**Expected Output:**
```
3
```

### Test Case 12: Returning from Functions
**Input:**
```lisp
(func earlyReturn (x)
  (cond (less x 0) (return 'negative))
  (times x 2)
)
(earlyReturn -5)
(earlyReturn 5)
```
**Expected Output:**
```
'negative
10
```

### Test Case 13: Breaking Out of Loops
**Input:**
```lisp
(setq counter 0)
(while true
       (setq counter (plus counter 1))
       (cond (greater counter 3) (break))
)
counter
```
**Expected Output:**
```
4
```

### Test Case 14: Working with `prog` Blocks
**Input:**
```lisp
(prog (x y)
  (setq x 10)
  (setq y 20)
  (return (plus x y))
)
```
**Expected Output:**
```
30
```

### Test Case 15: Complex Conditionals
**Input:**
```lisp
(cond (equal 1 2) 'equal
      (greater 3 2) 'greater
      (less 2 3) 'less)
```
**Expected Output:**
```
'greater
```

### Test Case 16: Factorial Calculation
**Input:**
```lisp
(func factorial (n)
  (cond (less n 2) 1
        true (times n (factorial (minus n 1))))
)
(factorial 5)
```

**Expected Output:**
```
120
```

### Program 2: Fibonacci Sequence Generator
**Input:**
```lisp
(func fib (n)
  (cond (lesseq n 0) 0
        (equal n 1) 1
        true (plus (fib (minus n 1)) (fib (minus n 2))))
)
(fib 10)
```

**Expected Output:**
```
55
```
