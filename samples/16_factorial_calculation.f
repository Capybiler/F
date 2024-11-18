(func factorial (n)
  (cond (less n 2) 1 (times n (factorial (minus n 1))))
)

(factorial 5)
