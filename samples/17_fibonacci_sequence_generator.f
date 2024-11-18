(func fib (n)
    (cond (lesseq n 0)
        0
        (cond (equal n 1)
            1
            (plus (fib (minus n 1)) (fib (minus n 2)))
        )
    )
)

(fib 10)
