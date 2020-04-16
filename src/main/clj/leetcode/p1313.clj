(ns leetcode.p1313)

(defn solve [input]
  (reduce
   (fn [result [freq val]]
     (concat result (repeat freq val)))
   []
   (partition 2 input)))

(solve [1 2 3 4])
