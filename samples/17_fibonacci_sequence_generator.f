(func fib (n)
  (cond (lesseq n 0) 0
        (equal n 1) 1
        true (plus (fib (minus n 1)) (fib (minus n 2))))
)
(fib 10)
