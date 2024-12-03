(setq x 0)
(setq y 0)

(prog (x y) (
    (setq x 10)
    (setq y 20)
    (return (plus x y))
  )
)

x
y
