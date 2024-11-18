(setq counter 0)

(while true (cond (less counter 6)
        (setq counter (plus counter 1))
        (break)
    )
)

counter
