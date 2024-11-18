(setq counter 0)

(while true (prog () (
           (setq counter (plus counter 1))
           (cond (greatereq counter 6) (break))
        )
    )
)

counter
