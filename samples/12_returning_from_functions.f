(func earlyReturn (x)
    (prog () (
            (cond (less x 0) (return 'negative))
            (return (times x 2))
        )
    )
)
(earlyReturn -5)
(earlyReturn 5)
